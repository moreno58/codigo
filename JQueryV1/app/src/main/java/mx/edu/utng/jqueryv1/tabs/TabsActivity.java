package mx.edu.utng.jqueryv1.tabs;

/**
 * Created by Jeanette Moreno on 24/04/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import mx.edu.utng.jqueryv1.GraficaActivity;
import mx.edu.utng.jqueryv1.MapsActivity;
import mx.edu.utng.jqueryv1.QuizTresActivity;
import mx.edu.utng.jqueryv1.QuizUnoActivity;
import mx.edu.utng.jqueryv1.R;
import mx.edu.utng.jqueryv1.SettingsActivity;
import mx.edu.utng.jqueryv1.videos.VideoActivity;


public class TabsActivity extends AppCompatActivity implements ActionBar.TabListener{




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Three tab to display in actionbar
        ab.addTab(ab.newTab().setText("Información").setTabListener(this));
        ab.addTab(ab.newTab().setText("Quiz").setTabListener(this));
        ab.addTab(ab.newTab().setText("Video").setTabListener(this));

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        //Called when a tab is selected
        int nTabSelected = tab.getPosition();
        switch (nTabSelected) {
            case 0:
                setContentView(R.layout.actionbar_tab_1);
                break;
            case 1:
                setContentView(R.layout.actionbar_tab_2);
                startActivity(new Intent(this, QuizUnoActivity.class));
                break;
            case 2:
                setContentView(R.layout.actionbar_tab_3);
                startActivity(new Intent(this, VideoActivity.class));
                break;
        }
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // Called when a tab unselected.
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        // Called when a tab is selected again.
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
            startActivity(new Intent(TabsActivity.this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_grafica) {
            startActivity(new Intent(TabsActivity.this, GraficaActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
