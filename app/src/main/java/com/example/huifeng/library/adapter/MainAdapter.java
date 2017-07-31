package com.example.huifeng.library.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.huifeng.library.bean.LibraryBean;

import java.util.List;

import static com.example.huifeng.library.R.*;

/**
 * MainActivity Adapter
 * Created by ShineF on 2017/7/3 0003.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LibraryBean> mList;
    private Context mContext;
    private final int TITLE_ITEM = 1;
    private final int CONTENT_ITEM = 2;
    private ItemClickListener mListener;

    public MainAdapter(List<LibraryBean> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TITLE_ITEM:
                holder = new MainTitleHolder(View.inflate(mContext, layout.view_main_item_title_layout, null));
                break;
            case CONTENT_ITEM:
                holder = new MainHolder(View.inflate(mContext, layout.view_main_item_layout, null));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final LibraryBean libraryBean = mList.get(position);
        switch (getItemViewType(position)) {
            case TITLE_ITEM:
                MainTitleHolder titleHolder = (MainTitleHolder) holder;
                titleHolder.mTvItem.setText(libraryBean.getItemName());
                break;
            case CONTENT_ITEM:
                final MainHolder mainHolder = (MainHolder) holder;
                mainHolder.mTvItem.setText(libraryBean.getItemName());
                mainHolder.mTvItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.clickItem(position, mainHolder.mTvItem, libraryBean);
                        }
                    }
                });
                ViewCompat.setTransitionName(mainHolder.mTvItem, String.valueOf(position) + "_image");
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String s = mList.get(position).getItemName();
        if (s.startsWith("About")) {
            return TITLE_ITEM;
        } else {
            return CONTENT_ITEM;
        }
    }

    public interface ItemClickListener {
        void clickItem(int position, View view, LibraryBean bean);
    }

    private class MainHolder extends RecyclerView.ViewHolder {

        TextView mTvItem;

        MainHolder(View itemView) {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(id.tv_item);
        }
    }

    private class MainTitleHolder extends RecyclerView.ViewHolder {

        TextView mTvItem;

        MainTitleHolder(View itemView) {
            super(itemView);
            mTvItem = (TextView) itemView.findViewById(id.tv_item);
        }
    }
}
