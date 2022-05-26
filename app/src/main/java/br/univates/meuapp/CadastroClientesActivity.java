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
import br.univates.meuapp.controller.LinguagemController;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.model.Nota;
import br.univates.meuapp.tools.Globais;

public class CadastroClientesActivity extends AppCompatActivity {

    EditText txtNome, txtDataNascimento;
    MaskEditText txtTelefone;
    Context context;
    Cliente objeto;
    int id_Cliente;
    ClienteController controller;
    Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);

        txtNome = findViewById(R.id.txtNome_cliente);
        txtTelefone = findViewById(R.id.txtTelefone_cliente);
        txtDataNascimento = findViewById(R.id.txtData_cliente);
        btnExcluir = findViewById(R.id.btnExcluir_cliente);

        context = CadastroClientesActivity.this;

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new ClienteController(context);
                boolean retorno = controller.excluir(id_Cliente);
                if(retorno){
                    Globais.exibirMensagem(context, "Excluido com sucesso!");
                    finish();
                }else{
                    Globais.exibirMensagem(context, "Erro ao excluir!");
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id_Cliente = extras.getInt("id", 0);

            //buscar atraves desta chave
            controller = new ClienteController(context);
            objeto = controller.buscar(id_Cliente);
            if(objeto != null){
                txtNome.setText(objeto.getNome());
                txtTelefone.setText(objeto.getTelefone());
                String data_formatada = Globais.converterData(objeto.getData_nascimento(), "yyyy-MM-dd", "dd/MM/yyyy");
                txtDataNascimento.setText(data_formatada);

            }

        }else{
            id_Cliente = 0;
            btnExcluir.setVisibility(View.GONE);
        }

        txtDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //função de clique do campo data

                Calendar calendario = Calendar.getInstance();

                Date data;

                try{
                    if(txtDataNascimento.getText().toString().equals("")){
                        calendario = Calendar.getInstance();
                    }else{
                        String dtStart = txtDataNascimento.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            data = format.parse(dtStart);
                            calendario.setTime(data);

                        } catch (ParseException e) {
                            e.printStackTrace();
                            calendario = Calendar.getInstance();
                        }
                    }

                    int ano = calendario.get(Calendar.YEAR);
                    int mes = calendario.get(Calendar.MONTH);
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);

                    new DatePickerDialog(context, android.R.style.Widget_DeviceDefault_DatePicker, mDateSetListener, ano, mes, dia).show();

                }catch (Exception ex){
                    calendario = Calendar.getInstance();
                    Globais.exibirMensagem(context,  "Erro ao abrir data");
                }
            }
        });

    }

    //Função de callback

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String data = (String.format("%02d", dayOfMonth)) + "/"+ (String.format("%02d", monthOfYear + 1)) + "/" + (String.format("%02d", year));

            txtDataNascimento.setText(data);
        }
    };

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
        String telefone = txtTelefone.getUnMasked().trim();
        String data = txtDataNascimento.getText().toString();

        if(nome.equals("")) {
            Globais.exibirMensagem(context, "Informe um nome");
            return;
        }

        String data_formatada = Globais.converterData(data, "dd/MM/yyyy", "yyyy-MM-dd");

        objeto = new Cliente();
        objeto.setNome(nome);
        objeto.setTelefone(telefone);
        objeto.setData_nascimento(data_formatada);


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