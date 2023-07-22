package com.egorpoprotskiy.myshopping.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.egorpoprotskiy.myshopping.R
import com.egorpoprotskiy.myshopping.databinding.FragmentShopItemBinding
import com.egorpoprotskiy.myshopping.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemFragment: Fragment() {
    private var screenMode: String = MODE_UNKNOW
    private var shopItemId: Int = ShopItem.ID_NOTFOUND
    private lateinit var viewModel: ShopItemViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var etName: TextView
    private lateinit var tilNameCount: TextInputLayout
    private lateinit var etNameCount: TextView
    private lateinit var tilPriceCount: TextInputLayout
    private lateinit var etPriceCount: TextView
    private lateinit var buttonSave: Button

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

//    6.2
    private var _binding: FragmentShopItemBinding? = null
    private val binding: FragmentShopItemBinding
        get() = _binding ?: throw java.lang.RuntimeException("FragmentShopItemBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        6.2
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        6.2
//        initViews(view)

//        6.2
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        addTextChangeListener()
        launchRightMode()
        observeViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        4.4
        parseParams()
    }

    //6.2
//    private fun initViews(view: View) {
//        tilName = view.findViewById(R.id.til_name)
//        etName = view.findViewById(R.id.et_name)
//        tilNameCount = view.findViewById(R.id.til_nameCount)
//        etNameCount = view.findViewById(R.id.et_nameCount)
//        tilPriceCount = view.findViewById(R.id.til_priceCount)
//        etPriceCount = view.findViewById(R.id.et_priceCount)
//        buttonSave = view.findViewById(R.id.save_button)
//    }

//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD) {
//            throw RuntimeException("Unknow screen mode $mode")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
//                throw RuntimeException("param shop item id is absent")
//            }
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.ID_NOTFOUND)
//        }
//    }

//    4.4
    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("param screen mode is absent")
        }
        val mode = args.getString(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknow screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent!")
            }
            shopItemId = args.getInt(EXTRA_SHOP_ITEM_ID, ShopItem.ID_NOTFOUND)
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
//        6.2
//        viewModel.shopItem.observe(viewLifecycleOwner) {
//            binding.etName.setText(it.name)
//            binding.etNameCount.setText(it.nameCount.toString())
//            binding.etPriceCount.setText(it.priceCount.toString())
//        }
        
        binding.saveButton.setOnClickListener {
            viewModel.editShopItem(binding.etName.text?.toString(), binding.etNameCount.text?.toString(), binding.etPriceCount.text?.toString())
        }
    }
    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addShopItem(binding.etName.text?.toString(), binding.etNameCount.text?.toString(), binding.etPriceCount.text?.toString())
        }
    }

    private fun addTextChangeListener() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.resetErrorInputName()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.etNameCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.resetErrorInputNameCount()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.etPriceCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.resetErrorInputPriceCount()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }
    private fun observeViewModel() {
//        6.2 Переместили в BindingAdapters
//        viewModel.errorInputName.observe(viewLifecycleOwner) {
//            val message = if (it) {
//                getString(R.string.error_input_name)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//        viewModel.errorInputNameCount.observe(viewLifecycleOwner) {
//            val message = if (it) {
//                getString(R.string.error_input_nameCount)
//            } else {
//                null
//            }
//            tilNameCount.error = message
//        }
//        viewModel.errorInputPriceCount.observe(viewLifecycleOwner) {
//            val message = if (it) {
//                getString(R.string.error_input_priceCount)
//            } else {
//                null
//            }
//            tilPriceCount.error = message
//        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner){
//            activity?.onBackPressed()
            onEditingFinishedListener.onEditingFinished()
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOW = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_SCREEN_MODE, MODE_EDIT)
                    putInt(EXTRA_SHOP_ITEM_ID, shopItemId)
                }
            }
        }


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("activity must implement OnEditinfFinishedListener")
        }
    }
}