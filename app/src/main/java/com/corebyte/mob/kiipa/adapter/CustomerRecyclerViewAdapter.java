package com.corebyte.mob.kiipa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.event.DialogEditEvent;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CustomerCrudOperation;

import java.util.List;

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder> {

    private List<Customer> mCustomers;
    private CustomerCrudOperation mCustomerCrudOperation;
    private DialogEditEvent mDialogEditEvent;

    public CustomerRecyclerViewAdapter(List<Customer> customerList,
                                       CustomerCrudOperation crudOperation,
                                       DialogEditEvent dialogEditEvent) {
        mCustomers = customerList;
        mCustomerCrudOperation = crudOperation;
        mDialogEditEvent = dialogEditEvent;
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

    public void refreshAdapter() {
        mCustomers = mCustomerCrudOperation.getAll();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTv;
        private TextView mPhoneTv;
        private ImageView mEditIv;
        private ImageView mDeleteIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.customer_name_tv);
            mPhoneTv = itemView.findViewById(R.id.customer_phone_tv);
            mEditIv = itemView.findViewById(R.id.customer_edit_btn);
            mDeleteIv = itemView.findViewById(R.id.customer_del_btn);
        }

        public void bind(Customer customer) {
            mNameTv.setText(customer.getName());
            mPhoneTv.setText(customer.getPhone());
            onButtonClick(customer);
        }

        private void onButtonClick(final Customer customer) {
            mEditIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialogEditEvent.onEditButtonClicked(customer);
                }
            });

            mDeleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCustomerCrudOperation.delete(customer);
                    refreshAdapter();
                }
            });
        }

    }

}
