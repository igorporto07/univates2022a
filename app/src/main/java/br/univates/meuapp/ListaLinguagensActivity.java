package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.univates.meuapp.adapter.LinguagemAdapter;
import br.univates.meuapp.controller.LinguagemController;
import br.univates.meuapp.model.Linguagem;
import br.univates.meuapp.tools.Globais;

public class ListaLinguagensActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<Linguagem> listagem;
    LinguagemAdapter adapter;
    Context context;
    Linguagem objeto;
    LinguagemController controller;
    private android.util.Log Log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_linguagens);
        context = ListaLinguagensActivity.this;

        lista = findViewById(R.id.Lista_cadastro);


    }

    @Override
    protected void onResume() {
        super.onResume();

        atualizarLista();
    }

    private void atualizarLista(){
        try {

            controller = new LinguagemController(context);
            listagem = controller.lista();

            adapter = new LinguagemAdapter(context, listagem);
            lista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}