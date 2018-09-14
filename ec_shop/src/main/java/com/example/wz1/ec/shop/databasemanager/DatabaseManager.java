package com.example.wz1.ec.shop.databasemanager;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2018-09-06.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.databasemanager
 */

public class DatabaseManager {

    private DaoSession daoSession;
    private UserProfileDao userProfileDao;

    public UserpassDao getUserpassDao() {
        return userpassDao;
    }

    private UserpassDao userpassDao;

    private final static class DatabaseManagerHolder{
        private final static DatabaseManager DATABASE_MANAGER=new DatabaseManager();
    }

    public static DatabaseManager getInstanse(){
        return DatabaseManagerHolder.DATABASE_MANAGER;
    }

    private DatabaseManager(){
    }

    public void init(Context context)
    {
        initDao(context);
    }

    private void initDao(Context context){
        ReleaseDatabaseHelp help = new ReleaseDatabaseHelp(context, "fast_ec.db");
        final Database writableDb = help.getWritableDb();
        daoSession = new DaoMaster(writableDb).newSession();
        userProfileDao = daoSession.getUserProfileDao();
        userpassDao= daoSession.getUserpassDao();
    }

    public UserProfileDao getUserProfileDao() {
        return userProfileDao;
    }


}
