package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.univates.meuapp.adapter.ClienteAdapter;
import br.univates.meuapp.adapter.VeiculoAdapter;
import br.univates.meuapp.controller.ClienteController;
import br.univates.meuapp.controller.VeiculoController;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.model.Veiculo;
import br.univates.meuapp.tools.Globais;

public class ListaVeiculosActivity extends AppCompatActivity {

    Button btnNovoCadastro;
    ListView lista;
    ArrayList<Veiculo> listagem;
    VeiculoAdapter adapter;
    Context context;
    Veiculo objeto;
    VeiculoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veiculos);

        context = ListaVeiculosActivity.this;

        btnNovoCadastro = findViewById(R.id.btnNovoCadastro_veiculo);
        lista = findViewById(R.id.Lista_veiculo);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuVeiculosActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        atualizarLista();
    }

    private void atualizarLista(){
        try {

            controller = new VeiculoController(context);
            listagem = controller.lista();

            adapter = new VeiculoAdapter(context, listagem);
            lista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }

}
