package com.example.wishlist.data

import javax.inject.Inject

class WishRepository @Inject constructor(
    private val wishDAO: WishDAO
) {
    suspend fun addWish(wish: Wish) {
        wishDAO.addWish(wish)
    }

    fun getAllWishes() = wishDAO.getAllWishes()

    suspend fun updateWish(wish: Wish) {
        wishDAO.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDAO.deleteWish(wish)
    }

    fun getWishById(wishId: Long) = wishDAO.getWishById(wishId)
}