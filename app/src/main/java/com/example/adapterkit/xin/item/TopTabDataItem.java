package com.example.adapterkit.xin.item;

import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;


import com.adapter.kit.DataItem;
import com.example.adapterkit.R;
import com.example.adapterkit.xin.model.NewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Author: 信仰年轻
 * Date: 2021-01-04 16:01
 * Email: hydznsqk@163.com
 * Des:
 */
public class TopTabDataItem extends DataItem<NewModel, RecyclerView.ViewHolder> {

   private NewModel mNewModel;
    public TopTabDataItem( @Nullable NewModel model) {
        super(model);
        mNewModel= model;
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.layout_list_item_top_tab;
    }


    @Override
    public void onBindData(@NotNull RecyclerView.ViewHolder holder, int position) {
        ImageView imageView = holder.itemView.findViewById(R.id.item_image);
        imageView.setImageResource(R.drawable.item_top_tab);

        String name = mNewModel.name;

    }


}
