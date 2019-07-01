package com.example.app1;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends Fragment {
    int def = R.drawable.palmtree;

    ArrayList<ContactItem> mData;

    FloatingActionButton floatingActionButton;

    public Contacts(){
        // Required empty public constructor
    }




    public JSONArray getContacts() {

        String image = "";
        String name = "";
        String number = "";

        JSONArray contacts = new JSONArray();

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

        return contacts;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        JSONArray arr = getContacts();
        mData = new ArrayList<ContactItem>();
        for(int i = 0; i < arr.length(); i ++){
            try {
                JSONObject temp = arr.getJSONObject(i);
                String timage = temp.getString("image");
                String tname = temp.getString("name");
                String tnumber = temp.getString("number");
                if(timage.equals("null")){ mData.add(new ContactItem(tname, tnumber, def)); }
                else { mData.add(new ContactItem(tname, tnumber, Uri.parse(timage))); }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        RecyclerView rv = view.findViewById(R.id.contacts_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new ContactsAdapter(mData));


        floatingActionButton = view.findViewById(R.id.contacts_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CreateContact.class));
            }
        });


        return view;
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
}
