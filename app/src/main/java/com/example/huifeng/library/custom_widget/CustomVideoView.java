package com.example.huifeng.library.custom_widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;


/**
 * 自定义VideoView防止白边
 * Created by ShineF on 2017/8/8 0008.
 */
public class CustomVideoView extends VideoView {
    private int mVideoWidth = 0;
    private int mVideoHeight = 0;

    public CustomVideoView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        // Log.i("@@@@", "onMeasure");

        //下面的代码是让视频的播放的长宽是根据你设置的参数来决定

        int width = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}