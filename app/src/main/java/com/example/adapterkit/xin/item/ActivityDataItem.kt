package com.example.adapterkit.xin.item

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.adapter.kit.IDataItem
import com.example.adapterkit.R
import com.example.adapterkit.xin.model.NewModel


/**
 * Author: 信仰年轻
 * Date: 2021-01-04 16:11
 * Email: hydznsqk@163.com
 * Des:
 */
class ActivityDataItem(data: NewModel): IDataItem<NewModel, ActivityDataItem.ActivityHolder>(data) {


    override fun getItemLayoutRes(): Int {
        return R.layout.layout_list_item_activity
    }

    override fun onBindData(holder: ActivityHolder, position: Int) {
        val imageView = holder!!.itemView.findViewById<ImageView>(R.id.item_image)
        imageView.setImageResource(R.drawable.item_activity)
    }

    class ActivityHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

}