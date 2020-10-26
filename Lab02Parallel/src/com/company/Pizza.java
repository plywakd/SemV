package com.company;

import java.util.Arrays;
import java.util.List;

import static com.company.Ingredients.*;

public enum Pizza {
    MARGHERITA("Marrgherita", THIN_CRUST, TOMATO_SUACE, CHEESE),
    CAPRICIOSA("Capriciosa", THIN_CRUST, TOMATO_SUACE, CHEESE, HAM,MUSHROOMS),
    HAVAII("Hawajska", THIN_CRUST, TOMATO_SUACE, CHEESE, HAM,PINEAPPLE),
    CARUSO("Caruso", THIN_CRUST, TOMATO_SUACE, SAUSAGE,PEPERONI),
    MAMA_MIA("Mama Mia", THIN_CRUST, TOMATO_SUACE, CHEESE, ONION,MUSHROOMS, BECON),
    SOPRANO("Soprano", THICK_CRUST, TOMATO_SUACE, CHEESE, HAM,MUSHROOMS, ONION, BECON, PEPPER),
    CALABRESE("Calabrese", THICK_CRUST, TOMATO_SUACE, CHEESE,HAM, MUSHROOMS, SAUSAGE, ONION, OLIVES),
    VEGETARIANA("Wegetarianska", THIN_CRUST, TOMATO_SUACE, CHEESE,ONION, BEAN, CORN, BROCCOLI, ARUGULA),
    CAPRESE("Caprese", THICK_CRUST, TOMATO_SUACE, MOZARELLA,FETA, TOMATO, BASIL),
    COLORADO("Caprese", THICK_CRUST, TOMATO_SUACE, MOZARELLA, HAM, OLIVES, BASIL, BEAN),
    PASCETORE("Pascetore", THIN_CRUST, TOMATO_SUACE, CHEESE,TUNA, ONION),
    FOUR_CHEESE("Cztery sery", THIN_CRUST, TOMATO_SUACE, CHEESE,MOZARELLA, FETA, BLUE_CHEESE),
    TABASCO("Tabasco", THICK_CRUST, TOMATO_SUACE, CHEESE, HAM,SALAMI, PEPERONI, CORN, TABASCO_SUACE),
    AMORE("Amore", THIN_CRUST, TOMATO_SUACE, CHEESE, CHICKEN,TOMATO),
    FARMER("Farmerska", THICK_CRUST, TOMATO_SUACE, CHEESE,CHICKEN, BECON, ONION, CORN);

    private final String name;
    private final List<Ingredients> ingredients;

    private Pizza(String name, Ingredients... ingredients) {
        this.name = name;
        this.ingredients = Arrays.asList(ingredients);
    }
    public String getName() {
        return name;
    }
    public List<Ingredients> getIngredients() {
        return ingredients;
    }
    @Override
    public String toString() {
        return name;}
}