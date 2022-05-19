package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.univates.meuapp.adapter.ClienteAdapter;
import br.univates.meuapp.controller.ClienteController;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.tools.Globais;

public class ListaClientesActivity extends AppCompatActivity {

    Button btnNovoCadastro;
    ListView lista;
    ArrayList<Cliente> listagem;
    ClienteAdapter adapter;
    Context context;
    Cliente objeto;
    ClienteController controller;
    private android.util.Log Log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        context = ListaClientesActivity.this;

        btnNovoCadastro = findViewById(R.id.btnNovoCadastro_cliente);
        lista = findViewById(R.id.Lista_cliente);

        btnNovoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CadastroClientesActivity.class);
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

            controller = new ClienteController(context);
            listagem = controller.lista();

            adapter = new ClienteAdapter(context, listagem);
            lista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}