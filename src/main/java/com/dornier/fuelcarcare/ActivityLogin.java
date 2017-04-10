package com.dornier.fuelcarcare;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity implements ReceiveFromServer{

    EditText email, password;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ModelDataManager.getInstance().setActualContext(ActivityLogin.this);

        email       = (EditText) findViewById(R.id.LoginEmail);
        password    = (EditText) findViewById(R.id.LoginPassword);
        login       = (Button) findViewById(R.id.LoginLogin);
        register    = (Button) findViewById(R.id.LoginRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActions();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerActions();
            }
        });
    }

    private void loginActions(){
        ModelDataManager.getInstance().login(this, email.getText().toString(), password.getText().toString());

    }

    private void registerActions(){
        Intent i = new Intent(this, ActivityRegister.class);
        startActivity(i);
    }

    @Override
    public void serverCall(String response, String TAG) {
        if(TAG.equals("login")){
            if(response.equals("1")){
                Intent i = new Intent(this, ActivitySelectVehicle.class);
                startActivity(i);
            }
            else{
                Toast.makeText(ActivityLogin.this,"Falha no login", Toast.LENGTH_LONG).show();
            }
        }
    }
}
