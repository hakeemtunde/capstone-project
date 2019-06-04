package com.corebyte.mob.kiipa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.event.StockEvent;
import com.corebyte.mob.kiipa.model.Stock;

import java.util.List;

public class StockRecyclerAdapter extends RecyclerView.Adapter<StockRecyclerAdapter.ViewHolder> {

    private final StockEvent mStockEvent;
    private List<Stock> mStockList;

    public StockRecyclerAdapter(StockEvent event) {
        mStockEvent = event;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.stock_list, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Stock stock = mStockList.get(i);
        viewHolder.bind(stock);
    }

    public void setDataAndFresh(List<Stock> stocks) {
        mStockList = stocks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mStockList == null? 0 : mStockList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView stockIv;
        private TextView nameTv;
        //        private TextView priceTv;
//        private TextView quantityTv;
        private ImageView editIv;
        private ImageView addToCartIv;

        public ViewHolder(View view) {
            super(view);

            stockIv = view.findViewById(R.id.stock_iv);
            nameTv = view.findViewById(R.id.stock_name_tv);
//            priceTv = view.findViewById(R.id.stock_price_tv);
//            quantityTv = view.findViewById(R.id.stock_qty_tv);
            editIv = view.findViewById(R.id.stock_edit_btn);
            addToCartIv = view.findViewById(R.id.stock_cart_btn);
        }

        public void bind(final Stock stock) {
            nameTv.setText(stock.getName().toUpperCase());
            stockIv.setImageResource(R.drawable.ic_image_black_24dp);
            editIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mStockEvent.onClick(stock);
                }
            });

            addToCartIv.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    mStockEvent.onCartClick(stock);
                }
            });

        }


    }
}
