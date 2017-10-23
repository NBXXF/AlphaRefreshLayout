package luck.zhaol.com.zhaol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhaol.refreshlayout.AlphaLoadFooter;
import com.zhaol.refreshlayout.AlphaRefreshHeader;
import com.zhaol.refreshlayout.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SmartRefreshLayout smartRefreshLayout;
    EmptyRecyclerView recyclerView;
    AlphaRefreshHeader headerView;
    AlphaLoadFooter footerView;

    private class Model {
        String name;
    }

    private BaseRecyclerAdapter<Model> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.SmartRefreshLayout);
        recyclerView = (EmptyRecyclerView) findViewById(R.id.EmptyRecyclerView);

        smartRefreshLayout.setEnableAutoLoadmore(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter = new BaseRecyclerAdapter<Model>(loadModels(), R.layout.listitem_practive_repast) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, Model model, int position) {
                holder.text(R.id.name, model.name);
            }
        });
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        smartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mAdapter.loadmore(loadModels1());
                smartRefreshLayout.finishLoadmore();
                if (mAdapter.getCount() >= 100) {
                    Toast.makeText(getBaseContext(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                    smartRefreshLayout.setEnableLoadmore(false);
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mAdapter.refresh(refresh());
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.setEnableLoadmore(true);
            }
        });
        smartRefreshLayout.autoRefresh();
    }



    /**
     * 模拟数据
     */
    private Collection<Model> loadModels() {

        List<Model> models = new ArrayList<>();
        return models;
    }
    /**
     * 模拟数据
     */
    private Collection<Model> refresh() {

        List<Model> models = new ArrayList<>();
//        for (int i = 1; i <= 20; i++) {
//            Model m = new Model();
//            m.name = "icourt_0" + i;
//            models.add(m);
//        }
        return models;
    }
    /**
     * 模拟数据
     */
    private Collection<Model> loadModels1() {

        List<Model> models = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Model m = new Model();
            m.name = "加载 ：icourt_0" + i;
            models.add(m);
        }
        return models;
    }
}
