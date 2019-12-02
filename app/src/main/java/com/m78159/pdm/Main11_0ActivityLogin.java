package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main11_0ActivityLogin extends AppCompatActivity {

    private EditText user,pass;
    private TextView sessionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11_0_login);

        user = findViewById(R.id.aula11_user);
        pass = findViewById(R.id.aula11_pass);
        sessionCount = findViewById(R.id.aula11_countLogin);

        getPreferences(getSharedPreferences("UserInfo", MODE_PRIVATE));
    }

    public void login(View view){
        SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        if (!hasSavedPreferences(settings)) {
            editor.putString("user", user.getText().toString());
            editor.putString("pass", pass.getText().toString());
            editor.putInt("count", 1);
            editor.apply();
            editor.commit();
            sessionCount.setText(String.valueOf(settings.getInt("count", 0)));
        } else {
            if (user.getText().toString().equals(settings.getString("user",""))
                    && pass.getText().toString().equals(settings.getString("pass",""))) {
                editor.putInt("count", settings.getInt("count", 0) + 1);
                editor.apply();
                editor.commit();
                sessionCount.setText(String.valueOf(settings.getInt("count", 0)));
                Intent intent = new Intent(getApplicationContext(), Main11Activity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void getPreferences(SharedPreferences settings){
        user.setText(settings.getString("user", ""));
        pass.setText(settings.getString("pass", ""));
        sessionCount.setText(String.valueOf(settings.getInt("count", 0)));
    }

    private boolean hasSavedPreferences(SharedPreferences settings){
        return (settings.contains("user") && settings.contains("pass") && settings.contains("count"));
    }
}
