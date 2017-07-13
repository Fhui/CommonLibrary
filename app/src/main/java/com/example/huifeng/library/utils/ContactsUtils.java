package com.example.huifeng.library.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;


import com.example.huifeng.library.bean.ContactsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录工具类
 * Created by ShineF on 2017/6/20 0020.
 */

public class ContactsUtils {

    public static List<ContactsBean> getSystemContacts(Context context) {
        List<ContactsBean> list = new ArrayList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(Uri.parse("content://com.android.contacts/contacts"), null, null, null, "sort_key COLLATE LOCALIZED asc");
        if (cursor.moveToNext()) {
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int displayNameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            do {
                String contactId = cursor.getString(idColumn);
                String displayName = cursor.getString(displayNameColumn);
                int phoneCount = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (phoneCount > 0) {
                    Cursor phoneCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, "sort_key COLLATE LOCALIZED asc");

                    if (phoneCursor != null && phoneCursor.moveToNext()) {
                        do {
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            ContactsBean contactsBean = new ContactsBean();
                            if (TextUtils.isEmpty(displayName)) {
                                contactsBean.setName(phoneNumber);
                            } else {
                                contactsBean.setName(displayName);
                            }
                            contactsBean.setPhone(phoneNumber);
                            list.add(contactsBean);
                        } while (phoneCursor.moveToNext());
                    }
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

}
