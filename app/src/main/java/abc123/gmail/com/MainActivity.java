package abc123.gmail.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = this.getClass().getSimpleName();
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count++;

        Log.d(TAG, "enter onCreate(), #" + count);
    }


    @Override
    protected void onStart() {
        super.onStart();
        // findViewById(android.R.id.content).setOnClickListener(this); // 找到view root，然後設定click監聽
        Log.d(TAG, "enter onStart(), #" + count);

        // 使用Handler自動切換螢幕畫面
        Message msg = myHandler.obtainMessage(); // 從Message pool 裡面取一個message出來
        // 比新建立一個有效率
        // Ctrl+Q 查看指令文件
        myHandler.sendMessageDelayed(msg, 500);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "enter onStop(), #" + count);
        super.onStop();
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
            case android.R.id.content:
                startActivity(new Intent(this, Bookkeeping.class));

                // 增加過場動畫
                overridePendingTransition(android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);

                // 強制MainActivity終止
                // MainActivity.this.finish();
                break;
        }
    }

    // --------------------------------
    // 宣告一個Handler物件，並且連結到UI Thread
    private Handler myHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            // 這裡不處理msg內容，所以收到任何msg都是執行Activity切換的工作
            startActivity(new Intent(MainActivity.this, Bookkeeping.class));

            // 增加過場動畫
            overridePendingTransition(android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right);
            MainActivity.this.finish();
        }
    };
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