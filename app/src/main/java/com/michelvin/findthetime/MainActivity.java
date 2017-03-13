//@Authors Owen Galvin and Daniel Michelin
//@Date 3/6/2017

package com.michelvin.findthetime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOK(View view) {
        EditText targetPhone = (EditText) findViewById(R.id.targetPhone);

        if (targetPhone.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show();
        } else {
            Callback listener = new Callback(this);
            FindTheTimeRestClientUsage clientUsage = new FindTheTimeRestClientUsage(listener);

            try {
                clientUsage.getCalendar(this, targetPhone.getText().toString());
            } catch (JSONException e) {
                Log.e("fuck", "error retrieving target calendar", e);
            }
        }
    }

    public void startNextActivity (String email) {
        if (email != null) {
            Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, setTimeQueryActivity.class);
            intent.putExtra("calendarID", email);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Could not retrieve id, please check phone number", Toast.LENGTH_SHORT).show();
        }
    }
}
