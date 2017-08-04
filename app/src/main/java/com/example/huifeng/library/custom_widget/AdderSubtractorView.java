package com.example.huifeng.library.custom_widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.example.huifeng.library.R;

import java.math.BigDecimal;


/**
 * 数字加减器
 * Created by HIMan on 2017/6/13 0013.
 */

public class AdderSubtractorView extends RelativeLayout implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    private Context mContext = null;

    private RelativeLayout mAdderRl = null; //加号

    private RelativeLayout mSubtractorRl = null; //减号

    private EditText mResult = null; //结果集

    private int mLeastValue = 0; //最小值

    private int mMaximumValue = 0; //最大值

    private double mDLeastValue = 0.0;

    private double mDMaximumValue = 0.0;

    private double mDNumber = 0.0;

    private int mStyle = 1; //样式

    private final int BORDERED = 1; //有框

    private final int NO_BORDER = 2; //无框

    private INumberListener mIListener = null; //针对结果集的监听(整数)


    public int mNumber = 0; //当前的数字

    private boolean isLongClick = false; //是否长按

    private Handler mHandler = new Handler();

    private static final int DELAY_TIME = 160; //线程运行时间

    private int mICount = 1;

    private int mDataType = 100;

    private boolean mWhether = false;

    public AdderSubtractorView(Context context) {
        this(context, null);
    }

    public AdderSubtractorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdderSubtractorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public void init() {
        View view;
        if (mStyle == NO_BORDER) {
            view = View.inflate(mContext, R.layout.view_adder_subtractor_no_border_layout, null);
        } else {
            view = View.inflate(mContext, R.layout.view_adder_subtractor_bordered_layout, null);
        }
        mAdderRl = (RelativeLayout) view.findViewById(R.id.rl_add);
        mSubtractorRl = (RelativeLayout) view.findViewById(R.id.rl_subtract);
        mResult = (EditText) view.findViewById(R.id.et_input_number);
        mResult.addTextChangedListener(new MyWatcher());
        initListener(mAdderRl, mSubtractorRl);
        mResult.setOnClickListener(this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(view, params);
    }

    public void initListener(RelativeLayout... rlv) {
        for (RelativeLayout relativeLayout : rlv) {
            relativeLayout.setOnClickListener(this);
            relativeLayout.setOnLongClickListener(this);
            relativeLayout.setOnTouchListener(this);
        }
    }

    /***
     * 针对边界监听
     * @param listener 监听
     */
    public void setIntNumberListener(INumberListener listener) {
        this.mIListener = listener;
    }

    /**
     * 设置默认值
     *
     * @param number 默认值
     */
    public void setDefaultValue(int number) {
        setNumber(number);
    }


    /**
     * 设置最小值
     *
     * @param leastValue 最小值
     */
    public void setLeastValue(int leastValue) {
        this.mLeastValue = leastValue;
    }

    public void setDLeastValue(double leastValue) {
        this.mDLeastValue = leastValue;
    }

    /**
     * 设置最大值
     *
     * @param maximumValue 最大值
     */
    public void setMaximumValue(int maximumValue) {
        this.mMaximumValue = maximumValue;
    }

    public void setDMaximumValue(double maximumValue) {
        this.mDMaximumValue = maximumValue;
    }

    /**
     * 设置样式
     *
     * @param style 样式
     */
    public void setStyle(int style) {
        this.mStyle = style;
        init();
    }

    /**
     * 获得当前结果集
     *
     * @return 返回当前结果集
     */
    public int getInputNumber() {
        return mNumber;
    }

    public void setWhetherToEnter(Boolean whether) {
        this.mWhether = whether;
        if (whether) {
            mResult.setFocusable(true);
            mResult.setEnabled(true);
        } else {
            mResult.setFocusable(false);
            mResult.setEnabled(false);
        }
    }


    @Override
    public void onClick(View v) {
        int iNum = getIntNumber();
        switch (v.getId()) {
            case R.id.rl_add:
                iNum += 1;
                break;
            case R.id.rl_subtract:
                iNum -= 1;
                break;
        }
        setNumber(iNum);
    }

    /**
     * double 直接相加会丢失精度
     *
     * @param d1 加数
     * @param d2 加数
     * @return 和
     */
    public double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * double 直接相减会丢失精度
     *
     * @param d1 减数
     * @param d2 被减数
     * @return 差
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public int getIntNumber() {
        String strNum = mResult.getText().toString().trim();
        if (TextUtils.isEmpty(strNum)) {
            strNum = String.valueOf(mLeastValue);
        }
        return Integer.parseInt(strNum);
    }


    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            setNumber(getIntNumber() + mICount);
            mHandler.postDelayed(mRunnable, DELAY_TIME);
        }
    };

    public void setNumber(int number) {
        if (number > mMaximumValue) {
            number = mMaximumValue;
            if (null != mIListener) {
                mIListener.setMoreListener(number, mAdderRl);
            }
        } else if (number < mLeastValue) {
            number = mLeastValue;
            if (null != mIListener) {
                mIListener.setLeastListener(number, mSubtractorRl);
            }
        }
        mResult.setText(String.valueOf(number));
        mResult.setSelection(mResult.getText().length());
    }


    @Override
    public boolean onLongClick(View v) {
        isLongClick = true;
        mHandler.postDelayed(mRunnable, DELAY_TIME);
        return isLongClick;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int i = v.getId();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (i == R.id.rl_add) {
                    mICount = Math.abs(mICount);
                } else if (i == R.id.rl_subtract) {
                    mICount = -1 * Math.abs(mICount);
                }
                break;
            case MotionEvent.ACTION_UP: //当手放开是移除所有线程运行
                isLongClick = false;
                mHandler.removeCallbacks(mRunnable);
                break;
        }
        return false;
    }

    private class MyWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s)) {
                int intIntputNum = getIntNumber();
                if (intIntputNum > mMaximumValue) {
                    mResult.setText(String.valueOf(mMaximumValue));
                    mNumber = mMaximumValue;
                    if (null != mIListener) {
                        mIListener.setMoreListener(mMaximumValue, mAdderRl);
                    }
                }
                if (getIntNumber() < mLeastValue) {
                    mNumber = mLeastValue;
                    if (null != mIListener) {
                        mIListener.setLeastListener(mLeastValue, mAdderRl);
                    }
                }
                mResult.setSelection(mResult.getText().toString().length());
            }
        }
    }


    public interface INumberListener {

        void setMoreListener(int number, View view);

        void setLeastListener(int number, View view);
    }
}
