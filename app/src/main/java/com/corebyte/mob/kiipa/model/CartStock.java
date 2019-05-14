package com.corebyte.mob.kiipa.model;

public class CartStock {

    private Long mId;
    private int mQuantity;
    private double mCostPerStock;
    private double mTotalCost;

    private CartStock() {
    }

    public CartStock(Long mId, double costperstock) {
        this.mId = mId;
        this.mCostPerStock = costperstock;
    }

    public void addQuantity(int newQty) {
        mQuantity = newQty;
    }

    public void calculateCost() {
        mTotalCost = mCostPerStock * mQuantity;
    }

    @Override
    public String toString() {
        return "CartStock{" +
                "mId=" + mId +
                ", mQuantity=" + mQuantity +
                ", mCostPerStock=" + mCostPerStock +
                ", mTotalCost=" + mTotalCost +
                '}';
    }
}
