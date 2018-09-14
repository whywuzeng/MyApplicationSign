package com.example.wz1.ec.shop.databasemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2018-09-06.
 * <p>
 * by author wz
 * //重写 DaoMaster 为了 防止数据被删除  升级数据库不会丢失原来的数据
 * <p>
 * com.example.wz1.ec.shop.databasemanager
 */

public class ReleaseDatabaseHelp extends DaoMaster.OpenHelper {
    public ReleaseDatabaseHelp(Context context, String name) {
        super(context, name);
    }

    public ReleaseDatabaseHelp(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }
}
