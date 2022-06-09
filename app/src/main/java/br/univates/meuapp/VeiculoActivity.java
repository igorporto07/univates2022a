package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.santalu.maskara.widget.MaskEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.univates.meuapp.controller.ClienteController;
import br.univates.meuapp.controller.VeiculoController;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.model.Veiculo;
import br.univates.meuapp.tools.Globais;

public class VeiculoActivity extends AppCompatActivity {

    EditText txtMarca, txtModelo, txtPlaca,txtAno;
    Context context;
    Veiculo objeto;
    int id_Veiculo;
    VeiculoController controller;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        txtMarca = findViewById(R.id.txtMarca_veiculo);
        txtModelo = findViewById(R.id.txtModelo_veiculo);
        txtPlaca = findViewById(R.id.txtplaca_veiculo);
        txtAno = findViewById(R.id.txtAno_veiculo);
        btnExcluir = findViewById(R.id.btnExcluir_cliente);

        context = VeiculoActivity.this;

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new VeiculoController(context);
                boolean retorno = controller.excluir(id_Veiculo);
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
            id_Veiculo = extras.getInt("id", 0);

            //buscar atraves desta chave
            controller = new VeiculoController(context);
            objeto = controller.buscar(id_Veiculo);
            if(objeto != null){
                txtMarca.setId(objeto.getId_marca());
                txtModelo.setId(objeto.getId_modelo());
                txtPlaca.setText(objeto.getPlaca());
                txtAno.setText(objeto.getAno_fabricacao());
            }

        }else{
            id_Veiculo = 0;
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
        int marca = txtMarca.getId();
        int modelo = txtModelo.getId();
        String placa = txtPlaca.getText().toString().trim();
        String ano = txtAno.getText().toString().trim();

        //Valida se algum campo estava vazio
        if(marca < 0 ) {
            Globais.exibirMensagem(context, "Informe uma marca");
            return;
        }
        if(modelo < 0) {
            Globais.exibirMensagem(context, "Informe um modelo");
            return;
        }
        if(placa.equals("")) {
            Globais.exibirMensagem(context, "Informe uma placa");
            return;
        }
        if(ano.equals("")) {
            Globais.exibirMensagem(context, "Informe um ano de fabricação");
            return;
        }

        //Preenche o objeto no banco de dados
        objeto = new Veiculo();
        objeto.setId_marca(marca);
        objeto.setId_modelo(modelo);
        objeto.setPlaca(placa);
        objeto.setAno_fabricacao(ano);


        controller = new VeiculoController(context);

        boolean retorno = false;
        if(id_Veiculo == 0){
            retorno = controller.incluir(objeto);
        }else{
            objeto.setId(id_Veiculo);
            retorno = controller.alterar(objeto);
        }

        if(retorno){
            Globais.exibirMensagem(context, "Sucesso");
            finish();
        }


    }
}