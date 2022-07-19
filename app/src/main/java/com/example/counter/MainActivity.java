//Api 22 Pixel XL API 22
package com.example.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bstart, bstop;
    TextView ct;
    public boolean r = false;
    public int c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bstart = (Button)findViewById(R.id.but_start);
        bstart.setOnClickListener(this);
        bstop = (Button)findViewById(R.id.but_end);
        bstop.setOnClickListener(this);
        ct = (TextView)findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v){
        if(v.equals(bstart)){
            counterstart();
        }
        else if(v.equals(bstop)){
            counterstop();
        }

    }

    private void counterstop() {
        this.r = false;
        bstart.setEnabled(true);
        bstop.setEnabled(false);
    }

    private void counterstart() {
        c = 0;

        this.r = true;
        new MyCounter().start();
        bstop.setEnabled(true);
        bstart.setEnabled(false);


    }
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg){
            ct.setText(String.valueOf(msg.what));
        }
    };
    private class MyCounter extends Thread {
        @Override
        public void run(){
            while(r){
                c++;
                handler.sendEmptyMessage(c);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}