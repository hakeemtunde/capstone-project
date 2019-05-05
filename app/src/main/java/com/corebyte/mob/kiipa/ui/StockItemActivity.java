package com.corebyte.mob.kiipa.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import com.corebyte.mob.kiipa.MeasurementDlgProcessor.MeasurementHandler;
import com.corebyte.mob.kiipa.PublishMeasurementTable;
import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.repo.CategoryCrudOperation;
import com.corebyte.mob.kiipa.repo.MeasurementCrudOperation;
import com.corebyte.mob.kiipa.repo.StockCrudOperation;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StockItemActivity extends AppCompatActivity implements MeasurementHandler {

    public static final String STOCKITEM = "STOCKITEM";
    public static final String ACTION_EDIT = "EDIT";
    public static final String ACTION_NEW = "NEW";
    private static final String DLG_TAG = "MEASUREMENTDLG";
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

    @BindView(R.id.add_measurements_btn)
    public Button addMeasureBtn;

    @BindView(R.id.tl_measure)
    public TableLayout tableLayout;

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;

    private Category mCategory;

    private Date mExpireDate;

    private StockCrudOperation mStockCrudOperation;
    private CategoryCrudOperation mCategoryCrudOperation;
    private MeasurementCrudOperation mMeasurementCrudOperation;

    private PublishMeasurementTable measurementTable;

    private Stock stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_item);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mStockCrudOperation = new StockCrudOperation(getApplicationContext());
        mCategoryCrudOperation = new CategoryCrudOperation(getApplicationContext());
        mMeasurementCrudOperation = new MeasurementCrudOperation(getApplicationContext());

        measurementTable = new PublishMeasurementTable(
                this, tableLayout);

        Intent intent = getIntent();
        if (intent.getAction() != null && intent.getAction().equals(ACTION_EDIT)) {
            if (intent.hasExtra(STOCKITEM)) {
                stock = intent.getParcelableExtra(STOCKITEM);
                displayStock(stock);
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stock_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.save_stock) {


            String name = nameEt.getText().toString();

            //new stock entry
            if (stock == null) {
                long stockId = mStockCrudOperation.create(new Stock(name, mExpireDate, mCategory.id));
                measurementTable.setStockId(stockId);
                Measurement[] measurements = measurementTable.getMeasurementsAsArray();
                mMeasurementCrudOperation.create(measurements);
            } else {
                //update stock/measurements

                if (mCategory != null) {
                    stock.setCategoryId(mCategory.id);
                }

                if (mExpireDate != null) {
                    stock.setExpireDate(mExpireDate);
                }

                mStockCrudOperation.update(stock);

                //new
                Measurement[] newMeasurements = measurementTable.getNewMeasurements();
                measurementTable.setStockId(stock.id, newMeasurements);
                mMeasurementCrudOperation.create(newMeasurements);

                //update
                Measurement[] existingMeasurements = measurementTable.getExistingMeasurements();
                mMeasurementCrudOperation.update(existingMeasurements);

            }

            Toast.makeText(getApplicationContext(), "save!", Toast.LENGTH_SHORT).show();

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void displayStock(Stock stock) {

        nameEt.setText(stock.getName());
        expireDateEt.setText(DateUtil.getDateFormat2(stock.getExpireDate()));
        Category category = mCategoryCrudOperation.getById(stock.getCategoryId());
        categoryEt.setText(category.getName());

        List<Measurement> measurements = mMeasurementCrudOperation.findByStockId(stock.id);
        measurementTable.attachToTable(measurements);

    }

    @OnClick(R.id.date_picker_btn)
    public void onClickDatePicker() {
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
//                String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                mExpireDate = calendar.getTime();
                expireDateEt.setText(DateUtil.getDateFormat2(mExpireDate));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @OnClick(R.id.show_category_btn)
    public void onClickShowCategoryBtn() {

        final String[] cats = mCategoryCrudOperation.getAllAsArray();

        new AlertDialog.Builder(this)
                .setTitle("Select stock category")
                .setSingleChoiceItems(cats, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mCategory = mCategoryCrudOperation.getCategoryWithIndex(i);
                        categoryEt.setText(cats[i]);
                    }
                })
                .setPositiveButton(getString(R.string.ok_string), null)
                .setNegativeButton(getString(R.string.cancel_string), null)
                .show();
    }

    @OnClick(R.id.add_measurements_btn)
    public void onClickAddMeasureBtn() {

        MeasureDialog measureDialog = new MeasureDialog();
        measureDialog.setMeasurementHandler(this);
        measureDialog.show(getSupportFragmentManager(), DLG_TAG);

    }

    @Override
    public void attach(Measurement measurement) {
        measurementTable.attachToTable(measurement);

    }


}
