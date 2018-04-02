package com.example.huifeng.library.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huifeng.library.R;
import com.example.huifeng.library.adapter.ContactAdapter;
import com.example.huifeng.library.bean.ContactsBean;
import com.example.huifeng.library.core.BaseFragment;
import com.example.huifeng.library.custom_widget.ClearEditText;
import com.example.huifeng.library.custom_widget.SectionBar;
import com.example.huifeng.library.utils.ContactsUtils;
import com.example.huifeng.library.utils.PermissionUtils;
import com.example.huifeng.library.utils.PinyinUtils;
import com.example.huifeng.library.utils.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *  Base联系人Fragment
 * Created by hui.feng on 2018/1/17.
 */

public class CommonContractsFragment extends BaseFragment implements AbsListView.OnScrollListener,
        View.OnClickListener, TextWatcher, SectionBar.OnTouchLetterChangeListenner,  PermissionUtils.PermissionCallbacks
        , DialogInterface.OnClickListener{

    @BindView(R.id.lv_contacts)
    ListView mLvContacts;
    @BindView(R.id.et_serach)
    ClearEditText mEditText;
    @BindView(R.id.rel_serach)
    RelativeLayout mRlSearch;
    @BindView(R.id.rel_input)
    RelativeLayout mRlInput;
    @BindView(R.id.sb_index)
    SectionBar mSectionBar = null;
    @BindView(R.id.tv_select_letters)
    TextView mTvSelectLetters;
    private final int QUARY_CONTACTS_OK = 1;
    private final int CHANGE_OK = 2;
    private final int SHOWLETTERS = 3;
    private final int HIDELETTERS = 4;
    private List<ContactsBean> mDataList;
    private ContactAdapter mAdapter;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case QUARY_CONTACTS_OK:
                    List<ContactsBean> mTempList = (List<ContactsBean>) msg.obj;
                    mDataList = filledData(mTempList);
                    mAdapter = new ContactAdapter(mDataList, mContext);
                    mLvContacts.setAdapter(mAdapter);
                    mSectionBar.setOnTouchLetterChangeListenner(CommonContractsFragment.this);
                    dismissProgressDialog();
                    break;
                case CHANGE_OK:
                    List<ContactsBean> changeList = (List<ContactsBean>) msg.obj;
                    mAdapter.updataAdapter(changeList);
                    break;
                case SHOWLETTERS:
                    String letters = (String) msg.obj;
                    mTvSelectLetters.setText(letters);
                    if (mTvSelectLetters.getVisibility() != View.VISIBLE) {
                        mTvSelectLetters.setVisibility(View.VISIBLE);
                    }
                    mHandler.removeMessages(HIDELETTERS);
                    mHandler.sendEmptyMessageDelayed(HIDELETTERS, 500);
                    break;
                case HIDELETTERS:
                    mTvSelectLetters.setText("");
                    mTvSelectLetters.setVisibility(View.GONE);
                    break;
            }
            return false;
        }
    });

    @Override
    public void setTitle() {

    }

    public int setContentLayout() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void init() {
        super.init();
        showProgressDialog("正在加载");
        PermissionUtils.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        mLvContacts.setOnScrollListener(this);
        mEditText.addTextChangedListener(this);
        mRlInput.setVisibility(View.GONE);
        mRlSearch.setVisibility(View.VISIBLE);
        mRlSearch.setOnClickListener(this);
    }

    public void initData() {
        ThreadUtils.newThread(new Runnable() {
            @Override
            public void run() {
                List<ContactsBean> mDataList = ContactsUtils.getSystemContacts(mContext);
                Message msg = Message.obtain();
                msg.what = QUARY_CONTACTS_OK;
                msg.obj = mDataList;
                mHandler.sendMessage(msg);
            }
        });
    }

    private List<ContactsBean> filledData(List<ContactsBean> beanList) {
        for (int i = 0; i < beanList.size(); i++) {
            ContactsBean bean = beanList.get(i);
            String phoneNum = beanList.get(i).getPhone().replaceAll(" ", "");
            phoneNum = phoneNum.replaceAll("-", "");
            if (phoneNum.length() == 14) {
                if ("+86".equals(phoneNum.substring(0, 3))) {
                    phoneNum = phoneNum.substring(3, phoneNum.length());
                }
            }
            bean.setPhone(phoneNum);
            String pinyin = PinyinUtils.getPinyin(beanList.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                bean.setInitials(sortString.toUpperCase());
            } else {
                bean.setInitials("#");
            }
        }
        return beanList;
    }

    /**
     * 搜索列表
     */
    private void filterData(String filterStr) {
        List<ContactsBean> filterDateList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = mDataList;
        } else {
            filterDateList.clear();
            for (ContactsBean bean : mDataList) {
                String name = bean.getName();
                String number = bean.getPhone();
                if (name.indexOf(filterStr.toString()) != -1 || PinyinUtils.getPinyin(name).startsWith(filterStr.toString())) {
                    filterDateList.add(bean);
                } else if (number.indexOf(filterStr.toString()) != -1 || number.startsWith(filterStr.toString())) {
                    filterDateList.add(bean);
                }
            }
        }
        Message msg = Message.obtain();
        msg.what = CHANGE_OK;
        msg.obj = filterDateList;
        mHandler.sendMessage(msg);
    }

    public void hideSoftKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public void showSoftKeyBoard(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL && TextUtils.isEmpty(mEditText.getText().toString())) {
            mEditText.clearFocus();
            hideSoftKeyBoard(mEditText);
            mRlSearch.setVisibility(View.VISIBLE);
            mRlInput.setVisibility(View.GONE);
        } else {
            mEditText.clearFocus();
            hideSoftKeyBoard(mEditText);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rel_serach) {
            mRlInput.setVisibility(View.VISIBLE);
            mRlSearch.setVisibility(View.GONE);
            showSoftKeyBoard(mEditText);
            mEditText.requestFocus();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        filterData(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onTouchLetterChange(boolean isTouch, String letter) {
        int pos = mAdapter.getStartPositionOfSection(letter);
        mLvContacts.setSelection(pos);
        Message msg = Message.obtain();
        msg.obj = letter;
        msg.what = SHOWLETTERS;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (requestCode == 1 && perms.size() == 1) {
            initData();
            Toast.makeText(mContext, "已经获得权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == 1) {
            Toast.makeText(mContext, "拒绝了" + perms.get(0) + "权限", Toast.LENGTH_SHORT).show();
            PermissionUtils.permissionDialog(mContext, perms, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        popFragment();
        Toast.makeText(mContext, "请去设置页面开启权限", Toast.LENGTH_SHORT).show();
    }
}
