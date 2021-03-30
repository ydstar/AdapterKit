package com.example.adapterkit.xin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adapter.kit.AdapterKit
import com.adapter.kit.DataItem
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
        val adapter = AdapterKit(this)

        recycler_view.layoutManager = GridLayoutManager(this, 2)
        recycler_view.adapter = adapter

        //数据
        val list: ArrayList<DataItem<*, out RecyclerView.ViewHolder>> = ArrayList()
        list.add(TopTabDataItem(NewModel()))//顶部导航
        list.add(TopBanner(NewModel()))//顶部轮播图
        list.add(GridDataItem(NewModel()))//金刚区
        list.add(ActivityDataItem(NewModel()))//活动区域
        list.add(ItemTabDataItem(NewModel()))//商品tab栏

        for (i in 0..9) {
            if (i % 2 == 0) {
                //feeds流的视频类型
                list.add(VideoDataItem(1, NewModel()))
            } else {
                //feeds流的图片类型
                list.add(ImageDataItem(1, NewModel()))
            }
        }

        //为adapter添加数据
        adapter.addItems(list, false)

    }
}