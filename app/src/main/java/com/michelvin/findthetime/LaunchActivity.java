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

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }

    public void login(View view) {
        EditText name =  (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.email);
        EditText phoneNum = (EditText) findViewById(R.id.phone);

        if (validData(name, email, phoneNum)) {
            FindTheTimeRestClientUsage clientUsage = new FindTheTimeRestClientUsage(new Callback());
            try {
                clientUsage.postUser(this, name.getText().toString(), email.getText().toString(), phoneNum.getText().toString());
            } catch (JSONException e) {
                Log.e("fuck", "error adding user", e);
            }

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("phoneNum", phoneNum.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid data, please ensure that no fields are empty.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validData (EditText name, EditText email, EditText phoneNum) {
        if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() ||
                phoneNum.getText().toString().isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}
