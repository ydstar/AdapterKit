package com.adapter.kit

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * Author: 信仰年轻
 * Date: 2020-11-19 18:44
 * Email: hydznsqk@163.com
 * Des: 高效缓存的viewHolder
 */
open class CacheViewHolder (val view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
    override val containerView: View?
        get() = view

    private var viewCache = SparseArray<View>()
    fun <T : View> findViewById(viewId: Int): T? {
        var view = viewCache.get(viewId)
        if (view == null) {
            view = itemView.findViewById<T>(viewId)
            viewCache.put(viewId, view)
        }
        return view as? T
    }
}