package com.test.testnewsapp.testnewsapp.vm

import android.arch.lifecycle.MutableLiveData
import com.test.testnewsapp.testnewsapp.data.DataManager
import com.test.testnewsapp.testnewsapp.data.network.model.Article
import com.test.testnewsapp.testnewsapp.ui.base.KViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//View state classes since livedata doesn't handle errors. We have to handle it ourself through ViewStates

sealed class DataViewState
data class ErrorView(val errorMessage: String) : DataViewState()
object NoResultView : DataViewState()
data class DataView(val articles: List<Article>) : DataViewState()

class MainViewModel @Inject constructor(
    private val dataManager: DataManager
) : KViewModel() {

    var data = MutableLiveData<DataViewState>()

    fun loadData(from: String) {
        dataManager.fetchLatestNews(from)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.articles.isNotEmpty()) {
                    data.postValue(DataView(it.articles))
                } else {
                    data.postValue(NoResultView)
                }
            }, {
                data.postValue(ErrorView(it.message ?: ""))
            }).disposeOnCleared()   //disposing this observable to save memory leaks. The observable will be disposed off when ViewModel will be cleared
    }
}