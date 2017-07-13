package com.example.huifeng.library.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.huifeng.library.R;
import com.example.huifeng.library.bean.ContactsBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 联系人Adapter
 * Created by ShineF on 2017/6/20 0020.
 */

public class ContactAdapter extends BaseAdapter {

    private List<ContactsBean> mList;
    private Context mContext;
    private ViewHolder mHolder;
    private Map<String, Integer> mMap = new HashMap<>();

    public ContactAdapter(List<ContactsBean> list, Context context) {
        this.mList = list;
        this.mContext = context;
        initMap();
    }

    private void initMap() {
        for (int i = 0; i < mList.size(); i++) {
            ContactsBean bean = mList.get(i);
            if (!mMap.containsKey(bean.getInitials())) {
                mMap.put(bean.getInitials(), i);
            }
        }
    }

    public void updataAdapter(List<ContactsBean> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.view_contacts_adapter_layout, null);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        ContactsBean contactsBean = mList.get(position);
        mHolder.mTvPersonName.setText(contactsBean.getName());
        mHolder.mTvPersonPhone.setText(contactsBean.getPhone());
        String sortLetter = getAlpha(contactsBean.getInitials());
        String previewStr = (position - 1) >= 0 ? getAlpha(mList.get(position - 1).getInitials()) : " ";
        if (!sortLetter.equals(previewStr)) {
            mHolder.mTvHeadIndex.setVisibility(View.VISIBLE);
            mHolder.mTvHeadIndex.setText(sortLetter);
        } else {
            mHolder.mTvHeadIndex.setVisibility(View.GONE);
        }
        return convertView;
    }

    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式匹配
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase(); // 将小写字母转换为大写
        } else {
            return "#";
        }
    }

    public int getStartPositionOfSection(String section) {
        if (mMap.containsKey(section)) {
            return mMap.get(section);
        }
        return -1;
    }

    class ViewHolder {

        private TextView mTvHeadIndex;
        private TextView mTvPersonName;
        private TextView mTvPersonPhone;

        public ViewHolder(View view) {
            mTvHeadIndex = (TextView) view.findViewById(R.id.tv_head_index);
            mTvPersonName = (TextView) view.findViewById(R.id.tv_person_name);
            mTvPersonPhone = (TextView) view.findViewById(R.id.tv_person_number);
        }
    }
}
