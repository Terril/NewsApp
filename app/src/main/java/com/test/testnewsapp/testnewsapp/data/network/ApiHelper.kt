package com.test.testnewsapp.testnewsapp.data.network

import com.test.testnewsapp.testnewsapp.data.network.model.response.NewsApiResponse
import io.reactivex.Observable

interface ApiHelper {

    fun fetchLatestNews(from: String): Observable<NewsApiResponse>
}
