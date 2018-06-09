package com.jachat.translateor.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

/**
 * Created by qingfeng on 2018/1/11.
 */

public class ContactUtil {

    public static void addContact(String name, String number, Context context){
        ContentValues contentValues = new ContentValues();
        Uri rawContactUri = context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI,contentValues);
        long rawContactId = ContentUris.parseId(rawContactUri);
        //insert contact name
        if(!TextUtils.isEmpty(name)){
            contentValues.clear();
            contentValues.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId);
            contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            contentValues.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,contentValues);
        }
        //insert contact number
        if(!TextUtils.isEmpty(number)){
            contentValues.clear();
            contentValues.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId);
            contentValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            contentValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER,number);
            contentValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,contentValues);
        }
    }
    public static void deleteContact(String name, Context context){
        try{
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI,new String[]{ContactsContract.Data.CONTACT_ID},"display_name=?",new String[]{name},null);
        if(cursor != null && cursor.moveToFirst()){
            int id = cursor.getInt(0);
            contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI,"display_name=?",new String[]{name});
            contentResolver.delete(ContactsContract.Data.CONTENT_URI,"raw_contact_id=?",new String[]{id+""});
        }
            cursor.close();
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}
