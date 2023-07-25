package com.egorpoprotskiy.myshopping.data

import androidx.room.Entity
import androidx.room.PrimaryKey
// 7
@Entity(tableName = "shop_item")
class ShopItemDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val nameCount: Int,
    val priceCount: Int,
    val value: Boolean
        )

