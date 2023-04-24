package com.example.softmethproj5.ui.yourOrder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.softmethproj5.R;
import com.example.softmethproj5.databinding.FragmentYourOrderBinding;
import com.example.softmethproj5.ui.adapters.BasketAdapter;
import com.example.softmethproj5.ui.adapters.YourOrderAdapter;
import com.example.softmethproj5.ui.main.MainViewModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_your_order, container, false);
    }
}