package com.example.dilogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.TimeAnimator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAlert;
    private Button btnDatePicker;
    private Button btnTimepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btnAlert.setOnClickListener(
                new BtnAlertClickListener()
        );

        btnDatePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(
                                        MainActivity.this,
                                        new DateListener(),
                                        2000,
                                        11,
                                        25
                                );
                        datePickerDialog.show();
                    }
                }
                );

        btnTimepicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog =
                                new TimePickerDialog(
                                        MainActivity.this,
                                        new TimeListener(),
                                        22,
                                        40,
                                        true
                                );
                        timePickerDialog.show();
                    }
                }
        );
    }

    private class BtnAlertClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("Bitcode Tech ");
            builder.setMessage("Do you want to work hard");

            DialogInterface.OnClickListener listener =
                    new AlertButtonsClickListener();

            builder.setPositiveButton("Yes",listener);
            builder.setNegativeButton("No",listener);
            builder.setNeutralButton("hmm",listener);

            //builder.setCancelable(true);

            builder.setOnCancelListener(
                    new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            mt("Cancelled");
                        }
                    }
            );

            builder.setOnDismissListener(
                    new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            mt("Dismissed");
                        }
                    }
            );

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private class DateListener implements DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            btnDatePicker.setText(dayOfMonth + "-" + (month+1) + "-" + year);
        }
    }

    private class TimeListener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            btnTimepicker.setText(hourOfDay + " : " + minute);
        }
    }

    private class BtnYesClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You said tou will work hard! Welcome to Bitcode");
            mt("Yes: " + dialog.hashCode());
        }
    }
    private class BtnNoClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You said you will Not hard work! Pay the fees and leave!");
        }
    }

    private class BtnHmmClickListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("??????");
        }
    }

    private class AlertButtonsClickListener implements DialogInterface.OnClickListener{
        public void onClick (DialogInterface dialog,int which){
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    mt("Yes");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    mt("No");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    mt("Hmm");
                    break;
            }
        }
    }

    private void initView() {
        btnAlert = findViewById(R.id.btnAlert);
        btnDatePicker = findViewById(R.id.btnDate);
        btnTimepicker = findViewById(R.id.btnTime);
    }

    private void mt(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        Log.e("tag",text);
    }
}