package com.example.appcjs.retrofits;

import com.example.appcjs.modal.OTP;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OTPApi {

    @GET("/otp")
    Call<OTP> getOtp();

    @GET("/status")
    Call<String> getStatus();

    @POST("/otp")
    Call<Void> generateOTP(@Body OTP otp);

    @POST("/verify-otp")
    Call<Void> verifyOTP(@Body OTP otp);

    @PUT("/otp")
    Call<Void> updateStatus(@Body OTP otp);
}
