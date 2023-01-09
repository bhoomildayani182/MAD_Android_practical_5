package com.example.Practical_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat mode;
    private TextView dob, todaysdate;
    private TextView day1, day2, day3, day4, day5;
    private TextView daySpend, monthSpend, yearSpend;
    private TextView nxtBday;
    private Button calculate;
    public String dobDate, nxtbday,nxtbday0, nxtbday1, nxtbday2, nxtbday3, nxtbday4, nxtbday5;
    public LocalDate localDate1, localDate2;
    public Period period;
    DatePickerDialog.OnDateSetListener setListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mode = findViewById(R.id.modeSwitch);
        dob = findViewById(R.id.dob);
        todaysdate = findViewById(R.id.todaydate);
        calculate = findViewById(R.id.calculate);

        daySpend = findViewById(R.id.spendDate);
        monthSpend = findViewById(R.id.spendMonth);
        yearSpend = findViewById(R.id.spendYear);


        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            mode.setChecked(true);
        }

        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        Calendar calendar = getInstance();
        final int yyyy = calendar.get(YEAR);
        final  int mm = calendar.get(MONTH);
        final int dd = calendar.get(DAY_OF_MONTH);

        String aaj;

        if(mm<10){
            todaysdate.setText(""+dd+"-0"+(mm+1)+"-"+yyyy);
            aaj = ""+dd+"-0"+(mm+1)+"-"+yyyy;
        }else{
            todaysdate.setText(""+dd+"-"+(mm+1)+"-"+yyyy);
            aaj = ""+dd+"-"+(mm+1)+"-"+yyyy;
        }

        final String today = aaj;

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,setListner,2000,00,01);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String DATE = null;

                if (month < 10 && dayOfMonth >= 10){
                    DATE = dayOfMonth+"-0"+month+"-"+year;
                    nxtbday0 = dayOfMonth+"-0"+month+"-"+(yyyy);
                    nxtbday = dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday1 = dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday2 = dayOfMonth+"-0"+month+"-"+(yyyy+2);
                    nxtbday3 = dayOfMonth+"-0"+month+"-"+(yyyy+3);
                    nxtbday4 = dayOfMonth+"-0"+month+"-"+(yyyy+4);
                    nxtbday5 = dayOfMonth+"-0"+month+"-"+(yyyy+5);
                }else  if (month < 10 && dayOfMonth < 10){
                    DATE = "0"+dayOfMonth+"-0"+month+"-"+year;
                    nxtbday0 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy);
                    nxtbday = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday1 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+1);
                    nxtbday2 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+2);
                    nxtbday3 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+3);
                    nxtbday4 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+4);
                    nxtbday5 = "0"+dayOfMonth+"-0"+month+"-"+(yyyy+5);
                }else if (dayOfMonth < 10 && month >= 10){
                    DATE = "0"+dayOfMonth+"-"+month+"-"+year;
                    nxtbday0 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+1);
                    nxtbday = "0"+dayOfMonth+"-"+month+"-"+(yyyy+1);
                    nxtbday1 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+1);
                    nxtbday2 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+2);
                    nxtbday3 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+3);
                    nxtbday4 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+4);
                    nxtbday5 = "0"+dayOfMonth+"-"+month+"-"+(yyyy+5);
                }
                dobDate = DATE;

                dob.setText(DATE);
            }
        };

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String check = dob.getText().toString().trim();
                if (check.isEmpty()){
                    dob.setError("Enter DOB");
                }else{
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Intent getV = new Intent(getApplicationContext(),MainActivity.class);
                        Log.d("Today Date Error : ", today);
                        Log.d("Dob Date error : ", dobDate);
                        Date d1 = simpleDateFormat.parse(today);
                        Date d2 = simpleDateFormat.parse(dobDate);

                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTimeInMillis(d1.getTime());


                        Calendar calendar2 = Calendar.getInstance();
                        calendar2.setTimeInMillis(d2.getTime());

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            localDate1 = LocalDate.of(calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH )+1,calendar1.get(Calendar.DAY_OF_MONTH));
                            localDate2 = LocalDate.of(calendar2.get(Calendar.YEAR),calendar2.get(Calendar.MONTH )+1,calendar2.get(Calendar.DAY_OF_MONTH));


                            if (localDate1 != null && localDate2 != null){
                                period = Period.between(localDate2,localDate1);

                                if (period.getYears() < 10){
                                    yearSpend.setText("0"+period.getYears());
                                }else{
                                    yearSpend.setText(""+period.getYears());
                                }
                                if (period.getMonths() < 10){
                                    monthSpend.setText("0"+period.getMonths());
                                }else{
                                    monthSpend.setText(""+period.getMonths());
                                }
                                if (period.getDays() < 10){
                                    daySpend.setText("0"+period.getDays());
                                }else {
                                    daySpend.setText(""+period.getDays());
                                }
                            }
                        }else{
                            Log.d("ERROR1 : ", "VERSION PROBLEM");
                        }


                    }catch (Exception e){
                        Log.d("ERROR : ", String.valueOf(e));
                        Toast.makeText(MainActivity.this,"ERROR : "+e,Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}