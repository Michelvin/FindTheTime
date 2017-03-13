//@Authors Owen Galvin and Daniel Michelin
//@Date 3/6/2017

package com.michelvin.findthetime;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.*;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

//This class is based on http://loopj.com/android-async-http/
public class FindTheTimeRestClientUsage{
    AsyncHttpClient client = new AsyncHttpClient();
    String thiscalendarID;
    OnTaskCompleted listener;

    FindTheTimeRestClientUsage (OnTaskCompleted listener) {
        this.listener = listener;
    }

    public void postUser(Context context, String name, String email, String phoneNum) throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("name", name);
        jsonParams.put("calendarid", email);
        jsonParams.put("phonenumber", phoneNum);
        jsonParams.put("key", "1234");
        try {
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(context, "https://calendarapp.gomix.me/api/newuser", entity, "application/json",
                    new JsonHttpResponseHandler() {});
        } catch(UnsupportedEncodingException e) {
            Log.e("JSON", "Failed to encode Json Object", e);
        }
    }

    public void getCalendar(Context context, String phoneNum) throws JSONException {

        JSONObject jsonParams = new JSONObject();
        jsonParams.put("phonenumber", phoneNum);
        jsonParams.put("key", "1234");
        try {
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(context, "https://calendarapp.gomix.me/api/getcalendar", entity, "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] resbytes) {
                            String res = "";

                            try {
                                res = new String(resbytes, "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                Log.e("FAILURE", "unsupported Encoding", e);
                            }
                            Log.e("Success", res);
                           listener.onTaskCompleted(res);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] resbytes, Throwable t) {
                            Log.e("Failure", "" + statusCode);
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        }
                    });
        } catch(UnsupportedEncodingException e) {
            Log.e("JSON", "Failed to encode Json Object", e);
        }
    }
}


