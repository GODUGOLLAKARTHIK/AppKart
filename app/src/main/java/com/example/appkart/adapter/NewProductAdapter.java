package com.example.appkart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appkart.Models.NewProductModel;
import com.example.appkart.R;

import java.util.List;

public class NewProductAdapter extends RecyclerView.Adapter<NewProductAdapter.ViewHolder> {


    private Context context;
    List<NewProductModel> list;

    public NewProductAdapter(Context context, List<NewProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.newproduct_listitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.newimg);
        String name=list.get(position).getName();
        int price=list.get(position).getPrice();

        holder.tvName.setText(name);
        holder.price.setText(price);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView price;
        ImageView newimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newimg=itemView.findViewById(R.id.newproductIMG);
            tvName=itemView.findViewById(R.id.newproduct_Nametv);
            price=itemView.findViewById(R.id.newproduct_PriceTag);
        }
    }
}
