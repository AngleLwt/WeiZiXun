package com.wentuo.weizixun.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.wentuo.weizixun.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderActivity extends AppCompatActivity {

    @BindView(R.id.mcv)
    MaterialCalendarView mcv;
    @BindView(R.id.btn_choose)
    Button btnChoose;
    private String newDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        ButterKnife.bind(this);
        Date date = new Date();
        long time = date.getTime();
        mcv.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2016, 4, 3))
                .setMaximumDate(CalendarDay.from(2020, 6, 15))
                .setCalendarDisplayMode(CalendarMode.MONTHS)

                .commit();
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                newDate = format.format(date.getCalendar().getTime());
            }
        });

    }

    @OnClick(R.id.btn_choose)
    public void onViewClicked() {
        if (newDate.isEmpty()) {
            Toast.makeText(CalenderActivity.this, "请选择日期", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("date", newDate);
        setResult(200, intent);
        finish();
    }
}
