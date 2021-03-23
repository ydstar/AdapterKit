package com.example.adapterkit.old;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adapterkit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 信仰年轻
 * Date: 2021-01-04 17:13
 * Email: hydznsqk@163.com
 * Des:
 */
public class OldAdapter extends RecyclerView.Adapter<OldAdapter.MyViewHolder> {
    private final LayoutInflater mInflater;
    private final Context context;
    private final List<OldModel> dataSets = new ArrayList<>();

    public OldAdapter(Context context, List<OldModel> dataSets) {
        this.context = context;
        this.dataSets.addAll(dataSets);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        OldModel OldModel = dataSets.get(position);
        return OldModel.itemType;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutRes = 0;
        switch (viewType) {
            case OldModel.TYPE_TOP_TAB:
                layoutRes = R.layout.layout_list_item_top_tab;
                break;
            case OldModel.TYPE_BANNER:
                layoutRes = R.layout.layout_list_item_banner;
                break;
            case OldModel.TYPE_GRID_ITEM:
                layoutRes = R.layout.layout_list_item_grid;
                break;
            case OldModel.TYPE_ACTIVITY:
                layoutRes = R.layout.layout_list_item_activity;
                break;
            case OldModel.TYPE_ITEM_TAB:
                layoutRes = R.layout.layout_list_item_tab;
                break;
            case OldModel.TYPE_VIDEO:
                layoutRes = R.layout.layout_list_item_video;
                break;
            case OldModel.TYPE_IMAGE:
                layoutRes = R.layout.layout_list_item_image;
                break;

        }
        View view = null;
        if (layoutRes != 0) {
            //根据type 加载对应的item 布局资源
            view = mInflater.inflate(layoutRes, parent, false);
        } else {
            //如果该item的类型不在已知类型内，则创建个兜底View。
            view = new TextView(context);
            view.setVisibility(View.GONE);
        }
        //这里更有甚者会创建多种ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //switch-case 分门别类的对每一种类型的item的数据进行绑定，以及其它业务逻辑处理
        OldModel OldModel = dataSets.get(position);
        switch (OldModel.itemType) {
            case com.example.adapterkit.old.OldModel.TYPE_TOP_TAB:
                holder.imageView.setImageResource(R.drawable.item_top_tab);
                break;
            case com.example.adapterkit.old.OldModel.TYPE_BANNER:
                holder.imageView.setImageResource(R.drawable.item_banner);
                break;
            case com.example.adapterkit.old.OldModel.TYPE_GRID_ITEM:
                holder.imageView.setImageResource(R.drawable.item_grid);
                break;
            case com.example.adapterkit.old.OldModel.TYPE_ACTIVITY:
                holder.imageView.setImageResource(R.drawable.item_activity);
                break;
            case com.example.adapterkit.old.OldModel.TYPE_ITEM_TAB:
                holder.imageView.setImageResource(R.drawable.item_tab);
                break;
            case com.example.adapterkit.old.OldModel.TYPE_VIDEO:
                holder.imageView.setImageResource(R.drawable.item_video);
                break;
            case com.example.adapterkit.old.OldModel.TYPE_IMAGE:
                holder.imageView.setImageResource(R.drawable.item_image);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dataSets.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

    }
}

