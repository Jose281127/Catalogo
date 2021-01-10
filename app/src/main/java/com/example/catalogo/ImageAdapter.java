package com.example.catalogo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.catalogo.Videojuego.Videojuego;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<Videojuego> juego;
    private static LayoutInflater inflater=null;
    public ImageAdapter(Context context, ArrayList<Videojuego> juego) {
        this.context = context;
        this.juego = juego;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return juego.size();
    }

    @Override
    public Object getItem(int position) {
        return juego.get(position);
    }

    @Override
    public long getItemId(int position) {
        return juego.get(position).getId();
    }
    public int getimagen(String entrada,View vistap){
         Integer[] arreglo={
                R.drawable.accion,
                R.drawable.arcade,
                R.drawable.deportes,
                R.drawable.estrategia,
                R.drawable.plataforma,
                R.drawable.rol,
                R.drawable.shore,
                R.drawable.terror,
                R.drawable.carrera,
                R.drawable.musica,
                R.drawable.lucha,
                R.drawable.simulador
        };
         if(entrada.equals(vistap.getResources().getString(R.string.Acción))){
             return arreglo[0];
         }
         if(entrada.equals(vistap.getResources().getString(R.string.Arcade))){
             return arreglo[1];
         }
         if(entrada.equals(vistap.getResources().getString(R.string.Deportes))){
             return arreglo[2];
         }
         if(entrada.equals(vistap.getResources().getString(R.string.Estrategia))){
             return arreglo[3];
        }
         if(entrada.equals(vistap.getResources().getString(R.string.Plataformeros))){
             return arreglo[4];
         }
         if (entrada.equals(vistap.getResources().getString(R.string.Rol))){
             return arreglo[5];
         }
         if ((entrada.equals(vistap.getResources().getString(R.string.Shoter)))){
             return arreglo[6];
         }
         if (entrada.equals(vistap.getResources().getString(R.string.Terror)))
         {
             return arreglo[7];
         }
         if (entrada.equals(vistap.getResources().getString(R.string.Carreras))){
             return arreglo[8];
         }
         if (entrada.equals(vistap.getResources().getString(R.string.Musica))){
             return arreglo[9];
         }
         if (entrada.equals(vistap.getResources().getString(R.string.Peleas))){
             return arreglo[10];
         }
         if (entrada.equals(vistap.getResources().getString(R.string.Simulador))){
             return arreglo[11];
         }
         return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista=inflater.inflate(R.layout.elemento_lista,null);

       TextView tvTitulo=vista.findViewById(R.id.Titulon);
        TextView Fech=vista.findViewById(R.id.fec);
        ImageView Imagen=vista.findViewById(R.id.Icono);
        TextView categoria=vista.findViewById(R.id.identificador3);
        RatingBar ratingBar=vista.findViewById(R.id.ratingBar2);
        ratingBar.setRating(juego.get(position).getCalif());
       // RatingBar var=vista.findViewById(R.id.raati);
        tvTitulo.setText(juego.get(position).getNam());
        Fech.setText(String.valueOf(juego.get(position).getAnio()));
        categoria.setText(juego.get(position).getCate());
        Imagen.setImageResource(getimagen(juego.get(position).getCate(),vista));

        return vista;
    }
}
