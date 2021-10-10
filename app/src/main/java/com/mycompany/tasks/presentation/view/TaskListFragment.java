package com.mycompany.tasks.presentation.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.tasks.R;
import com.mycompany.tasks.databinding.FragmentTaskListBinding;
import com.mycompany.tasks.presentation.repository.dto.TaskDto;
import com.mycompany.tasks.presentation.view.adapters.TaskListAdapter;
import com.mycompany.tasks.presentation.viewModel.TaskViewModel;

import java.util.List;

public class TaskListFragment extends Fragment {

    private FragmentTaskListBinding binding;
    private TaskViewModel taskViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTaskListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setColorFilter(Color.WHITE);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TaskListFragment.this)
                        .navigate(R.id.action_TaskListFragment_to_AddTaskFragment);
            }
        });


        // RecyclerView
        TaskListAdapter adapter = new TaskListAdapter();
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        //TaskViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks.observe(getViewLifecycleOwner(), (List<TaskDto> tasks) -> {
            adapter.setData(tasks);
        });


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_delete){
            //Удаление всех записей
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}