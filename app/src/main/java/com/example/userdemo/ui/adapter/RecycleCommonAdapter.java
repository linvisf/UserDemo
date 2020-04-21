package com.example.userdemo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.userdemo.R;
import com.example.userdemo.bean.GankBean;

import java.util.List;

public class RecycleCommonAdapter extends RecyclerView.Adapter<RecycleCommonAdapter.MyViewHolder>{

    private List<GankBean> mList;

    public RecycleCommonAdapter(List<GankBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GankBean gankBean = mList.get(position);
        if (gankBean != null) {
            holder.tv_desc.setText(gankBean.getDesc());
            holder.tv_category.setText(gankBean.getAuthor() + " * " + gankBean.getType());
            holder.tv_date.setText(gankBean.getPublishedAt());
            if (gankBean.getImages().size() > 0) {
                holder.iv_image.setVisibility(View.VISIBLE);
                Glide.with(holder.iv_image.getContext()).load(gankBean.getImages().get(0)).into(holder.iv_image);
            } else {
                holder.iv_image.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_desc;
        private TextView tv_category;
        private TextView tv_date;
        private ImageView iv_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_category = itemView.findViewById(R.id.tv_category);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_image = itemView.findViewById(R.id.iv_image);
        }
    }
}
