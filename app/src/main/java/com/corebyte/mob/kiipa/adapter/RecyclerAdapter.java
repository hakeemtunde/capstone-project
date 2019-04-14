package com.corebyte.mob.kiipa.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.corebyte.mob.kiipa.CategoryDialog;
import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.StockCategoryActivity;
import com.corebyte.mob.kiipa.event.ProgressBarEvent;
import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.repo.CategoryCrudOperation;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
        implements AdapterDataLoader<Category> {

    private final Context mContext;
    private final ProgressBarEvent mProgressBarEvent;
    private List<Category> mCategories;

    public RecyclerAdapter(Context context, ProgressBarEvent progressBarEvent) {
        mContext = context;
        mProgressBarEvent = progressBarEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.stock_category_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Category category = mCategories.get(position);
        viewHolder.bind(category);
    }

    @Override
    public int getItemCount() {
        return mCategories == null ? 0 : mCategories.size();
    }

    @Override
    public void loadData(List<Category> categories) {
        mProgressBarEvent.loadProgressBar();
        this.mCategories = categories;
        notifyDataSetChanged();
        mProgressBarEvent.unLoadProgressBar();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mCategoryNameTv;
        private TextView mTotalStockTv;
        private ImageView mEditImg;
        private ImageView mDeleteImg;


        public ViewHolder(View view) {
            super(view);
            mCategoryNameTv = view.findViewById(R.id.category_name_tv);
            mTotalStockTv = view.findViewById(R.id.stock_category_total_tv);
            mEditImg = view.findViewById(R.id.stock_category_edit_btn);
            mDeleteImg = view.findViewById(R.id.stock_category_del_btn);

        }

        public void bind(final Category category) {
            mCategoryNameTv.setText(category.getName());
            mTotalStockTv.setText("100");

            mEditImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CategoryDialog categoryDialog = new CategoryDialog();
                    categoryDialog.setAdapter(RecyclerAdapter.this);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(CategoryDialog.CATEGORY_KEY, category);
                    categoryDialog.setArguments(bundle);
                    FragmentManager fragmentManager = ((StockCategoryActivity) mProgressBarEvent)
                            .getSupportFragmentManager();
                    categoryDialog.show(fragmentManager, "EDIT_CATEGORY");

                }
            });

            mDeleteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CategoryCrudOperation categoryCrudOperation = new CategoryCrudOperation(mContext);
                    categoryCrudOperation.delete(category);
                    categoryCrudOperation.loadDataToAdapter(RecyclerAdapter.this);
                    Toast.makeText(mContext, "Deleted", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
