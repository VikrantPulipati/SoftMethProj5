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

@SuppressWarnings("ConstantConditions")
public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {

    private final MainViewModel viewModel;
    private List<MenuItem> itemList;

    public BasketAdapter(MainViewModel viewModel) {
        this.viewModel = viewModel;
        updateItemList();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateItemList () {
        itemList = new ArrayList<>(requireNonNull(viewModel.getCurrentScreenBasket().getValue()).keySet());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BasketAdapter.BasketViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.basket_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        View.OnClickListener removeListener = view -> viewModel.removeItemFromBasket(itemList.get(holder.getAdapterPosition()));
        MenuItem item = itemList.get(holder.getAdapterPosition());
        holder.bind(item.toString(), requireNonNull(viewModel.getCurrentScreenBasket().getValue()).get(item), removeListener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected static class BasketViewHolder extends RecyclerView.ViewHolder {

        BasketListItemBinding binding;

        public BasketViewHolder(@NonNull BasketListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind (String itemName, int quantity, View.OnClickListener removeListener) {
            binding.tvFlavorName.setText(String.format(Locale.getDefault(), "%s (%d)", itemName, quantity));
            binding.btRemoveFromBasket.setOnClickListener(removeListener);
        }
    }
}
