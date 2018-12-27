package com.example.acer.mundocelular;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.acer.mundocelular.dao.MensagemDAO;
import com.example.acer.mundocelular.model.Mensagem;

import java.util.List;

public class ListagemMensagensActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ListView listaMensagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_mensagens);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaMensagens = findViewById(R.id.lista);

        listaMensagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Mensagem mensagem = (Mensagem) listaMensagens.getItemAtPosition(i);
                Intent intent = new Intent(ListagemMensagensActivity.this, AvaliacaoActivity.class);
                intent.putExtra("mensagens", mensagem);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListagemMensagensActivity.this, AvaliacaoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        registerForContextMenu(listaMensagens);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Mensagem mensagem = (Mensagem) listaMensagens.getItemAtPosition(info.position);

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Mensagem mensagem = (Mensagem) listaMensagens.getItemAtPosition(info.position);
                MensagemDAO dao = new MensagemDAO(ListagemMensagensActivity.this);
                dao.deletar(mensagem);
                dao.close();
                carregarLista();
                Toast.makeText(getApplicationContext(), "Mensagem deletada!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });//fim deletar

    }

        @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listagem_mensagens, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sobre6) {
            Intent intent = new Intent(ListagemMensagensActivity.this, SobreActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio6) {

            Intent intent = new Intent(ListagemMensagensActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_organelas6) {

            Intent intent = new Intent(ListagemMensagensActivity.this, OrganelasActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria6) {
            Intent intent = new Intent(ListagemMensagensActivity.this, GaleriaActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_tipos6) {

            Intent intent = new Intent(ListagemMensagensActivity.this, TiposActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_avaliacao6) {

            Intent intent = new Intent(ListagemMensagensActivity.this, AvaliacaoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_lista6) {
            Toast.makeText(getApplicationContext(), "Você já está nessa tela", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void carregarLista(){
        MensagemDAO dao = new MensagemDAO(this);

        List<Mensagem> mensagens = dao.buscaMensagem();
        ArrayAdapter<Mensagem> adaptador = new ArrayAdapter<Mensagem>(this, android.R.layout.simple_list_item_1, mensagens);

        listaMensagens.setAdapter(adaptador);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

}




