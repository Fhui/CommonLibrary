package com.example.huifeng.library.fragment

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.Toast
import com.example.huifeng.library.MainActivity
import com.example.huifeng.library.R
import com.example.huifeng.library.adapter.SelectPicAdapter
import com.example.huifeng.library.core.BaseFragment
import com.example.huifeng.library.utils.LogUtils
import com.example.huifeng.library.utils.PermissionUtils
import com.example.huifeng.library.utils.SpaceItemDecoration
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine

/**
 * 知乎图片选择器
 * Created by ShineF on 2017/8/4 0004.
 */
class SelectPicFragment : BaseFragment(), PermissionUtils.PermissionCallbacks, DialogInterface.OnClickListener {

    val REQUEST_CODE_CHOOSE = 23
    var mSelectBtn: Button? = null
    var mRecycleContent: RecyclerView? = null

    override fun setTitle() {
        (mContext as MainActivity).setTitleText("图片选择器")
    }

    override fun setContentLayout(): Int {
        return R.layout.fragment_select_pic
    }

    override fun init() {
        super.init()
        mSelectBtn = rootView.findViewById(R.id.btn_select_pic) as Button
        mRecycleContent = rootView.findViewById(R.id.rcl_contacts) as RecyclerView
        mSelectBtn!!.setOnClickListener({ PermissionUtils.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1) })
    }


    override fun onClick(dialog: DialogInterface?, which: Int) {
        popFragment()
        Toast.makeText(mContext, "请去设置页面开启权限", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {
        if (requestCode == 1 && perms!!.size == 1) {
            Matisse.from(mContext)
                    .choose(MimeType.allOf())
                    .countable(true)
                    .maxSelectable(100)
                    .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.common_dimen_dp120))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(GlideEngine())
                    .forResult(REQUEST_CODE_CHOOSE)
            Toast.makeText(mContext, "已经获得权限", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        if (requestCode == 1) {
            Toast.makeText(mContext, "拒绝了" + perms!![0] + "权限", Toast.LENGTH_SHORT).show()
            PermissionUtils.permissionDialog(mContext, perms, this)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        LogUtils.showErrLog("askdljasldasl;dasjdasldj;aslkjlk")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == -1) {

        }
    }

    fun setData(dataList : List<Uri>) : Unit {
        val adapter: SelectPicAdapter = SelectPicAdapter(dataList, mContext)
        mRecycleContent!!.layoutManager = GridLayoutManager(mContext, 2)
        mRecycleContent!!.addItemDecoration(SpaceItemDecoration(10, 10))
        mRecycleContent!!.adapter = adapter
    }

}