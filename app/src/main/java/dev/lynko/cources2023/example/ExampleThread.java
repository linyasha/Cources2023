package dev.lynko.cources2023.example;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class ExampleThread {

    private int count;
    private Context context;
    private TextView textView;
    private static String TAG = "ExampleThread";

    public ExampleThread(int count, Context context, TextView textView) {
        this.count = count;
        this.context = context;
        this.textView = textView;
    }

    public void doAction() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handlerNew = new Handler(Looper.getMainLooper());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.d(TAG, "run: $e");
                }
                Log.d(TAG, "action complete");
//                handlerNew.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText("action complete");
//                    }
//                });
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("action complete");
                    }
                });


            }
        }).start();
    }


}
