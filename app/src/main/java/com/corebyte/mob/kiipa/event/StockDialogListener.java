package com.corebyte.mob.kiipa.event;

import android.content.Context;

import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.repo.Repository;

public interface StockDialogListener {

    void create(Repository repository, String name);

    boolean isValid(String name);

    class StockDialogListenerEvent implements StockDialogListener {

        @Override
        public void create(Repository repository, String name) {
            repository.insert(new Category(name, 1));
        }

        @Override
        public boolean isValid(String name) {
            return true;
        }
    }
}
