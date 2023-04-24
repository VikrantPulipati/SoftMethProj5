package com.example.softmethproj5.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.BasketListItemBinding;
import com.example.softmethproj5.models.MenuItem;
import com.example.softmethproj5.models.Order;
import com.example.softmethproj5.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class YourOrderAdapter extends RecyclerView.Adapter<YourOrderAdapter.YourOrderViewHolder> {

    private final MainViewModel viewModel;
    private List<MenuItem> itemList;
    private Order currentOrder;

    public YourOrderAdapter(MainViewModel viewModel) {
        this.viewModel = viewModel;
        currentOrder = viewModel.getCurrentOrder();
        updateItemList();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateItemList () {
        itemList = new ArrayList<>(currentOrder.getItems().keySet());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public YourOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YourOrderAdapter.YourOrderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.basket_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull YourOrderViewHolder holder, int position) {
        MenuItem item = itemList.get(holder.getAdapterPosition());
        View.OnClickListener removeListener = view -> viewModel.removeFromOrder(item);
        holder.bind(item.toString(), Objects.requireNonNull(currentOrder.getItems()).get(item), removeListener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected static class YourOrderViewHolder extends RecyclerView.ViewHolder {

        BasketListItemBinding binding;

        public YourOrderViewHolder(@NonNull BasketListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind (String itemName, int quantity, View.OnClickListener removeListener) {
            binding.tvFlavorName.setText(String.format(Locale.getDefault(), "%s (%d)", itemName, quantity));
            binding.btRemoveFromBasket.setOnClickListener(removeListener);
        }
    }
}
