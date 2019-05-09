package com.test.testnewsapp.testnewsapp.data.network

import com.test.testnewsapp.testnewsapp.BuildConfig
import com.test.testnewsapp.testnewsapp.data.network.model.response.NewsApiResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

class AppApiHelper @Inject
constructor(private val mRetrofit: Retrofit) : ApiHelper {

    override fun fetchLatestNews(from: String): Observable<NewsApiResponse> {
        val map = HashMap<String, String>()
        map["from"] = from
        map["domains"] = "wsj.com,nytimes.com"
        map["apiKey"] = BuildConfig.API_KEY
        return Observable.fromCallable {
            mRetrofit.create(RetrofitApi::class.java)
                .fetchAllLatestNews(map)
                .execute()
                .body()
        }
    }
}
