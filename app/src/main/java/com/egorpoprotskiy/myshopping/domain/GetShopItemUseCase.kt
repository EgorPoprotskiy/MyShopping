package com.egorpoprotskiy.myshopping.domain

class GetShopItemUseCase(private val shopRepository: ShopRepository) {
    fun getShopItem(shopList: Int): ShopList {
        return shopRepository.getShopItem(shopList)
    }
}