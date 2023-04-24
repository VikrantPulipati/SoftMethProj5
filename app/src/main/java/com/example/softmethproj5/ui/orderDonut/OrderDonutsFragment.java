package com.example.softmethproj5.ui.orderDonut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentOrderDonutsBinding;
import com.example.softmethproj5.models.Donut;
import com.example.softmethproj5.models.MenuItem;
import com.example.softmethproj5.ui.adapters.BasketAdapter;
import com.example.softmethproj5.ui.adapters.DonutFlavorAdapter;
import com.example.softmethproj5.ui.main.MainFragment;
import com.example.softmethproj5.ui.main.MainViewModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDonutsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDonutsFragment extends Fragment {

    FragmentOrderDonutsBinding binding;

    MainViewModel viewModel;

    DonutFlavorAdapter donutFlavorAdapter;
    BasketAdapter basketAdapter;

    /**
     * Required public constructor
     */
    public OrderDonutsFragment() { }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OrderDonutsFragment.
     */
    public static OrderDonutsFragment newInstance() { return new OrderDonutsFragment(); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_donuts, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpFlavorOptions();
        setUpDonutBasketRecycler();

        binding.btAddToOrder.setOnClickListener(view1 -> {
            viewModel.addCurrentScreenBasketToOrder();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance()).commit();
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.clearCurrentScreenBasket();
    }

    private void setUpFlavorOptions () {
        binding.rvDonutFlavorOptions.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        donutFlavorAdapter = new DonutFlavorAdapter(getFlavorList(), viewModel);
        binding.rvDonutFlavorOptions.setAdapter(donutFlavorAdapter);
    }

    private void setUpDonutBasketRecycler () {
        binding.rvDonutBasket.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        basketAdapter = new BasketAdapter(viewModel);
        binding.rvDonutBasket.setAdapter(basketAdapter);
        viewModel.getCurrentScreenBasket().observe(getViewLifecycleOwner(), menuItemIntegerMap -> ((BasketAdapter) Objects.requireNonNull(binding.rvDonutBasket.getAdapter())).updateItemList());
    }

    private List<Pair<String, Integer>> getFlavorList () {
        return List.of(
                new Pair<>(Donut.FLAVOR_JELLY, null),
                new Pair<>(Donut.FLAVOR_GLAZED, null),
                new Pair<>(Donut.FLAVOR_POWDERED, null),
                new Pair<>(Donut.FLAVOR_SUGAR, null),
                new Pair<>(Donut.FLAVOR_PUMPKIN, null),
                new Pair<>(Donut.FLAVOR_LEMON, null),

                new Pair<>(Donut.FLAVOR_BUTTERNUT, null),
                new Pair<>(Donut.FLAVOR_BLUEBERRY, null),
                new Pair<>(Donut.FLAVOR_CINNAMON, null),

                new Pair<>(Donut.FLAVOR_CHOCOLATE, R.drawable.flavor_chocolate),
                new Pair<>(Donut.FLAVOR_VANILLA, R.drawable.flavor_vanilla),
                new Pair<>(Donut.FLAVOR_COFFEE, R.drawable.flavor_cinnamon)
        );
    }
}