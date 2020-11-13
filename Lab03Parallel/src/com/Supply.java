package com;

import java.time.LocalDate;

public class Supply {
    private String item;
    private double quantity;
    private LocalDate supplyDate;
    private double unitPrice;

    public Supply(String item, double quantity, LocalDate supplyDate, double unitPrice){
        this.item=item;
        this.quantity=quantity;
        this.supplyDate=supplyDate;
        this.unitPrice=unitPrice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(LocalDate supplyDate) {
        this.supplyDate = supplyDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Override
    public String toString() {
        return getItem()+","+getQuantity()+","+getSupplyDate()+","+getUnitPrice();}

}
