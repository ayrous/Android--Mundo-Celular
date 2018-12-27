package com.example.acer.mundocelular;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.acer.mundocelular.dao.MensagemDAO;
import com.example.acer.mundocelular.model.Mensagem;

public class AvaliacaoActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button botaoCadastrar;
    private EditText mensagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaoCadastrar = findViewById(R.id.btnEnviar);
        mensagens = findViewById(R.id.edTexto);

        final MensagemHelper helper = new MensagemHelper(AvaliacaoActivity.this);
        Intent intent = getIntent();
        final Mensagem mensagem = (Mensagem) intent.getSerializableExtra("mensagens");

        if (mensagem != null) {
            helper.preecherAvaliacao(mensagem);
        }

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mensagens.getText().length() != 0) {
                    Intent intent = new Intent(AvaliacaoActivity.this, ListagemMensagensActivity.class);
                    startActivity(intent);

                    Mensagem mensagem1 = helper.pegaMensgem();
                    MensagemDAO dao = new MensagemDAO(AvaliacaoActivity.this);

                    if (mensagem1.getId() != null) {
                        dao.alterar(mensagem1);
                    } else {
                        dao.inserir(mensagem1);
                    }

                    dao.close();
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Dê sua avaliação!", Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Você já está nessa tela!", Toast.LENGTH_LONG).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.avaliacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sobre5) {
            Intent intent = new Intent(AvaliacaoActivity.this, SobreActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio5) {

            Intent intent = new Intent(AvaliacaoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_galeria5) {

            Intent intent = new Intent(AvaliacaoActivity.this, GaleriaActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_organelas5) {

            Intent intent = new Intent(AvaliacaoActivity.this, OrganelasActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_tipos5) {

            Intent intent = new Intent(AvaliacaoActivity.this, TiposActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_avaliacao5) {

            Toast.makeText(getApplicationContext(), "Você já está nessa tela!", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_lista5) {
            Intent intent = new Intent(AvaliacaoActivity.this, ListagemMensagensActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
