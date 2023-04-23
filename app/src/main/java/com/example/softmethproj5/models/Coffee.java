package com.example.softmethproj5.models;

import java.util.Set;

public class Coffee extends MenuItem {

    public static final String CUP_SIZE_SHORT = "Short";
    public static final String CUP_SIZE_TALL = "Tall";
    public static final String CUP_SIZE_GRANDE = "Grande";
    public static final String CUP_SIZE_VENTI = "Venti";

    private final String cupSize;
    private final Set<String> addIns;

    public Coffee(String cupSize, Set<String> addIns) {
        this.cupSize = cupSize;
        this.addIns = addIns;
    }

    @Override
    public double itemPrice() {
        double basePrice = 0;
        switch (cupSize) {
            case CUP_SIZE_SHORT:
                basePrice = 1.89;
                break;
            case CUP_SIZE_TALL:
                basePrice = 2.29;
                break;
            case CUP_SIZE_GRANDE:
                basePrice = 2.69;
                break;
            case CUP_SIZE_VENTI:
                basePrice = 3.09;
                break;
        }
        return .3*addIns.size() + basePrice;
    }
}
