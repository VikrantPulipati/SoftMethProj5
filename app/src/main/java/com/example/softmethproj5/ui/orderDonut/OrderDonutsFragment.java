package com.example.softmethproj5.ui.orderDonut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentOrderDonutsBinding;
import com.example.softmethproj5.models.Donut;
import com.example.softmethproj5.ui.adapters.BasketAdapter;
import com.example.softmethproj5.ui.adapters.DonutFlavorAdapter;
import com.example.softmethproj5.ui.main.MainFragment;
import com.example.softmethproj5.ui.MainViewModel;

import java.util.List;
import java.util.Objects;

/**
 * The OrderDonutsFragment class contains lifecycle callbacks for the order donuts fragment as
 * well as methods to add functionality to the Buttons and RecyclerView of the order donuts fragment.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class OrderDonutsFragment extends Fragment {

    FragmentOrderDonutsBinding binding;

    MainViewModel viewModel;

    DonutFlavorAdapter donutFlavorAdapter;
    BasketAdapter basketAdapter;

    /**
     * Required empty public constructor
     */
    public OrderDonutsFragment() { }

    /**
     * Creates a new instance of OrderDonutsFragment.
     * @return A new instance of OrderDonutsFragment.
     */
    public static OrderDonutsFragment newInstance() { return new OrderDonutsFragment(); }

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_donuts, container, false);
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

        setUpFlavorOptions();
        setUpDonutBasketRecycler();

        binding.btAddToOrder.setOnClickListener(view1 -> {
            if (Objects.requireNonNull(viewModel.getCurrentScreenBasket().getValue()).isEmpty()) {
                Toast.makeText(requireContext(), "Basket is empty.", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.addCurrentScreenBasketToOrder();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance()).commit();
        });
    }

    /**
     * Lifecycle callback which runs when the fragment stops.
     */
    @Override
    public void onStop() {
        super.onStop();
        viewModel.clearCurrentScreenBasket();
    }

    /**
     * Inputs all flavor options into the donut flavors RecyclerView.
     */
    private void setUpFlavorOptions () {
        binding.rvDonutFlavorOptions.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        donutFlavorAdapter = new DonutFlavorAdapter(getFlavorList(), viewModel);
        binding.rvDonutFlavorOptions.setAdapter(donutFlavorAdapter);
    }

    /**
     * Sets up the ordering basket RecyclerView.
     */
    private void setUpDonutBasketRecycler () {
        binding.rvDonutBasket.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        basketAdapter = new BasketAdapter(viewModel);
        binding.rvDonutBasket.setAdapter(basketAdapter);
        viewModel.getCurrentScreenBasket().observe(getViewLifecycleOwner(), menuItemIntegerMap -> basketAdapter.updateItemList());
    }

    /**
     * Gets the list of donut flavors.
     * @return a List of Pairs containing the id of each flavor and an image for each flavor.
     */
    private List<Pair<String, Integer>> getFlavorList () {
        return List.of(
                new Pair<>(Donut.FLAVOR_JELLY, R.drawable.flavor_jelly),
                new Pair<>(Donut.FLAVOR_GLAZED, R.drawable.flavor_glazed),
                new Pair<>(Donut.FLAVOR_POWDERED, R.drawable.flavor_powdered),
                new Pair<>(Donut.FLAVOR_SUGAR, R.drawable.flavor_sugar),
                new Pair<>(Donut.FLAVOR_PUMPKIN, R.drawable.flavor_pumpkin),
                new Pair<>(Donut.FLAVOR_LEMON, R.drawable.flavor_lemon),

                new Pair<>(Donut.FLAVOR_BUTTERNUT, R.drawable.flavor_butternut),
                new Pair<>(Donut.FLAVOR_BLUEBERRY, R.drawable.flavor_blueberry),
                new Pair<>(Donut.FLAVOR_CINNAMON, R.drawable.flavor_cinnamon),

                new Pair<>(Donut.FLAVOR_CHOCOLATE, R.drawable.flavor_chocolate),
                new Pair<>(Donut.FLAVOR_VANILLA, R.drawable.flavor_vanilla),
                new Pair<>(Donut.FLAVOR_COFFEE, R.drawable.flavor_coffee)
        );
    }
}