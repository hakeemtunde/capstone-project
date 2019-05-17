package com.corebyte.mob.kiipa;

import android.os.Parcel;
import android.os.Parcelable;

import com.corebyte.mob.kiipa.model.CartStock;

import java.util.ArrayList;

public class CartSummary implements Parcelable {

    public static Parcelable.Creator<CartSummary> CREATOR = new Parcelable.Creator<CartSummary>() {

        @Override
        public CartSummary createFromParcel(Parcel parcel) {
            return new CartSummary(parcel);
        }

        @Override
        public CartSummary[] newArray(int size) {
            return new CartSummary[size];
        }
    };

    private int mTotalItems;
    private double mTotalAmount = 0;
    private ArrayList<CartStock> mCartStocks;

    public CartSummary(ArrayList<CartStock> cartitems) {
        mCartStocks = cartitems;
        mTotalItems = cartitems.size();

        calculateStockAmount();
    }

    public CartSummary(Parcel parcel) {
        mTotalItems = parcel.readInt();
        mTotalAmount = parcel.readDouble();

        mCartStocks = new ArrayList<CartStock>();
        parcel.readTypedList(mCartStocks, CartStock.CREATOR);
    }

    private double calculateStockAmount() {

        for (CartStock cartStock : mCartStocks) {
            mTotalAmount += cartStock.getTotalCost();
        }
        return mTotalAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mTotalItems);
        parcel.writeDouble(mTotalAmount);
        parcel.writeTypedList(mCartStocks);
    }

    @Override
    public String toString() {
        return "CartSummary{" +
                "mTotalItems=" + mTotalItems +
                ", mTotalAmount=" + mTotalAmount +
                ", mCartStocks=" + mCartStocks +
                '}';
    }

    public int getmTotalItems() {
        return mTotalItems;
    }

    public double getmTotalAmount() {
        return mTotalAmount;
    }

    public ArrayList<CartStock> getmCartStocks() {
        return mCartStocks;
    }
}
