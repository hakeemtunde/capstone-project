package com.corebyte.mob.kiipa.model;

public class CartStock {

    private Long mStockId;
    private Long mId;
    private int mQuantity;
    private double mCostPerStock;
    private double mTotalCost;
    private String mStockName;
    private String mMeasureName;


    private CartStock() {
    }

    public CartStock(Long sId, Long mId, String sName, String mName, double costperstock) {
        mStockId = sId;
        this.mId = mId;
        mStockName = sName;
        mMeasureName = mName;
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
