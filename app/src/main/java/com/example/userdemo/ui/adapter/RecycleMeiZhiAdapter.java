package com.example.userdemo.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.userdemo.R;
import com.example.userdemo.bean.GankBean;

import java.util.List;

public class RecycleMeiZhiAdapter extends RecyclerView.Adapter<RecycleMeiZhiAdapter.MyViewHolder> {

    private Context context;
    private List<GankBean> mList;

    public RecycleMeiZhiAdapter(Context context, List<GankBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fuli,parent,false), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.e("RecycleMeiZhiAdapter", "================"+mList.get(position).getUrl());
        Glide.with(context)
                .load(mList.get(position).getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Drawable> target, boolean b) {
                        Log.e("RecycleMeiZhiAdapter", "失败============"+e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable drawable, Object o, Target<Drawable> target, DataSource dataSource, boolean b) {
                        Log.e("RecycleMeiZhiAdapter", "成功========="+dataSource.name());
                        return false;
                    }
                })
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(View itemView, int type){
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }
}
