package id.hudiohizari.weather.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.hudiohizari.weather.BuildConfig
import id.hudiohizari.weather.data.network.Client
import id.hudiohizari.weather.data.network.NetworkConnectionInterceptor
import id.hudiohizari.weather.data.network.api.OpenWeatherMapApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getChuckerInterceptor(
        @ApplicationContext
        context: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

    @Provides
    @Singleton
    fun getClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): Client {
        return Client(networkConnectionInterceptor, chuckerInterceptor)
    }

    @Provides
    @Singleton
    fun getRetrofit(client: Client): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client.provideClient())
            .build()
    }

    @Provides
    @Singleton
    fun getOpenWeatherMapApiInstance(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

}