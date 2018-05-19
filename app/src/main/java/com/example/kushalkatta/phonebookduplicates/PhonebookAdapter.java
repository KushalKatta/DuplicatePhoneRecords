package com.example.kushalkatta.phonebookduplicates;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PhonebookAdapter extends RecyclerView.Adapter<PhoneContactViewHolder> {

    ArrayList<PhoneContact> phonebook;

    public PhonebookAdapter(ArrayList<PhoneContact> phonebook) {
        this.phonebook = phonebook;
    }

    @NonNull
    @Override
    public PhoneContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.phone_contact, parent, false);
        return new PhoneContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneContactViewHolder holder, int position) {
        String contactName = phonebook.get(position).getContactName();
        String contactNo = phonebook.get(position).getContactNo();
        holder.setContact(contactName, contactNo);
    }

    @Override
    public int getItemCount() {
        return phonebook.size();
    }
}
