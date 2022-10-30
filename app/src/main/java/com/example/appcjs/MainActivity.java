package com.example.appcjs;

import static com.example.appcjs.Constant.EMAIL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcjs.modal.OTP;
import com.example.appcjs.retrofits.OTPApi;
import com.example.appcjs.retrofits.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button requestOTP, verifyBtn, requestAnotherOTP;
    EditText emailEdt, codeEdt;
    public TextView countDownTxt;
    ConstraintLayout requestLayout, verifyLayout;
    MainActivity mAct ;
    OTPApi otpApi;
    Handler handler;
    public static SharedPreferences sharedPreferences, sharedPreferencesRed;
    public static SharedPreferences.Editor editor;

    public static String valid = "VALID";
    public static String invalid = "INVALID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAct = new MainActivity();
        handler = new Handler();

        sharedPreferences = getSharedPreferences(Constant.SHARED_PREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        sharedPreferencesRed = getSharedPreferences(Constant.SHARED_PREF, MODE_MULTI_PROCESS);
        if(sharedPreferencesRed.contains("email")){
            String email = sharedPreferencesRed.getString("email", "");
            if(!email.isEmpty()) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        }


        requestOTP = (Button) findViewById(R.id.btn);
        verifyBtn = (Button) findViewById(R.id.verify_btn);
        requestAnotherOTP = (Button) findViewById(R.id.request_another_btn);
        emailEdt = (EditText) findViewById(R.id.email);
        codeEdt = (EditText) findViewById(R.id.code_edt);
        countDownTxt = (TextView) findViewById(R.id.countDown_txt);
        requestLayout = (ConstraintLayout) findViewById(R.id.requestLayout);
        verifyLayout = (ConstraintLayout) findViewById(R.id.verifyLayout);


        verifyLayout.setVisibility(View.GONE);
        requestLayout.setVisibility(View.VISIBLE);

        RetrofitService retrofitService = new RetrofitService();
        otpApi = retrofitService.getRetrofit().create(OTPApi.class);


        requestAnotherOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestAnotherOTP.setEnabled(false);

                String email = emailEdt.getText().toString();
                OTP otp = new OTP();
                otp.setEmail(email);
                EMAIL = email;


                otpApi.generateOTP(otp)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code() == 200) {
                                    requestLayout.setVisibility(View.GONE);
                                    verifyLayout.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this, "OTP generated successfully", Toast.LENGTH_SHORT).show();
                                    Constant.STATUS = valid;
                                    new CountDownTimer(30000, 1000){
                                        @Override
                                        public void onFinish() {
                                            requestAnotherOTP.setEnabled(true);
                                            otpApi.updateStatus(otp)
                                                    .enqueue(new Callback<Void>() {
                                                        @Override
                                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                                            Constant.STATUS = invalid;
                                                            Toast.makeText(MainActivity.this, "OTP Expired", Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Void> call, Throwable t) {
                                                            Toast.makeText(MainActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onTick(long l) {
                                            countDownTxt.setText(String.valueOf((int)Math.floor(l/1000)));
                                        }
                                    }.start();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                            }
                        });


            }
        });

        requestOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(emailEdt.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Enter your email first !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = emailEdt.getText().toString();
                OTP otp = new OTP();
                otp.setEmail(email);


                otpApi.generateOTP(otp)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code() == 200) {
                                    requestLayout.setVisibility(View.GONE);
                                    verifyLayout.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this, "OTP generated successfully", Toast.LENGTH_SHORT).show();
                                    Constant.STATUS = valid;
                                    //mAct.startTimeDown();
                                    new CountDownTimer(30000, 1000){
                                        @Override
                                        public void onFinish() {
                                            requestAnotherOTP.setEnabled(true);
                                            otpApi.updateStatus(otp)
                                                    .enqueue(new Callback<Void>() {
                                                        @Override
                                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                                            Constant.STATUS = invalid;
                                                            Toast.makeText(MainActivity.this, "OTP Expired", Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onFailure(Call<Void> call, Throwable t) {
                                                            Toast.makeText(MainActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onTick(long l) {
                                            countDownTxt.setText(String.valueOf((int)Math.floor(l/1000)));
                                        }
                                    }.start();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured", t);
                            }
                        });


            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(codeEdt.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter the code", Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = codeEdt.getText().toString();
                OTP otp = new OTP();
                otp.setCode(code);

                otpApi.verifyOTP(otp)
                        .enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(Constant.STATUS == valid) {
                                    if (response.code() == 200) {
                                        Toast.makeText(MainActivity.this, "Log in Successfull", Toast.LENGTH_SHORT).show();
                                        editor.putString("email", EMAIL);
                                        editor.commit();
                                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                        finish();
                                    } else if (response.code() == 400) {
                                        Toast.makeText(MainActivity.this, "Code incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }else if(Constant.STATUS == invalid){
                                    Toast.makeText(MainActivity.this, "OTP expired !!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}