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

public class StoreOrderAdapter extends RecyclerView.Adapter<StoreOrderAdapter.StoreOrderViewHolder> {


    private List<Order> orderList;
    private MainViewModel viewModel;

    public StoreOrderAdapter(MainViewModel viewModel) {
        this.viewModel = viewModel;
        updateStoreOrderList();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateStoreOrderList () {
        orderList = viewModel.getStoreOrders().getValue();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoreOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StoreOrderViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.store_order_list_item, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoreOrderViewHolder holder, int position) {
        int i = holder.getAdapterPosition();
        Order order = orderList.get(i);
        View.OnClickListener cancelListener = view -> viewModel.cancelOrder(i);
        holder.bind(order, cancelListener);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    protected static class StoreOrderViewHolder extends RecyclerView.ViewHolder {

        private final StoreOrderListItemBinding binding;

        public StoreOrderViewHolder(@NonNull StoreOrderListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind (Order order, View.OnClickListener cancelListener) {
            binding.setOrder(order);
            binding.btCancelOrder.setOnClickListener(cancelListener);
        }
    }
}
