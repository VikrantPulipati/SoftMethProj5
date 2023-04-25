package com.example.softmethproj5.ui.main;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentMainBinding;
import com.example.softmethproj5.ui.orderCoffee.OrderCoffeeFragment;
import com.example.softmethproj5.ui.orderDonut.OrderDonutsFragment;
import com.example.softmethproj5.ui.storeOrders.StoreOrdersFragment;
import com.example.softmethproj5.ui.yourOrder.YourOrderFragment;

/**
 * The MainFragment class contains lifecycle callbacks for the main menu fragment and
 * a method to switch fragments from the main fragment to a different fragment.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class MainFragment extends Fragment {


    /**
     * returns a new instance of a MainFragment.
     * @return a new instance of a MainFragment.
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    FragmentMainBinding binding;

    /**
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to. The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @return the root View of the fragment binding.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
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
        binding.btOrderDonut.setOnClickListener(view1 -> navigateMainToFragment(OrderDonutsFragment.newInstance()));
        binding.btOrderCoffee.setOnClickListener(view1 -> navigateMainToFragment(OrderCoffeeFragment.newInstance()));
        binding.btYourOrder.setOnClickListener(view1 -> navigateMainToFragment(YourOrderFragment.newInstance()));
        binding.btStoreOrders.setOnClickListener(view1 -> navigateMainToFragment(StoreOrdersFragment.newInstance()));
    }

    /**
     * Switches fragments from the main fragment to a different fragment
     * @param fragment the fragment you wish to switch to.
     */
    private void navigateMainToFragment (Fragment fragment) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }


}