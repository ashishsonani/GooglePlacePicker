package com.ashishsonani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {
    EditText placeName,placeAddress;
    Button button;
    int PLACE_PICKER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeName= (EditText) findViewById(R.id.placeName);
        placeAddress= (EditText) findViewById(R.id.placeAddress);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(MainActivity.this), PLACE_PICKER_REQUEST);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String Name = String.format("%s", place.getName());
                placeName.setText(Name);
                String Address = String.format("%s",place.getAddress(),"\n"+place.getLatLng().latitude,"\n"+place.getLatLng().longitude);
                placeAddress.setText(Address);
            }
        }
    }
}