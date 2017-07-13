package com.example.huifeng.library.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

/**
 *  Library Bean
 * Created by ShineF on 2017/7/6 0006.
 */

public class LibraryBean implements Parcelable {

    Fragment mFragment;
    String itemName;

    public LibraryBean(Fragment mFragment, String itemName) {
        this.mFragment = mFragment;
        this.itemName = itemName;
    }

    public LibraryBean(){

    }



    public Fragment getmFragment() {
        return mFragment;
    }

    public void setmFragment(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.mFragment, flags);
        dest.writeString(this.itemName);
    }

    protected LibraryBean(Parcel in) {
        this.mFragment = in.readParcelable(Fragment.class.getClassLoader());
        this.itemName = in.readString();
    }

    public static final Parcelable.Creator<LibraryBean> CREATOR = new Parcelable.Creator<LibraryBean>() {
        @Override
        public LibraryBean createFromParcel(Parcel source) {
            return new LibraryBean(source);
        }

        @Override
        public LibraryBean[] newArray(int size) {
            return new LibraryBean[size];
        }
    };
}
