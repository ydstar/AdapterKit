package com.adapter.kit

import android.content.Context
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import java.lang.RuntimeException
import java.lang.ref.WeakReference
import java.lang.reflect.ParameterizedType

/**
 * Author: 侯亚东
 * Date: 2021-01-04 11:38
 * Email: houyadong1@gome.com.cn
 * Des: recyclerView通用数据适配器-具体使用请参考https://github.com/ydstar/AdapterKit
 */
class AdapterKit(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var BASE_ITEM_TYPE_HEADER = 1000000
    private var BASE_ITEM_TYPE_FOOTER = 2000000

    private var mRecyclerViewRef: WeakReference<RecyclerView>? = null
    private var mContext: Context
    private var mInflater: LayoutInflater? = null

    private var mDataSets = ArrayList<DataItem<*, out RecyclerView.ViewHolder>>()

    //装载不同视图itemType的集合
    private val mTypePositions = SparseIntArray();

    private var mHeaders = SparseArray<View>()
    private var mFooters = SparseArray<View>()

    init {
        this.mContext = context
        this.mInflater = LayoutInflater.from(context)
    }

    /**
     * 多少条数据
     */
    override fun getItemCount(): Int {
        return mDataSets.size + getHeaderSize() + getFooterSize()
    }

    /**
     * 返回列表上面每个item的视图类型
     * 这个方法会传进一个参数position表示当前是第几个Item，然后我们可以通过position拿到当前的Item对象，
     * 然后判断这个item对象需要那种视图，返回一个int类型的视图标志，
     * 然后在onCreateViewHolder方法中给引入布局，这样就能够实现多种item显示了
     */
    override fun getItemViewType(position: Int): Int {
        if (isHeaderPosition(position)) {
            return mHeaders.keyAt(position)
        }
        if (isFooterPosition(position)) {
            //footer的位置应该计算一下,position = 6,headerCount=1,footerSize=1
            val footerPosition = position - getHeaderSize() - getOriginalItemSize()
            return mFooters.keyAt(footerPosition)
        }

        //普通item类型
        val itemPosition = position - getHeaderSize()
        val dataItem = mDataSets.get(itemPosition)
        val type = dataItem.javaClass.hashCode()
        mTypePositions.put(type, position)
        return type
    }

    /**
     * 样式
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (mHeaders.indexOfKey(viewType) >= 0) {
            val view = mHeaders[viewType]
            return object : RecyclerView.ViewHolder(view) {}
        }
        if (mFooters.indexOfKey(viewType) >= 0) {
            val view = mFooters[viewType]
            return object : RecyclerView.ViewHolder(view) {}
        }

        //根据类型取出dataItem
        val position = mTypePositions.get(viewType)
        val dataItem = mDataSets[position]

        //拿到itemView
        var itemView = dataItem.getItemView(parent)
        if (itemView == null) {
            //继续拿布局资源ID
            val itemLayoutRes = dataItem.getItemLayoutRes()
            if (itemLayoutRes < 0) {
                RuntimeException("dataItem:" + dataItem.javaClass.name + "必须重写 getItemView 或者 getItemLayoutRes方法")
            }
            itemView = mInflater!!.inflate(itemLayoutRes, parent, false)
        }

        val vh = dataItem.onCreateViewHolder(itemView!!)
        if (vh != null) {
            return vh
        }
        return createViewHolderInternal(dataItem.javaClass, itemView)
    }

    private fun createViewHolderInternal(
        javaClass: Class<DataItem<*, out RecyclerView.ViewHolder>>,
        itemView: View): RecyclerView.ViewHolder {

        val superclass = javaClass.genericSuperclass
        if (superclass is ParameterizedType) {
            //得到它携带的泛型参数的数组
            val arguments = superclass.actualTypeArguments
            for (argument in arguments) {
                if (argument is Class<*> && RecyclerView.ViewHolder::class.java.isAssignableFrom(argument)) {
                    try {
                        //如果是则使用反射 实例化类上标记的实际的泛型对象
                        //这里需要  try-catch 一把，如果咱们直接在DataItem子类上标记 RecyclerView.ViewHolder，抽象类是不允许反射的
                        return argument.getConstructor(View::class.java)
                            .newInstance(itemView) as RecyclerView.ViewHolder
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return object : CacheViewHolder(itemView) {}
    }

    /**
     * 绑定数据
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isHeaderPosition(position) || isFooterPosition(position)) {
            return
        }
        val itemPosition = position - getHeaderSize()
        getItem(itemPosition)?.onBindData(holder, itemPosition)
    }

    private fun getItem(position: Int): DataItem<*, RecyclerView.ViewHolder>? {
        if (position < 0 || position >= mDataSets.size) {
            return null
        }
        return mDataSets[position] as DataItem<*, RecyclerView.ViewHolder>
    }

////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *onAttachedToRecyclerView和onDetachedFromRecyclerView
     *这两个回调则是当 RecyclerView 调用了 setAdapter() 时会触发，旧的 adapter 回调 onDetached，新的 adapter 回调 onAttached。
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerViewRef = WeakReference(recyclerView)
        /**
         * 为列表上的item 适配网格布局
         */
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            //占据整个屏幕的宽度,这里的spanCount是从GridLayoutManager(this,2)中取的
            //这里的2（自己设置的最好设置成偶数）就相当于分母，2默认显示一整行（1列），
            //下面的1 和2 就相当于分子，返回1就是（1/2）所以此类型对应的是2列，返回2就是1列
            val spanCount = layoutManager.spanCount//spanCount=2

            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (isHeaderPosition(position) || isFooterPosition(position)) {
                        return spanCount
                    }
                    val itemPosition = position - getHeaderSize()
                    if (itemPosition < mDataSets.size) {
                        val dataItem = mDataSets.get(itemPosition)
                        val spanSize = dataItem.getSpanSize()
                        return if (spanSize <= 0) spanCount else spanSize
                    }
                    return spanCount
                }
            }
        }
    }

    /**
     *onAttachedToRecyclerView和onDetachedFromRecyclerView
     *这两个回调则是当 RecyclerView 调用了 setAdapter() 时会触发，旧的 adapter 回调 onDetached，新的 adapter 回调 onAttached。
     */
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        if (mRecyclerViewRef != null) {
            mRecyclerViewRef?.clear()
        }
    }

    /**
     * 该item被滑进屏幕
     * 瀑布流适配
     */
    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        val recyclerView = getAttachRecyclerView()
        if (recyclerView != null) {
            //瀑布流的item占比适配
            val position = recyclerView.getChildAdapterPosition(holder.itemView)
            val isHeaderFooter = isHeaderPosition(position) || isFooterPosition(position)
            val itemPosition = position - getHeaderSize()
            val dataItem = getItem(itemPosition) ?: return
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {
                val manager = recyclerView.layoutManager as StaggeredGridLayoutManager?
                if (isHeaderFooter) {
                    lp.isFullSpan = true
                    return
                }
                val spanSize = dataItem.getSpanSize()
                if (spanSize == manager!!.spanCount) {
                    lp.isFullSpan = true
                }
            }
            dataItem.onViewAttachedToWindow(holder)
        }
    }

    /**
     * 该item被滑出屏幕
     */
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        val position = holder.adapterPosition
        if (isHeaderPosition(position) || isFooterPosition(position)) {
            return
        }
        val itemPosition = position - getHeaderSize()
        val dataItem = getItem(itemPosition) ?: return
        dataItem.onViewDetachedFromWindow(holder)
    }


    fun getAttachRecyclerView(): RecyclerView? {
        return if (mRecyclerViewRef != null) mRecyclerViewRef?.get() else null
    }

    //////////////添加头部,添加脚/////////////////////////////////////////////////////////////////
    fun addHeadView(view: View) {
        if (mHeaders.indexOfValue(view) < 0) {
            mHeaders.put(BASE_ITEM_TYPE_HEADER++, view)
            notifyItemInserted(mHeaders.size() - 1)
        }
    }

    fun removeHeaderView(view: View) {
        val indexOfValue = mHeaders.indexOfValue(view)
        if (indexOfValue < 0) {
            return
        }
        mHeaders.removeAt(indexOfValue)
        notifyItemRemoved(indexOfValue)
    }

    fun addFooterView(view: View) {
        if (mFooters.indexOfValue(view) < 0) {
            mFooters.put(BASE_ITEM_TYPE_FOOTER++, view)
            notifyItemInserted(itemCount)
        }
    }

    fun removeFooterView(view: View) {
        val indexOfValue = mFooters.indexOfValue(view)
        if (indexOfValue < 0) {
            return
        }
        mFooters.removeAt(indexOfValue)
        //position 代表的是在列表中的位置
        notifyItemRemoved(indexOfValue + getHeaderSize() + getOriginalItemSize())
    }

    fun getHeaderSize(): Int {
        return mHeaders.size()
    }

    fun getFooterSize(): Int {
        return mFooters.size()
    }

    fun getOriginalItemSize(): Int {
        return mDataSets.size
    }

    /**
     * position 是否是头部position
     */
    private fun isHeaderPosition(position: Int): Boolean {
        //如果mHeaders没有值,0<0么,并不小于,那就是false,就position不是头部的position
        //如果mHeaders有5个元素,然后position 0 1 2 3 4都小于5,那这个position 就是在头
        //0<5 1<5 2<5 3<5 4<5
        return position < mHeaders.size()
    }

    /**
     * position 是否是脚部position
     */
    private fun isFooterPosition(position: Int): Boolean {
        return position >= getHeaderSize() + getOriginalItemSize()
    }


////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 在指定位置添加数据
     */
    fun addItem(index: Int, item: DataItem<*, out RecyclerView.ViewHolder>, notify: Boolean) {
        if (index > 0) {
            mDataSets.add(index, item)
        } else {
            //追加到列表最后面
            mDataSets.add(item)
        }
        if (notify) {
            val notifyPos = if (index > 0) index else mDataSets.size - 1
            notifyItemInserted(notifyPos)
        }
    }

    /**
     * 往现有集合的尾部添加items集合
     */
    fun addItems(items: List<DataItem<*, out RecyclerView.ViewHolder>>, notify: Boolean) {
        val start = mDataSets.size
        for (item in items) {
            mDataSets.add(item)
        }
        if (notify) {
            notifyItemRangeInserted(start, items.size)
        }
    }

    /**
     * 从指定位置上移除item
     */
    fun removeItem(index: Int): DataItem<*, out RecyclerView.ViewHolder>? {
        if (index > 0 && index < mDataSets.size) {
            val item = mDataSets.get(index)
            notifyItemRemoved(index)
            return item
        } else {
            return null
        }
    }

    /**
     * 移除指定item
     */
    fun removeItem(item: DataItem<*, out RecyclerView.ViewHolder>) {
        val index = mDataSets.indexOf(item)
        removeItem(index)
    }

    fun refreshItem(iDataItem: DataItem<*, out RecyclerView.ViewHolder>) {
        val indexOf = mDataSets.indexOf(iDataItem)
        notifyItemChanged(indexOf)
    }

    fun clearItems() {
        mDataSets.clear()
        notifyDataSetChanged()
    }


}