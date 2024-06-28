package com.example.wishlist

import android.content.Context
import androidx.room.Room
import com.example.wishlist.data.WishDAO
import com.example.wishlist.data.WishDB
import com.example.wishlist.data.WishRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WishDB {
        val database = Room.databaseBuilder(
            context = context,
            klass = WishDB::class.java,
            name = "wishlist.db"
        ).build()
        return database
    }

    @Provides
    @Singleton
    fun provideWishDAO(database: WishDB) = database.wishDAO()

    @Provides
    @Singleton
    fun provideWishRepository(wishDAO: WishDAO): WishRepository {
        return WishRepository(wishDAO)
    }
}