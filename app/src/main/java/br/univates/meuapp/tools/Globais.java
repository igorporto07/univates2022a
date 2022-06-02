package br.univates.meuapp.tools;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Globais {

    public static void exibirMensagem(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();

    }

    public static String converterData(String data, String formatoInicial, String formatoDesejado) {
        String wDataConvertida = "";
        try {
            java.text.DateFormat parser = new SimpleDateFormat(formatoInicial, Locale.getDefault());
            java.text.DateFormat formatter = new SimpleDateFormat(formatoDesejado, Locale.getDefault());

            wDataConvertida = formatter.format(parser.parse(data));

        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return wDataConvertida;
        }
    }

    public static String cpf_formatado(String cpf) {
        try {
            if(cpf != null) {
                cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
                return cpf;
            }else{
                return "";
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return "";
        }
    }

    public static String telefone_formatado(String telefone) {
        try {
            if (telefone != null) {
                telefone = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
                return telefone;
            } else {
                return "";
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return "";
        }
    }

    public static String apenasNumeros(String texto){
        try{
            String retorno = texto.replaceAll("\\D+","");
            return retorno;

        }catch (Exception ex){
            return "";
        }
    }

}
