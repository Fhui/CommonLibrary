package com.example.huifeng.library.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.huifeng.library.R
import java.util.ArrayList

@Suppress("UNREACHABLE_CODE")
/**
 *  Kotlin Adapter
 * Created by ShineF on 2017/7/26 0026.
 */
class KotlinContentAdapter(var dataList: ArrayList<String>, var context: Context) : BaseAdapter() {

    var  mHolder : ViewHolder? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.view_kotlin_adapter, null)
        }
        else {
            mHolder = convertView.getTag() as ViewHolder?
        }
        mHolder?.tvContent?.text = dataList[position]
        return convertView
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return position.toLong()
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return dataList.size
    }

    class ViewHolder(val view: View) {
        var mView: View? = null
        var tvContent: TextView? = null

        init {
            mView = this.view
            tvContent = (mView as View).findViewById(R.id.tv_content) as TextView?
        }
    }

}