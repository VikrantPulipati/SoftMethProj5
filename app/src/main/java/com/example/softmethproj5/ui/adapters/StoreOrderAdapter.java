package com.example.softmethproj5.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.StoreOrderListItemBinding;
import com.example.softmethproj5.models.Order;
import com.example.softmethproj5.ui.MainViewModel;

import java.util.List;

/**
 * An adapter for the list of store orders.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class StoreOrderAdapter extends RecyclerView.Adapter<StoreOrderAdapter.StoreOrderViewHolder> {


    private List<Order> orderList;
    private MainViewModel viewModel;

    /**
     * Constructor for the StoreOrderAdapter
     * @param viewModel the ViewModel
     */
    public StoreOrderAdapter(MainViewModel viewModel) {
        this.viewModel = viewModel;
        updateStoreOrderList();
    }

    /**
     * Updates the list of store orders.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateStoreOrderList () {
        orderList = viewModel.getStoreOrders().getValue();
        notifyDataSetChanged();
    }

    /**
     * Lifecycle callback for when the ViewHolder is created.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return a ViewHolder.
     */
    @NonNull
    @Override
    public StoreOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoreOrderViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.store_order_list_item, parent,
                        false));
    }

    /**
     * Lifecycle callback for when the ViewHolder is bound.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull StoreOrderViewHolder holder, int position) {
        int i = holder.getAdapterPosition();
        Order order = orderList.get(i);
        View.OnClickListener cancelListener = view -> viewModel.cancelOrder(i);
        holder.bind(order, cancelListener);
    }

    /**
     * Returns the size of the list of store orders.
     * @return the size of the list of store orders.
     */
    @Override
    public int getItemCount() {
        return orderList.size();
    }

    /**
     * The StoreOrderViewHolder class is a ViewHolder for the list of store orders.
     * @author Gabriel Ruszala, Vikrant Pulipati
     */
    protected static class StoreOrderViewHolder extends RecyclerView.ViewHolder {

        private final StoreOrderListItemBinding binding;

        /**
         * Constructor for the StoreOrderViewHolder.
         * @param binding the view binding.
         */
        public StoreOrderViewHolder(@NonNull StoreOrderListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Binds relevant data to the ViewHolder.
         * @param order the order
         * @param cancelListener the function for cancelListener.
         */
        private void bind (Order order, View.OnClickListener cancelListener) {
            binding.setOrder(order);
            binding.btCancelOrder.setOnClickListener(cancelListener);
        }
    }
}
