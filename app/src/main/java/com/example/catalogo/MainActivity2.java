package com.example.catalogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.catalogo.Videojuego.Videojuego;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView lv;
    MediaPlayer mp;
    ArrayList<Videojuego> arrayList=new ArrayList<>();
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String caracter[];
        String [] comon=getResources().getStringArray(R.array.Categoria);

        lv=findViewById(R.id.Contenedor);
        Bundle bundle=getIntent().getExtras();
        Toast.makeText(this, getResources().getString(R.string.otro),Toast.LENGTH_LONG).show();
        Videojuego aux=new Videojuego(1,"aux",2,"no","no",1.0f);
        caracter=aux.leerarchivo(this);
       // Toast.makeText(this,"yo soy act6653"+caracter[2], Toast.LENGTH_LONG).show();
        if(bundle!=null) {

            Videojuego juegotemp;


            for (int i=0;i<caracter.length;i++){
               //Toast.makeText(this,caracter[i]+String.valueOf(i), Toast.LENGTH_LONG).show();
               String[] partes=caracter[i].split("\\$");
               // Toast.makeText(this,partes[0]+String.valueOf(i), Toast.LENGTH_LONG).show();
                juegotemp=new Videojuego(0,partes[0],Integer.parseInt(partes[1]),comon[Integer.parseInt(partes[2])],partes[3],Float.parseFloat(partes[4]));
                arrayList.add(juegotemp);
            }
            ImageAdapter adapter = new ImageAdapter(this, arrayList);

            lv.setAdapter(adapter);
            lv.setClickable(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    Videojuego item=(Videojuego) lv.getItemAtPosition(position);
                    Toast.makeText(arg0.getContext(),item.getIde(),Toast.LENGTH_SHORT).show();
                    mp= MediaPlayer.create(arg0.getContext(),R.raw.power);
                    mp.start();


                }
            });
        }
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        mp=MediaPlayer.create(this,R.raw.byebye);
        Toast.makeText(this,"Destruido",Toast.LENGTH_LONG);
        Thread timer=new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }catch (Exception e){

                }finally {

                }
            }
        };
        mp.start();
        timer.start();
    }
    @Override protected void onStop() {
        super.onStop();
        mp=MediaPlayer.create(this,R.raw.byebye);
        Toast.makeText(this,"Destruido",Toast.LENGTH_LONG);
    }

}