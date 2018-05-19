package com.example.kushalkatta.phonebookduplicates;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnFindDuplicates;
    RecyclerView phonebookView;
    ArrayList<PhoneContact> phonebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phonebookView = (RecyclerView) findViewById(R.id.phonebookView);
        phonebookView.setLayoutManager(new LinearLayoutManager(this));
        phonebook = fillContacts();
        phonebookView.setAdapter(new PhonebookAdapter(phonebook));

        btnFindDuplicates = (Button) findViewById(R.id.btnFindDuplicates);
        btnFindDuplicates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<DuplicateContact> duplicatePhonebook = fillDuplicateContacts(phonebook);
                phonebookView.setAdapter(new DuplicatePhonebookAdapter(duplicatePhonebook));
            }
        });

    }

    public ArrayList<DuplicateContact> fillDuplicateContacts(ArrayList<PhoneContact> phonebook)
    {
        HashMap<String, ArrayList<String>> hashmap = new HashMap<>();
        ArrayList<DuplicateContact> duplicatePhonebook = new ArrayList<DuplicateContact>();
        for(PhoneContact contact : phonebook)
//        For Each Contact
        {
            for(String phoneNo : contact.getContacts())
//            For Each Contact No
            {
//                DuplicateContact duplicateContact = new DuplicateContact();
//                duplicateContact.setContactNo(phoneNo);
//                duplicateContact.addContactName(contact.getContactName());
//                duplicatePhonebook.add(duplicateContact);
                ArrayList<String> names = new ArrayList<>();
                if(hashmap.containsKey(phoneNo))
                {
                    names = hashmap.get(phoneNo);
                }
                names.add(contact.getContactName());
                hashmap.put(phoneNo,names);

            }
        }
        for(Map.Entry<String,ArrayList<String>> e : hashmap.entrySet())
        {
            if(e.getValue().size()>1)
            {
                duplicatePhonebook.add(new DuplicateContact(e.getKey(), e.getValue()));
            }
        }

//        System.out.println(hashmap.toString());
        return duplicatePhonebook;
    }

    public ArrayList<PhoneContact> fillContacts()
    {
        ArrayList<PhoneContact> phonebook = new ArrayList<PhoneContact>();

        Cursor c1;
        // list Columns to retive  , pass null to get all the columns
        String col[]={ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME};
        c1 = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, col, null, null, ContactsContract.Contacts.DISPLAY_NAME);


        String personName = null, number = "";
        if(c1==null)
            return phonebook;
        // Fetch the Corresponding Phone Number of  Person Name
        try
        {
            if(c1.getCount() > 0)
            {
                int i=0;
                while(c1.moveToNext())
//                while(c1.moveToNext() && i<10)
                {
                    PhoneContact contact = new PhoneContact();
                    i++;
                    String id = c1.getString(c1.getColumnIndex(ContactsContract.Contacts._ID));
                    personName = c1.getString(c1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contact.setContactName(personName);

                    if(id==null||personName==null)
                        continue;
                    Cursor cur = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id}, null);
                    if(cur==null)
                        continue;
                    number = "";
                    while(cur.moveToNext())
                    {
                        number = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.addContact(number);
                    }
                    cur.close();
                    phonebook.add(contact);
                }
            }
        }
        catch(Exception e)
        {

        }
        finally
        {
            c1.close();
            return phonebook;
        }
    }
}
