package com.adapter.kit

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: 信仰年轻
 * Date: 2021-01-04 11:18
 * Email: hydznsqk@163.com
 * Des:数据条目的包装类,持有数据和viewHolder
 * 1.泛型参数实例化对象
 * 2.itemViewType和DataItem实例一一对应
 */
abstract class DataItem<DATA, VH : RecyclerView.ViewHolder> {

    protected var mAdapter: AdapterKit? = null
    var mData: DATA? = null

    constructor(data: DATA? = null) {
        this.mData = data
    }

    /**
     * 绑定数据
     */
    abstract fun onBindData(holder: VH, position: Int)

    /**
     * 返回该item的布局资源id
     */
    open fun getItemLayoutRes(): Int {
        return -1
    }

    /**
     * 返回该item的布局资源ID
     */
    open fun getItemView(parent: ViewGroup): View? {
        return null
    }

    /**
     *刷新列表
     */
    fun refreshItem() {
        mAdapter?.refreshItem(this)
    }

    /**
     *从列表上移除
     */
    fun removeItem() {
        mAdapter?.removeItem(this)
    }

    /**
     * 该item在列表上占几列
     */
    open fun getSpanSize(): Int {
        return 0
    }

    fun setAdapter(adapter: AdapterKit) {
        this.mAdapter = adapter
    }

    /**
     * 该item被滑进屏幕
     */
    open fun onViewAttachedToWindow(holder: VH) {

    }

    /**
     * 该item被滑出屏幕
     */
    open fun onViewDetachedFromWindow(holder: VH) {

    }

    /**
     * 创建viewHolder
     */
    open fun onCreateViewHolder(itemView: View): VH? {
        return null
    }

}