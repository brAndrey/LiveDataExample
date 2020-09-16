package com.example.livedataexample.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.livedataexample.R;
import com.example.livedataexample.model.Blog;
import com.example.livedataexample.ui.BlogAdapter;
import com.example.livedataexample.viewmovel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefresh;
    private  MainViewModel mainViewModel;

    BlogAdapter mBlogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    initializationViews();

    mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    getPopularBlog();

    swipeRefresh.setOnRefreshListener(()->getPopularBlog());


    }



    private void initializationViews() {
        swipeRefresh = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.blogRecyclerView);
    }
    private void getPopularBlog() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllBlog().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogList) {
                swipeRefresh.setRefreshing(false);
                prepareRecyclerView(blogList);
            }
        });
    }

    private void prepareRecyclerView(List<Blog> blogList) {
        mBlogAdapter = new BlogAdapter(blogList);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        }else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBlogAdapter);
        mBlogAdapter.notifyDataSetChanged();

    }

}