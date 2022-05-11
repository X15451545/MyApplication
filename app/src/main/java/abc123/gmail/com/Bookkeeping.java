package abc123.gmail.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Bookkeeping extends AppCompatActivity implements View.OnClickListener {

    final String TAG = this.getClass().getSimpleName();
    static int count = 0;

    TextView theDate, theTime;
    Button btSave;

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