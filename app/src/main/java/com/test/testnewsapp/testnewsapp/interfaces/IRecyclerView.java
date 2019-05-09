package com.test.testnewsapp.testnewsapp.interfaces;

/**
 * Created by terril on 8/9/2017.
 */

public interface IRecyclerView {
    void onRecyclerViewItemClick(int position);

    void onRecyclerViewItemLongClick(int position);

    void onRecyclerViewItemDeleteTapped(int position);
}
