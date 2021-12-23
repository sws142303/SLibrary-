package com.mylibrary.swslibrary;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mylibrary.stools.base.mvvm.base.BaseActivity;
import com.mylibrary.swslibrary.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainVM> {

    private String[] itemsArray = new String[]{"测试Item"};

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.mainVM;
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.setAdapter(new MyAdapter(this, this::onClickEvt));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
        private Context context;
        private OnItemClick onItemClick;

        MyAdapter(Context context, OnItemClick onItemClick) {
            this.context = context;
            this.onItemClick = onItemClick;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_main_adapter, parent, false);
            return new MyHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            String item = itemsArray[position];
            holder.tvContent.setText(item);
            holder.tvContent.setOnClickListener(v -> {
                onItemClick.onClick(position);
            });
        }

        @Override
        public int getItemCount() {
            return itemsArray.length;
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tvContent;

            MyHolder(@NonNull View itemView) {
                super(itemView);
                tvContent = itemView.findViewById(R.id.tvContent);
            }
        }
    }

    public interface OnItemClick {
        void onClick(int position);
    }

    private void onClickEvt(int p) {
        switch (p) {
            case 0:
                Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}