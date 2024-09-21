package com.latihan.kpuapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.latihan.kpuapp.database.KpuDatabase
import com.latihan.kpuapp.database.PemilihDao
import com.latihan.kpuapp.repository.Repository
import com.latihan.kpuapp.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
   @Provides
   @Singleton
   fun provideDatabase(app: Application): KpuDatabase {
      return Room.databaseBuilder(
         app,
         KpuDatabase::class.java,
         "kpu_db"
      ).build()
   }

   @Provides
   @Singleton
   fun provideDao(db: KpuDatabase): PemilihDao {
      return db.dao
   }

   @Provides
   @Singleton
   fun provideRepository(db: KpuDatabase): Repository {
      return RepositoryImpl(db.dao)
   }
}