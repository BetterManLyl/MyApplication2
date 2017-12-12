package com.example.lyl.myapplication.tablayout;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/8.
 */

public class TabTypeActivity extends BaseActivity {
    @BindView(R.id.btn_tab_viewpage_fragment)
    Button btnTabViewpageFragment;
    @BindView(R.id.btn_tab_viewpage_view)
    Button btnTabViewpageView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_type;
    }

    @OnClick({R.id.btn_tab_viewpage_fragment, R.id.btn_tab_viewpage_view})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {

            case R.id.btn_tab_viewpage_fragment:
                intent.putExtra("type", "fragment");
                intent.setClass(this, ListFragmentActivity.class);

                startActivity(intent);
                break;
            case R.id.btn_tab_viewpage_view:
                intent.putExtra("type", "view");
                intent.setClass(this, ListFragmentActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
