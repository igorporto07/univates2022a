package br.univates.meuapp.foradeuso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.univates.meuapp.R;

public class TabuadaActivity extends AppCompatActivity {
    //Declaração das variáveis
    TextView lblTabuada;
    Button btnTabuada, btnVoltar;
    EditText txtTabuada;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabuada);

        context = TabuadaActivity.this;

        //Vinculação das variáveis com os campos do XML
        lblTabuada = findViewById(R.id.lblTabuada_tabu);
        txtTabuada = findViewById(R.id.txtTabuada_tabu);
        btnTabuada = findViewById(R.id.btnTabuada_tabu);
        btnVoltar = findViewById(R.id.btnVoltar_tabu);

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
                btnVoltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Acessar uma nova activity
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();//fecha a tela em questão

                    }

                });
        //Fim do evento do botão

    }
}
