package com.example.appcjs.modal;

public class OTP {

    private String email;
    private String code;
    private String status;

    public OTP() {
    }


    public OTP(String email, String code, String status) {
        this.email = email;
        this.code = code;
        this.status = status;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "OTP [email=" + email + ", code=" + code + ", status=" + status + "]";
    }


}
