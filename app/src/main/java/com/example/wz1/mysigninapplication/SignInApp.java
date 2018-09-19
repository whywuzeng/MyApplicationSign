package com.example.wz1.mysigninapplication;

import android.app.Application;

import com.example.wz1.ec.core.app.ECApp;
import com.example.wz1.ec.core.interceptor.DebugInterceptor;
import com.example.wz1.ec.shop.databasemanager.DatabaseManager;
import com.example.wz1.mysigninapplication.font.IconTianModule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Created by Administrator on 2018-08-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication
 */

public class SignInApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initStackView();
        initStetho();
        initToast();

        DatabaseManager.getInstanse().init(this);

        ECApp.init(this).configureApiHost("http://127.0.0.0/")
                .withInterceptor(new DebugInterceptor("http://127.0.0.0",R.raw.test))
                .withIcon(new FontAwesomeModule())
                .withIcon(new IconTianModule())
                .withIcon(new IoniconsModule())
                .configure();

        //一个configure class 配置类 集成各种 配置信息

        //appec class 类  初始化
    }

    private void initToast() {
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    private void initStackView() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }
}
