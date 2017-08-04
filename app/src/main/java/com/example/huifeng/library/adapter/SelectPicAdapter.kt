package com.example.huifeng.library.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.huifeng.library.R
import com.example.huifeng.library.utils.BitmapUtils

/**
 *  SelectPicAdapter
 * Created by ShineF on 2017/8/4 0004.
 */
class SelectPicAdapter(private val dataList: List<Uri>, val context: Context) : RecyclerView.Adapter<SelectPicAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.img_pc!!.setImageBitmap(BitmapUtils.decodeUri(context, dataList[position], 200, 100))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(View.inflate(context, R.layout.view_select_pic_adapter, null))
    }

    override fun getItemCount(): Int = dataList.size

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var img_pc: ImageView? = null

        init {
            img_pc = itemView!!.findViewById(R.id.iv_pic) as ImageView
        }

    }

}
