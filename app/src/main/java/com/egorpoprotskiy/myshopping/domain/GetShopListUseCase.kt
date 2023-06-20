package com.egorpoprotskiy.myshopping.domain

class GetShopListUseCase(private val shopRepository: ShopRepository) {
    fun getShopList(): List<ShopList> {
        return shopRepository.getShopList()
    }
}