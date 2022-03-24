package br.univates.meuapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //Declaração das variáveis
    Button btnTabuada, btnToast, btnSnackbars;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = MainActivity.this;

        /**String chave_preference = getString(R.string.shared_preferences);**/


        //Vinculação das variáveis com os campos do XML
        btnTabuada = findViewById(R.id.btnTabuada_main);
        btnToast = findViewById(R.id.btnToast_main);
        btnSnackbars = findViewById(R.id.btnSnackbars_main);

        //Início do evento do botão
        btnTabuada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, TabuadaActivity.class);
                startActivity(intent);
            }

        });

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Olá Mundo!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }

        });

        btnSnackbars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(btnSnackbars,"Olá Mundo!", Snackbar.LENGTH_LONG).show();

            }

        });

        /**criar botão pra gravar
        btnTabuada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usuario", "igor");
                boolean ret = editor.commit();
                if(ret){
                    Snackbar.make(btnSnackbars,"Gravado com sucesso!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        //criar botão pra exebir
        btnTabuada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String preferemcia = sharedPreferences.getString("usuario", "");
            }
        });**/


    }


}