package com.matthewsusanto.android.producktivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    private Button startPauseBtn;
    private Button resetBtn;
    private Switch focusModeCb;
    private TextView timmy;
    private TextView ducks;
    private TextView totalTime;
    private TextView duckLeftTv;
    private static int duckCount;
    private static final long START_TIME = 1800000;
    private long mTimeLeftInMillis = START_TIME;
    private int duckArmy;
    int duckInHours;
    int duckInMinutes;
    int duckInSeconds;
    private ImageView ImageView1;
    private ImageView ImageView2;
    private ImageView ImageView3;
    private ImageView ImageView4;
    private ImageView ImageView5;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private static boolean mInFocusMode = false;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //access the txt
        SharedPreferences sharedPref = getSharedPreferences("saveFiles1", Context.MODE_PRIVATE);
        duckCount = sharedPref.getInt("duckCount",0);

        // the number of ducks shown on  screen
        duckArmy = duckCount/5;





        // ==================================================

        ducks = findViewById(R.id.ducks);
        ducks.setText("Press the Start button!");

        startPauseBtn = findViewById(R.id.startPauseBtn);
        resetBtn = findViewById(R.id.resetBtn);
        timmy = findViewById(R.id.timmy);
        focusModeCb = findViewById(R.id.focusmodeCb);
        ImageView1 = findViewById(R.id.imageView);
        ImageView2 = findViewById(R.id.imageView2);
        ImageView3 = findViewById(R.id.imageView3);
        ImageView4 = findViewById(R.id.imageView4);
        ImageView5 = findViewById(R.id.imageView5);
        duckLeftTv = findViewById(R.id.duckLeftTv);
        totalTime = findViewById(R.id.totalTime);

        duckInHours = ((duckCount * 1800)/60)/60;
        duckInMinutes = ((duckCount * 1800)/60)%60;
        duckInSeconds = duckCount * 1800;




        totalTime.setText("Which is equivalent to " + duckInHours + " hours and "+ duckInMinutes + " minutes of productivity!");

        // this to show how many current ducklings you have

        if((duckCount % 5)== 0){
            ImageView1.setVisibility(View.VISIBLE);
            ImageView2.setVisibility(View.VISIBLE);
            ImageView3.setVisibility(View.VISIBLE);
            ImageView4.setVisibility(View.VISIBLE);
            ImageView5.setVisibility(View.VISIBLE);
            duckLeftTv.setText("You need 5 more ducklings to collect a duck!");
        }
        else if((duckCount % 5)== 1){
            ImageView1.setVisibility(View.VISIBLE);
            ImageView2.setVisibility(View.INVISIBLE);
            ImageView3.setVisibility(View.INVISIBLE);
            ImageView4.setVisibility(View.INVISIBLE);
            ImageView5.setVisibility(View.INVISIBLE);
            duckLeftTv.setText("You need 4 more ducklings to collect a duck!");
        }
        else if((duckCount % 5)== 2){
            ImageView1.setVisibility(View.VISIBLE);
            ImageView2.setVisibility(View.VISIBLE);
            ImageView3.setVisibility(View.INVISIBLE);
            ImageView4.setVisibility(View.INVISIBLE);
            ImageView5.setVisibility(View.INVISIBLE);
            duckLeftTv.setText("You need 3 more ducklings to collect a duck!");
        }
        else if((duckCount % 5)== 3){
            ImageView1.setVisibility(View.VISIBLE);
            ImageView2.setVisibility(View.VISIBLE);
            ImageView3.setVisibility(View.VISIBLE);
            ImageView4.setVisibility(View.INVISIBLE);
            ImageView5.setVisibility(View.INVISIBLE);
            duckLeftTv.setText("You need 2 more ducklings to collect a duck!");
        }
        else if((duckCount % 5)== 4){
            ImageView1.setVisibility(View.VISIBLE);
            ImageView2.setVisibility(View.VISIBLE);
            ImageView3.setVisibility(View.VISIBLE);
            ImageView4.setVisibility(View.VISIBLE);
            ImageView5.setVisibility(View.INVISIBLE);
            duckLeftTv.setText("You need 1 more duckling to collect a duck!");
        }



        //determine whether focusmode is on or off

        focusModeCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (focusModeCb.isChecked()) {
                    Toast.makeText(getApplicationContext(), "You are now in focus mode", Toast.LENGTH_SHORT).show();
                    mInFocusMode = true;

                } else {
                    Toast.makeText(getApplicationContext(), "You are no longer in focus mode", Toast.LENGTH_SHORT).show();
                    mInFocusMode = false;
                }

            }
        });




        // it directs user to DND setting in order to allow access if the user hasn't

        startPauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                //if notification is granted to this
                if(mNotificationManager.isNotificationPolicyAccessGranted()) {


                    if (mTimerRunning) {

                        pauseTimer();
                        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL);
                        Toast.makeText(getApplicationContext(), "Time to take a break!", Toast.LENGTH_SHORT).show();

                    } else if (mInFocusMode == true) {


                        startTimerFocus();
                        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALARMS);
                        Toast.makeText(getApplicationContext(), "You can start being productive now!", Toast.LENGTH_SHORT).show();


                    } else {
                        startTimer();
                        mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALARMS);
                        Toast.makeText(getApplicationContext(), "You can start being productive now", Toast.LENGTH_SHORT).show();
                    }

                }

                else {

                    // open dialog to allow access to the dnd
                    openDialog();

                }


            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();

    }


    //method for timer

    private void startTimer() {

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                ducks.setText("You have " + duckArmy + "  ducks!");
            }

            @Override
            public void onFinish() {

                if(mInFocusMode == false) {

                    mTimerRunning = false;
                    startPauseBtn.setText("start");


                    duckCount++;



                    SharedPreferences sharedPref = getSharedPreferences("saveFiles1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("duckCount", duckCount);
                    editor.apply();

                    if((duckCount % 5)== 0){

                        Toast.makeText(getApplicationContext(), "You earned a duck! Congratulations!", Toast.LENGTH_SHORT).show();
                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.VISIBLE);
                        ImageView4.setVisibility(View.VISIBLE);
                        ImageView5.setVisibility(View.VISIBLE);
                        duckLeftTv.setText("You need 5 more ducklings to collect a duck!");


                    }
                    else if((duckCount % 5)== 1){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.INVISIBLE);
                        ImageView3.setVisibility(View.INVISIBLE);
                        ImageView4.setVisibility(View.INVISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 4 more ducklings to collect a duck!");

                    }

                    else if((duckCount % 5)== 2){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.INVISIBLE);
                        ImageView4.setVisibility(View.INVISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 3 more ducklings to collect a duck!");

                    }

                    else if((duckCount % 5)== 3){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.VISIBLE);
                        ImageView4.setVisibility(View.INVISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 2 more ducklings to collect a duck!");

                    }
                    else if((duckCount % 5)== 4){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.VISIBLE);
                        ImageView4.setVisibility(View.VISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 1 more duckling to collect a duck!");

                    }
                    duckArmy = duckCount/5;
                    ducks.setText("You have " + duckArmy + "  ducks!");

                    duckInHours = ((duckCount * 1800)/60)/60;
                    duckInMinutes = ((duckCount * 1800)/60)%60;
                    duckInSeconds = duckCount * 1800;
                    totalTime.setText("Which is equivalent to " + duckInHours + " hours and "+ duckInMinutes + " minutes of productivity!");





                    mTimeLeftInMillis = START_TIME;
                } else if(mInFocusMode) {

                    startTimerFocus();

                }



            }
        }.start();

        mTimerRunning = true;
        startPauseBtn.setText("pause");
        resetBtn.setVisibility(View.INVISIBLE);

    }

    //method for focus timer

    private void startTimerFocus() {

        if (mInFocusMode == true) {

            mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {


                @Override
                public void onTick(long millisUntilFinished) {
                    mTimeLeftInMillis = millisUntilFinished;
                    updateCountDownText();
                    ducks.setText("You have " + duckArmy + "  ducks!");
                }

                @Override
                public void onFinish() {

                    mTimerRunning = false;
                    startPauseBtn.setText("start");


                    duckCount++;



                    SharedPreferences sharedPref = getSharedPreferences("saveFiles1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("duckCount", duckCount);
                    editor.apply();

                    if((duckCount % 5)== 0){

                        Toast.makeText(getApplicationContext(), "You earned a duck! Congratulations!", Toast.LENGTH_SHORT).show();
                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.VISIBLE);
                        ImageView4.setVisibility(View.VISIBLE);
                        ImageView5.setVisibility(View.VISIBLE);
                        duckLeftTv.setText("You need 5 more ducklings to collect a duck!");


                    }
                    else if((duckCount % 5)== 1){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.INVISIBLE);
                        ImageView3.setVisibility(View.INVISIBLE);
                        ImageView4.setVisibility(View.INVISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 4 more ducklings to collect a duck!");

                    }

                    else if((duckCount % 5)== 2){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.INVISIBLE);
                        ImageView4.setVisibility(View.INVISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 3 more ducklings to collect a duck!");

                    }

                    else if((duckCount % 5)== 3){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.VISIBLE);
                        ImageView4.setVisibility(View.INVISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 2 more ducklings to collect a duck!");

                    }
                    else if((duckCount % 5)== 4){


                        ImageView1.setVisibility(View.VISIBLE);
                        ImageView2.setVisibility(View.VISIBLE);
                        ImageView3.setVisibility(View.VISIBLE);
                        ImageView4.setVisibility(View.VISIBLE);
                        ImageView5.setVisibility(View.INVISIBLE);
                        duckLeftTv.setText("You need 1 more duckling to collect a duck!");

                    }
                    duckArmy = duckCount/5;
                    ducks.setText("You have " + duckArmy + "  ducks!");


                    duckInHours = ((duckCount * 1800)/60)/60;
                    duckInMinutes = ((duckCount * 1800)/60)%60;
                    duckInSeconds = duckCount * 1800;
                    totalTime.setText("Which is equivalent to " + duckInHours + " hours and "+ duckInMinutes + " minutes of productivity!");




                    mTimeLeftInMillis = START_TIME;
                    startTimerFocus();


                }
            }.start();

            mTimerRunning = true;
            startPauseBtn.setText("pause");
            resetBtn.setVisibility(View.INVISIBLE);
        }
    }

    // the method for the timer that ticks every second

    private void updateCountDownText() {

        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timmy.setText(timeLeftFormatted);

    }

    private void resetTimer() {

        mTimeLeftInMillis = START_TIME;
        updateCountDownText();
        resetBtn.setVisibility(View.INVISIBLE);
        startPauseBtn.setVisibility(View.VISIBLE);

    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        startPauseBtn.setText("start");
        resetBtn.setVisibility(View.VISIBLE);

    }

    //method to open the dialog

    private void openDialog(){

        AllowPermissionDialog allowPermissionDialog = new AllowPermissionDialog();
        allowPermissionDialog.show(getSupportFragmentManager(), "");

    }


}
