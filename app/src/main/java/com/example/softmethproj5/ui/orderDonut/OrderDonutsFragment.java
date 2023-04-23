package com.example.softmethproj5.ui.orderDonut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentOrderDonutsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDonutsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDonutsFragment extends Fragment {

    FragmentOrderDonutsBinding binding;

    public OrderDonutsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OrderDonutsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDonutsFragment newInstance() {
        return new OrderDonutsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_donuts, container, false);
        return binding.getRoot();
    }


}