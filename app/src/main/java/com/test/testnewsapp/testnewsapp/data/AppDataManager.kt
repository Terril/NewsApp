package com.test.testnewsapp.testnewsapp.data


import android.content.Context
import com.test.testnewsapp.testnewsapp.data.network.ApiHelper
import com.test.testnewsapp.testnewsapp.data.network.model.response.NewsApiResponse
import com.test.testnewsapp.testnewsapp.di.ApplicationContext
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject
constructor(
    @param:ApplicationContext private val mContext: Context,
    private val mApiHelper: ApiHelper
) : DataManager {


    override fun fetchLatestNews(from: String): Observable<NewsApiResponse> =
        mApiHelper.fetchLatestNews(from)

    companion object {

        private val TAG = "AppDataManager"
    }
}
