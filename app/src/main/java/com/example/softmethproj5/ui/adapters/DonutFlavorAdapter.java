package com.example.softmethproj5.ui.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softmethproj5.BR;
import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.DonutFlavorChoiceItemBinding;
import com.example.softmethproj5.models.Donut;
import com.example.softmethproj5.ui.main.MainViewModel;

import java.util.List;

public class DonutFlavorAdapter extends RecyclerView.Adapter<DonutFlavorAdapter.DonutFlavorViewHolder> {

    private static final Integer[] quantities = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    List<Pair<String, Integer>> flavorList;
    private final MainViewModel viewModel;

    public DonutFlavorAdapter (List<Pair<String, Integer>> flavorList, MainViewModel viewModel) {
        this.flavorList = flavorList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public DonutFlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonutFlavorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.donut_flavor_choice_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DonutFlavorViewHolder holder, int position) {
        View.OnClickListener addButtonListener = view -> viewModel.addItemToBasket(new Donut(flavorList.get(holder.getAdapterPosition()).first), holder.selectedQuantity);
        holder.bind(flavorList.get(holder.getAdapterPosition()), addButtonListener);
    }

    @Override
    public int getItemCount() { return flavorList.size(); }

    protected static class DonutFlavorViewHolder extends RecyclerView.ViewHolder {

        private final DonutFlavorChoiceItemBinding binding;
        Integer selectedQuantity = 0;

        public DonutFlavorViewHolder(@NonNull DonutFlavorChoiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind (Pair<String, Integer> flavor, View.OnClickListener addButtonListener) {
            Context context = binding.getRoot().getContext();

            binding.spQuantity.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, quantities));
            binding.spQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { selectedQuantity = i+1; }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    binding.spQuantity.setSelection(0);
                    selectedQuantity = 1;
                }
            });

            binding.btAddToBasket.setOnClickListener(addButtonListener);
            binding.tvFlavorName.setText(flavor.first);
            binding.ivDonutImage.setImageDrawable((flavor.second == null)? null : AppCompatResources.getDrawable(binding.getRoot().getContext(), flavor.second));

            binding.executePendingBindings();
        }
    }
}
