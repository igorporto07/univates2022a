package br.univates.meuapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaração das variáveis
    TextView lblTabuada;
    Button btnTabuada;
    EditText txtTabuada;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Título");
        context = MainActivity.this;

        //Vinculação das variáveis com os campos do XML
        lblTabuada = findViewById(R.id.lblTabuada_main);
        txtTabuada = findViewById(R.id.txtTabuada_main);
        btnTabuada = findViewById(R.id.btnCalcular_main);

        //Início do evento do botão
        btnTabuada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tabuada = 0;

                try {
                    tabuada = Integer.parseInt(txtTabuada.getText().toString());
                } catch (Exception ex) {
                    Log.e("CALCULADORA", ex.getMessage());
                    Ferramentas.mostrarAlerta(context, "EITAAAA", "Informe um número válido");
                }

                if(tabuada != 0) {
                    String impressao = "";

                    for (int i = 0; i <= 10; i++) {

                        int res = i * tabuada;
                        impressao += tabuada + "x" + i + "=" + res + "\n";
                    }
                    lblTabuada.setText(impressao);
                }

            }
        });
        //Fim do evento do botão

    }


}