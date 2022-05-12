package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import br.univates.meuapp.controller.LinguagemController;
import br.univates.meuapp.foradeuso.Ferramentas;
import br.univates.meuapp.model.Linguagem;
import br.univates.meuapp.model.Nota;
import br.univates.meuapp.tools.Globais;

public class CadastroLinguagensActivity extends AppCompatActivity {

    CheckBox chbFavorito;
    EditText txtNome, txtCargo;
    Linguagem objeto;
    LinguagemController controller;
    Context context;
    int id_linguagem;
    Spinner spiNota;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_linguagens);

        txtNome = findViewById(R.id.txtNome_cadastro);
        txtCargo = findViewById(R.id.txtCargo_cadastro);
        chbFavorito = findViewById(R.id.chbFavorito_cadastro);
        spiNota = findViewById(R.id.spiNota_linguagem);
        btnExcluir = findViewById(R.id.btnExcluir_linguagem);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new LinguagemController(context);
                boolean retorno = controller.excluir(id_linguagem);
                if(retorno){
                    Globais.exibirMensagem(context, "Excluido com sucesso!");
                    finish();
                }else{
                Globais.exibirMensagem(context, "Erro ao excluir!");
            }
            }
        });


        context = CadastroLinguagensActivity.this;

        ArrayList<Nota> lista_notas = new ArrayList<>();
        lista_notas.add(new Nota(0,"Sem Nota"));
        lista_notas.add(new Nota(1,"Nota 1"));
        lista_notas.add(new Nota(2,"Nota 2"));
        lista_notas.add(new Nota(3,"Nota 3"));

        ArrayAdapter<Nota> adapter_notas = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista_notas);

        spiNota.setAdapter(adapter_notas);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id_linguagem = extras.getInt("id", 0);

            //buscar atraves desta chave
            controller = new LinguagemController(context);
            objeto = controller.buscar(id_linguagem);
            if(objeto != null){
                txtNome.setText(objeto.getNome());
                txtCargo.setText(objeto.getCargo());

                if(objeto.getFavorito() ==1){
                    chbFavorito.setChecked(true);
                }else{
                    chbFavorito.setChecked(false);
                }
                //preenchendo o spinner da nota
                for(int i = 0; i < spiNota.getAdapter().getCount(); i++){
                    Nota nota = (Nota) spiNota.getItemAtPosition(i);
                    if(nota.getId() == objeto.getNota()){
                        spiNota.setSelection(i);
                        break;
                    }
                }
            }

        }else{
            id_linguagem = 0;
            btnExcluir.setVisibility(View.GONE);
        }


    }

    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_ok:

                salvar();

            case R.id.action_cancel:

                finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(){

        String nome = txtNome.getText().toString().trim();
        String cargo = txtCargo.getText().toString().trim();
        Nota nota = (Nota)spiNota.getSelectedItem();

        if(nome.equals("")){
            Globais.exibirMensagem(context, "Informe um nome");
            return;
        }
        if(cargo.equals("")){
            Globais.exibirMensagem(context, "Informe um cargo");
            return;
        }
        if(nota.getId() <= 0){
            Globais.exibirMensagem(context, "Informe uma nota");
            return;
        }

            objeto = new Linguagem();
            objeto.setNome(nome);
            objeto.setCargo(cargo);
            objeto.setNota(nota.getId());

            if(chbFavorito.isChecked()){
                objeto.setFavorito(1);
            }else{
                objeto.setFavorito(0);
            }


            controller = new LinguagemController(context);

            boolean retorno = false;
            if(id_linguagem == 0){
                retorno = controller.incluir(objeto);
            }else{
                objeto.setId(id_linguagem);
                retorno = controller.alterar(objeto);
            }

            if(retorno){
                Globais.exibirMensagem(context, "Sucesso");
                finish();
            }


    }

}