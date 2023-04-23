package com.example.softmethproj5.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.softmethproj5.MainActivity;
import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentMainBinding;
import com.example.softmethproj5.ui.orderDonut.OrderDonutsFragment;

import java.util.Objects;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    FragmentMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();
        Button coffeeButton = (Button) binding.btOrderCoffee;
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                Fragment coffeeViewFragment = new CoffeeView();
                fragmentTransaction2.addToBackStack("mainToCoffee");
                fragmentTransaction2.hide(MainFragment.this);
                fragmentTransaction2.add(android.R.id.content, coffeeViewFragment);
                fragmentTransaction2.commit();
            }
        });
        return view;
    }

    @Override
    // is this method necessary? Difference between onCreate and onCreateView?
    public void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btOrderDonut.setOnClickListener(view1 -> requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, OrderDonutsFragment.newInstance())
                .addToBackStack(null)
                .commit());
    }



}