package com.example.adapterkit.xin.item

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.adapter.kit.DataItem
import com.example.adapterkit.R
import com.example.adapterkit.xin.model.NewModel


/**
 * Author: 信仰年轻
 * Date: 2021-01-04 16:19
 * Email: hydznsqk@163.com
 * Des:
 */
class VideoDataItem(spanCount:Int,data: NewModel): DataItem<NewModel, RecyclerView.ViewHolder>(data) {

    var mSpancount:Int
    init {
        this.mSpancount=spanCount
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.layout_list_item_video;
    }

    override fun getSpanSize(): Int {
        return mSpancount
    }
    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        val imageview = holder.itemView.findViewById<ImageView>(R.id.item_image)
        imageview.setImageResource(R.drawable.item_video);
    }


}