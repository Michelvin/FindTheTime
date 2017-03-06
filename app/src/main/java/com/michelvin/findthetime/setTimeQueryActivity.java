//@Authors Owen Galvin and Daniel Michelin
//@Date 3/6/2017

package com.michelvin.findthetime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class setTimeQueryActivity extends AppCompatActivity {
    ArrayList<Date> selectedDates = new ArrayList<>();
    String currentlySelected = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time_query);
    }

    public void addDate(View view) {
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        updateSelectedDates(getDateFromDatePicket(datePicker));
    }

    private void updateSelectedDates(Date newDate) {
        String dateString = new SimpleDateFormat("MMMM dd, yyyy").format(newDate);
        currentlySelected = currentlySelected + dateString + ", ";
        TextView selectedDates = (TextView) findViewById(R.id.selectedDates);
        selectedDates.setText(currentlySelected);
    }

    //from http://stackoverflow.com/questions/2592499/casting-and-getting-values-from-date-picker-and-time-picker-in-android
    private java.util.Date getDateFromDatePicket(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public void findATime(View view) {
        Intent intent = new Intent(this, findATimeActivity.class);
        intent.putExtra("SelectedDates", selectedDates);
        startActivity(intent);
    }
}
