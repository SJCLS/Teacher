package com.example.teacher.helper;

import java.text.SimpleDateFormat;

public class DateCustom {
    public static String dataAtual(){
        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;
    }
    public static String dateCustomEscolhida(String data){
        String retornoData[] = data.split("/");
        String dia = retornoData[0];
        String mes = retornoData[1];
        String ano = retornoData[2];
        String mesAno = mes+ano;
         return mesAno;
    }

}
