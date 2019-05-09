package com.test.testnewsapp.testnewsapp.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import com.test.testnewsapp.testnewsapp.R
import com.test.testnewsapp.testnewsapp.interfaces.IRecyclerView
import com.test.testnewsapp.testnewsapp.ui.base.BaseActivity
import com.test.testnewsapp.testnewsapp.ui.detail.DetailActivity
import com.test.testnewsapp.testnewsapp.utils.ActivityUtils
import com.test.testnewsapp.testnewsapp.utils.GlobalConst
import com.test.testnewsapp.testnewsapp.vm.DataView
import com.test.testnewsapp.testnewsapp.vm.ErrorView
import com.test.testnewsapp.testnewsapp.vm.MainViewModel
import com.test.testnewsapp.testnewsapp.vm.NoResultView
import org.joda.time.DateTime
import javax.inject.Inject

class MainActivity : BaseActivity(), IRecyclerView {

    @Inject
    lateinit var viewModel: MainViewModel

    @BindView(R.id.rv_news)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.refresh_layout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var mAdapter: NewsAdapter
    private lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUnBinder(ButterKnife.bind(this))
        getActivityComponent().inject(this)

        setUp()

        loadData()
    }

    override fun setUp() {
        setSupportActionBar(mToolbar)

        mAdapter = NewsAdapter(this)
        mLinearLayoutManager = LinearLayoutManager(this)
        mAdapter.setRecyclerViewCallback(this)

        recyclerView.layoutManager = mLinearLayoutManager
        recyclerView.adapter = mAdapter

        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }

        //Observing live data.. Whenever, ViewModel will update `data` our observer will render it on UI accordingly automatically
        viewModel.data.observe(this, Observer {
            when (it) {
                is DataView -> {
                    mAdapter.addNews(it.articles, true)
                }
                is NoResultView -> {
                    mAdapter.clearData()
                    Toast.makeText(this, "No result", Toast.LENGTH_LONG).show()
                }
                is ErrorView -> {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
            swipeRefreshLayout.isRefreshing = false
        })
    }

    //Made this method separately so that in case we need to load anything else during setup, we can simply call that method inside it
    private fun loadData() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.loadData(DateTime.now().minusDays(2).toString("yyy-M-d"))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_about) {
            LibsBuilder()
                //provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                //start the activity
                .start(this);
        }
        return super.onOptionsItemSelected(item)
    }

    //This method will be called when recycler view's item would be tapped and from here we can get the actual item and push it to the next screen
    override fun onRecyclerViewItemClick(position: Int) {
        val selectedArticle = mAdapter.getItemAtPosition(position)
        val bundle = Bundle()
        bundle.putParcelable(GlobalConst.KEY_ARTICLE, selectedArticle)  //Pushing article in bundle so that it can be used on next screen
        ActivityUtils.launchActivity(this, DetailActivity::class.java, false, bundle)
    }

    //This method is not yet needed so I have not implemented it yet.. We can do it whenever needed
    override fun onRecyclerViewItemDeleteTapped(position: Int) {
        //TODO: Implement it to handle delete
    }

    //This method is not yet needed so I have not implemented it yet.. We can do it whenever needed
    override fun onRecyclerViewItemLongClick(position: Int) {
        //TODO: Implement it to handle long tap
    }
}