package com.example.wz1.mysigninapplication;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2018-08-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication
 */

public class MainService extends AccessibilityService {
    private static final String TAG = "MainService";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo node=getRootInActiveWindow();
        try {
            if (node == null || !Comm.launcher_PakeName.equals(node.getPackageName().toString())) {
                throw new Exception("程序不在初始化启动器页面,抛出异常");
            }

            gotoYUNZHIJIA();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void gotoYUNZHIJIA() throws Exception {
        AccessibilityNodeInfo node=getRootInActiveWindow();
        int m = 10;
        while (m > 0) {
            Log.e(TAG, "onAccessibilityEvent: "+node );
            if (node != null && Comm.dingding_PakeName.equals(node.getPackageName().toString())) {
                node = getRootInActiveWindow(); //刷新根页面节点
                Log.e(TAG, "onAccessibilityEvent: "+node );
                break;
            } else {
                startApplication(getApplicationContext(), Comm.dingding_PakeName);
            }
            sleepT(1000);  //1秒钟启动一次
            if (node != null) {
//                node = refshPage();
            }
            m--;
        }
        if (m <= 0) {
            throw new Exception("进入钉钉主页异常");
        }
    }
    //启动应用
    private void startApplication(Context applicationContext, String dingding_pakeName) {
        try {
            PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(dingding_pakeName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.setPackage(packageInfo.packageName);
            PackageManager manager = applicationContext.getPackageManager();
            List<ResolveInfo> apps = manager.queryIntentActivities(resolveIntent, 0);

            ResolveInfo ri =(ResolveInfo) apps.iterator().next();
            if (ri!=null)
            {
                dingding_pakeName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName cn = new ComponentName(dingding_pakeName, className);
                intent.setComponent(cn);
                startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(applicationContext,"此应用未安装",Toast.LENGTH_SHORT);
        }

    }

    //刷新页面返回root
    private AccessibilityNodeInfo refshPage() {

        return null;
    }

    private void sleepT(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    private void openAccessSettingOn(){
        if (!isAccessibilitySettingsOn(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "请开启辅助服务", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        }
    }


    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        // TestService为对应的服务
        final String service = getPackageName() + "/" + MainService.class.getCanonicalName();
        // com.z.buildingaccessibilityservices/android.accessibilityservice.AccessibilityService
        try {
            accessibilityEnabled = Settings.Secure.getInt(mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString(mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    if (accessibilityService.equalsIgnoreCase(service)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }
}
