package com.example.kushalkatta.phonebookduplicates;

import java.util.ArrayList;

public class DuplicateContact {
    String contactNo;
    ArrayList<String> contactNames;

    public DuplicateContact()
    {
        contactNames=  new ArrayList<String>();
    }

    public DuplicateContact(String contactNo, ArrayList<String> contactNames) {
        this.contactNo = contactNo;
        this.contactNames = contactNames;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void addContactName(String contactName)
    {
        contactNames.add(contactName);
    }
    public String getContactName()
    {
        return contactNames.toString();
    }
}
