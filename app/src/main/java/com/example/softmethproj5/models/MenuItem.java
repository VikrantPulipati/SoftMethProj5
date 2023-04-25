package com.example.softmethproj5.models;

/**
 * The MenuItem class is an abstract class extended by all menu item classes (Coffee, and the Donut classes).
 * It provides an abstract method to return the price of a MenuItem.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public abstract class MenuItem {

    /**
     * Returns the price of a Menu Item.
     * @return a double signifying the price of a MenuItem.
     */
    public abstract double itemPrice();
}
