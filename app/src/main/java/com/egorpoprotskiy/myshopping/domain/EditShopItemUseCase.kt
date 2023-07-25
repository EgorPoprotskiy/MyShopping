package com.egorpoprotskiy.myshopping.domain

class EditShopItemUseCase(private val shopRepository: ShopRepository) {
    // 10
    suspend fun editShopItem(shopItem: ShopItem) {
        shopRepository.editShopItem(shopItem)
    }
}