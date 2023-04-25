package com.example.softmethproj5.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.softmethproj5.MainActivity;
import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentMainBinding;
import com.example.softmethproj5.ui.orderDonut.OrderDonutsFragment;
import com.example.softmethproj5.ui.yourOrder.YourOrderFragment;

import java.util.Objects;

public class MainFragment extends Fragment {


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btOrderDonut.setOnClickListener(view1 -> navigateMainToFragment(OrderDonutsFragment.newInstance()));
        binding.btOrderCoffee.setOnClickListener(view1 -> navigateMainToFragment(CoffeeView.newInstance("blah", "blah")));
        binding.btYourOrder.setOnClickListener(view1 -> navigateMainToFragment(YourOrderFragment.newInstance()));
    }

    private void navigateMainToFragment (Fragment fragment) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }


}