package com.egorpoprotskiy.myshopping.domain

class GetShopItemUseCase(private val shopRepository: ShopRepository) {
    fun getShopItem(shopList: Int): ShopItem {
        return shopRepository.getShopItem(shopList)
    }
}