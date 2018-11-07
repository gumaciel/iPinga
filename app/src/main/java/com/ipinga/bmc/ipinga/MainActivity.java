package com.ipinga.bmc.ipinga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista = (ListView) findViewById(R.id.listLoja);
        ArrayList<Loja> lojas = adicionarLojas();
        ArrayAdapter adapter = new MainAdapter(this, lojas);
        lista.setAdapter(adapter);

    }

    private ArrayList<Loja> adicionarLojas() {
        ArrayList<Loja> lojas = new ArrayList<Loja>();
        Loja e = new Loja("Loja 1",
                    "Rua Pacatuba S/N", R.drawable.cerveja);
        lojas.add(e);
        e = new Loja("Loja 2",
                    "Rua Sergipe S/N", R.drawable.cerveja);
        lojas.add(e);
        e = new Loja("Loja 3",
                    "Av. Otoniel Dórea", R.drawable.cerveja);
        lojas.add(e);
        e = new Loja("Loja4",
                    "R. Franklin de Campos Sobral, 1675", R.drawable.cerveja);
        lojas.add(e);
        return lojas;
    }
}