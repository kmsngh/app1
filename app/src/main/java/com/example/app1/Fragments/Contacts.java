package com.example.app1.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.provider.ContactsContract.CommonDataKinds.Phone;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app1.Classes.ContactItem;
import com.example.app1.FragmentActivities.CreateContact;
import com.example.app1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends Fragment {
    private int CONTACTS_PERMISSION_CODE = 1;
    int def = R.drawable.palmtree;

    ArrayList<ContactItem> mData;
    JSONArray arr;

    ContactsAdapter contactsAdapter;

    FloatingActionButton floatingActionButton;
    RelativeLayout contact_item;
    RecyclerView rv;

    public Contacts(){
        // Required empty public constructor
    }


    public JSONArray getContacts() {



        String image = "";
        String name = "";
        String number = "";

        JSONArray contacts = new JSONArray();

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        CONTACTS_PERMISSION_CODE);
            }
        } else {
            Cursor cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        JSONObject contact = new JSONObject();
                        image = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI));
                        name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        if (image == null){
                            image = "null";
                        }
                        try{
                            contact.put("image", image);
                            contact.put("name", name);
                            contact.put("number", number);}
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        contacts.put(contact);
                    }
                }
            }
            // Permission has already been granted
        }

        return contacts;
    }

    public ArrayList<ContactItem> parseJSON (JSONArray contactsJSON){
        ArrayList<ContactItem> contactsList = new ArrayList<>();

        for(int i = 0; i < contactsJSON.length(); i ++){
            try {
                JSONObject temp = contactsJSON.getJSONObject(i);
                String timage = temp.getString("image");
                String tname = temp.getString("name");
                String tnumber = temp.getString("number");
                if(timage.equals("null")){ contactsList.add(new ContactItem(tname, tnumber, def)); }
                else { contactsList.add(new ContactItem(tname, tnumber, Uri.parse(timage))); }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return contactsList;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        arr = getContacts();
        mData = parseJSON(arr);


        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        rv = view.findViewById(R.id.contacts_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        contactsAdapter = new ContactsAdapter(mData);
        rv.setAdapter(contactsAdapter);


        floatingActionButton = view.findViewById(R.id.contacts_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CreateContact.class);
                startActivityForResult(i, 1);
            }
        });





        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String newname = data.getStringExtra("name");
                String newphone = data.getStringExtra("phone");
                contactsAdapter.add(new ContactItem(newname,newphone, def));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> {

//        Context mContext;
        ArrayList<ContactItem> mData;



        public ContactsAdapter(/*Context mContext,*/ ArrayList<ContactItem> mData) {
//            this.mContext = mContext;
            this.mData = mData;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            View layout = layoutInflater.inflate(R.layout.contact_item, parent, false);

            return new ContactViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

//        bind data here


            holder.tv_name.setText(mData.get(position).getName());
            holder.tv_number.setText(mData.get(position).getNumber());
            if (mData.get(position).getUserPhoto() != null){
                holder.img_user.setImageURI(mData.get(position).getUserPhoto());
            }
            else {
                holder.img_user.setImageResource(mData.get(position).getUser());
            }

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public void add(ContactItem newitem){
            this.mData.add(newitem);
            this.notifyDataSetChanged();
        }

    }

    private void writeContact(String displayName, String number) {
        mData.add(new ContactItem(displayName, number, def));
        contactsAdapter = new ContactsAdapter(mData);
    }
    
    
    
    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_number;
        ImageView img_user;


        public ContactViewHolder(@NonNull View itemView){
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_number = itemView.findViewById(R.id.tv_number);
            img_user = itemView.findViewById(R.id.img_user);

        }
    }


    private void requestContactsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.READ_CONTACTS)) {

            new AlertDialog.Builder(getContext())
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[] {Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] {Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CONTACTS_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission GRANTED", Toast.LENGTH_SHORT).show();
                arr = getContacts();
                mData = parseJSON(arr);
                contactsAdapter = new ContactsAdapter(mData);
                contactsAdapter.notifyDataSetChanged();
            }
            else {
                requestContactsPermission();
                Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
