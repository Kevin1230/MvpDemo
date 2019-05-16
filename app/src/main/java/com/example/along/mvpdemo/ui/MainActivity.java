package com.example.along.mvpdemo.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.along.mvpdemo.R;
import com.example.along.mvpdemo.base.BaseMvpActivity;
import com.example.along.mvpdemo.base.BaseMvpFragment;
import com.example.along.mvpdemo.contract.MainContract;
import com.example.along.mvpdemo.model.bean.LanguageBean;
import com.example.along.mvpdemo.presenter.MainPresenter;
import com.example.along.mvpdemo.ui.home.HomeFragment;
import com.example.along.mvpdemo.ui.home.MallFragment;
import com.example.along.mvpdemo.ui.home.MarketFragment;
import com.example.along.mvpdemo.ui.home.MineFragment;
import com.example.along.mvpdemo.ui.home.TradeFragment;
import com.example.along.mvpdemo.widget.NoRollViewpager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements RadioGroup.OnCheckedChangeListener,MainContract.View {

    @BindView(R.id.view_pager)
    NoRollViewpager viewPager;
    @BindView(R.id.rg_main)
    RadioGroup rg_main;

    private ArrayList<BaseMvpFragment> mFragments = new ArrayList<>();
    private int currentPageId = 0;
    private HomeFragment homeFragment;
    private TradeFragment tradeFragment;
    private MarketFragment marketFragment;
    private MallFragment mallFragment;
    private MineFragment mineFragment;
    private BaseMvpFragment basefragment;//当前页面活动的fragment
    private int rb_id = 0;//radioButton的按键id
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();
    }

    @Override
    protected void initEvent() {
        rg_main.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.onGetLanguage(mContext,"cn");
    }

    @Override
    protected boolean isMvp() {
        return true;
    }

    //初始化Fragment
    private void initFragment() {
        homeFragment = HomeFragment.newInstance();//首页
        tradeFragment = TradeFragment.newInstance();//交易
        marketFragment = MarketFragment.newInstance();//自选
        mallFragment = MallFragment.newInstance(); //理财
        mineFragment = MineFragment.newInstance();//我的

        mFragments.clear();
        mFragments.add(homeFragment);
        mFragments.add(tradeFragment);
        mFragments.add(marketFragment);
        mFragments.add(mallFragment);
        mFragments.add(mineFragment);


        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(0,false);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_common_home://首页
                currentPageId = 0;
                setChecked(currentPageId);
                break;
            case R.id.rb_trade://交易
                currentPageId = 1;
                setChecked(currentPageId);
                break;
            case R.id.rb_optional://自选
                currentPageId = 2;
                setChecked(currentPageId);
                break;
            case R.id.rb_financial://理财
                currentPageId = 3;
                setChecked(currentPageId);
                break;
            case R.id.rb_my://我的
                currentPageId = 4;
               setChecked(currentPageId);
                break;
        }
    }

    private void setChecked(int page) {
        switch (page) {
            case 0:
                rb_id = R.id.rb_common_home;
                break;
            case 1:
                rb_id = R.id.rb_trade;
                break;
            case 2:
                rb_id = R.id.rb_optional;
                break;
            case 3:
                rb_id = R.id.rb_financial;
                break;
            case 4:
                rb_id = R.id.rb_my;
                break;
            default:
                rb_id = R.id.rb_common_home;
                break;
        }
        viewPager.setCurrentItem(currentPageId,false);
    }

    @Override
    public void onGetLanguageSuccess(List<LanguageBean> languageBeanList) {
        Toast.makeText(mContext,"数据获取成功:  "+languageBeanList.get(0).getLname(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetLanguageFail(String errMsg) {
        Toast.makeText(mContext,errMsg,Toast.LENGTH_LONG).show();
    }
}
