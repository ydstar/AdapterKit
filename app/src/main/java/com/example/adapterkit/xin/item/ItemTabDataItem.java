package com.example.adapterkit.xin.item;


import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;


import com.adapter.kit.IDataItem;
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
public class ItemTabDataItem extends IDataItem<NewModel, RecyclerView.ViewHolder> {

    public ItemTabDataItem(@Nullable NewModel newModel) {
        super(newModel);
    }

    @Override
    public int getItemLayoutRes() {
        return R.layout.layout_list_item_tab;
    }

    @Override
    public void onBindData(@NotNull RecyclerView.ViewHolder holder, int position) {
        AppCompatImageView imageView = holder.itemView.findViewById(R.id.item_image);
        imageView.setImageResource(R.drawable.item_tab);
    }

}
