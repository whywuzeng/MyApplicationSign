package com.example.wz1.ec.core.app;

import com.example.wz1.ec.core.utils.storage.PrefrencesUtils;

/**
 * Created by wz on 2018/9/8.
 * 用户的状态
 */

public class AccountManager {
    public enum SignTag{
        SIGN_TAG
    }

    public static void setCheckState(){
        PrefrencesUtils.setAppFlag(SignTag.SIGN_TAG.name(),true);
    }

    public static boolean  getCheckState()
    {
        return PrefrencesUtils.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserCheck iUserCheck){
        if (PrefrencesUtils.getAppFlag(SignTag.SIGN_TAG.name()))
        {
            iUserCheck.onUserSignIn();
        }else{
            iUserCheck.onUserNoSignIn();
        }
    }

}
