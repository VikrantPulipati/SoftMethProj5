package com.example.softmethproj5.ui.main;

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

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Map<MenuItem, Integer>> currentScreenBasket = new MutableLiveData<>(new HashMap<>());

    private final MutableLiveData<Order> currentOrder = new MutableLiveData<>(new Order());

    private final MutableLiveData<List<Order>> storeOrders = new MutableLiveData<>(new ArrayList<>());

    private final MutableLiveData<Double> currentScreenSubtotal = new MutableLiveData<>(0.0);

    @NonNull
    public LiveData<Map<MenuItem, Integer>> getCurrentScreenBasket () { return currentScreenBasket; }

    @NonNull
    public LiveData<Order> getCurrentOrder () { return currentOrder; }

    @NonNull
    public LiveData<List<Order>> getStoreOrders () { return storeOrders; }

    @NonNull
    public LiveData<Double> getCurrentScreenSubtotal () { return currentScreenSubtotal; }

    public void clearCurrentScreenBasket () {
        currentScreenBasket.setValue(new HashMap<>());
        calculateSubtotal();
    }

    public void addItemToBasket (MenuItem menuItem, int quantity) {
        Map<MenuItem, Integer> basket = requireNonNull(this.currentScreenBasket.getValue());
        if (basket.containsKey(menuItem)) basket.put(menuItem, requireNonNull(basket.get(menuItem))+quantity);
        else basket.put(menuItem, quantity);
        this.currentScreenBasket.setValue(basket);
        calculateSubtotal();
    }

    public void removeItemFromBasket (MenuItem menuItem) {
        Map<MenuItem, Integer> basket = requireNonNull(this.currentScreenBasket.getValue());
        basket.remove(menuItem);
        this.currentScreenBasket.setValue(basket);
        calculateSubtotal();
    }

    private void calculateSubtotal () {
        Map<MenuItem, Integer> basket = requireNonNull(this.currentScreenBasket.getValue());
        double subtotal = 0;
        for (MenuItem item : basket.keySet()) subtotal += item.itemPrice()*basket.get(item);
        this.currentScreenSubtotal.setValue(subtotal);
    }

    public void addCurrentScreenBasketToOrder () {
        Order order = requireNonNull(this.currentOrder.getValue());
        Map<MenuItem, Integer> currentScreenBasket = requireNonNull(this.currentScreenBasket.getValue());
        order.addItemsFromBasket(currentScreenBasket);
        this.currentScreenBasket.setValue(new HashMap<>());
        this.currentScreenSubtotal.setValue(0.0);
        this.currentOrder.setValue(order);
    }

    public void removeFromOrder (MenuItem menuItem) {
        Order order = requireNonNull(this.currentOrder.getValue());
        order.removeItem(menuItem);
        this.currentOrder.setValue(order);
    }

    public void placeOrder () {
        List<Order> orders = requireNonNull(storeOrders.getValue());
        orders.add(currentOrder.getValue());
        this.storeOrders.setValue(orders);
        this.currentOrder.setValue(new Order());
    }

    public void cancelOrder (int index) {
        List<Order> orders = requireNonNull(this.storeOrders.getValue());
        orders.remove(index);
        this.storeOrders.setValue(orders);
    }
}