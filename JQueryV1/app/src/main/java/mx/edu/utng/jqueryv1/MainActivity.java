package mx.edu.utng.jqueryv1;

import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import mx.edu.utng.jqueryv1.juego1.PenguinsoniceMainActivity;

import mx.edu.utng.jqueryv1.ppt.MainActivityPPT;
import mx.edu.utng.jqueryv1.recetario.Main;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_grafica) {
        startActivity(new Intent(MainActivity.this, GraficaActivity.class));
            return true;
        }
       if (id == R.id.action_theme) {
           startActivity(new Intent(this, ThemesActivity.class));
          /* SharedPreferences sharedPreferences;
           sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
           int theme = sharedPreferences.getInt("THEME", 1);

           switch (theme) {
               case 1:
                   setTheme(R.style.AppTheme3);
                   break;
               case 2:
                   setTheme(R.style.AppTheme2);
                   break;
               case 3:
                   setTheme(R.style.AppTheme1);
                   break;
           }

           sharedPreferences.edit().putInt("THEME",1).apply();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            //startActivity(new Intent(MainActivity.this, ThemesActivity.class));
            return true;*/
        }
        if (id == R.id.action_salir) {
            itmSalir();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_modulos) {
            startActivity(new Intent(MainActivity.this, ModulosActivity.class));
        } else if (id == R.id.nav_ubicacion) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));
        } else if (id == R.id.nav_audio) {
            startActivity(new Intent(MainActivity .this, AudioActivity.class));
        } else if (id == R.id.nav_practica) {
            startActivity(new Intent(MainActivity.this, PruebasCodigoActivity.class));
        }else if (id == R.id.nav_send) {
            if(LoginActividad.acceso==1) {
                startActivity(new Intent(MainActivity.this, CorreoActivity.class));
            }else {
                startActivity(new Intent(MainActivity.this, LoginActividad.class));
            }
           // startActivity(new Intent(MainActivity.this, CorreoActivity.class));
        }else if (id == R.id.nav_acercade) {
            startActivity(new Intent(MainActivity.this, AcercaDeActivity.class));
        } else if (id == R.id.nav_calculator) {
            startActivity(new Intent(MainActivity.this, CalculadoraActivity.class));
        } else if (id == R.id.nav_conversor) {
            startActivity(new Intent(MainActivity.this, Euroconvert.class));
        } else if (id == R.id.nav_recetario) {
            startActivity(new Intent(MainActivity.this, Main.class));
        }  else if (id == R.id.nav_pinguino) {
        startActivity(new Intent(MainActivity.this, PenguinsoniceMainActivity.class));

        }  else if (id == R.id.nav_ppt) {
            startActivity(new Intent(MainActivity.this, MainActivityPPT.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void itmSalir(){
        finish();
    }
}
