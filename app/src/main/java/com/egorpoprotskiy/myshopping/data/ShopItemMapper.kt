package com.egorpoprotskiy.myshopping.data

import com.egorpoprotskiy.myshopping.domain.ShopItem
// 8
class ShopItemMapper {
    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel {
        return ShopItemDbModel(
            id = shopItem.id,
            name = shopItem.name,
            nameCount = shopItem.nameCount,
            priceCount = shopItem.priceCount,
            value = shopItem.value
        )
    }
    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem {
        return ShopItem(
            id = shopItemDbModel.id,
            name = shopItemDbModel.name,
            nameCount = shopItemDbModel.nameCount,
            priceCount = shopItemDbModel.priceCount,
            value = shopItemDbModel.value
        )
    }
    fun mapItemDbModelToItemEntity(item: List<ShopItemDbModel>) = item.map {
        mapDbModelToEntity(it)
    }
}