package com.example.asus.quizthread;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    Button trueB, falseB;
    TextView timer, color, score;
    Switch sw;
    int scoree = 0;
    ArrayList<String>colors;
    String Color;
    String ColorText;
    int randomNumColors;
    int randomNumString;
    Handler mHandler, handler;
    Runnable runnable = null, mRunnable = null;
    int timeleft = 10;
    FrameLayout k;
    Boolean kill = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        trueB = (Button)findViewById(R.id.trueBut);
        falseB = (Button)findViewById(R.id.falseBut);
        timer = (TextView) findViewById(R.id.time);
        color = (TextView)findViewById(R.id.color);
        score = (TextView)findViewById(R.id.score);
        k = (FrameLayout)findViewById(R.id.frame);
        sw = (Switch)findViewById(R.id.switch1);
         colors = new ArrayList<>();
        colors.add("RED");
        colors.add("YELLOW");
        colors.add("GREEN");


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    timeleft = 10;
                    mHandler = new Handler();
                    randomColor();
                    if(randomNumColors == 0) {
                        k.setBackgroundColor(android.graphics.Color.RED);
                        color.setText(colors.get(randomNumString));
                    }
                    else if(randomNumColors == 1){
                        k.setBackgroundColor(android.graphics.Color.YELLOW);
                        color.setText("YELLOW");
                        color.setText(colors.get(randomNumString));
                    }
                    else{
                        k.setBackgroundColor(android.graphics.Color.GREEN);
                        color.setText(colors.get(randomNumString));
                    }

                   mRunnable = new Runnable() {
                       @Override
                       public void run() {
                           if(kill == true) {
                               if (timeleft >= 0) {
                                   timer.setText(timeleft + "");
                                   Log.e("kobe", timeleft + "");
                                   timeleft--;
                               } else {
                                   timeleft = 10;
                                  // handler.removeCallbacksAndMessages(null);
                                   mHandler = new Handler();
                                   randomColor();
                                   if(randomNumColors == 0) {
                                       k.setBackgroundColor(android.graphics.Color.RED);
                                       color.setText(colors.get(randomNumString));
                                   }
                                   else if(randomNumColors == 1){
                                       k.setBackgroundColor(android.graphics.Color.YELLOW);
                                       color.setText(colors.get(randomNumString));
                                   }
                                   else{
                                       k.setBackgroundColor(android.graphics.Color.GREEN);
                                       color.setText(colors.get(randomNumString));
                                   }
                                    /* mRunnable.run();*/
                               }
                           }
                           else{
                               return;
                           }
                           mHandler.postDelayed(mRunnable,1000);
                       }
                   };
                    mRunnable.run();
                }
                else{

                }
            }
        });

        trueB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomColor();
                if(randomNumColors == 0) {
                    k.setBackgroundColor(android.graphics.Color.RED);
                    color.setText(colors.get(randomNumString));
                }
                else if(randomNumColors == 1){
                    k.setBackgroundColor(android.graphics.Color.YELLOW);
                    color.setText(colors.get(randomNumString));
                }
                else{
                    k.setBackgroundColor(android.graphics.Color.GREEN);
                    color.setText(colors.get(randomNumString));
                }
                if(Color == ColorText){
                    scoree++;

                }else{
                    scoree--;
                }
                score.setText(scoree+"");
                kill = false;
                kill = true;
                mRunnable.run();



            }
        });
        falseB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomColor();
                if(randomNumColors == 0) {
                    k.setBackgroundColor(android.graphics.Color.RED);
                    color.setText(colors.get(randomNumString));
                }
                else if(randomNumColors == 1){
                    k.setBackgroundColor(android.graphics.Color.YELLOW);
                    color.setText(colors.get(randomNumString));
                }
                else{
                    k.setBackgroundColor(android.graphics.Color.GREEN);
                    color.setText(colors.get(randomNumString));
                }
                if(Color != ColorText){
                    scoree++;

                }else{
                    scoree--;
                }
                score.setText(scoree+"");
            }
        });
    }
public void randomColor(){
    Random rn = new Random();
     randomNumColors = 0 + rn.nextInt((2-0)+1);
     randomNumString = 0 + rn.nextInt((2-0)+1);
    Color = colors.get(randomNumColors);
    ColorText = colors.get(randomNumString);


}
    private class myThread implements Runnable{


        @Override
        public void run() {
            Message message = Message.obtain();
            for(int i = 0; i < 10000; i++){
                message.arg1=i;
                mHandler.sendMessage(message);
            }
            Looper.loop();
        }
    }


}
