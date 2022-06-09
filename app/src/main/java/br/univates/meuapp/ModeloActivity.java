package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.univates.meuapp.controller.MarcaController;
import br.univates.meuapp.controller.ModeloController;
import br.univates.meuapp.model.Marca;
import br.univates.meuapp.model.Modelo;
import br.univates.meuapp.tools.Globais;

public class ModeloActivity extends AppCompatActivity {

    EditText txtNome;
    Context context;
    Modelo objeto;
    int id_Modelo;
    ModeloController controller;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modelo);

        txtNome = findViewById(R.id.txtNome_modelo);

        btnExcluir = findViewById(R.id.btnExcluir_modelo);

        context = ModeloActivity.this;

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new ModeloController(context);
                boolean retorno = controller.excluir(id_Modelo);
                if(retorno){
                    Globais.exibirMensagem(context, "Excluido com sucesso!");
                    finish();
                }else{
                    Globais.exibirMensagem(context, "Erro ao excluir!");
                }
            }
        });

        //Verifica se veio ID na tela anterior
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id_Modelo = extras.getInt("id", 0);

            //buscar atraves desta chave
            controller = new ModeloController(context);
            objeto = controller.buscar(id_Modelo);
            if(objeto != null){
                txtNome.setText(objeto.getNome());

            }

        }else{
            id_Modelo = 0;
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
        //puxa tudo que foi preenchido
        String nome = txtNome.getText().toString().trim();

        //Valida se algum campo estava vazio
        if(nome.equals("") ) {
            Globais.exibirMensagem(context, "Informe um nome");
            return;
        }

        //Preenche o objeto no banco de dados
        objeto = new Modelo();
        objeto.setNome(nome);


        controller = new ModeloController(context);

        boolean retorno = false;
        if(id_Modelo == 0){
            retorno = controller.incluir(objeto);
        }else{
            objeto.setId(id_Modelo);
            retorno = controller.alterar(objeto);
        }

        if(retorno){
            Globais.exibirMensagem(context, "Sucesso");
            finish();
        }
    }
}