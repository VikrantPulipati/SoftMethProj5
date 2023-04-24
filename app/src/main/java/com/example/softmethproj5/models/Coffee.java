package com.example.softmethproj5.models;

import androidx.annotation.NonNull;

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

    public String getCupSize() { return cupSize; }

    public Set<String> getAddIns() { return addIns; }

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

    /**
     * Converts Coffee object information into a String.
     * @return a string containing info on Coffee cup size and add-ins.
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(this.cupSize);
        for (String addIn : addIns) {
            output.append("\n- ").append(addIn);
        }
        return output.toString();
    }

    /**
     *
     * @return a computed hash code for Coffee instance.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.cupSize.length(); i++) {
            hash += (((i+1)*this.cupSize.charAt(i)) << i);
        }
        for (String addIn : this.addIns) {
            int subHash = 0;
            for (int i = 0; i < addIn.length(); i++) {
                subHash += (((i+1)*addIn.charAt(i)) << i);
            }
            hash += subHash;
        }
        return hash;
    }

    /**
     * Compares two coffee objects
     * @param obj the Coffee object you wish to compare
     * @return true if the two Coffee objects have the same size and add-ins, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coffee) {
            Coffee other = (Coffee) obj;
            return (this.getCupSize().equals(other.getCupSize())) && this.addIns.equals(other.getAddIns());
        }
        return false;
    }
}
