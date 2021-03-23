package com.example.adapterkit.xin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adapter.kit.IAdapter
import com.adapter.kit.IDataItem
import com.example.adapterkit.R
import com.example.adapterkit.xin.item.*
import com.example.adapterkit.xin.model.NewModel
import kotlinx.android.synthetic.main.activity_new_recyclerview.*


class NewRecyclerviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_recyclerview)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = IAdapter(this)

        recycler_view.layoutManager = GridLayoutManager(this,2)
        recycler_view.adapter=adapter

        //数据
        val mList:ArrayList<IDataItem<*, out RecyclerView.ViewHolder>> = ArrayList()
        mList.add(TopTabDataItem(NewModel()))//顶部导航
        mList.add(TopBanner(NewModel()))//顶部轮播图
        mList.add(GridDataItem(NewModel()))//金刚区
        mList.add(ActivityDataItem(NewModel()))//活动区域
        mList.add(ItemTabDataItem(NewModel()))//商品tab栏

        for (i in 0..9) {
            if (i % 2 == 0) {
                //feeds流的视频类型
                mList.add(VideoDataItem(1, NewModel()))
            } else {
                //feeds流的图片类型
                mList.add(ImageDataItem(1, NewModel()))
            }
        }

        //为adapter添加数据
        adapter.addItems(mList, false)

    }
}