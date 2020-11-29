package com.example.cmpt385.Database;


import com.example.cmpt385.Model.Friend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import java.util.ArrayList;
import java.util.List;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;




public class Database extends SQLiteAssetHelper {
    //Database name and version
    private static final String DB_NAME = "friend.db";
    private static final int DB_VER=1;

    public Database(Context context) {
        super(context,DB_NAME, null, DB_VER);
    }

    public List<Friend> getFriend(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String array of the columns in the database
        String[] sqlSelect={"ID","Name","Address","Phone","Email"};
        String tableName = "Friends";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect,null,null,null,null,null);
        List<Friend> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Friend friend = new Friend();
                friend.setID(cursor.getInt(cursor.getColumnIndex("ID")));
                friend.setName(cursor.getString(cursor.getColumnIndex("Name")));
                friend.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
                friend.setPhone(cursor.getString(cursor.getColumnIndex("Phone")));
                friend.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                result.add(friend);
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<String> getNames(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String array of the columns in the database
        String[] sqlSelect={"Name"};
        String tableName = "Friends";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{

                result.add(cursor.getString((cursor.getColumnIndex("Name"))));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public List<Friend> getFriendByName(String name){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //String array of the columns in the database
        String[] sqlSelect={"ID","Name","Address","Phone","Email"};
        String tableName = "Friends";

        qb.setTables(tableName);
        //Searches the database where the name is similar to what the user typed in.
        Cursor cursor = qb.query(db, sqlSelect,"Name LIKE ?",new String[] {"%"+name+"%"},null,null,null);
        List<Friend> result = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Friend friend = new Friend();
                friend.setID(cursor.getInt(cursor.getColumnIndex("ID")));
                friend.setName(cursor.getString(cursor.getColumnIndex("Name")));
                friend.setAddress(cursor.getString(cursor.getColumnIndex("Address")));
                friend.setPhone(cursor.getString(cursor.getColumnIndex("Phone")));
                friend.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                result.add(friend);
            }while (cursor.moveToNext());
        }
        return result;
    }
}
