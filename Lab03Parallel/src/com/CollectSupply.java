package com;

import java.time.LocalDate;

public class CollectSupply {
    private String item;
    private double quantity;
    private LocalDate collectDate;

    public CollectSupply(String item, double quantity, LocalDate collectDate) {
        this.item = item;
        this.quantity = quantity;
        this.collectDate = collectDate;
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

    public LocalDate getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(LocalDate collectDate) {
        this.collectDate = collectDate;
    }
    @Override
    public String toString() {
        return getItem()+","+getQuantity()+","+getCollectDate();}
}
