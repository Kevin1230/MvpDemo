package com.example.along.mvpdemo.ui.home;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.along.mvpdemo.R;
import com.example.along.mvpdemo.base.BaseMvpFragment;
import com.example.along.mvpdemo.contract.home.HomeContract;
import com.example.along.mvpdemo.model.bean.AreaCodeBean;
import com.example.along.mvpdemo.presenter.home.HomePresenter;
import com.example.along.mvpdemo.utils.LogUtil;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {
    @BindView(R.id.tv_content)
    TextView tv_content;
    
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }
    
    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }
    
    @Override
    protected void initView(View view) {
        LogUtil.e("111111111111111111111111111111111111111111");
    }
    
    @Override
    protected void initEvent() {
    
    }
    
    @Override
    protected void initData() {
        mPresenter.queryAvailableValunInfo(mActivity, "", "cn");
    }
    
    @Override
    protected boolean isMvp() {
        return true;
    }
    
    @Override
    public void onQueryAvailableValueInfoSuccess(List<AreaCodeBean> availableValueInfo) {
        tv_content.setText(availableValueInfo.get(0).getArea() + "    " + availableValueInfo.get(0).getCountry());
    }
    
    @Override
    public void onQueryAvailableValueInfoError(String errMsg) {
        Toast.makeText(mActivity, errMsg, Toast.LENGTH_LONG).show();
    }
}
