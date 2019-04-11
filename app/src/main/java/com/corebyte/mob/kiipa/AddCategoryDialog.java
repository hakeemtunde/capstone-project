package com.corebyte.mob.kiipa;

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

import com.corebyte.mob.kiipa.event.StockDialogListener;
import com.corebyte.mob.kiipa.repo.Repository;

public class AddCategoryDialog extends DialogFragment {

    private StockDialogListener mStockDialogListener;
    private Repository mRepository;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        dialogBuilder.setTitle(R.string.categoryDlgTitle);

        final View view = inflater.inflate(R.layout.dialog_addstock, null);
        final EditText editText = view.findViewById(R.id.add_category_name_et);

        dialogBuilder.setView(view);
        dialogBuilder.setPositiveButton(R.string.ok_string, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String catname = editText.getText().toString();
                mStockDialogListener.create(mRepository, catname);
                mRepository.getAll();
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

    public void setDialogListener(StockDialogListener listener) {
        if (listener == null) return;
        this.mStockDialogListener = listener;
    }

    public void setRepository(Repository repository) {
        this.mRepository = repository;

    }

}
