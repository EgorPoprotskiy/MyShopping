package com.egorpoprotskiy.myshopping.domain

class EditShopItemUseCase(private val shopRepository: ShopRepository) {
    fun editShopItem(shopItem: ShopItem) {
        shopRepository.editShopItem(shopItem)
    }
}