package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

import java.util.Arrays;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "measurements",
foreignKeys =
@ForeignKey(entity = Stock.class, parentColumns = "id", childColumns = "stockId", onDelete = CASCADE))
public class Measurement extends BaseModel {

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
    public Measurement() {}

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
}
