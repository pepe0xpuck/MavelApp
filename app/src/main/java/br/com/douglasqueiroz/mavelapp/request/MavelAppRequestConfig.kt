package br.com.douglasqueiroz.mavelapp.request

import br.com.douglasqueiroz.mavelapp.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MavelAppRequestConfig {
    companion object Factory {
        private val TIMEOUT = 90
        private val SINGLE_REQUEST = 1
        private val GSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"


        fun createRetrofit(): Retrofit {
            val client = getOkHttpClient(getLoggingInterceptor())

            return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .callbackExecutor(Executors.newCachedThreadPool())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build()
        }


        private fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .dispatcher(getDispatcher())
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build()
        }

        private fun createGson(): Gson {
            return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setDateFormat(GSON_DATE_FORMAT)
                .create()
        }

        private fun getLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return interceptor
        }

        private fun getDispatcher(): Dispatcher {
            val dispatcher = Dispatcher()
            dispatcher.maxRequests = SINGLE_REQUEST
            dispatcher.maxRequestsPerHost = SINGLE_REQUEST
            return dispatcher
        }
    }
}