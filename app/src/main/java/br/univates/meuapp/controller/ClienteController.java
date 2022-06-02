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
                objeto.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("telefone")));
                objeto.setData_nascimento(resultado.getString(resultado.getColumnIndexOrThrow("data_nascimento")));
                objeto.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("cpf")));
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
            valores.put("telefone", objeto.getTelefone());
            valores.put("data_nascimento", objeto.getData_nascimento());
            valores.put("cpf", objeto.getCpf());

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
            valores.put("telefone", objeto.getTelefone());
            valores.put("data_nascimento", objeto.getData_nascimento());
            valores.put("cpf", objeto.getCpf());

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
    public ArrayList<Cliente> lista(){

        ArrayList<Cliente> listagem = new ArrayList<>();
        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_CLIENTES);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToFirst()){

                Cliente objeto;
                do{
                    objeto = new Cliente();
                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                    objeto.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("telefone")));
                    objeto.setData_nascimento(resultado.getString(resultado.getColumnIndexOrThrow("data_nascimento")));
                    objeto.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("cpf")));

                    listagem.add(objeto);

                }while (resultado.moveToNext());

            }

            return listagem;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return listagem;
        }
    }
}
