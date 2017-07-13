package com.example.huifeng.library.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  联系人Bean
 * Created by ShineF on 2017/6/20 0020.
 */

public class ContactsBean implements Parcelable {

    private String name;
    private String phone;
    private String initials;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.initials);
    }

    public ContactsBean() {
    }

    protected ContactsBean(Parcel in) {
        this.name = in.readString();
        this.phone = in.readString();
        this.initials = in.readString();
    }

    public static final Creator<ContactsBean> CREATOR = new Creator<ContactsBean>() {
        @Override
        public ContactsBean createFromParcel(Parcel source) {
            return new ContactsBean(source);
        }

        @Override
        public ContactsBean[] newArray(int size) {
            return new ContactsBean[size];
        }
    };
}
