package br.univates.meuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.santalu.maskara.widget.MaskEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.univates.meuapp.controller.ClienteController;
import br.univates.meuapp.controller.MarcaController;
import br.univates.meuapp.controller.ModeloController;
import br.univates.meuapp.controller.VeiculoController;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.model.Marca;
import br.univates.meuapp.model.Modelo;
import br.univates.meuapp.model.Nota;
import br.univates.meuapp.model.Veiculo;
import br.univates.meuapp.tools.Globais;

public class VeiculoActivity extends AppCompatActivity {

    EditText txtPlaca,txtAno;
    Context context;
    Veiculo objeto;
    int id_Veiculo;
    VeiculoController controller;
    Button btnExcluir;
    Spinner spiMarca, spiModelo;
    ArrayList<br.univates.meuapp.model.Marca> Marca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        txtPlaca = findViewById(R.id.txtplaca_veiculo);
        txtAno = findViewById(R.id.txtAno_veiculo);
        btnExcluir = findViewById(R.id.btnExcluir_cliente);
        spiMarca = findViewById(R.id.spiMarca_veiculo);
        spiModelo = findViewById(R.id.spiModelo_veiculo);

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

        spiMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Marca marca = (Marca) spiMarca.getSelectedItem();

                //Spinner Modelo
                ModeloController objModeloController = new ModeloController(context);
                ArrayList<Modelo> listamodelo = objModeloController.lista(marca.getId());

                ArrayAdapter<Modelo> adapter_modelos = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listamodelo);

                spiModelo.setAdapter(adapter_modelos);
                //...
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner Marca
        MarcaController objMarcaController = new MarcaController(context);
        ArrayList<Marca> listamarca = objMarcaController.lista();

        ArrayAdapter<Marca> adapter_marcas = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listamarca);

        spiMarca.setAdapter(adapter_marcas);
        //...




        //Verifica se veio ID na tela anterior
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id_Veiculo = extras.getInt("id", 0);

            //buscar atraves desta chave
            controller = new VeiculoController(context);
            objeto = controller.buscar(id_Veiculo);
            if(objeto != null){
                txtPlaca.setText(objeto.getPlaca());
                txtAno.setText(objeto.getAno_fabricacao());
            }
            //preenchendo o spinner da Marca
            for(int i = 0; i < spiMarca.getAdapter().getCount(); i++){
                Marca marca = (Marca) spiMarca.getItemAtPosition(i);
                if(marca.getId() == objeto.getId_marca()){
                    spiMarca.setSelection(i);
                    break;
                }
            }

            ModeloController objModeloController = new ModeloController(context);
            ArrayList<Modelo> listamodelo = objModeloController.lista(objeto.getId_marca());

            ArrayAdapter<Modelo> adapter_modelos = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listamodelo);

            spiModelo.setAdapter(adapter_modelos);

            //preenchendo o spinner do Modelo
            for(int i = 0; i < spiModelo.getAdapter().getCount(); i++){
                Modelo modelo = (Modelo) spiModelo.getItemAtPosition(i);
                if(modelo.getId() == objeto.getId_modelo()){
                    spiModelo.setSelection(i);
                    break;
                }
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
        String placa = txtPlaca.getText().toString().trim();
        String ano = txtAno.getText().toString().trim();
        Marca marca = (Marca) spiMarca.getSelectedItem();
        Modelo modelo = (Modelo) spiModelo.getSelectedItem();

        //Valida se algum campo estava vazio
        if(marca.getId() < 1 ) {
            Globais.exibirMensagem(context, "Informe uma marca");
            return;
        }
        if(modelo.getId() < 1) {
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
        objeto.setId_marca(marca.getId());
        objeto.setId_modelo(modelo.getId());
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