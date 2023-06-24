package com.egorpoprotskiy.myshopping.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egorpoprotskiy.myshopping.data.ShopListRepositoryImpl
import com.egorpoprotskiy.myshopping.domain.AddShopItemUseCase
import com.egorpoprotskiy.myshopping.domain.EditShopItemUseCase
import com.egorpoprotskiy.myshopping.domain.GetShopItemUseCase
import com.egorpoprotskiy.myshopping.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun addShopItem(inputName: String?, inputNameCount: String?, inputPriceCount: String?) {
        val name = parseName(inputName)
        val nameCount = parseNameCount(inputNameCount)
        val priceCount = parsePriceCount(inputPriceCount)
        val fieldsValid = validateInput(name, nameCount, priceCount)
        if (fieldsValid) {
            val shopItem = ShopItem(name, nameCount, priceCount, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }
    }
    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }
    private fun parseNameCount(inputNameCout: String?): Int {
        return try {
            inputNameCout?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }
    private fun parsePriceCount(inputPriceCount: String?): Int {
        return try {
            inputPriceCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }


    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem
    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }
    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen
    fun editShopItem(inputName: String?, inputNameCount: String?, inputPriceCount: String?) {
        val name = parseName(inputName)
        val nameCount = parseNameCount(inputNameCount)
        val priceCount = parsePriceCount(inputPriceCount)
        val fieldsValid = validateInput(name, nameCount, priceCount)
        if (fieldsValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, nameCount = nameCount, priceCount = priceCount)
                editShopItemUseCase.editShopItem(item)
                finishWork()
            }
        }
    }


    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputNameCount = MutableLiveData<Boolean>()
    val errorInputNameCount: LiveData<Boolean>
        get() = _errorInputNameCount

    private val _errorinputPriceCount = MutableLiveData<Boolean>()
    val errorInputPriceCount: LiveData<Boolean>
        get() = _errorinputPriceCount


    private fun validateInput(name: String, nameCount: Int, priceCount: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = true
        }
        if (nameCount <= 0) {
            _errorInputNameCount.value = true
            result = false
        }
        if (priceCount <= 0) {
            _errorinputPriceCount.value = true
            result = false
        }
        return result
    }
    fun resetErrorInputName() {
        _errorInputName.value = false
    }
    fun resetErrorInputNameCount() {
        _errorInputNameCount.value = false
    }
    fun resetErrorInputPriceCount() {
        _errorinputPriceCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
