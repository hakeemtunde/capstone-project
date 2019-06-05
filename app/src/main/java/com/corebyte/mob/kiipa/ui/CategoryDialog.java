package com.corebyte.mob.kiipa.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.AdapterDataLoader;
import com.corebyte.mob.kiipa.event.StockDialogAction;
import com.corebyte.mob.kiipa.event.StockDialogAction.StockDialogActionImp;
import com.corebyte.mob.kiipa.model.Category;

public class CategoryDialog extends DialogFragment {

    public static final String CATEGORY_KEY = "category";
    private StockDialogAction dialogAction;
    private Category mCategory;
    private String mDlgTitle;
    private String mPositiveButtonStr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDlgTitle = getString(R.string.categoryDlgTitleAdd);
        mPositiveButtonStr = getString(R.string.ok_string);

        dialogAction = new StockDialogActionImp(getContext());

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(CATEGORY_KEY)) {
            mCategory = (Category) bundle.get(CATEGORY_KEY);
            mDlgTitle = getString(R.string.categoryDlgTitleUpdate);
            mPositiveButtonStr = getString(R.string.update_string);
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        dialogBuilder.setTitle(mDlgTitle);

        final View view = inflater.inflate(R.layout.dialog_addstock, null);
        final EditText editText = view.findViewById(R.id.add_category_name_et);

        if (mCategory != null) {
            editText.setText(mCategory.getName());
        }

        dialogBuilder.setView(view);
        dialogBuilder.setPositiveButton(mPositiveButtonStr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String catname = editText.getText().toString();

                if (mCategory == null) {
                    dialogAction.create(catname);
                } else {

                    if (catname.equalsIgnoreCase(mCategory.getName())) return;
                    mCategory.setName(catname);
                    dialogAction.update(mCategory);
                }


            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel_string, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("TAG", "CANCEL");
            }
        });

        return dialogBuilder.create();
    }

}
