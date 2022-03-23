package com.zynksoftware.futureup.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zynksoftware.futureup.BaseApplication
import com.zynksoftware.futureup.Repository
import com.zynksoftware.futureup.network.NetworkExceptionHandler
import com.zynksoftware.futureup.network.interceptors.NetworkNotAvailableInterceptor
import com.zynksoftware.futureup.network.RemoteServicesHandler
import com.zynksoftware.futureup.network.ServiceProvider
import com.zynksoftware.futureup.ui.portofolio.PortfolioViewModel
import com.zynksoftware.futureup.ui.wallet.WalletViewModel
import com.zynksoftware.futureup.usecase.GetCoinsUseCase
import com.zynksoftware.futureup.usecase.GetTotalBalanceUseCase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val viewModelModule = module {
    viewModel { WalletViewModel(get(), get()) }
    viewModel { PortfolioViewModel(get(), get()) }
}

val repositoryModule = module {
    single { provideSharedPreferences(androidContext()) }
    single { Repository(get(), get()) }
}

val utilsModule = module {
    single { NetworkExceptionHandler(get()) }
    single { RemoteServicesHandler(get()) }
    single { provideMoshi() }
    single { provideOkHttpBuilder() }
    single { ServiceProvider(get(), provideOkHttpBuilder().build()).createApiService() }
}

val useCaseModule = module {
    single { GetCoinsUseCase(get()) }
    single { GetTotalBalanceUseCase(get()) }
}

fun provideOkHttpBuilder(): OkHttpClient.Builder {
    val okHttpClientBuilder = OkHttpClient.Builder()

    BaseApplication.addInterceptors(okHttpClientBuilder)

    okHttpClientBuilder
        .addInterceptor(NetworkNotAvailableInterceptor())
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)

    return okHttpClientBuilder
}

fun provideSharedPreferences(context: Context): SharedPreferences {
    return EncryptedSharedPreferences.create(
        "AppPreferences",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()