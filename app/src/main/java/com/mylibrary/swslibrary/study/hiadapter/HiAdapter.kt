package com.mylibrary.swslibrary.study.hiadapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType

/**
 *@author Sws
 *@Time 2021/12/9 22:29
 *@msg
 **/
class HiAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mInflater: LayoutInflater? = null
    private var dataSets = ArrayList<HiDateItem<*, RecyclerView.ViewHolder>>()
    private var typeArray = SparseArray<HiDateItem<*, RecyclerView.ViewHolder>>()
    private val mContext: Context = context

    init {
        this.mInflater = LayoutInflater.from(mContext)
    }

    fun addItem(index: Int, item: HiDateItem<*, RecyclerView.ViewHolder>, notify: Boolean) {
        if (index > 0) {
            dataSets.add(index, item)
        } else {
            dataSets.add(item)
        }

        val notifyPos = if (index > 0) index else dataSets.size - 1
        if (notify) {
            notifyItemInserted(notifyPos)
        }
    }

    fun initTest(date : HiDateItem<*, RecyclerView.ViewHolder>){

    }

    fun addItems(items: List<HiDateItem<*, RecyclerView.ViewHolder>>, notify: Boolean) {
        val start = dataSets.size
        for (item in items) {
            dataSets.add(item)
        }

        if (notify) {
            notifyItemRangeInserted(start, items.size)
        }
    }

    fun removeItem(index: Int): HiDateItem<*, RecyclerView.ViewHolder>? {
        if (index > 0 && index < dataSets.size) {
            val remove = dataSets.removeAt(index)
            notifyItemRemoved(index)
            return remove
        } else {
            return null
        }
    }

    fun removeItem(item: HiDateItem<*, *>) {
        if (item != null) {
            val index = dataSets.indexOf(item)
            removeItem(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dateItem = typeArray.get(viewType)
        var view: View? = dateItem.getItemView(parent)
        if (view == null) {
            val layoutRes = dateItem.getItemLayoutRes()
            if (layoutRes < 0) {
                throw RuntimeException("dateItem:" + dateItem.javaClass.name + "must override getItemView or getItemLayoutRes")
            }
            view = mInflater!!.inflate(layoutRes, parent, false)
        }
        return createViewHolderInternal(dateItem.javaClass, view)
    }

    private fun createViewHolderInternal(
            javaClass: Class<HiDateItem<*, RecyclerView.ViewHolder>>,
            view: View?
    ): RecyclerView.ViewHolder {
        //得到HiDateItem实例对象
        val superClass = javaClass.genericSuperclass
        //判断是否为参数类型的对象
        if (superClass is ParameterizedType) {
            //得到泛型集合
            val arguments = superClass.actualTypeArguments
            //遍历泛型集合
            for (argument in arguments) {
                //满足条件就得到了HiDateItem中的ViewHolder泛型对象
                if (argument is Class<*> && RecyclerView.ViewHolder::class.java.isAssignableFrom(
                        argument
                    )
                ) {
                    //通过反射来得到他的实例
                    //首先得到他的构造函数 viewHolder的构造函数需要传入一个view 再通过newInstance来得到他的实例
                    return argument.getConstructor(View::class.java)
                        .newInstance(view) as RecyclerView.ViewHolder
                }
            }
        }
        // 如果构建失败 则返回默认的viewHolder对象 防止返回null导致奔溃
        return object : RecyclerView.ViewHolder(view!!) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hiDateItem = dataSets.get(position)
        hiDateItem.onBindData(holder, position)
    }

    /**
     * RecyclerView与Adapter相关联的时候会触发onAttachedToRecyclerView这个方法
     * 可以在这个方法中对多列的item做宽度适配
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val spanCount = layoutManager.spanCount
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position < dataSets.size) {
                        val hiDateItem = dataSets.get(position)
                        val spanSize = hiDateItem.getSpanSize()
                        return if (spanSize <= 0) spanCount else spanSize
                    }
                    return spanCount
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val dateItem = dataSets.get(position)
        val type = dateItem.javaClass.hashCode()
        //如果没有包含这种类型的Item 则添加进来
        if (typeArray.indexOfKey(type) < 0) {
            typeArray.put(type, dateItem)

        }
        return type

    }

    override fun getItemCount(): Int {
        return dataSets.size
    }

    fun refreshItem(hiDateItem: HiDateItem<*, *>) {
        val indexOf = dataSets.indexOf(hiDateItem)
        notifyItemChanged(indexOf)
    }
}