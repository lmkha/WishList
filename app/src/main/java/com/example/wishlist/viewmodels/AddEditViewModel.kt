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
class AddEditViewModel @Inject constructor(
    private val wishRepository: Lazy<WishRepository>
): ViewModel() {
    fun addWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.get().addWish(wish)
        }
    }

    fun getWishById(wishId: Long): Flow<Wish> {
        return wishRepository.get().getWishById(wishId).distinctUntilChanged()
    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(context = Dispatchers.IO) {
            wishRepository.get().updateWish(wish)
        }
    }
}