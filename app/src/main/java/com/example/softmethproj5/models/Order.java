package com.example.softmethproj5.models;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.softmethproj5.BR;
import com.example.softmethproj5.ui.MainActivity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The Order class contains information on a user's order.
 * This includes order number, order items, and order costs.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
@SuppressWarnings("ConstantConditions")
public class Order extends BaseObservable {

    private static final double SALES_TAX = 0.06625;

    private static long index = 1;

    private final long orderNumber;

    private final Map<MenuItem, Integer> items;

    private double subtotal = 0;
    private double salesTax = 0;
    private double orderTotal = 0;

    /**
     * Constructs an Order object with a HashMap to track order items and a long to track the order number.
     */
    public Order () {
        this.items = new HashMap<>();
        this.orderNumber = index;
        index++;
    }

    /**
     * Returns the order number.
     * @return the order number.
     */
    public long getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns a Map containing the order items.
     * @return a Map containing the order items.
     */
    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    /**
     * Gets the subtotal of the Order.
     * @return a double signifying the subtotal of the Order.
     */
    @Bindable
    public double getSubtotal() { return subtotal; }

    /**
     * Gets the sales tax of the Order.
     * @return a double signifying the sales tax of the Order.
     */
    @Bindable
    public double getSalesTax() { return salesTax; }

    /**
     * Gets the total cost of the Order.
     * @return a double signifying the total cost of the Order.
     */
    @Bindable
    public double getOrderTotal() { return orderTotal; }

    /**
     * add an item to the Map containing order items.
     * @param basket containing the items to be added.
     */
    public void addItemsFromBasket(Map<MenuItem, Integer> basket) {
        for (MenuItem menuItem : basket.keySet()) {
            if (items.containsKey(menuItem)) items.put(menuItem, items.get(menuItem)+basket.get(menuItem));
            else items.put(menuItem, basket.get(menuItem));
        }
        calculateOrderTotal();
    }

    /**
     * Removes a menuItem from the order.
     * @param menuItem the menuItem you wish to remove.
     */
    public void removeItem (MenuItem menuItem) {
        this.items.remove(menuItem);
        calculateOrderTotal();
    }

    /**
     * Calculates the total cost of the Order.
     */
    private void calculateOrderTotal () {
        double sub = 0;
        for (MenuItem item : items.keySet())
            sub += (item.itemPrice() * items.get(item));
        subtotal = sub;
        notifyPropertyChanged(BR.subtotal);
        salesTax = subtotal*SALES_TAX;
        notifyPropertyChanged(BR.salesTax);
        orderTotal = subtotal*(1+SALES_TAX);
        notifyPropertyChanged(BR.orderTotal);
    }

    /**
     * Convert order information into a String.
     * @return a String containing the order items and order total cost.
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (MenuItem item : items.keySet()) {
            output.append(String.format(Locale.getDefault(), MainActivity.LIST_VIEW_STRING_FORMAT, items.get(item), item.toString()));
            output.append("\n");
        }
        return output.toString().trim();
    }
}