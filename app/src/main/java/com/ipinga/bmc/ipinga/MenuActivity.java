package com.ipinga.bmc.ipinga;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ListView lista = (ListView) findViewById(R.id.listLoja);
        ArrayAdapter adapter = new MainAdapter(this, adicionarLojas());
        lista.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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