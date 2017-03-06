//@Authors Owen Galvin and Daniel Michelin
//@Date 3/6/2017

package com.michelvin.findthetime;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.util.Log;

import org.json.*;
import com.loopj.android.http.*;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

//This class is based on http://loopj.com/android-async-http/
public class FindTheTimeRestClientUsage {
    AsyncHttpClient client = new AsyncHttpClient();

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
}
