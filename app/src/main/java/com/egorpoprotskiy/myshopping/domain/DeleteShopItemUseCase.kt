package com.egorpoprotskiy.myshopping.domain

class DeleteShopItemUseCase(private val shopRepository: ShopRepository) {
    // 10
    suspend fun deleteShopItem(shopItem: ShopItem){
        shopRepository.deleteShopItem(shopItem)
    }
}