package com.example.wishlist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlist.data.Wish
import com.example.wishlist.data.WishRepository
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(
    private val wishRepository: Lazy<WishRepository>
): ViewModel() {
    init {
        viewModelScope.launch {
            allWishes = wishRepository.get().getAllWishes()
        }
    }
    private lateinit var allWishes: Flow<List<Wish>>

    fun addWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.get().addWish(wish)
        }
    }

    fun getWishById(wishId: Long): Flow<Wish> {
        return wishRepository.get().getWishById(wishId).distinctUntilChanged()
    }

    fun getAllWishes(): Flow<List<Wish>> {
        return allWishes
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.get().updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.get().deleteWish(wish)
        }
    }
}
