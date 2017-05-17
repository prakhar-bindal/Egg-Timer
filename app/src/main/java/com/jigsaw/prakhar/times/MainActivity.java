package com.jigsaw.prakhar.times;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar bar;
    TextView timer;
    Boolean isActive = false;
    Button start;
    CountDownTimer count;

    public void updateTimer(int secondsLeft){

        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes*60;

        String sec = Integer.toString(seconds);

        if(seconds<=9){
            sec = "0"+ sec;
        }

        timer.setText(Integer.toString(minutes)+":"+sec);



    }

    public void reset(){
        timer.setText("0:30");
        bar.setEnabled(true);
        bar.setProgress(30);
        count.cancel();
        start.setText("START");
        isActive=false;

    }



    public void controller(View view) {

        if(isActive == false) {


            isActive = true;
            bar.setEnabled(false);
            start.setText("STOP");

            count = new CountDownTimer(bar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {

                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {

                    reset();
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mPlayer.start();

                }
            }.start();
        }
        else
        {
           reset();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (SeekBar) findViewById(R.id.seekBar);
        timer = (TextView) findViewById(R.id.textView);
        start = (Button) findViewById(R.id.button);
        bar.setMax(600);
        bar.setProgress(30);

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
