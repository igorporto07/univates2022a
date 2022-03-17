package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtSenha;
    Button btnEntrar;
    Context context;
    String USER = "igor";
    String PASS = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        context = LoginActivity.this;

        txtUsuario = findViewById(R.id.txtUsuario_login);
        txtSenha = findViewById(R.id.txtSenha_login);
        btnEntrar = findViewById(R.id.btnEntrar_login);

        txtUsuario.requestFocus();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtUsuario.getText().toString().equals(USER) && txtSenha.getText().toString().equals(PASS)) {

                    //Acessar uma nova activity
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();//fecha a tela em questão
                }else{
                    Ferramentas.mostrarAlerta(context, "ALERTA", "Informe um login válido!");
                }

            }
        });
    }
}