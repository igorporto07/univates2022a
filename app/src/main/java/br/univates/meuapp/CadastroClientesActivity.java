package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import br.univates.meuapp.controller.ClienteController;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.tools.Globais;

public class CadastroClientesActivity extends AppCompatActivity {

    EditText txtNome;
    Context context;
    Cliente objeto;
    int id_Cliente;
    ClienteController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);

        txtNome = findViewById(R.id.txtNome_cadastroCliente);

        context = CadastroClientesActivity.this;

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

        if(nome.equals("")) {
            Globais.exibirMensagem(context, "Informe um nome");
            return;
        }

        objeto = new Cliente();
        objeto.setNome(nome);


        controller = new ClienteController(context);

        boolean retorno = false;
        if(id_Cliente == 0){
            retorno = controller.incluir(objeto);
        }else{
            objeto.setId(id_Cliente);
            retorno = controller.alterar(objeto);
        }

        if(retorno){
            Globais.exibirMensagem(context, "Sucesso");
            finish();
        }


    }
}