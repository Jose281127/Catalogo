package com.example.catalogo.Videojuego;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
public class Videojuego implements Serializable {
    private long id;
    private String ide;
    private String nam;
    private int anio;
    private String cate;
    private float calif;

    public Videojuego(long id, String nam, int anio, String cate,String nue,float calif) {
        this.id = id;
        this.nam = nam;
        this.anio = anio;
        this.cate = cate;
        this.ide=nue;
        this.calif=calif;
    }


    public String[] leerarchivo(Context context){
        int i=0;
        try {
            BufferedReader aux=new BufferedReader(new InputStreamReader(context.openFileInput("Archivo.txt")));
            String[] Arreglo;
            String texto=aux.readLine();
            String todo="";
            if(texto==null){
                Arreglo=new String[1];
                Arreglo[0]="Exc";
                return Arreglo;
            }

            while (texto!=null){
                todo=todo+texto+"\n";
                texto=aux.readLine();
                i++;
            }
            aux.close();
            int a=i;
            aux=new BufferedReader(new InputStreamReader(context.openFileInput("Archivo.txt")));
            Arreglo=new String[i];
            i=0;
            texto=aux.readLine();
            Arreglo[i]=texto;
            //Toast.makeText(this,"nani"+Arreglo[0],Toast.LENGTH_LONG).show();
            //Toast.makeText(this,"nani2"+Arreglo.length,Toast.LENGTH_LONG).show();
            while (i<Arreglo.length-1){
                // Toast.makeText(this,"nani3"+Arreglo[i],Toast.LENGTH_LONG).show();
                //Toast.makeText(this,aux.readLine(), Toast.LENGTH_LONG).show();
                //  Toast.makeText(this,String.valueOf(i),Toast.LENGTH_LONG).show();

                i++;
                //Toast.makeText(this,"porqua"+texto,Toast.LENGTH_LONG).show();
                // Toast.makeText(this,"nani3"+Arreglo[i],Toast.LENGTH_LONG).show();
                texto=aux.readLine();
                Arreglo[i]=texto;
            }


            // Toast.makeText(this,"nani3"+Arreglo[i],Toast.LENGTH_LONG).show();
            aux.close();


            return Arreglo;

        }catch (Exception e){
            Log.e("Archivo","Error al escribir un archivo");
            // titulo.setText("Bankau");

            String[]Arreglo=new String[1];
            Arreglo[0]="Exc";
            return Arreglo;
        }


    }


    public long getId() {
        return id;
    }

    public float getCalif() {
        return calif;
    }

    public void setCalif(float calif) {
        this.calif = calif;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String  getIde() {
        return ide;
    }

    public String getNam() {
        return nam;
    }

    public int getAnio() {
        return anio;
    }

    public String getCate() {
        return cate;
    }
}
