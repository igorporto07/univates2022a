package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    ListView listaContas;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        context = ListaActivity.this;
        listaContas = findViewById(R.id.Lista_list);

        ArrayList<String> listagem = new ArrayList<>();
        listagem.add("Java");
        listagem.add("Javascript");
        listagem.add("C#");
        listagem.add("Python");
        listagem.add("Cobol");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listagem);

        listaContas.setAdapter(adapter);

        //clicar no item
        listaContas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item_clicado = listagem.get(i);
            }
        });


    }
}