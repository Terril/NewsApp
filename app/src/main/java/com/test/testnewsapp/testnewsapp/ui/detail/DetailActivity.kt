package com.test.testnewsapp.testnewsapp.ui.detail

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.test.testnewsapp.testnewsapp.R
import com.test.testnewsapp.testnewsapp.data.network.model.Article
import com.test.testnewsapp.testnewsapp.ui.base.BaseActivity
import com.test.testnewsapp.testnewsapp.utils.ActivityUtils
import com.test.testnewsapp.testnewsapp.utils.GlobalConst
import org.joda.time.DateTime

class DetailActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.img_article)
    lateinit var articleImage: ImageView

    @BindView(R.id.tv_article_title)
    lateinit var tvArticleTitle: TextView

    @BindView(R.id.tv_article_description)
    lateinit var tvArticleDescription: TextView

    @BindView(R.id.tv_domain)
    lateinit var tvDomain: TextView

    @BindView(R.id.tv_published_at)
    lateinit var tvPublishedAt: TextView

    private lateinit var article: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getActivityComponent().inject(this)
        setUnBinder(ButterKnife.bind(this))

        setUp()
    }

    override fun setUp() {
        val arguments = intent.getBundleExtra(ActivityUtils.BUNDLE_KEY)
        article = arguments.getParcelable(GlobalConst.KEY_ARTICLE) as Article

        toolbar.setTitle(R.string.str_heading_detail)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        Glide.with(this).load(article.urlToImage).into(articleImage)
        tvArticleTitle.text = article.title
        tvArticleDescription.text = article.content
        tvDomain.text = article.author
        tvPublishedAt.text = DateTime.parse(article.publishedAt).toString("EEE, dd.MM.yyyy HH:mm a")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}