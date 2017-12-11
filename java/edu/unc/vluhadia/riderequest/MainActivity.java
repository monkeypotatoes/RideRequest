package edu.unc.vluhadia.riderequest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    ListView openRequests;
    public static String clickedLocation;
    public static String clickedNumber;
    public static String clickedTime;
    private ArrayList<DataSnapshot> dataArray;
    public static DataSnapshot clickedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataArray = new ArrayList<>();


        makeList();
    }

    public void createEvent (View v) {
        startActivity(new Intent(MainActivity.this, CreateRequest.class));


    }
    public void makeList(){
        openRequests = (ListView) findViewById(R.id.listView);
        final CustomAdapter adapter = new CustomAdapter(dataArray,getApplicationContext());
        openRequests.setAdapter(adapter);

        openRequests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                clickedItem = (DataSnapshot) (openRequests.getItemAtPosition(position));
                String selectedValue = openRequests.getAdapter().getItem(position).toString();
                clickedLocation = clickedItem.child("destination").getValue().toString();
                clickedNumber = clickedItem.getKey().toString();
                clickedTime = fixTime(clickedItem.child("time").getValue().toString());
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getApplicationContext(), DetailView.class);
                startActivity(i);
            }
        });

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Requests");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    DataSnapshot value = child;
                    dataArray.add(value);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }

    public static String fixTime(String str){
        if(str.substring(str.indexOf(':')).length()<3){
            str += '0';
        }
        return str;
    }

}
