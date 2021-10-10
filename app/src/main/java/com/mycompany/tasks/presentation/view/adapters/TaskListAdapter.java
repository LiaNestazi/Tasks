package com.mycompany.tasks.presentation.view.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.tasks.R;
import com.mycompany.tasks.presentation.repository.dto.TaskDto;
import com.mycompany.tasks.presentation.view.TaskListFragment;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    private List<TaskDto> taskList;
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TaskDto currentItem = taskList.get(position);
        ((TextView)holder.itemView.findViewById(R.id.name_view)).setText(currentItem.getName().toString());
        ((TextView)holder.itemView.findViewById(R.id.desc_view)).setText(currentItem.getDesc().toString());
        ((TextView)holder.itemView.findViewById(R.id.creator_view)).setText(currentItem.getCreator().getLogin().toString());
        ((TextView)holder.itemView.findViewById(R.id.phone_view)).setText(currentItem.getCreator_phone().toString());
        LocalDate localDate = currentItem.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ((TextView)holder.itemView.findViewById(R.id.date_view)).setText(localDate.getDayOfMonth()+"-"+localDate.getMonthValue()+"-"+localDate.getYear());
        holder.itemView.findViewById(R.id.row_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),"Updating item", Toast.LENGTH_SHORT).show();
            }
        });
        Button shareBtn = (Button) holder.itemView.findViewById(R.id.row_layout).findViewById(R.id.share_button);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((TextView)holder.itemView.findViewById(R.id.name_view)).getText().toString();
                String desc = ((TextView)holder.itemView.findViewById(R.id.desc_view)).getText().toString();
                String creator = ((TextView)holder.itemView.findViewById(R.id.creator_view)).getText().toString();
                String date = ((TextView)holder.itemView.findViewById(R.id.date_view)).getText().toString();

                String sharing_pattern = name+" by "+creator+": \n"+desc+"\nTo be done on "+date;

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sharing_pattern);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                holder.itemView.getContext().startActivity(shareIntent);
            }
        });
        //if (currentItem.isDone()){
        //    ((CheckBox)holder.itemView.findViewById(R.id.checkBox)).setChecked(true);
        //}
        //else{
        //    ((CheckBox)holder.itemView.findViewById(R.id.checkBox)).setChecked(false);
        //}
    }

    @Override
    public int getItemCount() {
        if (taskList != null)
            return taskList.size();
        else
            return 0;
    }

    public void setData(List<TaskDto> task){
        this.taskList = task;
        notifyDataSetChanged();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
        public TaskViewHolder(View view){
            super(view);

        }

    }
}
