package com.example.softmethproj5.models;

import androidx.annotation.NonNull;

/**
 * The Donut class contains relevant information on Donut attributes such as Donut prices
 * and flavors, as well as methods for comparing and printing Donut objects.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class Donut extends MenuItem {

    public static final double YEAST_DONUT_PRICE = 1.59;
    public static final double CAKE_DONUT_PRICE = 1.79;
    public static final double DONUT_HOLE_PRICE = 0.39;

    public static final String FLAVOR_CINNAMON = "Cinnamon";
    public static final String FLAVOR_BUTTERNUT = "Butternut";
    public static final String FLAVOR_BLUEBERRY = "Blueberry";

    public static final String FLAVOR_CHOCOLATE = "Chocolate";
    public static final String FLAVOR_VANILLA = "Vanilla";
    public static final String FLAVOR_COFFEE = "Coffee";

    public static final String FLAVOR_JELLY = "Jelly";
    public static final String FLAVOR_GLAZED = "Glazed";
    public static final String FLAVOR_POWDERED = "Powdered";
    public static final String FLAVOR_PUMPKIN = "Pumpkin";
    public static final String FLAVOR_SUGAR = "Sugar";
    public static final String FLAVOR_LEMON = "Lemon";

    private final String flavor;

    /**
     * Donut constructor.
     * @param flavor the flavor of the Donut.
     */
    public Donut (String flavor) {
        this.flavor = flavor;
    }

    /**
     * Gets the flavor of the Donut
     * @return the flavor of the Donut
     */
    public String getFlavor () { return this.flavor; }

    /**
     * Calculates the price of the Donut
     * @return the price of the Donut
     */
    @Override
    public double itemPrice() {
        if (flavor.equals(FLAVOR_CINNAMON)
                || flavor.equals(FLAVOR_BUTTERNUT)
                || flavor.equals(FLAVOR_BLUEBERRY)) {
            return CAKE_DONUT_PRICE;
        }
        else if (flavor.equals(FLAVOR_CHOCOLATE)
                || flavor.equals(FLAVOR_VANILLA)
                || flavor.equals(FLAVOR_COFFEE)) {
            return DONUT_HOLE_PRICE;
        } else {
            return YEAST_DONUT_PRICE;
        }
    }

    /**
     * Converts Donut object information into a String.
     * @return a String containing info on Donut flavor.
     */
    @NonNull
    @Override
    public String toString() {
        return this.flavor;
    }

    /**
     * Compare two Donut objects
     * @param obj the Donut you wish to compare
     * @return true if the two Donut objects have the same flavor, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Donut) && (this.flavor.equals(((Donut)obj).getFlavor()));
    }

    /**
     * returns a computed hash code for Donut instance.
     * @return a computed hash code for Donut instance.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.flavor.length(); i++) {
            hash += (((i+1)*this.flavor.charAt(i)) << i);
        }
        return hash;
    }
}
