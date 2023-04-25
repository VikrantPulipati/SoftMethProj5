package com.example.softmethproj5.ui.storeOrders;

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

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentStoreOrdersBinding;
import com.example.softmethproj5.models.Order;
import com.example.softmethproj5.ui.MainViewModel;
import com.example.softmethproj5.ui.adapters.StoreOrderAdapter;

import java.util.List;

/**
 * The StoreOrdersFragment class contains lifecycle callbacks for the store orders fragment as
 * well as methods to add functionality to the store orders fragment.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class StoreOrdersFragment extends Fragment {

    FragmentStoreOrdersBinding binding;
    MainViewModel viewModel;
    StoreOrderAdapter adapter;

    /**
     * Required empty public constructor
     */
    public StoreOrdersFragment() { }

    /**
     * Creates a new instance of StoreOrdersFragment.
     * @return A new instance of StoreOrdersFragment.
     */
    public static StoreOrdersFragment newInstance() { return new StoreOrdersFragment(); }

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
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_orders, container, false);
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

        viewModel.getStoreOrders().observe(getViewLifecycleOwner(), orders -> adapter.updateStoreOrderList());

        setUpStoreOrderRecycler();
    }

    /**
     * Sets up the RecylerView containing information on all store orders.
     */
    private void setUpStoreOrderRecycler () {
        binding.rvStoreOrders.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new StoreOrderAdapter(viewModel);
        binding.rvStoreOrders.setAdapter(adapter);
    }
}