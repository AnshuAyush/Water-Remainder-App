package com.example.drinkwater;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Before
        Button btn_set = findViewById(R.id.btn_set);
        btn_set.setVisibility(View.GONE);
        Animation text_animation = AnimationUtils.loadAnimation(this , R.anim.text_bottom);
        TextView txt_below = findViewById(R.id.text_quote);
        txt_below.setAnimation(text_animation);
        LottieAnimationView red_fish = findViewById(R.id.red_fish);
        TextView Life_is_simple = findViewById(R.id.Life_is_simple);
        LottieAnimationView water_bottle = findViewById(R.id.water_animation);
        LottieAnimationView hand = findViewById(R.id.hand);
        LottieAnimationView fish = findViewById(R.id.purple_fish);
        fish.setScale(4.4f);
        hand.setScale(2.4f);
        LottieAnimationView timer = findViewById(R.id.timer); // timer animation

        // Drink
        LottieAnimationView drink = findViewById(R.id.drink);
        drink.setScale(2.4f);
        Button restart = findViewById(R.id.restart);



        // After
        Animation reminder_animation = AnimationUtils.loadAnimation(this , R.anim.reminder_animation);
        EditText reminder_for_water = findViewById(R.id.Set_the_timer);
        reminder_for_water.setVisibility(View.GONE);
        Animation btn_set_animation = AnimationUtils.loadAnimation(this , R.anim.button_set_animation);
        LottieAnimationView women = findViewById(R.id.women);

        Life_is_simple.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                water_bottle.playAnimation();
                Life_is_simple.setX(20);
                Life_is_simple.setVisibility(View.GONE);
                hand.setVisibility(View.GONE);
                fish.setVisibility(View.GONE);
                txt_below.setVisibility(View.GONE);
                red_fish.setVisibility(View.GONE);
                btn_set.setVisibility(View.VISIBLE);


                // main function
                reminder_for_water.setVisibility(View.VISIBLE);
                reminder_for_water.setAnimation(reminder_animation);
                btn_set.setAnimation(btn_set_animation);

                LottieAnimationView complete = findViewById(R.id.complete);
                // Animation
                Animation zero_to_100 = AnimationUtils.loadAnimation(MainActivity.this , R.anim.timer_opacity_for_0to100);
                Animation hundred_to_zero = AnimationUtils.loadAnimation(MainActivity.this , R.anim.timer_opacity_for_100to0);
                Animation go_bottom = AnimationUtils.loadAnimation(MainActivity.this , R.anim.go_bottom);

                    btn_set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    // Next Button_set on click Listner
                     public void onClick(View v) {
                            try {
                                String value = reminder_for_water.getText().toString();
                                if(Integer.parseInt(value) > 16){
                                    Toast.makeText(MainActivity.this, "Time is long to remind I would suggest you to set shorter time period (1-5)", Toast.LENGTH_LONG).show();
                                    throw new Exception("Long");
                                }
                                else if(Integer.parseInt(value) == 0){
                                    Toast.makeText(MainActivity.this, "Please Enter greater than 0", Toast.LENGTH_LONG).show();
                                    throw new Exception("Invalid");
                                }
                                int integer_value = Integer.parseInt(value); // Exception will caught here

                                reminder_for_water.setVisibility(View.INVISIBLE);
                                timer.setScale(2.2f);
                                timer.setVisibility(View.VISIBLE);
                                water_bottle.setVisibility(View.INVISIBLE);

                                Toast.makeText(MainActivity.this, "Will Notify You to Drink", Toast.LENGTH_SHORT).show();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                            Looper.prepare();
                                        for (int i = 0; i < 16 / integer_value; i++){

                                            // When ever You want to touch view on a thread Always use runOnUiThread
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    btn_set.setAnimation(hundred_to_zero);
                                                    btn_set.setAnimation(go_bottom);
                                                    btn_set.setVisibility(View.GONE);
                                                    timer.setVisibility(View.VISIBLE);
                                                    drink.setVisibility(View.GONE);

                                                }
                                            });
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    women.setVisibility(View.VISIBLE);
                                                }
                                            });

                                            SystemClock.sleep(integer_value * 1000 * 60 * 60);
//                                            SystemClock.sleep(35000);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(MainActivity.this, "Time to Drink Water !!", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                            MediaPlayer m = MediaPlayer.create(MainActivity.this, R.raw.sound);
                                            m.start();

                                            // Vibrate

                                            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                vibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE));
                                            } else {
                                                vibrator.vibrate(5000);
                                            }

                                            // When ever You want to touch view on a thread Always use runOnUiThread
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    timer.setVisibility(View.GONE);
                                                    drink.setVisibility(View.VISIBLE);

                                                }
                                            });
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    women.setVisibility(View.GONE);
                                                }
                                            });
                                            SystemClock.sleep(29000);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    drink.setVisibility(View.GONE);
                                                }
                                            });

                                        }
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                restart.setVisibility(View.VISIBLE);
                                                complete.setVisibility(View.VISIBLE);

                                            }
                                        });
                                        restart.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                finish();
                                                startActivity(getIntent());
                                            }
                                        });
                                    }
                                }
                                ).start();
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, "Enter the Hours", Toast.LENGTH_SHORT).show();
                            }
                        }

                        
                    }
                    );
                }
        });
    }
}