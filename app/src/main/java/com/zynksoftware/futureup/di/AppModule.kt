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
import com.zynksoftware.futureup.network.RemoteServicesHandler
import com.zynksoftware.futureup.network.ServiceProvider
import com.zynksoftware.futureup.ui.wallet.WalletViewModel
import com.zynksoftware.futureup.usecase.GetCoinsUseCase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val viewModelModule = module {
    viewModel { WalletViewModel(get()) }
}

val repositoryModule = module {
    single { provideSharedPreferences(androidContext()) }
    single { Repository(get(), get()) }
}

val utilsModule = module {
    single { NetworkExceptionHandler() }
    single { RemoteServicesHandler(get()) }
    single { provideMoshi() }
    single { provideOkHttpBuilder() }
    single { ServiceProvider(get(), provideOkHttpBuilder().build()).createApiService() }
}

val useCaseModule = module {
    single { GetCoinsUseCase(get()) }
}

fun provideOkHttpBuilder(): OkHttpClient.Builder {
    val okHttpClientBuilder = OkHttpClient.Builder()

    BaseApplication.addInterceptors(okHttpClientBuilder)

    okHttpClientBuilder
//        .addInterceptor(NetworkNotAvailableInterceptor())
//        .addInterceptor(BasicInterceptor())
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