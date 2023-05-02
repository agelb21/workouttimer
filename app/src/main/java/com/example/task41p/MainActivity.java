package com.example.task41p;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager layoutManager;
    ArrayList<MyDataModel> timersList;

    MyAdapter myAdapter;

    FloatingActionButton floatingActionButton;


    int currentTimerIndex = 0;
    final long START_TIME_IN_MILLIS = 0;

    TextView mTextViewCountDown;
    TextView mTextViewCurrentTask;
    Button mButtonStartPause;
    Button mButtonReset;

   CountDownTimer mCountDownTimer;

    boolean mTimerRunning;

    long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //recycler view

        mRecyclerView = findViewById(R.id.recyclerViewTimers);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        timersList = new ArrayList<>();

        timersList.add(new MyDataModel("taskName", "timer", "timer2"));

        layoutManager = new LinearLayoutManager(this);

        myAdapter = new MyAdapter(this, timersList);

        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(layoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timersList.add(new MyDataModel("Set /Rest", "00",":00"));
                mRecyclerView.getAdapter().notifyItemInserted(timersList.size());
                mRecyclerView.smoothScrollToPosition(timersList.size());

            }
        });

        //timer
        mTextViewCurrentTask = findViewById(R.id.textViewCurrentTask);
        mTextViewCountDown = findViewById(R.id.textViewerCountdown);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_restart);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning){
                    pauseTimer();
                }else {
                    startTimer();
                }

            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();

            }
        });

        updateCountDownText();




    }

    private void startTimer(){
        long timerDurationInMillis = timersList.get(currentTimerIndex).getTimerDurationInMillis();
        mTextViewCurrentTask.setText(timersList.get(currentTimerIndex).getTaskName());
        mCountDownTimer = new CountDownTimer(timerDurationInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                currentTimerIndex++;
                if (currentTimerIndex < timersList.size()) {
                    mTimeLeftInMillis = timersList.get(currentTimerIndex).getTimerDurationInMillis();
                    mTextViewCurrentTask.setText(timersList.get(currentTimerIndex).getTaskName());
                    updateCountDownText();

                }

            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);

    }

    private void resetTimer(){
        mTimeLeftInMillis = timersList.get(currentTimerIndex).getTimerDurationInMillis();
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonReset.setVisibility(View.VISIBLE);

    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}

