package luck.zhaol.com.zhaol;

import android.app.Application;
import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.zhaol.refreshlayout.AlphaLoadFooter;
import com.zhaol.refreshlayout.AlphaRefreshHeader;

/**
 * @author lu.zhao  E-mail:zhaolu@icourt.cc
 * @version 2.0.0
 * @Description
 * @Company Beijing icourt
 * @date createTime：17/10/21
 */

public class BaseApplication extends Application {
    {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.trnas);
                layout.setHeaderHeight(100);
                return new AlphaRefreshHeader(context).setSpinnerStyle(SpinnerStyle.FixedBehind);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.trnas);
                layout.setFooterHeight(100);
                return new AlphaLoadFooter(context).setSpinnerStyle(SpinnerStyle.FixedBehind);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
