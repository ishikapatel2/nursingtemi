package com.example.page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView re;
    private MAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        re = findViewById(R.id.re);
        adapter = new MAdapter(R.layout.adapter_item);
        re.setLayoutManager(new GridLayoutManager(this,4));
        re.setAdapter(adapter);
        List<MBean> mList = new ArrayList<>();
        mList.add(new MBean(R.mipmap.m1,R.mipmap.b1,23,"message"));
        mList.add(new MBean(R.mipmap.m2,R.mipmap.b2,12,"delivery"));
        mList.add(new MBean(R.mipmap.m3,R.mipmap.b3,4,"info"));
        mList.add(new MBean(R.mipmap.m4,R.mipmap.b4,36,"tour"));
        adapter.setNewData(mList);
    }
    private class MAdapter extends BaseQuickAdapter<MBean, BaseViewHolder>{

        public MAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, MBean item) {
            Glide.with(mContext).load(item.getIcon()).circleCrop().into((ImageView) helper.getView(R.id.iv_1));
            Glide.with(mContext).load(item.getImg()).centerCrop().into((ImageView) helper.getView(R.id.iv_3));
            helper.setText(R.id.tv_2,item.getName())
                    .setText(R.id.tv_3,item.getLikeNum()+"")
                    .setText(R.id.tv_6,item.getName());
        }
    }
}