package com.example.catalogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.catalogo.Videojuego.Videojuego;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.catalogo.R.color.purple_700;
import static com.example.catalogo.R.color.white;

public class MainActivity extends AppCompatActivity {
    Spinner Spin;
    EditText anio;
    MediaPlayer mp;
    EditText titulo;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String cadenan;
    ImageView imag;
    RatingBar calif;
    int auxiliar;
    private Integer[] arreglo={
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=getPreferences(Context.MODE_PRIVATE);

        editor=sp.edit();
        Spin=(Spinner) findViewById(R.id.spinner);
        anio=findViewById(R.id.Anio);
        titulo=findViewById(R.id.Titulo);
        imag=findViewById(R.id.Imagen);
        calif=findViewById(R.id.ratingBar);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Categoria, R.layout.text_view);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin.setAdapter(adapter);


        Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                cadenan=(String)adapterView.getItemAtPosition(position);
                auxiliar=position;
                if (position != 0) {

                    //Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(adapterView.getContext(),String.valueOf(identi()),Toast.LENGTH_LONG).show();
                    imag.setImageResource(arreglo[position-1]);
                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }

        });


    }
    public String identi(){
        int a[];
        int w=0;
        String cadena;
        Random random;
        random=new Random();
        a=new int[5];
        do {
            for (int i = 0; i < 5; i++) {

                a[i] = random.nextInt(10);
            }
            cadena = String.valueOf(a[4]) + String.valueOf(a[3]) + String.valueOf(a[2]) + String.valueOf(a[1]) + String.valueOf(a[0]);
            Videojuego aux=new Videojuego(1,"aux",2,"no","no",1.0f);
            String caracter[]=aux.leerarchivo(this);
            if(caracter[0].equals("Exc")){
                return cadena;
            }
            for(int i=0;i<caracter.length;i++){
                String[] partes=caracter[i].split("\\$");
                if(partes[3].equals(cadena)){
                    w=0;
                    i=caracter.length;
                }
                else {
                    w++;
                }
            }

        }while (w==0);
        return cadena;
    }
    public boolean Fecha_correcta(){
        final Calendar now = Calendar.getInstance();
        now.getTime();
        SimpleDateFormat dfDate_day= new SimpleDateFormat("E, dd/MM/yyyy HH:mm:ss");
        String[] an= dfDate_day.format(now.getTime()).split(" ");
        String[] bn=an[1].split("/");
        if(Integer.parseInt(anio.getText().toString())>=1970 && Integer.parseInt(anio.getText().toString())<=Integer.parseInt(bn[2])){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean validacion(){
        boolean val=true;
        boolean ban=true;
        Pattern pattern;
        pattern=Pattern.compile("(([A-Z][a-z]+)|[0-9]+)(\\s(([A-Z][a-z]+)|[0-9]+))*");
        Matcher matcher=pattern.matcher(titulo.getText().toString());
        if(titulo.getText().toString().equals("")&&ban) {
            titulo.setError(getResources().getString(R.string.Campo));
            val = false;
            ban = false;
        }

        if(!matcher.matches()&&ban){
            titulo.setError(getResources().getString(R.string.Validaciotitulo));
            ban=false;
            val=false;
        }
        if(anio.getText().toString().equals("")){
            anio.setError(getResources().getString(R.string.Fecha_vac));
            val=false;
        }
        else {
            if (!Fecha_correcta()) {
                anio.setError(getResources().getString(R.string.Rango));
                val= false;
            }

        }
        if(!validanoduplicado()){
            val=false;
        }
        if(cadenan.equals("Elige una categoria")){
            val=false;
            TextView errorText = (TextView)Spin.getSelectedView();
            errorText.setError(getResources().getString(R.string.Categorian));
            Toast.makeText(this,"Debes seleccionar una categoria",Toast.LENGTH_LONG).show();
        }
        return val;

    }
    public boolean validanoduplicado(){
        Videojuego aux=new Videojuego(1,"aux",2,"no","no",1.0f);
        String caracter[]=aux.leerarchivo(this);
        if(caracter[0].equals("Exc")){
            return true;
        }
        for(int i=0;i<caracter.length;i++){
            String[] partes=caracter[i].split("\\$");
            if(partes[0].equals(titulo.getText().toString())&&partes[1].equals(anio.getText().toString())&&partes[2].equals(cadenan)){
                titulo.setError(getResources().getString(R.string.Repetido));
                anio.setError(getResources().getString(R.string.Repetido));
                TextView errorText = (TextView)Spin.getSelectedView();
                errorText.setError(getResources().getString(R.string.Repetido));
                return false;
            }
        }
        return true;
    }
    public void clic(View view) throws IOException {
      //  Toast.makeText(this,identi(),Toast.LENGTH_LONG).show();
        BufferedWriter bw;
        Videojuego aux=new Videojuego(1,"aux",2,"no","no",1.0f);
       // String caracter[]=aux.leerarchivo(this);

        if(validacion()){
//            InputStreamReader archivo=new InputStreamReader(getResources().openRawResource(R.raw.archivo));

            Intent intent = new Intent(this, MainActivity2.class);
            Bundle bundle=new Bundle();

            Videojuego videojuego=new Videojuego(0,titulo.getText().toString(),Integer.parseInt(anio.getText().toString()),cadenan,identi(),calif.getRating());
            bundle.putSerializable("Videojuego",  videojuego);
            guardaarchivo();
            mp= MediaPlayer.create(this,R.raw.torch);
            mp.start();
            intent.putExtras(bundle);
            
            startActivity(intent);
            Animatoo.animateShrink(this);
        }
        else {
            mp= MediaPlayer.create(this,R.raw.sms);
            mp.start();
        }


    }

    public void guardaarchivo(){
        try {
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput("Archivo.txt",Context.MODE_APPEND));
            archivo.write(titulo.getText().toString()+"$"+anio.getText().toString()+"$"+auxiliar+"$"+identi()+"$"+String.valueOf(calif.getRating())+"\n");
            //archivo.write(titulo.getText().toString()+"$"+anio.getText().toString()+"$"+cadenan+"$"+identi()+"$"+String.valueOf(calif.getRating()));
            archivo.flush();
            archivo.close();
            //leerarchivo();
        }catch (Exception e){
            Log.e("Archivo","Error al escribir un archivo");
            titulo.setText("Bankau");
        }
    }

    @Override
    protected void onDestroy() {
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

    @Override
    protected void onRestart() {
        super.onRestart();
        titulo.setText("");
        anio.setText("");
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Categoria, R.layout.text_view);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spin.setAdapter(adapter);
        imag.setImageResource(android.R.color.transparent);
        calif.setRating(0);
    }
}