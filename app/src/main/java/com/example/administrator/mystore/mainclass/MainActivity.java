package com.example.administrator.mystore.mainclass;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.administrator.mystore.R;
import com.example.administrator.mystore.mainclass.market.MarketFragment;
import com.example.administrator.mystore.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnmarket;
    Button btnme;
    Button btnmessage;
    Button btncontact;
    ViewPager mviewPager;
    List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initbtn();
        MyAdapter myAdapter = new MyAdapter(this.getSupportFragmentManager(), initdata());
        mviewPager.setAdapter(myAdapter);
    }
    private void initbtn() {
        btnmarket= (Button) this.findViewById(R.id.market);
        btnme= (Button) this.findViewById(R.id.me);
        btncontact= (Button) this.findViewById(R.id.contacts);
        btnmessage= (Button) this.findViewById(R.id.message);
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