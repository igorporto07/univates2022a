package br.univates.meuapp.foradeuso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import br.univates.meuapp.CadastroClientesActivity;
import br.univates.meuapp.ListaClientesActivity;
import br.univates.meuapp.ListaLinguagensActivity;
import br.univates.meuapp.ListaVeiculosActivity;
import br.univates.meuapp.R;

public class MainActivity extends AppCompatActivity {
    //Declaração das variáveis
    Button btnTabuada, btnCalculadora, btnToast, btnSnackbars, btnSair, btnLista, btnMapa, btnCadastro, btnCadastroClientes, btnCadastroVeiculo;
    Context context;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = MainActivity.this;

        String chave_preference = getString(R.string.shared_preferences);

        sharedPreferences = getSharedPreferences(chave_preference, MODE_PRIVATE);

        //Vinculação das variáveis com os campos do XML
        btnTabuada = findViewById(R.id.btnTabuada_main);
        btnToast = findViewById(R.id.btnToast_main);
        btnSnackbars = findViewById(R.id.btnSnackbars_main);
        btnCalculadora = findViewById(R.id.btnCalculadora_main);
        btnSair = findViewById(R.id.btnSair_main);
        btnLista = findViewById(R.id.btnLista_main);
        btnMapa = findViewById(R.id.btnMapa_main);
        btnCadastro = findViewById(R.id.btnCadastro_main);
        btnCadastroClientes = findViewById(R.id.btnCadastroCliente_main);
        btnCadastroVeiculo = findViewById(R.id.btnCadastroVeiculo_main);

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


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("login", "");
                    boolean ret = editor.commit();

                } catch (Exception ex) {
                    Log.e("CATCH", ex.getMessage());
                }

                //Acessar uma nova activity
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();//fecha a tela em questão
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, ListaActivity.class);
                startActivity(intent);
            }

        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }

        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, ListaLinguagensActivity.class);
                startActivity(intent);
            }

        });

        btnCadastroClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, ListaClientesActivity.class);
                startActivity(intent);
            }

        });

        btnCadastroVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, ListaVeiculosActivity.class);
                startActivity(intent);
            }

        });
    }


}