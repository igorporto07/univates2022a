package br.univates.meuapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.univates.meuapp.CadastroClientesActivity;
import br.univates.meuapp.R;
import br.univates.meuapp.VeiculoActivity;
import br.univates.meuapp.controller.MarcaController;
import br.univates.meuapp.controller.ModeloController;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.model.Marca;
import br.univates.meuapp.model.Modelo;
import br.univates.meuapp.model.Veiculo;
import br.univates.meuapp.tools.Globais;

public class VeiculoAdapter extends ArrayAdapter<Veiculo> {

    private final Context context;
    private final ArrayList<Veiculo> elementos;

    public VeiculoAdapter(Context context, ArrayList<Veiculo> elementos){
        super(context, R.layout.item_lista_veiculos, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Veiculo objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_veiculos, parent, false);

            TextView nome = rowView.findViewById(R.id.lblNome_item_veiculo);
            TextView marcac = rowView.findViewById(R.id.lblMarca_item_Veiculo);
            TextView ano = rowView.findViewById(R.id.lblAno_item_veiculo);
            TextView placa = rowView.findViewById(R.id.lblPlaca_item_veiculo);

            ModeloController controller = new ModeloController(context);
            Modelo modelo = controller.buscar(objeto.getId_modelo());
            if(modelo != null){
                nome.setText(modelo.getNome());
            }

            MarcaController objMarcaController = new MarcaController(context);
            Marca marca = objMarcaController.buscar(objeto.getId_marca());
            if(marca != null){
                marcac.setText(marca.getNome());
            }

            ano.setText(objeto.getAno_fabricacao());
            placa.setText(objeto.getPlaca());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//Libera o click na lista
                    Intent tela = new Intent(context, VeiculoActivity.class);
                    tela.putExtra("id", objeto.getId());
                    context.startActivity(tela);
                }
            });

            return rowView;

        }catch (Exception ex){
            Log.e("ERRO_ADAPTER", ex.getMessage());
            return null;
        }

    }
}
