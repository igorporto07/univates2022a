package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    ListView lista;
    Context context;
    Button btnSalvar;
    EditText txtItem;
    ArrayList<String> listagem;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        context = ListaActivity.this;
        lista = findViewById(R.id.Lista_list);
        btnSalvar = findViewById(R.id.btnSalvar_list);
        txtItem = findViewById(R.id.txtItem_list);

        listagem = new ArrayList<>();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item = txtItem.getText().toString().trim();
                if(!item.equals("")){
                    listagem.add(item);

                    adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listagem);

                    lista.setAdapter(adapter);

                    txtItem.setText("");//limpa o campo txt
                    txtItem.requestFocus();//Envia o foco para o campo
                }
            }
        });

        //clicar no item
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item_clicado = listagem.get(i);
            }
        });


    }
}