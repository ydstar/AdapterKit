package com.example.adapterkit.xin.item

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.adapter.kit.IDataItem
import com.example.adapterkit.R
import com.example.adapterkit.xin.model.NewModel


/**
 * Author: 信仰年轻
 * Date: 2021-01-04 16:06
 * Email: hydznsqk@163.com
 * Des:
 */
class GridDataItem(data: NewModel): IDataItem<NewModel, GridDataItem.MyHolder>(data) {

    override fun getItemLayoutRes(): Int {
        return R.layout.layout_list_item_grid;
    }

    override fun onBindData(holder: MyHolder, position: Int) {
        holder.imageView!!.setImageResource(R.drawable.item_grid)
    }

    class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var imageView: ImageView? = null

        init {
            imageView = itemView.findViewById<ImageView>(R.id.item_image)
        }
    }
}