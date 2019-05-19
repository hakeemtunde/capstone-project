package com.corebyte.mob.kiipa.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CartStock implements Parcelable {

    public static Parcelable.Creator<CartStock> CREATOR = new Parcelable.Creator<CartStock>() {

        @Override
        public CartStock createFromParcel(Parcel parcel) {
            return new CartStock(parcel);
        }

        @Override
        public CartStock[] newArray(int size) {
            return new CartStock[size];
        }
    };

    private Long mStockId;
    private Long mId;
    private int mQuantity;
    private double mCostPerStock;
    private double mTotalCost;

    private String mStockName;
    private String mMeasureName;

    public CartStock() {
    }

    public CartStock(Parcel parcel) {
        mStockId = parcel.readLong();
        mId = parcel.readLong();
        mQuantity = parcel.readInt();
        mCostPerStock = parcel.readDouble();
        mTotalCost = parcel.readDouble();
        mStockName = parcel.readString();
        mMeasureName = parcel.readString();
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(mStockId);
        parcel.writeLong(mId);
        parcel.writeInt(mQuantity);
        parcel.writeDouble(mCostPerStock);
        parcel.writeDouble(mTotalCost);
        parcel.writeString(mStockName);
        parcel.writeString(mMeasureName);
    }

    @Override
    public String toString() {
        return "CartStock{" +
                "mStockId=" + mStockId +
                ", mId=" + mId +
                ", mQuantity=" + mQuantity +
                ", mCostPerStock=" + mCostPerStock +
                ", mTotalCost=" + mTotalCost +
                ", mStockName='" + mStockName + '\'' +
                ", mMeasureName='" + mMeasureName + '\'' +
                '}';
    }

    public double getTotalCost() {
        return mTotalCost;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public double getmCostPerStock() {
        return mCostPerStock;
    }

    public double getmTotalCost() {
        return mTotalCost;
    }

    public String getmStockName() {
        return mStockName;
    }

    public String getmMeasureName() {
        return mMeasureName;
    }

    public Long getmStockId() {
        return mStockId;
    }

    public Long getmId() {
        return mId;
    }
}