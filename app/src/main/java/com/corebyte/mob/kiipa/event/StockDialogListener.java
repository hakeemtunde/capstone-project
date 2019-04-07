package com.corebyte.mob.kiipa.event;

public interface StockDialogListener {

    void create(String name);

    boolean isValid(String name);

    class StockDialogListenerEvent implements StockDialogListener {

        @Override
        public void create(String name) {

        }

        @Override
        public boolean isValid(String name) {
            return true;
        }
    }
}
