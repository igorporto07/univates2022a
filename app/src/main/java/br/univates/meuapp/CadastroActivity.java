package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import br.univates.meuapp.adapter.LinguagemAdapter;
import br.univates.meuapp.model.Linguagem;

public class CadastroActivity extends AppCompatActivity {

    Button btnSalvar, btnLimpar;
    EditText txtNome, txtCargo;
    ListView lista;
    ArrayList<Linguagem> listagem;
    LinguagemAdapter adapter;
    Context context;
    Linguagem objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        context = CadastroActivity.this;

        btnSalvar = findViewById(R.id.btnSalvar_cadastro);
        btnLimpar = findViewById(R.id.btnLimpar_cadastro);
        txtNome = findViewById(R.id.txtNome_cadastro);
        txtCargo = findViewById(R.id.txtCargo_cadastro);
        lista = findViewById(R.id.Lista_cadastro);

        listagem = new ArrayList<>();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = txtNome.getText().toString().trim();
                String cargo = txtCargo.getText().toString().trim();

                if(!nome.equals("") && !cargo.equals("")){
                    objeto = new Linguagem();
                    objeto.setNome(nome);
                    objeto.setCargo(cargo);

                    listagem.add(objeto);

                    adapter = new LinguagemAdapter(context, listagem);

                    lista.setAdapter(adapter);
                    txtNome.setText("");//limpa o campo txt
                    txtCargo.setText("");//limpa o campo txt
                    txtNome.requestFocus();//Envia o foco para o campo
                }else{
                    Ferramentas.mostrarAlerta(context, "ALERTA", "Preencha todos os campos!");
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtNome.setText("");//limpa o campo txt
                txtCargo.setText("");
                txtNome.requestFocus();//Envia o foco para o campo

            }
        });
    }
}