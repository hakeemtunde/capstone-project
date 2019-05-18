package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "measurements",
        foreignKeys =
        @ForeignKey(entity = Stock.class, parentColumns = "id", childColumns = "stockId", onDelete = CASCADE))
public class Measurement extends BaseModel implements Parcelable {

    static Parcelable.Creator<Measurement> CREATOR = new Parcelable.Creator<Measurement>() {

        @Override
        public Measurement createFromParcel(Parcel parcel) {
            return new Measurement(parcel);
        }

        @Override
        public Measurement[] newArray(int size) {
            return new Measurement[size];
        }
    };

    private long stockId;
    private String name;
    private double supplyPrice;
    private double sellingPrice;
    private int supplyQty;
    private int lastSupplyQty;
    private int showStatus;
    private Date lastSupplyDate;

    public Measurement(long stockId, String name, double supplyPrice, double sellingPrice, int supplyQty,
                       int lastSupplyQty, int showStatus, Date lastSupplyDate) {
        this.stockId = stockId;
        this.name = name;
        this.supplyPrice = supplyPrice;
        this.sellingPrice = sellingPrice;
        this.supplyQty = supplyQty;
        this.lastSupplyQty = lastSupplyQty;
        this.showStatus = showStatus;
        this.lastSupplyDate = lastSupplyDate;
    }

    @Ignore
    public Measurement() {
    }

    @Ignore
    public Measurement(Parcel parcel) {
        this.id = parcel.readLong();
        this.createdAt = new Date(parcel.readLong());
        this.updatedAt = new Date(parcel.readLong());
        this.stockId = parcel.readLong();
        this.name = parcel.readString();
        this.supplyPrice = parcel.readDouble();
        this.sellingPrice = parcel.readDouble();
        this.supplyQty = parcel.readInt();
        this.lastSupplyQty = parcel.readInt();
        this.showStatus = parcel.readInt();
        this.lastSupplyDate = new Date(parcel.readLong());
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(double supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getSupplyQty() {
        return supplyQty;
    }

    public void setSupplyQty(int supplyQty) {
        this.supplyQty = supplyQty;
    }

    public int getLastSupplyQty() {
        return lastSupplyQty;
    }

    public void setLastSupplyQty(int lastSupplyQty) {
        this.lastSupplyQty = lastSupplyQty;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    public Date getLastSupplyDate() {
        return lastSupplyDate;
    }

    public void setLastSupplyDate(Date lastSupplyDate) {
        this.lastSupplyDate = lastSupplyDate;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "stockId=" + stockId +
                ", name='" + name + '\'' +
                ", supplyPrice=" + supplyPrice +
                ", sellingPrice=" + sellingPrice +
                ", supplyQty=" + supplyQty +
                ", lastSupplyQty=" + lastSupplyQty +
                ", showStatus=" + showStatus +
                ", lastSupplyDate=" + lastSupplyDate +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(this.id);
        parcel.writeLong(this.createdAt.getTime());
        parcel.writeLong(this.updatedAt.getTime());
        parcel.writeLong(this.stockId);
        parcel.writeString(this.name);
        parcel.writeDouble(this.supplyPrice);
        parcel.writeDouble(this.sellingPrice);
        parcel.writeInt(this.supplyQty);
        parcel.writeInt(this.lastSupplyQty);
        parcel.writeInt(this.showStatus);
        parcel.writeLong(this.lastSupplyDate.getTime());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;
        Measurement that = (Measurement) o;
        return id == that.id &&
                getStockId() == that.getStockId() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getStockId(), getName());
    }

    public void reduceQuantity(int soldQty) {
        this.supplyQty -= getSupplyQty() - soldQty;
    }
}
