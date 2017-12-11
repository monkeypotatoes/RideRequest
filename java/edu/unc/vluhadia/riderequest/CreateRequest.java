package edu.unc.vluhadia.riderequest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateRequest extends AppCompatActivity {

    Button submit;
    Button cancel;
    EditText destination;
    EditText phone_number;
    TimePicker depart_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request2);

        submit = (Button)findViewById(R.id.request_your_ride);
        cancel = (Button)findViewById(R.id.cancel);
        destination = (EditText)findViewById(R.id.destination);
        phone_number = (EditText)findViewById(R.id.phone_number);
        depart_time = (TimePicker)findViewById(R.id.depart_time);

    }

    public void writeDB (View v) {
        boolean validate = true;


        String dest = destination.getText().toString();
        String phone = phone_number.getText().toString();
        int hour = depart_time.getCurrentHour();
        int minute = depart_time.getCurrentMinute();
        String depart = hour+":"+minute;

        if (dest.equals("")) {
            validate = false;
            Toast.makeText(CreateRequest.this, "Please fill out the Destination field!",
                    Toast.LENGTH_SHORT).show();
        }
        if (phone.equals("")) {
            validate = false;
            Toast.makeText(CreateRequest.this, "Please fill out the Phone Number field!",
                    Toast.LENGTH_SHORT).show();
        }
        if (depart.equals("")) {
            validate = false;
            Toast.makeText(CreateRequest.this, "Please fill out the Depart Time field!",
                    Toast.LENGTH_SHORT).show();
        }



        if (validate) {
            Request r = new Request(phone, dest, depart);

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference requests = db.getReference("Requests");

            requests.child(phone).setValue(r);

            Toast.makeText(CreateRequest.this, "Ride Requested!",
                    Toast.LENGTH_SHORT).show();

            //startActivity(new Intent(CreateRequest.this, MainActivity.class));
            this.finish();
        }






    }

    public void cancel (View v) {

        //startActivity(new Intent(CreateRequest.this, MainActivity.class));
        this.finish();

    }





}
