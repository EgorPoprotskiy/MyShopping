package com.egorpoprotskiy.myshopping.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egorpoprotskiy.myshopping.R
import com.egorpoprotskiy.myshopping.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {
    private lateinit var viewModel: ShopItemViewModel
    private lateinit var tilName: TextInputLayout
    private lateinit var etName: TextView
    private lateinit var tilNameCount: TextInputLayout
    private lateinit var etNameCount: TextView
    private lateinit var tilPriceCount: TextInputLayout
    private lateinit var etPriceCount: TextView
    private lateinit var buttonSave: Button
    private var screenMode = MODE_UNKNOW
    private var shopItemId = ShopItem.ID_NOTFOUND

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews()
        addTextChangeListener()
        launchRightMode()
        observeViewModel()
    }

    private fun initViews() {
        tilName = findViewById(R.id.til_name)
        etName = findViewById(R.id.et_name)
        tilNameCount = findViewById(R.id.til_nameCount)
        etNameCount = findViewById(R.id.et_nameCount)
        tilPriceCount = findViewById(R.id.til_priceCount)
        etPriceCount = findViewById(R.id.et_priceCount)
        buttonSave = findViewById(R.id.save_button)
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknow screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.ID_NOTFOUND)
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
        viewModel.shopItem.observe(this) {
            etName.setText(it.name)
            etNameCount.setText(it.nameCount.toString())
            etPriceCount.setText(it.priceCount.toString())
        }
        buttonSave.setOnClickListener {
            viewModel.editShopItem(etName.text?.toString(), etNameCount.text?.toString(), etPriceCount.text?.toString())
        }
    }
    private fun launchAddMode() {
        viewModel.addShopItem(etName.text?.toString(), etNameCount.text?.toString(), etPriceCount.text?.toString())
    }

    private fun addTextChangeListener() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.resetErrorInputName()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        etNameCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.resetErrorInputNameCount()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        etPriceCount.addTextChangedListener(object : TextWatcher {
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
        viewModel.errorInputName.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = message
        }
        viewModel.errorInputNameCount.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_nameCount)
            } else {
                null
            }
            tilNameCount.error = message
        }
        viewModel.errorInputPriceCount.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_priceCount)
            } else {
                null
            }
            tilPriceCount.error = message
        }
        viewModel.shouldCloseScreen.observe(this){
            finish()
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOW = ""

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
}