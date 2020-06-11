package com.example.authentication25022020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity {

    EditText mEdtEmail, mEdtPass;
    Button mBtnDangnhap,
            mBtnDangky,
            mBtnThongTin,
            mBtnCapNhatThongTin,
            mBtnResetMk, mBtnXacThuc;
    FirebaseAuth mAuth;

    // mail ao : android2502@yopmail.com
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

        mAuth = FirebaseAuth.getInstance();

        mBtnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEdtEmail.getText().toString();
                String password = mEdtPass.getText().toString();
//                validation : firebase auto check
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("BBB", task.getException().getMessage());
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        mBtnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEdtEmail.getText().toString();
                String password = mEdtPass.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("BBB", task.getException().getMessage());
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        mBtnThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    Uri photoUrl = user.getPhotoUrl();
                    // Check if user's email is verified
                    boolean emailVerified = user.isEmailVerified();
                    String uid = user.getUid();
                    Toast.makeText(MainActivity.this, "Name : " + name + "\n" + "email : " + email, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User khong ton tai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtnCapNhatThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName("Android 2502")
                            .setPhotoUri(Uri.parse("https://cellphones.com.vn/sforum/wp-content/uploads/2020/05/android_logo_stacked__rgb_.5.jpg"))
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.d("BBB", task.getException().getMessage());
                                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        mBtnResetMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    mAuth.sendPasswordResetEmail(user.getEmail())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Gui email cap nhat password thanh cong", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.d("BBB", task.getException().getMessage());
                                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        mBtnXacThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

                user.sendEmailVerification()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Gui email xac thuc thanh cong", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("BBB", task.getException().getMessage());
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
//        FirebaseAuth.getInstance().signOut();
    }
}