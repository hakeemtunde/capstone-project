package com.corebyte.mob.kiipa.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.event.AdapterAction;
import com.corebyte.mob.kiipa.event.PickCreditCustomerEvent;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.util.AppUtil;

import java.util.List;

public class PickCreditCustomerRecyclerAdapter
        extends RecyclerView.Adapter<PickCreditCustomerRecyclerAdapter.ViewHolder> implements AdapterAction<Customer> {

    private PickCreditCustomerEvent mEventHandler;
    private List<Customer> mCustomers;
    private int mSelectedItem = -1;
    private PickCreditCustomerEvent.OnClickCreditor mOnClickCreditor;
    private double mCartTotalSum;
    private boolean mIsStoreKeeper;

    public PickCreditCustomerRecyclerAdapter(PickCreditCustomerEvent handler, PickCreditCustomerEvent.OnClickCreditor onClickCreditor) {
        mEventHandler = handler;
        mCustomers = handler.fetchAll();
        mOnClickCreditor = onClickCreditor;

        mIsStoreKeeper = AppUtil.getPreferenceSettings(mEventHandler.getContext(),
                AppUtil.APP_MODE, false);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_credit_customer_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Customer customer = mCustomers.get(position);
        viewHolder.bind(customer, position);
    }

    @Override
    public int getItemCount() {
        return mCustomers == null ? 0 : mCustomers.size();
    }

    @Override
    public void appendModel(Customer model) {
        mCustomers.add(model);
    }

    @Override
    public void refreshAdapter() {
        notifyDataSetChanged();
    }

    public void setCartTotalSum(double sum) {
        mCartTotalSum = sum;
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View mItemView;
        private TextView mNameTv;
        private TextView mPhoneTv;
        private TextView mCreditTv;
        private RadioButton mPickRb;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTv = itemView.findViewById(R.id.customer_name_tv);
            mPhoneTv = itemView.findViewById(R.id.customer_phone_tv);
            mCreditTv = itemView.findViewById(R.id.credit_value_tv);
            mPickRb = itemView.findViewById(R.id.pick_credit_customer_rb);
            itemView.setOnClickListener(this);
            mPickRb.setOnClickListener(this);
            mItemView = itemView;

        }

        public void bind(Customer customer, int position) {

            double ownCredit = 0;

            //selling
            if (!mIsStoreKeeper ) {

                ownCredit = customer.getOwnCredit();
                mCreditTv.setText(String.valueOf(ownCredit));

                mCreditTv.setTextColor(ContextCompat.getColor(mEventHandler.getContext(),
                        R.color.colorTextPrimary));
            }


            mNameTv.setText(customer.getName());
            mPhoneTv.setText(customer.getPhone());


            mItemView.setBackgroundColor(ContextCompat
                    .getColor(mEventHandler.getContext(), R.color.cardview_light_background));

            mPickRb.setChecked(position == mSelectedItem);

            if (position == mSelectedItem) {

                mItemView.setBackgroundColor(ContextCompat.getColor(mEventHandler.getContext(),
                        R.color.colorLightGray));

                //selling
                if(!mIsStoreKeeper) {
                    double total = ownCredit + mCartTotalSum;

                    StringBuilder sb = new StringBuilder();
                    sb.append(ownCredit).append(" + ").append(mCartTotalSum).append(" = ")
                            .append(total);

                    String sCreditLimit = AppUtil.getPreferenceSettings(mEventHandler.getContext(),
                            AppUtil.CREDIT_LIMIT, "0");
                    int nCreditLimit = Integer.valueOf(sCreditLimit);

                    //credit limit hit
                    if (total >= nCreditLimit) {
                        sb.append("\nCredit limit hit");
                        mCreditTv.setTextColor(ContextCompat.getColor(mEventHandler.getContext(),
                                R.color.colorRed));
                    } else {
                        mCreditTv.setTextColor(ContextCompat.getColor(mEventHandler.getContext(),
                                R.color.colorAccent));
                    }

                    mCreditTv.setText(sb.toString());
                }

            }

        }

        @Override
        public void onClick(View view) {
            mSelectedItem = getAdapterPosition();
            mOnClickCreditor.onClick(mCustomers.get(mSelectedItem));
            notifyDataSetChanged();

        }
    }
}
