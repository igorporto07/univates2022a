package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtSenha;
    Button btnEntrar;
    Context context;
    String USER = "igor";
    String PASS = "123";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        context = LoginActivity.this;

        String chave_preference = getString(R.string.shared_preferences);

        sharedPreferences = getSharedPreferences(chave_preference, MODE_PRIVATE);

        txtUsuario = findViewById(R.id.txtUsuario_login);
        txtSenha = findViewById(R.id.txtSenha_login);
        btnEntrar = findViewById(R.id.btnEntrar_login);

        txtUsuario.requestFocus();

        String preferencia = sharedPreferences.getString("login", "");
        if (!preferencia.equals("")){
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();//fecha a tela em questão
        }

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (txtUsuario.getText().toString().equals(USER) && txtSenha.getText().toString().equals(PASS)) {
                    //gravar o shared
                    try {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("login", txtUsuario.getText().toString());
                        boolean ret = editor.commit();

                    } catch (Exception ex) {
                        Log.e("CATCH", ex.getMessage());
                    }

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