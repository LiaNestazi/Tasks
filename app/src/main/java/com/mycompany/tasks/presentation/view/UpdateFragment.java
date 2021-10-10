package com.mycompany.tasks.presentation.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.tasks.R;
import com.mycompany.tasks.databinding.FragmentAddTaskBinding;
import com.mycompany.tasks.databinding.FragmentUpdateBinding;
import com.mycompany.tasks.presentation.viewModel.TaskViewModel;


public class UpdateFragment extends Fragment {

    private FragmentUpdateBinding binding;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}