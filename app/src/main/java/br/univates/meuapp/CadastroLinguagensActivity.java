package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import br.univates.meuapp.controller.LinguagemController;
import br.univates.meuapp.foradeuso.Ferramentas;
import br.univates.meuapp.model.Linguagem;
import br.univates.meuapp.tools.Globais;

public class CadastroLinguagensActivity extends AppCompatActivity {

    EditText txtNome, txtCargo;
    Linguagem objeto;
    LinguagemController controller;
    Context context;
    int id_linguagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_linguagens);

        txtNome = findViewById(R.id.txtNome_cadastro);
        txtCargo = findViewById(R.id.txtCargo_cadastro);
        context = CadastroLinguagensActivity.this;

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id_linguagem = extras.getInt("id", 0);

            //buscar atraves desta chave
            controller = new LinguagemController(context);
            objeto = controller.buscar(id_linguagem);
            if(objeto != null){
                txtNome.setText(objeto.getNome());
                txtCargo.setText(objeto.getCargo());
            }
        }else{
            id_linguagem = 0;
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

        if(!nome.equals("") && !cargo.equals("")){
            objeto = new Linguagem();
            objeto.setNome(nome);
            objeto.setCargo(cargo);

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

        }else{
            Ferramentas.mostrarAlerta(context, "ALERTA", "Preencha todos os campos!");
        }
    }

}