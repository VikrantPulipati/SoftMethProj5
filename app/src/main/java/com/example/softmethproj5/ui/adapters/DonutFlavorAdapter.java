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

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.DonutFlavorChoiceItemBinding;
import com.example.softmethproj5.models.Donut;
import com.example.softmethproj5.ui.MainViewModel;

import java.util.List;

/**
 * An adapter for the list of donut flavors.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class DonutFlavorAdapter extends RecyclerView.Adapter<DonutFlavorAdapter.DonutFlavorViewHolder> {

    private static final Integer[] quantities = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    List<Pair<String, Integer>> flavorList;
    private final MainViewModel viewModel;

    /**
     * Constructor for the DonutFlavorAdapter
     * @param flavorList list of donut flavors.
     * @param viewModel the viewModel.
     */
    public DonutFlavorAdapter (List<Pair<String, Integer>> flavorList, MainViewModel viewModel) {
        this.flavorList = flavorList;
        this.viewModel = viewModel;
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
    public DonutFlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonutFlavorViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.donut_flavor_choice_item, parent, false));
    }

    /**
     * Lifecycle callback for when the ViewHolder is bound.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull DonutFlavorViewHolder holder, int position) {
        View.OnClickListener addButtonListener = view -> viewModel.addItemToBasket(new Donut(flavorList.get(holder.getAdapterPosition()).first), holder.selectedQuantity);
        holder.bind(flavorList.get(holder.getAdapterPosition()), addButtonListener);
    }

    /**
     * Returns the size of the flavorList.
     * @return the size of the flavorList.
     */
    @Override
    public int getItemCount() { return flavorList.size(); }

    /**
     * The DonutFlavorViewHolder class is a ViewHolder for the donut flavors.
     * @author Gabriel Ruszala, Vikrant Pulipati
     */
    protected static class DonutFlavorViewHolder extends RecyclerView.ViewHolder {

        private final DonutFlavorChoiceItemBinding binding;
        Integer selectedQuantity = 0;

        /**
         * Constructor for the DonutFlavorViewHolder
         * @param binding the view binding.
         */
        public DonutFlavorViewHolder(@NonNull DonutFlavorChoiceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * Binds relevant data to the ViewHolder.
         * @param flavor donut flavor.
         * @param addButtonListener the function for addButtonListener.
         */
        private void bind (Pair<String, Integer> flavor, View.OnClickListener addButtonListener) {
            binding.spQuantity.setAdapter(new ArrayAdapter<>(binding.getRoot().getContext(), android.R.layout.simple_spinner_item, quantities));
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
