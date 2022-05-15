package abc123.gmail.com;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Bookkeeping extends AppCompatActivity implements View.OnClickListener {

    final String TAG = this.getClass().getSimpleName();
    static int count = 0;

    TextView theDate, theTime;
    Button btSave;


    SimpleDateFormat df = new SimpleDateFormat("hha", Locale.US);
    // 設定時間顯示的格式
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    // 設定日期顯示的格式

    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookkeeping_constraintlayout);

        count++;
        Log.d(TAG, "enter onCreate(), #" + count);
        uiInit(); // 設置框架uiInit()
    }

    private void uiInit() { // findViewById全都放這
        theDate = findViewById(R.id.textView6);
        theTime = findViewById(R.id.textView7);

        btSave = findViewById(R.id.button2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        varInit();
        setUiListener();
        Log.d(TAG, "enter onStart(), #" + count);
    }

    private void varInit() {
        c = Calendar.getInstance();

        theTime.setText(df.format(c.getTime()));
        theDate.setText(df2.format(c.getTime()));
    }

    private void setUiListener() { // ui監聽 寫在onStart()中
        theDate.setOnClickListener(this);
        theTime.setOnClickListener(this);
        btSave.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "enter onStop(), #" + count);
        releaseUiListener();
        super.onStop();
    }

    private void releaseUiListener() {
        theDate.setOnClickListener(null);
        theTime.setOnClickListener(null);
        btSave.setOnClickListener(null);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "enter onDestroy(), #" + count);
        count--;
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "enter onPause(), #" + count);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "enter onResume(), #" + count);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "enter onRestart(), #" + count);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                // 帳務資料儲存

                // 返回主畫面
                startActivity(new Intent(this, MainActivity.class));
                // Bookkeeping.this.finish();
                break;
            case R.id.textView6:
                // 設定日期
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.MONTH, month);
                        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        theDate.setText(df2.format(c.getTime()));
                    }
                },
                        c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.textView7:
                // 設定時間
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        theTime.setText(df.format(c.getTime()));
                    }
                },
                        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
                break;
        }
    }
}

//public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//
//    TextView tx;
//    Button bt;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        tx = findViewById(R.id.textView);
//        bt = findViewById(R.id.button);
//
//        bt.setOnClickListener(this);
//        tx.setOnClickListener(this);
//
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.textView:
//                tx.setTextColor(Color.RED);
//                tx.setText("Hello World!");
//
//                break;
//            case R.id.button:
//                tx.setTextColor(Color.BLUE);
//                tx.setText("點我");
//
//                break;
//        }
//
//    }
//}


//    bt.setOnClickListener(myClickListener);
//    private View.OnClickListener myClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            tx.setTextColor(Color.RED);
//        }
//    };


//    public void onButtonClick(View view) {
//        tx.setTextColor(Color.BLUE);
//    }