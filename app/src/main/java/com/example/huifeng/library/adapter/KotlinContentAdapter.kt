package com.example.huifeng.library.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.huifeng.library.R
import com.example.huifeng.library.bean.AllContentBean
import com.example.huifeng.library.utils.DataUtils

@Suppress("NAME_SHADOWING")
/**
 *  Kotlin Adapter
 * Created by ShineF on 2017/7/26 0026.
 */
class KotlinContentAdapter(var dataList: List<AllContentBean.ResultsBean>, var context: Context) : BaseAdapter() {

    var mListener: OnItemClickListener? = null

    fun setOnItemClick(listener: OnItemClickListener): Unit {
        this.mListener = listener
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val holder: ViewHolder
        val view: View
        if (convertView == null) {
            view = View.inflate(context, R.layout.view_kotlin_adapter, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView!!
            holder = view.tag as ViewHolder
        }
        val data = dataList[position]
        holder.tvContent.text = data.desc
        holder.tvTime.text = DataUtils.dataFormat(data.publishedAt)
        holder.tvType.text = data.type
        holder.rlItem.setOnClickListener { view ->
            mListener!!.onItemClick(view, position, data)
        }
        return view
    }

    override fun getItem(position: Int): Any = dataList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = dataList.size

}

class ViewHolder(val view: View) {
    var tvContent = view.findViewById(R.id.tv_content) as TextView
    var tvTime = view.findViewById(R.id.tv_time) as TextView
    var tvType = view.findViewById(R.id.tv_type) as TextView
    var rlItem = view.findViewById(R.id.rl_item_bg) as RelativeLayout

}

interface OnItemClickListener {
    fun onItemClick(view: View, position: Int, bean : AllContentBean.ResultsBean)
}