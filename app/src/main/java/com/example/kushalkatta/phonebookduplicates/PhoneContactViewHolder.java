package com.example.kushalkatta.phonebookduplicates;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PhoneContactViewHolder extends RecyclerView.ViewHolder
{
    TextView contactName, contactNo;
    public PhoneContactViewHolder(View itemView)
    {
        super(itemView);
        contactName = (TextView) itemView.findViewById(R.id.contactName);
        contactNo = (TextView) itemView.findViewById(R.id.contactNo);
    }
    public void setContact(String contactName, String contactNo)
    {
        this.contactName.setText(contactName);
        this.contactNo.setText(contactNo);
    }
}