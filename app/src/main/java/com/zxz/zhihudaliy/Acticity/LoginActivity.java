package com.zxz.zhihudaliy.Acticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GO_TO_REGIST = 100;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView register_link =(TextView)findViewById(R.id.register_link);
        final EditText loginName_a =(EditText)findViewById(R.id.username_et);
        final EditText pwd_a =(EditText) findViewById(R.id.password_et);
        Button login = (Button)findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginName=loginName_a.getText().toString().trim();
                String pwd = pwd_a.getText().toString().trim();
                if(loginName.equals("admin")){
                    if(pwd.equals("admin")){
                        Intent data=new Intent();
                        data.putExtra("name", loginName);
                        setResult(RESULT_OK, data);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }
                }else{
//                    AlertDialog.Builder dialog =new AlertDialog.Builder(LoginActivity.this);
//                    dialog.setMessage("查无此用户");
//                    dialog.setCancelable(false);
//                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialogInterface, int i) {
//
//                           }
//                       });
//                        dialog.show();
                    Toast.makeText(LoginActivity.this,"查无此用户",Toast.LENGTH_SHORT).show();
                }
            }
        });
        register_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent, REQUEST_CODE_GO_TO_REGIST);
            }
        });
    }
}
