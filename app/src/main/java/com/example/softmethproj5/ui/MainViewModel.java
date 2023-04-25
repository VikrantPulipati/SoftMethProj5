package com.example.softmethproj5.ui;

import static java.util.Objects.requireNonNull;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.softmethproj5.models.MenuItem;
import com.example.softmethproj5.models.Order;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The MainViewModel class contains data on current screen ordering baskets, current orders, store orders,
 * and current screen subtotals. The MainViewModel class also contains methods for manipulating
 * the ordering basket and store orders.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
@SuppressWarnings("ConstantConditions")
public class MainViewModel extends ViewModel {

    private final MutableLiveData<Map<MenuItem, Integer>> currentScreenBasket = new MutableLiveData<>(new HashMap<>());

    private final MutableLiveData<Order> currentOrder = new MutableLiveData<>(new Order());

    private final MutableLiveData<List<Order>> storeOrders = new MutableLiveData<>(new ArrayList<>());

    private final MutableLiveData<Double> currentScreenSubtotal = new MutableLiveData<>(0.0);

    /**
     * Gets the current on-screen ordering basket.
     * @return the current on-screen ordering basket.
     */
    @NonNull
    public LiveData<Map<MenuItem, Integer>> getCurrentScreenBasket () { return currentScreenBasket; }

    /**
     * Gets the current order.
     * @return the current order.
     */
    @NonNull
    public LiveData<Order> getCurrentOrder () { return currentOrder; }

    /**
     * Gets the store orders.
     * @return the store orders.
     */
    @NonNull
    public LiveData<List<Order>> getStoreOrders () { return storeOrders; }

    /**
     * Gets the subtotal displayed on the current screen.
     * @return the subtotal displayed on the current screen.
     */
    @NonNull
    public LiveData<Double> getCurrentScreenSubtotal () { return currentScreenSubtotal; }

    /**
     * clears the current on-screen ordering basket.
     */
    public void clearCurrentScreenBasket () {
        currentScreenBasket.setValue(new HashMap<>());
        calculateSubtotal();
    }

    /**
     * Adds a menuItem to the ordering basket.
     * @param menuItem the menuItem you wish to add.
     * @param quantity the quantity of the menuItem you wish to add.
     */
    public void addItemToBasket (MenuItem menuItem, int quantity) {
        Map<MenuItem, Integer> basket = requireNonNull(this.currentScreenBasket.getValue());
        if (basket.containsKey(menuItem)) basket.put(menuItem, requireNonNull(basket.get(menuItem))+quantity);
        else basket.put(menuItem, quantity);
        this.currentScreenBasket.setValue(basket);
        calculateSubtotal();
    }

    /**
     * Removes a menuItem from the ordering basket.
     * @param menuItem the menuItem you wish to remove.
     */
    public void removeItemFromBasket (MenuItem menuItem) {
        Map<MenuItem, Integer> basket = requireNonNull(this.currentScreenBasket.getValue());
        basket.remove(menuItem);
        this.currentScreenBasket.setValue(basket);
        calculateSubtotal();
    }

    /**
     * Calculates the subtotal of the ordering basket.
     */
    private void calculateSubtotal () {
        Map<MenuItem, Integer> basket = requireNonNull(this.currentScreenBasket.getValue());
        double subtotal = 0;
        for (MenuItem item : basket.keySet()) subtotal += item.itemPrice()*basket.get(item);
        this.currentScreenSubtotal.setValue(subtotal);
    }

    /**
     * Adds all the items in the current on-screen ordering basket to an Order.
     */
    public void addCurrentScreenBasketToOrder () {
        Order order = requireNonNull(this.currentOrder.getValue());
        Map<MenuItem, Integer> currentScreenBasket = requireNonNull(this.currentScreenBasket.getValue());
        order.addItemsFromBasket(currentScreenBasket);
        this.currentScreenBasket.setValue(new HashMap<>());
        this.currentScreenSubtotal.setValue(0.0);
        this.currentOrder.setValue(order);
    }

    /**
     * Removes a menuItem from an Order.
     * @param menuItem the menuItem you wish to Remove.
     */
    public void removeFromOrder (MenuItem menuItem) {
        Order order = requireNonNull(this.currentOrder.getValue());
        order.removeItem(menuItem);
        this.currentOrder.setValue(order);
    }

    /**
     * Adds the Order to the list of StoreOrders.
     */
    public void placeOrder () {
        List<Order> orders = requireNonNull(storeOrders.getValue());
        orders.add(currentOrder.getValue());
        this.storeOrders.setValue(orders);
        this.currentOrder.setValue(new Order());
    }

    /**
     * Removes an Order from the list of StoreOrders
     * @param index the index of the Order you wish to remove.
     */
    public void cancelOrder (int index) {
        List<Order> orders = requireNonNull(this.storeOrders.getValue());
        orders.remove(index);
        this.storeOrders.setValue(orders);
    }
}