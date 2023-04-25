package com.example.softmethproj5.ui.orderCoffee;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentOrderCoffeeBinding;
import com.example.softmethproj5.models.Coffee;
import com.example.softmethproj5.ui.MainViewModel;
import com.example.softmethproj5.ui.adapters.BasketAdapter;
import com.example.softmethproj5.ui.main.MainFragment;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The OrderCoffeeFragment class contains lifecycle callbacks for the order coffee fragment as
 * well as methods to add functionality to the Buttons and RecyclerView of the order coffee fragment.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class OrderCoffeeFragment extends Fragment {

    private static final Integer[] quantities = {1, 2, 3, 4, 5};

    FragmentOrderCoffeeBinding binding;
    BasketAdapter adapter;
    MainViewModel viewModel;

    /**
     * Required empty public constructor
     */
    public OrderCoffeeFragment() { }

    /**
     * Creates a new instance of OrderCoffeeFragment.
     * @return A new instance of OrderCoffeeFragment.
     */
    public static OrderCoffeeFragment newInstance() { return new OrderCoffeeFragment(); }

    /**
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return the root View of the fragment binding.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_coffee, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    /**
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpBasketRecycler();
        binding.btAddCoffee.setOnClickListener(onAddButtonClick());
        binding.btAddCoffeeToOrder.setOnClickListener(onAddToOrderClick());
        binding.spCoffeeQuantity.setAdapter(new ArrayAdapter<>(binding.getRoot().getContext(), android.R.layout.simple_spinner_item, quantities));
    }

    /**
     * Provides functionality for the "Add to Order" button.
     * @return the OnClickListener function for AddToOrder that you wish to implement.
     */
    private View.OnClickListener onAddToOrderClick () {
        return view -> {
            if (Objects.requireNonNull(viewModel.getCurrentScreenBasket().getValue()).isEmpty()) {
                Toast.makeText(requireContext(), "Basket is empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.addCurrentScreenBasketToOrder();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance()).commit();
        };
    }

    /**
     * Provides functionality for the "Add" button.
     * @return the OnClickListener function for Add that you wish to implement.
     */
    private View.OnClickListener onAddButtonClick () {
        return view -> {
            String cupSize = ((RadioButton) binding.rgCupSize.findViewById(binding.rgCupSize.getCheckedRadioButtonId())).getText().toString();
            Set<String> addOns = new HashSet<>();
            if (binding.cbSweetCream.isChecked()) addOns.add(binding.cbSweetCream.getText().toString());
            if (binding.cbIrishCream.isChecked()) addOns.add(binding.cbIrishCream.getText().toString());
            if (binding.cbMocha.isChecked()) addOns.add(binding.cbMocha.getText().toString());
            if (binding.cbFrenchVanilla.isChecked()) addOns.add(binding.cbFrenchVanilla.getText().toString());
            if (binding.cbCaramel.isChecked()) addOns.add(binding.cbCaramel.getText().toString());
            Coffee newCoffee = new Coffee(cupSize, addOns);
            viewModel.addItemToBasket(newCoffee, ((int) binding.spCoffeeQuantity.getSelectedItem()));
        };
    }

    /**
     * Sets up the RecyclerView for the ordering basket at the bottom of the screen.
     */
    private void setUpBasketRecycler () {
        binding.rvCoffeeBasket.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new BasketAdapter(viewModel);
        binding.rvCoffeeBasket.setAdapter(adapter);
        viewModel.getCurrentScreenBasket().observe(getViewLifecycleOwner(), menuItemIntegerMap -> adapter.updateItemList());
    }
}