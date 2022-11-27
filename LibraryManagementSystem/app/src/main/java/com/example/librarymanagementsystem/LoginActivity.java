package com.example.librarymanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText mTvStudentID;
    EditText mTvPassword;
    Button mBtnLogin;
    TextView mTvRegister;
    LoginDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new LoginDatabase(this);
        mTvStudentID = (EditText) findViewById(R.id.et_studentId);
        mTvPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.button_login);
        mTvRegister = (TextView) findViewById(R.id.tv_register);

        mTvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mTvStudentID.getText().toString().trim();
                String pwd = mTvPassword.getText().toString().trim();
                Boolean res = db.checkUser(id, pwd);
                if (res == true) {
                    Intent intent = new Intent(LoginActivity.this, Menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}