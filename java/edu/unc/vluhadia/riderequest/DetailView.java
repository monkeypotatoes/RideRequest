package edu.unc.vluhadia.riderequest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailView extends AppCompatActivity {


    TextView locationText;
    TextView phoneNumberText;
    TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        locationText = (TextView) findViewById(R.id.locationText);
        phoneNumberText = (TextView) findViewById(R.id.phoneNumberText);
        timeText = (TextView) findViewById(R.id.timeText);

        locationText.setText(MainActivity.clickedLocation);
        phoneNumberText.setText(MainActivity.clickedNumber);
        timeText.setText(MainActivity.clickedTime);





    }
    public void deleteFromDb () {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference requests = db.getReference("Requests");
        Log.v("a",phoneNumberText.toString());
        requests.child(MainActivity.clickedNumber).removeValue();


    }

    public void accept(View v) {
        Toast.makeText(DetailView.this, "Request Accepted!",
                Toast.LENGTH_SHORT).show();
        //firebase delete method call
        deleteFromDb();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumberText));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},   //request specific permission from user
                    10);
            return;
        }else {
            startActivity(callIntent);

        }

    }


}
