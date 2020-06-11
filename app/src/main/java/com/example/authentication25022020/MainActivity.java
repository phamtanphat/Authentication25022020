package com.example.authentication25022020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText mEdtEmail,mEdtPass;
    Button mBtnDangnhap,
            mBtnDangky,
            mBtnThongTin,
            mBtnCapNhatThongTin,
            mBtnResetMk,mBtnXacThuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtEmail = findViewById(R.id.edittextEmail);
        mEdtPass = findViewById(R.id.edittextPassword);
        mBtnDangnhap = findViewById(R.id.buttonDangnhap);
        mBtnDangky = findViewById(R.id.buttonDangky);
        mBtnThongTin = findViewById(R.id.buttonThongtin);
        mBtnCapNhatThongTin = findViewById(R.id.buttonCapnhat);
        mBtnResetMk = findViewById(R.id.buttonResetpassword);
        mBtnXacThuc = findViewById(R.id.buttonVerification);
    }
}