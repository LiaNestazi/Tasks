package com.mycompany.tasks.presentation.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.mycompany.tasks.R;
import com.mycompany.tasks.databinding.FragmentAddTaskBinding;
import com.mycompany.tasks.models.User;
import com.mycompany.tasks.presentation.repository.dto.TaskDto;
import com.mycompany.tasks.presentation.viewModel.TaskViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskFragment extends Fragment {

    private FragmentAddTaskBinding binding;
    private TaskViewModel taskViewModel;

    private AddTaskFragmentArgs args;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        args = AddTaskFragmentArgs.fromBundle(getArguments());
        //taskViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity().getApplicationContext()).get(TaskViewModel.class);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        //SafeArgs
        String display_name = args.getName();
        String phone =args.getPhone();
        if (!display_name.equals("Создатель задачи")&& !phone.equals("Номер телефона")) {
            display_name = args.getName();
            phone = args.getPhone();
            binding.taskCreator.setText(display_name);
            binding.taskCreatorPhone.setText(phone);
        }


        //Insert data to DB
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataToDB();
            }
        });


        //Переход на выбор контакта
        binding.btnContactsChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddTaskFragment.this)
                        .navigate(R.id.action_AddTaskFragment_to_phoneBookFragment);
            }
        });



    }

    private void insertDataToDB() {
        String name = binding.taskName.getText().toString();
        String desc = binding.taskDescription.getText().toString();
        String creator = binding.taskCreator.getText().toString();
        String date = binding.taskDate.getText().toString();
        String creator_phone = binding.taskCreatorPhone.getText().toString();
        if (inputCheck(name, desc, creator, date)){
            TaskDto task = new TaskDto();
            task.setId(0);
            task.setName(name);
            task.setDesc(desc);
            User user = new User(creator);
            task.setCreator(user);
            task.setCreator_phone(creator_phone);
            Date tempDate;
            try {
                tempDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                task.setDate(tempDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            taskViewModel.addTask(task);
            Toast.makeText(getContext(),"Task added!", Toast.LENGTH_LONG).show();
            NavHostFragment.findNavController(AddTaskFragment.this)
                    .navigate(R.id.action_AddTaskFragment_to_TaskListFragment);
        } else{
            Toast.makeText(getContext(),"Please fill out all the fields.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean inputCheck(String name, String desc, String creator, String date){
        return !(TextUtils.isEmpty(name)&&TextUtils.isEmpty(desc)&&TextUtils.isEmpty(creator)&&TextUtils.isEmpty(date));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}