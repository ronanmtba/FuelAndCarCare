package com.dornier.fuelcarcare;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityRegister extends AppCompatActivity implements ReceiveFromServer{

    EditText email, password, confirmPassword;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ModelDataManager.getInstance().setActualContext(ActivityRegister.this);

        email           = (EditText) findViewById(R.id.RegisterEmail);
        password        = (EditText) findViewById(R.id.RegisterPassword);
        confirmPassword = (EditText) findViewById(R.id.RegisterConfirmPassword);
        confirm         = (Button)   findViewById(R.id.RegisterConfirmButton);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmActions();
            }
        });
    }

    private void confirmActions(){
        ModelDataManager.getInstance().createUser(this,email.getText().toString(), password.getText().toString());
    }

    @Override
    public void serverCall(String response, String TAG) {
        if(response.equals("1")) {
            Toast.makeText(ActivityRegister.this, "Sucesso", Toast.LENGTH_LONG).show();
        }
        else if(response.equals("0")){
            Toast.makeText(ActivityRegister.this, "Usuário já existente", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(ActivityRegister.this, "Falha na conexão", Toast.LENGTH_LONG).show();
        }
    }
}
