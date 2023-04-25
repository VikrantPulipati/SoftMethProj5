package com.example.softmethproj5.ui.yourOrder;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentYourOrderBinding;
import com.example.softmethproj5.ui.adapters.YourOrderAdapter;
import com.example.softmethproj5.ui.main.MainFragment;
import com.example.softmethproj5.ui.MainViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourOrderFragment extends Fragment {

    FragmentYourOrderBinding binding;

    MainViewModel viewModel;

    YourOrderAdapter adapter;

    /**
     * Required public constructor
     */
    public YourOrderFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment YourOrderFragment.
     */
    public static YourOrderFragment newInstance() { return new YourOrderFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_your_order, container, false);
        binding.setLifecycleOwner(this);
        binding.setOrder(viewModel.getCurrentOrder().getValue());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getCurrentOrder().observe(getViewLifecycleOwner(), order -> adapter.updateItemList());

        binding.btPlaceOrder.setOnClickListener(view1 -> {
            if (Objects.requireNonNull(viewModel.getCurrentOrder().getValue()).getItems().isEmpty()) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("No items to order.")
                        .setNeutralButton(android.R.string.ok, (dialog, id) -> dialog.dismiss())
                        .show();
                return;
            }
            viewModel.placeOrder();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance()).commit();
        });

        setUpBasketRecycler();

    }

    private void setUpBasketRecycler () {
        binding.rvOrderItems.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new YourOrderAdapter(viewModel);
        binding.rvOrderItems.setAdapter(adapter);
    }
}