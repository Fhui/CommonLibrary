package com.example.huifeng.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huifeng.library.R;
import com.example.huifeng.library.fragment.SharedElementsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 共享元素Adapter
 * Created by ShineF on 2017/7/28 0028.
 */

public class SharedElementsAdapter extends RecyclerView.Adapter<SharedElementsAdapter.SharedElementsViewHolder> {

    private Context mContext;
    private List<String> mList;
    private OnItemClick mClick;

    public SharedElementsAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setOnItemClick(OnItemClick click){
        this.mClick = click;
    }

    @Override
    public SharedElementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SharedElementsViewHolder(View.inflate(mContext, R.layout.view_shared_elements_adapter, null));
    }

    @Override
    public void onBindViewHolder(SharedElementsViewHolder holder, final int position) {
        holder.mIvPic.setBackgroundResource(R.mipmap.iv_pic);
        holder.mIvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClick != null){
                    mClick.itemClick(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SharedElementsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_pic)
        ImageView mIvPic;

        public SharedElementsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface  OnItemClick{
        void itemClick(View view, int position);
    }
}
