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
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreOrdersFragment extends Fragment {

    FragmentStoreOrdersBinding binding;
    MainViewModel viewModel;
    StoreOrderAdapter adapter;

    public StoreOrdersFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StoreOrdersFragment.
     */
    public static StoreOrdersFragment newInstance() { return new StoreOrdersFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_orders, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getStoreOrders().observe(getViewLifecycleOwner(), orders -> adapter.updateStoreOrderList());

        setUpStoreOrderRecycler();
    }

    private void setUpStoreOrderRecycler () {
        binding.rvStoreOrders.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new StoreOrderAdapter(viewModel);
        binding.rvStoreOrders.setAdapter(adapter);
    }
}