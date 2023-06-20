package com.egorpoprotskiy.myshopping.domain

class EditShopItemUseCase(private val shopRepository: ShopRepository) {
    fun editShopItem(shopList: ShopList) {
        shopRepository.editShopItem(shopList)
    }
}