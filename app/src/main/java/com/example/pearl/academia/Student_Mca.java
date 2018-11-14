package com.example.pearl.academia;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student_Mca extends Activity {

    ListView listView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference;

    //list to store uploads data
    List<Upload> uploadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mca);

        uploadList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.mca_listView);


        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                Upload upload = uploadList.get(i);

                //Opening the upload file in browser using the upload url
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(upload.getUrl()));
                startActivity(intent);
            }
        });

        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //retrieving upload data from firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    if(mDatabaseReference.child("name").toString().contains("MCA_")){
                        uploadList.add(upload);
                    }
                    else Toast.makeText(getApplicationContext(),"Empty List",Toast.LENGTH_SHORT).show();


                }

                String[] uploads = new String[uploadList.size()];

                for (int i = 0; i < uploads.length; i++) {

                    uploads[i] = uploadList.get(i).getName();
                    /*if(uploads[i].contains("MCA_")){
                        uploads[i] = uploadList.get(i).getName();
                    }
                    else Toast.makeText(getApplicationContext(),"Empty List",Toast.LENGTH_SHORT).show();
                    */

                }

                //displaying it to list
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
