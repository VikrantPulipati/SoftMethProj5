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
import com.example.softmethproj5.ui.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * An adapter for your order.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
@SuppressWarnings("ConstantConditions")
public class YourOrderAdapter extends RecyclerView.Adapter<YourOrderAdapter.YourOrderViewHolder> {

    private final MainViewModel viewModel;
    private List<MenuItem> itemList;
    private Order currentOrder;

    /**
     * YourOrderAdapter constructor.
     * @param viewModel the viewModel.
     */
    public YourOrderAdapter(MainViewModel viewModel) {
        this.viewModel = viewModel;
        currentOrder = viewModel.getCurrentOrder().getValue();
        updateItemList();
    }

    /**
     * Updates the list of items.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateItemList () {
        itemList = new ArrayList<>(currentOrder.getItems().keySet());
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
    public YourOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YourOrderViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.basket_list_item, parent,
                        false));
    }

    /**
     * Lifecycle callback for when the ViewHolder is bound.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull YourOrderViewHolder holder, int position) {
        MenuItem item = itemList.get(holder.getAdapterPosition());
        View.OnClickListener removeListener = view -> viewModel.removeFromOrder(item);
        holder.bind(item.toString(), Objects.requireNonNull(currentOrder.getItems()).get(item), removeListener);
    }

    /**
     * Returns the size of the itemList.
     * @return the size of the itemList.
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * The YourOrderViewHolder class is a ViewHolder for your order.
     * @author Gabriel Ruszala, Vikrant Pulipati
     */
    protected static class YourOrderViewHolder extends RecyclerView.ViewHolder {

        BasketListItemBinding binding;

        /**
         * Constructor for YourOrderViewHolder
         * @param binding the binding.
         */
        public YourOrderViewHolder(@NonNull BasketListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Binds relevant data to the ViewHolder.
         * @param itemName the name of the item.
         * @param quantity the quantity of the item.
         * @param removeListener the function for removeListener.
         */
        private void bind (String itemName, int quantity, View.OnClickListener removeListener) {
            binding.tvFlavorName.setText(String.format(Locale.getDefault(), "%s (%d)", itemName, quantity));
            binding.btRemoveFromBasket.setOnClickListener(removeListener);
        }
    }
}
