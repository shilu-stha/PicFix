package com.hackathon.picfix.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackathon.picfix.R;
import com.hackathon.picfix.activity.DetailActivity;
import com.hackathon.picfix.data.Data;
import com.hackathon.picfix.utils.Constants;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by root on 7/4/15.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyHolder> {

    Data data;
    String[] value;
    Context context;

    public ContentAdapter(Context context) {
        data = new Data();
        value = data.getData();
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_single_item_layout, parent, false);
        MyHolder myHolder = new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.content.setText(value[position]);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewActvity(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return value.length;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.txt_name)
        TextView content;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    private void startNewActvity(MyHolder holder) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constants.FEATURE, holder.content.getText());
        context.startActivity(intent);
    }
}
