package com.corebyte.mob.kiipa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.Customer;

import java.util.List;

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder> {

    private List<Customer> mCustomers;

    public CustomerRecyclerViewAdapter(List<Customer> customerList) {
        mCustomers = customerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_customer_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Customer customer = mCustomers.get(position);
        viewHolder.bind(customer);
    }

    @Override
    public int getItemCount() {
        return mCustomers == null ? 0 : mCustomers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTv;
        private TextView mPhoneTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.customer_name_tv);
            mPhoneTv = itemView.findViewById(R.id.customer_phone_tv);
        }

        public void bind(Customer customer) {
            mNameTv.setText(customer.getName());
            mPhoneTv.setText(customer.getPhone());
        }
    }
}
