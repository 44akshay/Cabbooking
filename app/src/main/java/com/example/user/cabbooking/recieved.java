package com.example.user.cabbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class recieved extends AppCompatActivity {

   FirebaseListAdapter<vehicle> firebaseListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved);
        getSupportActionBar().setTitle("Raysil Cab vehicle recieving page");
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        User user = SharedPrefManager.getInstance(this).getUser();
        String empid=user.getEmp_id();
        DatabaseReference databaseReference=firebaseDatabase.getReference("user").child(empid);
        databaseReference.keepSynced(true);
        final ListView lst=(ListView)findViewById(R.id.lst);
        TextView empty=(TextView)findViewById(R.id.Empty);
        lst.setEmptyView(empty);
        firebaseListAdapter=new FirebaseListAdapter<vehicle>(recieved.this,vehicle.class,android.R.layout.simple_list_item_1,
                databaseReference) {
            @Override
            protected void populateView(View v, vehicle model, int position) {

                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getRemarks()+
                        "\n"+"Car name :"+model.getCarname()+"\n"+"Car no :"+model.getCarno()+
                "\n"+"Driver's no :"+model.getCardriverno());
            }
        };
        lst.setAdapter(firebaseListAdapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent intent=new Intent();
                intent.setClass(recieved.this,completed.class);
                startActivity(intent);
                DatabaseReference myref=firebaseListAdapter.getRef(position);
                myref.removeValue();
            }
        });
    }
}
