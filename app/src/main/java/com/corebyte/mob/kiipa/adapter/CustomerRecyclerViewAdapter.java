package com.corebyte.mob.kiipa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.event.AdapterAction;
import com.corebyte.mob.kiipa.event.EventHandler;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.ui.dlg.CreditorsPaymentDlg;
import com.corebyte.mob.kiipa.ui.dlg.DialogPresenter;
import com.corebyte.mob.kiipa.util.AppUtil;

import java.util.List;

public class CustomerRecyclerViewAdapter
        extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder> implements AdapterAction<Customer> {

    private List<Customer> mCustomers;
    private EventHandler mEventHandler;
    private DialogPresenter.ModelDialogCallback<Customer> dialogCallback;

    public CustomerRecyclerViewAdapter(EventHandler handler) {
        mEventHandler = handler;
    }

    public void setDialogCallback(DialogPresenter.ModelDialogCallback callback) { this.dialogCallback = callback; }

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

    @Override
    public void setAdapterData(List<Customer> data) {
        mCustomers = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTv;
        private TextView mPhoneTv;
        private ImageView mEditIv;
        private ImageView mDeleteIv;
        private TextView mCreditTv;
        private ImageView postPayment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTv = itemView.findViewById(R.id.customer_name_tv);
            mPhoneTv = itemView.findViewById(R.id.customer_phone_tv);
            mEditIv = itemView.findViewById(R.id.customer_edit_btn);
            mDeleteIv = itemView.findViewById(R.id.customer_del_btn);
            mCreditTv = itemView.findViewById(R.id.customer_credit_value);
            postPayment = itemView.findViewById(R.id.post_payment_btn);


        }

        public void bind(Customer customer) {
            String ownCredit = AppUtil.formatPriceWithCurrencySymbol(
                    mEventHandler.getContext(), customer.getOwnCredit());

            mNameTv.setText(customer.getName());
            mPhoneTv.setText(customer.getPhone());
            mCreditTv.setText(ownCredit);

            onButtonClick(customer);
        }

        private void onButtonClick(final Customer customer) {
            mEditIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEventHandler.onEditButtonClicked(customer);
                }
            });

            mDeleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEventHandler.delete(customer);

                }
            });

            postPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogCallback.click(customer);
                }
            });
        }

    }

}
