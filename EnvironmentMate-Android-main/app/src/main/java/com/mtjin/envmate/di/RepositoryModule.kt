package com.mtjin.envmate.di

import com.mtjin.envmate.api.ApiInterface
import com.mtjin.envmate.api.MainApiInterface
import com.mtjin.envmate.data.chart.ChartRepository
import com.mtjin.envmate.data.chart.ChartRepositoryImpl
import com.mtjin.envmate.data.sign_in.SignInRepository
import com.mtjin.envmate.data.sign_in.SignInRepositoryImpl
import com.mtjin.envmate.data.user_info.UserInfoRepository
import com.mtjin.envmate.data.user_info.UserInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideUserInfoRepository(apiInterface: ApiInterface): UserInfoRepository {
        return UserInfoRepositoryImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideSignInRepository(apiInterface: ApiInterface): SignInRepository {
        return SignInRepositoryImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideChartRepository(mainApiInterface: MainApiInterface): ChartRepository {
        return ChartRepositoryImpl(mainApiInterface)
    }
}