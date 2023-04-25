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
 * The YourOrderFragment class contains lifecycle callbacks for the your order fragment as
 * well as methods to add functionality to the your order fragment.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class YourOrderFragment extends Fragment {

    FragmentYourOrderBinding binding;

    MainViewModel viewModel;

    YourOrderAdapter adapter;

    /**
     * Required empty public constructor
     */
    public YourOrderFragment() { }

    /**
     * Creates a new instance of YourOrderFragment.
     * @return A new instance of YourOrderFragment.
     */
    public static YourOrderFragment newInstance() { return new YourOrderFragment(); }

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_your_order, container, false);
        binding.setLifecycleOwner(this);
        binding.setOrder(viewModel.getCurrentOrder().getValue());
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

    /**
     * Sets up the RecyclerView containing all the items in the Order.
     */
    private void setUpBasketRecycler () {
        binding.rvOrderItems.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new YourOrderAdapter(viewModel);
        binding.rvOrderItems.setAdapter(adapter);
    }
}