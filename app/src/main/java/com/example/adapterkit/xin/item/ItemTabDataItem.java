package com.example.adapterkit.xin.item;


import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.adapter.kit.CacheViewHolder;
import com.adapter.kit.DataItem;
import com.example.adapterkit.R;
import com.example.adapterkit.xin.model.NewModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Author: 信仰年轻
 * Date: 2021-01-04 16:13
 * Email: hydznsqk@163.com
 * Des:
 */
public class ItemTabDataItem extends DataItem<NewModel, CacheViewHolder> {

    public ItemTabDataItem(@Nullable NewModel newModel) {
        super(newModel);
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.layout_list_item_tab;
    }


    @Override
    public CacheViewHolder onCreateViewHolder(@NonNull View itemView) {
        return new CacheViewHolder(itemView);
    }

    @Override
    public void onBindData(@NotNull CacheViewHolder holder, int position) {
//        AppCompatImageView imageView = holder.itemView.findViewById(R.id.item_image);
//        imageView.setImageResource(R.drawable.item_tab);
        AppCompatImageView viewById = holder.findViewById(R.id.item_image);
        viewById.setImageResource(R.drawable.item_tab);
    }

}
