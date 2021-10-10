package com.mycompany.tasks.presentation.view;

import android.Manifest;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.CursorLoader;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.tasks.R;
import com.mycompany.tasks.databinding.FragmentPhoneBookBinding;
import com.mycompany.tasks.models.Contact;
import com.mycompany.tasks.presentation.view.adapters.ContactListAdapter;
import com.mycompany.tasks.presentation.viewModel.ContactViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PhoneBookFragment extends Fragment {

    private FragmentPhoneBookBinding binding;
    private ContactViewModel contactViewModel;
    private Cursor cursor;
    List<Contact> listContact;
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (!isGranted) {
                    Toast.makeText(getContext(), "Необходимо разрешение", Toast.LENGTH_SHORT).show();
                    requestPermission();
                } else{
                    setData(getContext());
                }
            });

    public void requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPhoneBookBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        //Create list
        listContact = new ArrayList<Contact>();

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        } else{
            setData(getContext());
        }




        // ListViewAdapter
        ContactListAdapter adapter = new ContactListAdapter(getContext(), listContact);
        ListView listView = binding.listView;
        listView.setAdapter(adapter);

        //List item on click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = ((TextView)view.findViewById(R.id.display_name_view)).getText().toString();
                String phone = ((TextView)view.findViewById(R.id.phone_view)).getText().toString();
                Log.d("MyTag", name+" "+phone);

                PhoneBookFragmentDirections.ActionPhoneBookFragmentToAddTaskFragment action = PhoneBookFragmentDirections.actionPhoneBookFragmentToAddTaskFragment();
                action.setName(name);
                action.setPhone(phone);
                NavHostFragment.findNavController(PhoneBookFragment.this).navigate(action);
            }
        });

        //Log.d("MyTag", list.get(0).getPhone());
    }


    public void setData(Context context) {

        //ContentProvider
        //CursorLoader cl = new CursorLoader(getContext(),ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        CursorLoader cl = new CursorLoader(context, ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        cursor = cl.loadInBackground();
        Contact tempContact;
//        cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                tempContact = new Contact();
                int id = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID);
                int name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int phone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                //Log.d("MyTag", cursor.getString(id)+" - "+cursor.getString(name) + " - " + cursor.getString(phone));
                tempContact.setContactID(cursor.getString(id));
                tempContact.setDisplayName(cursor.getString(name));
                tempContact.setPhone(cursor.getString(phone));
                //listContact.add(tempContact);
                if (!isInList(tempContact)){
                    listContact.add(tempContact);
                    //Log.d("MyTag", tempContact.getContactID()+" - "+tempContact.getDisplayName() + " - " + tempContact.getPhone());
                }
            }
        }
        //Log.d("MyTag",listContact.get(0).getContactID()+" "+listContact.get(1).getContactID()+" "+listContact.get(3).getContactID());
    }
    public boolean isInList(Contact contact){
        for (Contact c : listContact){
            if (c.getContactID().equals(contact.getContactID())){
                return true;
            }
        }
        return false;
    }

//            while (cursor.moveToNext()) {
//                if (cursor != null) {
//                    Contact tempContact = new Contact();
//                    int first_name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
//                    int last_name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME);
//                    int phone;
//                    int hasPhone = cursor.getColumnIndex(ContactsContract.Contacts.Entity.HAS_PHONE_NUMBER);
//                    if (cursor.getString(hasPhone).equals("1")){
//                        //first_name = cursor.getColumnIndex(ContactsContract.Contacts.Entity.DISPLAY_NAME);
//                        first_name = cursor.getColumnIndex(ContactsContract.Contacts.Entity.DISPLAY_NAME);
//                        //first_name = cursor.getColumnIndex(ContactsContract.PhoneLookup.NUMBER);
//                        phone = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//                        Log.d("MyTag", cursor.getString(first_name));
//
//                    }
                    //tempContact.setFirstName(cursor.getString(phone));
                   // Log.d("MyTag", cursor.getString(phone));
                    //tempContact.setLastName(cursor.getString(phone));
                    //list.add(tempContact);
               // }
           // }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}