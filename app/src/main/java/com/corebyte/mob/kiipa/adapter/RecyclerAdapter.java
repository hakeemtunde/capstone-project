package com.corebyte.mob.kiipa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.Category;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
        implements AdapterDataLoader<Category> {

    private final Context mContext;
    private List<Category> mCategories;

    public RecyclerAdapter(Context context) {
        mContext = context;
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
        return mCategories == null ? 0 : mCategories.size() ;
    }

    @Override
    public void loadData(List<Category> categories) {
        this.mCategories = categories;
        notifyDataSetChanged();
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

        public void bind(Category category) {
            mCategoryNameTv.setText(category.getName());
            mTotalStockTv.setText("100");

            mEditImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Edit Clicked!", Toast.LENGTH_LONG).show();
                }
            });

            mDeleteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Delete Clicked!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }


}
