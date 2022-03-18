package com.zynksoftware.futureup

import android.app.Application
import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.zynksoftware.futureup.di.repositoryModule
import com.zynksoftware.futureup.di.useCaseModule
import com.zynksoftware.futureup.di.utilsModule
import com.zynksoftware.futureup.di.viewModelModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initInterceptor(this)
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(level = Level.ERROR)
            modules(
                listOf(
                    repositoryModule,
                    useCaseModule,
                    utilsModule,
                    viewModelModule,
                )
            )
        }
    }

    fun initInterceptor(context: Context) {
        if (FlipperUtils.shouldEnableFlipper(context)) {
            SoLoader.init(context, false)
            val client: FlipperClient = AndroidFlipperClient.getInstance(context)
            client.addPlugin(networkFlipper)
            networkFlipper
            client.start()
        }
    }

    companion object {
        private var networkFlipper = NetworkFlipperPlugin()

        fun addInterceptors(okHttpClientBuilder: OkHttpClient.Builder) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            okHttpClientBuilder.addInterceptor(loggingInterceptor)
            okHttpClientBuilder.addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipper))
        }
    }
}
