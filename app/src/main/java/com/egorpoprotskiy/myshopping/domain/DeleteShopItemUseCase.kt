package com.egorpoprotskiy.myshopping.domain

class DeleteShopItemUseCase(private val shopRepository: ShopRepository) {
    fun deleteShopItem(shopList: ShopList){
        shopRepository.deleteShopItem(shopList)
    }
}