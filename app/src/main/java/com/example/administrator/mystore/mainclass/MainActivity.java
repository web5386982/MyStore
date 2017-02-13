package com.example.administrator.mystore.mainclass;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.mystore.R;
import com.example.administrator.mystore.mainclass.market.MarketFragment;
import com.example.administrator.mystore.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    TextView title;
    @BindView(R.id.toolbar)Toolbar bar;
    RadioGroup radioGroup;
    Button btnmarket;
    Button btnme;
    Unbinder bind;
    Button btnmessage;
    Button btncontact;
    ViewPager mviewPager;
    List<Fragment> fragments;
    List<Fragment> initdata;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        initbtn();
        getSupportActionBar().hide();
        fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        initdata = initdata();
        MyAdapter myAdapter = new MyAdapter(this.getSupportFragmentManager(),initdata);
        mviewPager.setAdapter(myAdapter);
        btnmarket.setSelected(true);
        bar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimary));
        initEvent();
    }
    private void initbtn() {
        title= (TextView) this.findViewById(R.id.title_bar);
        bar= (Toolbar) this.findViewById(R.id.toolbar);
        btnmarket= (Button) this.findViewById(R.id.market);
        btnme= (Button) this.findViewById(R.id.me);
        btncontact= (Button) this.findViewById(R.id.contacts);
        btnmessage= (Button) this.findViewById(R.id.message);
        radioGroup= (RadioGroup) this.findViewById(R.id.main_radio_group);
        mviewPager= (ViewPager) this.findViewById(R.id.main_mid_View_pager);
    }
    public List<Fragment> initdata(){
        //ViewPager的List<>中原本只能放数据，由于fragment放回的是视图， 所以也能放fragment对象。
        fragments = new ArrayList<>();
        fragments.add(new MarketFragment());
        fragments.add(new MessageFragment());
        fragments.add(new ContactsFragment());
        fragments.add(new UserFragment());
        return fragments;
    }
    public void initEvent(){
//          ViewPager的监听事件
        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btnmarket.setSelected(true);
                        btnmessage.setSelected(false);
                        btncontact.setSelected(false);
                        btnme.setSelected(false);
                        title.setText("市场");
                        break;
                    case 1:
                        btnmarket.setSelected(false);
                        btnmessage.setSelected(true);
                        btncontact.setSelected(false);
                        btnme.setSelected(false);
                        title.setText("消息");
                        break;
                    case 2:
                        btnmarket.setSelected(false);
                        btnmessage.setSelected(false);
                        btncontact.setSelected(true);
                        btnme.setSelected(false);
                        title.setText("通讯录");
                        break;
                    case 3:
                        btnmarket.setSelected(false);
                        btnmessage.setSelected(false);
                        btncontact.setSelected(false);
                        btnme.setSelected(true);
                        title.setText("我的");
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.market:
                        mviewPager.setCurrentItem(0);
                        title.setText("市场");
                        break;
                    case  R.id.message:
                        mviewPager.setCurrentItem(1);
                        title.setText("消息");
                        break;
                    case  R.id.contacts:
                        mviewPager.setCurrentItem(2);
                        title.setText("通讯录");
                        break;
                    case  R.id.me:
                        mviewPager.setCurrentItem(3);
                        title.setText("我的");
                        break;
                }

            }
        });


    }
/**
     * 按两次返回键退出
     */
    Boolean isFirst;
    @Override
    public void onBackPressed() {

        if(!isFirst){
            super.onBackPressed();
            isFirst=true;
            mviewPager.postDelayed(new Runnable() {//postDelayed这个方法是View的所以随便找个视图就行
                @Override
                public void run() {//如果超过2秒就将改变isFirst的值。不算连续点击。不能退出
                    isFirst=false;
                }
            }, 2000);//postDelayed这个方法是点击返回键2秒（时间自己确定的）之后才执行的

        }else{
            finish();
        }

    }
    /**
     * 解绑butterknife,可以不解绑.主要是为了节省内存
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}

/**
 * 这是一个外部类做的适配器，需要传数据，内部类就不需要传数据了
 */
      class MyAdapter extends FragmentPagerAdapter{
          List<Fragment> fragments;
          public MyAdapter(FragmentManager fm,List<Fragment> fragments) {
              super(fm);
              this.fragments=fragments;
          }

          @Override
          public Fragment getItem(int position) {
              return fragments.get(position);
          }

          @Override
          public int getCount() {
              return fragments!=null?fragments.size():0;
          }
      }