package br.univates.meuapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaração das variáveis
    Button btnTabuada, btnCalculadora;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = MainActivity.this;

        //Vinculação das variáveis com os campos do XML
        btnTabuada = findViewById(R.id.btnTabuada_main);
        btnCalculadora = findViewById(R.id.btnCalculadora_main);

        //Início do evento do botão
        btnTabuada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, TabuadaActivity.class);
                startActivity(intent);
                finish();//fecha a tela em questão

            }

        });


    }


}