package br.univates.meuapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.univates.meuapp.database.DadosOpenHelper;
import br.univates.meuapp.database.Tabelas;
import br.univates.meuapp.model.Cliente;
import br.univates.meuapp.model.Linguagem;
import br.univates.meuapp.tools.Globais;

public class ClienteController {

    private SQLiteDatabase conexao;
    private Context context;

    public ClienteController(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }


    public Cliente buscar(int id){
        try{

            Cliente objeto = null;

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_CLIENTES);
            sql.append(" WHERE id = '"+ id +"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new Cliente();
                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
            }

            return objeto;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return null;
        }
    }

    public boolean incluir(Cliente objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());

            conexao.insertOrThrow(Tabelas.TB_CLIENTES, null, valores);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean alterar(Cliente objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tabelas.TB_CLIENTES, valores, "id = ?" , parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int id){
        try{

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(id);

            conexao.delete(Tabelas.TB_CLIENTES, "id = ?", parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }
}
