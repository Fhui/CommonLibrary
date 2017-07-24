package com.example.huifeng.library.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huifeng.library.MainActivity;
import com.example.huifeng.library.R;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.core.Constant;
import com.example.huifeng.library.net.retrofit.ApiManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit Demo
 * Created by ShineF on 2017/7/3 0003.
 */

public class RetrofitFragment extends BaseFragment {

    @BindView(R.id.btn_get)
    Button mGetBtn;
    @BindView(R.id.btn_post)
    Button mPostBtn;
    @BindView(R.id.tv_load)
    TextView mTvLoad;

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                ApiManager.getApiManager().initApiService(Constant.GANK_BASE).loadHistory(1, 1).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        mTvLoad.setText(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        mTvLoad.setText(t.getMessage());
                    }
                });
                break;
            case R.id.btn_post:
                Map<String, Object> map = new HashMap<>();
                map.put("apikey", Constant.BAIDU_KEY);
                ApiManager.getApiManager().initApiService(Constant.BAIDU_BASE).loadBaiDuAttribute(map).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                        mTvLoad.setText(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        mTvLoad.setText(t.getMessage());
                    }
                });
                break;
        }
    }


    public RetrofitFragment() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.fragment_retrofit;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void setTitle() {
        ((MainActivity) mContext).setmTitleText("Retrifit");
    }

}
