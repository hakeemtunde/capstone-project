package com.corebyte.mob.kiipa.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StockItemActivity extends AppCompatActivity {

    @BindView(R.id.ed_name)
    public EditText nameEt;

    @BindView(R.id.ed_expiration)
    public EditText expireDateEt;

    @BindView(R.id.ed_category)
    public EditText categoryEt;

    @BindView(R.id.date_picker_btn)
    public ImageButton datePickerBtn;

    @BindView(R.id.show_category_btn)
    public ImageButton showCategoryBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_item);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.date_picker_btn)
    public void onClickDatePicker() {
       final  Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
//                String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                String date = DateUtil.getDateFormat2(calendar.getTime());
                expireDateEt.setText(date);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @OnClick(R.id.show_category_btn)
    public void onClickShowCategoryBtn() {
        final String[] cats = new String[]{"Grains", "Toiletries", "Cosmetics"};

        new AlertDialog.Builder(this)
                .setTitle("Select stock category")
                .setSingleChoiceItems(cats, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        categoryEt.setText(cats[i]);
                    }
                })
                .setPositiveButton(getString(R.string.ok_string), null)
                .setNegativeButton(getString(R.string.cancel_string), null)
                .show();
    }
}
