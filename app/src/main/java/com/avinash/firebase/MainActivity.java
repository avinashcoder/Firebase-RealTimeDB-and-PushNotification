package com.avinash.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("User List");

//        myRef.child("user4").child("Name").setValue("Animesh");
//        myRef.child("user4").child("Contact").setValue("9876543210");
//        myRef.child("user4").child("Address").setValue("Bhabua");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child( "user1" ).child("Name").toString();
                Log.d("Tag", "Value is: " + value);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

                // Use this class to convert data into list
                //GenericTypeIndicator<List<ModelClass>> genericTypeIndicator =new GenericTypeIndicator<List<ModelClass>>(){};
                //List<TaskDes> taskDesList=dataSnapshot.getValue(genericTypeIndicator);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
