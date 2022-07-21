package MyAssistHub.App;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import MyAssistHub.App.database.BookingTable;
import MyAssistHub.App.databinding.ActivityServiceDetailBinding;
import MyAssistHub.App.helpers.Utils;
import MyAssistHub.App.model.Booking;
import MyAssistHub.App.model.Subcategory;

public class ServiceDetailActivity extends AppCompatActivity {

    private ActivityServiceDetailBinding binding;
    private int currentUnit = 0;
    private String hourBooking, dateBooking;
    private Subcategory subcategory;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private BookingTable bookingTable;
    private SessionManager sessionManager;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        subcategory = getIntent().getParcelableExtra("subcategory");
        categoryName = getIntent().getStringExtra("category");
        if (subcategory != null) {
            sessionManager = new SessionManager(this);
            bookingTable = new BookingTable(this);
            binding.tvsubcategories.setText(subcategory.getTitle());
            Glide.with(this)
                    .load(subcategory.getPhoto())
                    .centerCrop()
                    .into(binding.imgsubcategories);

            binding.imgplus.setOnClickListener((v) -> {
                currentUnit++;
                binding.tvunits.setText(String.valueOf(currentUnit));
                calculatePrice();
            });

            binding.imgminus.setOnClickListener((v) -> {
                if (currentUnit > 0) {
                    currentUnit--;
                    binding.tvunits.setText(String.valueOf(currentUnit));
                    calculatePrice();
                }
            });

            binding.btnDate.setOnClickListener((v) -> {
                if (datePickerDialog == null) {
                    final Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    datePickerDialog = new DatePickerDialog(ServiceDetailActivity.this, (datePicker, i, i1, i2) -> {
                        if ((i1 + 1) < 10) {
                            dateBooking = i + "-0" + (i1 + 1) + "-" + i2;
                        } else {
                            dateBooking = i + "-" + (i1 + 1) + "-" + i2;
                        }

                        binding.textView4.setText(dateBooking);
                    }, year, month, day);
                }
                datePickerDialog.show();
            });

            //btn time picker
            binding.btnTime.setOnClickListener((v) -> {
                if (timePickerDialog == null) {
                    final Calendar myCalender = Calendar.getInstance();
                    int hour = myCalender.get(Calendar.HOUR_OF_DAY);
                    int minute = myCalender.get(Calendar.MINUTE);
                    timePickerDialog = new TimePickerDialog(ServiceDetailActivity.this, (timePicker, i, i1) -> {
                        if (timePicker.isShown()) {
                            myCalender.set(Calendar.HOUR_OF_DAY, hour);
                            myCalender.set(Calendar.MINUTE, minute);
                        }
                        hourBooking = +i + ":" + i1;
                        binding.textView6.setText(hourBooking);
                    }, hour, minute, true);

                }
                timePickerDialog.show();
            });

            //add booking
            binding.btnBookNow.setOnClickListener((v) -> {

                if (currentUnit == 0) {
                    showToast("Please increase number unit");
                    return;
                }
                if (dateBooking == null) {
                    showToast("Please select date");
                    return;
                }
                if (hourBooking == null) {
                    showToast("Please select time");
                    return;
                }
                binding.btnBookNow.setEnabled(false);
                final String noteBooking = binding.edtNote.getText().toString();
                final String username = sessionManager.getUsername();
                final String serviceName = subcategory.getTitle();
                final long priceService = subcategory.getPrice();

                try {
                    bookingTable.addBooking(serviceName, noteBooking, username, priceService, currentUnit, hourBooking, dateBooking,categoryName);
                    Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(() -> {
                        finish();
                    }, 800);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Something error", Toast.LENGTH_SHORT).show();
                    binding.btnBookNow.setEnabled(true);
                }
            });
        }
    }

    //calculate price service
    private void calculatePrice() {
        final long totalPrice = currentUnit * subcategory.getPrice();
        final String price = Utils.moneyFormat(totalPrice);
        binding.tvpricesubcategory.setText(price);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}