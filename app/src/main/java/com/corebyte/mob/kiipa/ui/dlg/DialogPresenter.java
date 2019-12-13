package com.corebyte.mob.kiipa.ui.dlg;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.corebyte.mob.kiipa.model.BaseModel;
import com.corebyte.mob.kiipa.model.Customer;

public abstract class DialogPresenter implements DialogPresenterInterface {

    AppDialogAbstract appDialogAbstract;
    FragmentManager fragmentManager;

    public DialogPresenter(Context context, FragmentManager fmgr, View view, int resId,
                           String title) {
        appDialogAbstract = new AppDialogAbstract(context, view, resId, title, this);
        fragmentManager = fmgr;

    }

    public void display() {
        appDialogAbstract.show(fragmentManager, "DLG_APP");
    }

    @Override
    public View getDialogView() {
        return appDialogAbstract.dlgView;
    }

    public interface DialogCallback {
        void click();
    }

    public interface ModelDialogCallback<T extends BaseModel> {
        void click(T model);
    }
}


