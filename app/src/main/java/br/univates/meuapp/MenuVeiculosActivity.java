package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.univates.meuapp.foradeuso.MainActivity;
import br.univates.meuapp.foradeuso.TabuadaActivity;
import br.univates.meuapp.model.Marca;
import br.univates.meuapp.model.Modelo;

public class MenuVeiculosActivity extends AppCompatActivity {

    Button btnVeiculo, btnModelo, btnMarca;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_veiculos);

        context = MenuVeiculosActivity.this;

        btnVeiculo = findViewById(R.id.btnVeiculo_veiculo);
        btnModelo = findViewById(R.id.btnModelo_veiculo);
        btnMarca= findViewById(R.id.btnMarca_veiculo);

        btnVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context,VeiculoActivity.class);
                startActivity(intent);
            }

        });

        btnModelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, ModeloActivity.class);
                startActivity(intent);
            }

        });

        btnMarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acessar uma nova activity
                Intent intent = new Intent(context, MarcaActivity.class);
                startActivity(intent);
            }

        });
    }
}