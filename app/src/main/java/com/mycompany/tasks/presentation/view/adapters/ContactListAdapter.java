package com.mycompany.tasks.presentation.view.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mycompany.tasks.R;
import com.mycompany.tasks.models.Contact;

import java.util.List;


public class ContactListAdapter extends ArrayAdapter<Contact> {

    Context context;
    private List<Contact> contactList;

    public ContactListAdapter(Context c,List<Contact> list){
        super(c, R.layout.custom_row_contact, R.id.display_name_view, list);
        this.context=c;
        this.contactList=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.custom_row_contact, parent, false);
        TextView name_view = row.findViewById(R.id.display_name_view);
        TextView phone_view = row.findViewById(R.id.phone_view);
        if (contactList.get(position) != null){
            name_view.setText(contactList.get(position).getDisplayName());
            phone_view.setText(contactList.get(position).getPhone());
        }
        return row;
    }
}
