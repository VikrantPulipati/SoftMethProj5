package com.example.softmethproj5.ui.adapters;

import static java.util.Objects.requireNonNull;

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
import com.example.softmethproj5.ui.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * An adapter for the ordering basket.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
@SuppressWarnings("ConstantConditions")
public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private final MainViewModel viewModel;
    private List<MenuItem> itemList;

    /**
     * Constructor for the BasketAdapter
     * @param viewModel the viewModel you wish to link the BasketAdapter to.
     */
    public BasketAdapter(MainViewModel viewModel) {
        this.viewModel = viewModel;
        updateItemList();
    }

    /**
     * Updates the list of items in the ordering basket.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void updateItemList () {
        itemList = new ArrayList<>(requireNonNull(viewModel.getCurrentScreenBasket().getValue()).keySet());
        notifyDataSetChanged();
    }

    /**
     * Lifecycle callback for when the ViewHolder is created.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *        an adapter position.
     * @param viewType The view type of the new View.
     * @return a ViewHolder.
     */
    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BasketAdapter.BasketViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.basket_list_item, parent, false));
    }

    /**
     * Lifecycle callback for when the ViewHolder is bound.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        View.OnClickListener removeListener = view -> viewModel.removeItemFromBasket(itemList.get(holder.getAdapterPosition()));
        MenuItem item = itemList.get(holder.getAdapterPosition());
        holder.bind(item.toString(), requireNonNull(viewModel.getCurrentScreenBasket().getValue()).get(item), removeListener);
    }

    /**
     * Returns the number of items.
     * @return the size of the adapter list.
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * The BasketViewHolder class is a ViewHolder for the ordering basket.
     * @author Gabriel Ruszala, Vikrant Pulipati
     */
    protected static class BasketViewHolder extends RecyclerView.ViewHolder {

        BasketListItemBinding binding;

        /**
         * Constructor for the BasketViewHolder
         * @param binding the view binding.
         */
        public BasketViewHolder(@NonNull BasketListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Binds relevant data to the ViewHolder
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
