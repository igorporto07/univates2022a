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
import br.univates.meuapp.model.Cliente;

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private final Context context;
    private final ArrayList<Cliente> elementos;

    public ClienteAdapter(Context context, ArrayList<Cliente> elementos){
        super(context, R.layout.item_lista_linguagem, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Cliente objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_cliente, parent, false);

            TextView nome = rowView.findViewById(R.id.lblNome_item_cliente);
            TextView telefone = rowView.findViewById(R.id.lblTelefone_item_cliente);

            nome.setText(objeto.getNome());
            telefone.setText(objeto.getTelefone());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {//Libera o click na lista
                    Intent tela = new Intent(context, CadastroClientesActivity.class);
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
