package com.example.librarymanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    LoginDatabase db;
    EditText mTvStudentID;
    EditText mTvPassword;
    EditText mTvCnf;
    Button mBtnRegister;
    TextView mTvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new LoginDatabase(this);
        mTvStudentID = (EditText)findViewById(R.id.et_studentId);
        mTvPassword = (EditText)findViewById(R.id.et_password);
        mTvCnf = (EditText)findViewById(R.id.et_cnf_password);
        mBtnRegister = (Button)findViewById(R.id.btn_register);
        mTvLogin = (TextView) findViewById(R.id.tv_login);

        mTvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentid = mTvStudentID.getText().toString().trim();
                String pwd = mTvPassword.getText().toString().trim();
                String cnf_pwd = mTvCnf.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(studentid,pwd);
                    if(val > 0){
                        Toast.makeText(RegisterActivity.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"Password is not matched",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}