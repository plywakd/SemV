package com;

public enum Item {
    GLUE("klej",11.0,1),
    TAPE("tasma",20.0,1);


    private String name;
    private double price;
    private int quantity;

    private Item(String name, double price, int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }
}
