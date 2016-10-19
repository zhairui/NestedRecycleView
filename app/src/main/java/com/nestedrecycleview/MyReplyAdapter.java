package com.nestedrecycleview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 10769 on 2016/10/19.
 */

public class MyReplyAdapter extends RecyclerView.Adapter<MyReplyAdapter.ViewHolder>{
    private Activity context;
    public ArrayList<String> mlist=new ArrayList<>();
    public MyReplyAdapter(Activity context, ArrayList<String> wikiInfos){
        this.context=context;
        this.mlist=wikiInfos;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.txt_item);
        }
        public void setData(int position){
            textView.setText(mlist.get(position));
        }
    }
}
