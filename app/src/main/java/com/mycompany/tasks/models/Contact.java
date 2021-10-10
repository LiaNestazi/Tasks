package com.mycompany.tasks.models;

public class Contact {
    private String contactID;
    private String displayName;
    private String phone;

    public String getContactID() {
        return contactID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhone() {
        return phone;
    }

    public void setContactID(String contactID) {
        this.contactID = contactID;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
