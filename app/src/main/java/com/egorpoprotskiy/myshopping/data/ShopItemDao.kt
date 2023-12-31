package com.egorpoprotskiy.myshopping.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
// 7
@Dao
interface ShopItemDao {
    @Query("SELECT * FROM shop_item")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //10
    suspend fun addShopList(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_item WHERE id=:shopItemId")
    //10
    suspend fun deleteShopList(shopItemId: Int)

    @Query("SELECT * FROM shop_item WHERE id=:shopItemId LIMIT 1")
    //10
    suspend fun getShopItem(shopItemId: Int): ShopItemDbModel
}