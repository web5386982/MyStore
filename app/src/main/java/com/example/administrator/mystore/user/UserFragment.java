package com.example.administrator.mystore.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mystore.R;
import com.example.administrator.mystore.mainclass.MainActivity;
import com.example.administrator.mystore.network.EasyClient;
import com.mikhaellopez.circularimageview.CircularImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/7.
 */

public class UserFragment extends Fragment {
    MainActivity activity;
    TextView registerOrLogin;
    CircularImageView photo;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, null);
        registerOrLogin= (TextView) view.findViewById(R.id.register_login);
        photo= (CircularImageView) view.findViewById(R.id.user_photo);
        ButterKnife.bind(getContext(),view);
         activity = (MainActivity) getActivity();
        initClick();
        return view;
    }

    private void initClick() {
        registerOrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retisterOrLogin();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retisterOrLogin();
            }
        });
    }

    public void retisterOrLogin(){
        Toast.makeText(activity, "可以点击", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }

}
