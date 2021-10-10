package com.mycompany.tasks.presentation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mycompany.tasks.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    public List<Contact> getAllContacts;
    private List<Contact> list;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        list = new ArrayList<Contact>();
        Contact c = new Contact();
        c.setDisplayName("Ana Li");
        c.setPhone("+7");
        list.add(c);
        getAllContacts = list;
    }

}
