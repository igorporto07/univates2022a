package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtSenha;
    Button btnEntrar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");
        context = LoginActivity.this;

        txtUsuario = findViewById(R.id.txtUsuario_main);
        txtSenha = findViewById(R.id.txtSenha_main);
        btnEntrar = findViewById(R.id.btnEntrar_main);
    }
}