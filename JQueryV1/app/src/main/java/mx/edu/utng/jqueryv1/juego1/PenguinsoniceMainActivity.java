package mx.edu.utng.jqueryv1.juego1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import mx.edu.utng.jqueryv1.R;


public class PenguinsoniceMainActivity extends Activity {
    public static AlmacenPuntuacionesFichero almacen;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_pinguino);
        loadPlayButton();
        loadPreferencesButton();
        loadExitButton();
        loadScoreButton();
        loadHelpButton();
        loadAboutmeButton();
        almacen = new AlmacenPuntuacionesFichero(this);
    }

	private void loadAboutmeButton() {
		Button bAboutme = (Button) findViewById(R.id.Aboutme);
		bAboutme.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StartAboutme();
			}
		});
	}

	protected void StartAboutme() {
		Intent i = new Intent(this, AboutmeActivity.class);
		startActivity(i);
		
	}

	private void loadHelpButton() {
		Button bHelp = (Button) findViewById(R.id.Help);
		bHelp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StartHelp();
			}
		});
		
	}

	protected void StartHelp() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, HelpActivity.class);
		startActivity(i);
	}

	private void loadScoreButton() {
		Button bScore = (Button) findViewById(R.id.Score);
		bScore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StartScore();
			}
		});
		
	}

	protected void StartScore() {
		Intent i = new Intent(this,Puntuaciones.class);
		startActivity(i);
	}

	private void loadExitButton() {
		Button bExit = (Button) findViewById(R.id.Exit);
		bExit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	private void loadPreferencesButton() {
		Button bPreferences = (Button) findViewById(R.id.Preferences);
		bPreferences.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StartPreferences();
			}
		});
		
	}

	protected void StartPreferences() {
		Intent i = new Intent(this, Preferencias.class);
		startActivity(i);
	}

	private void loadPlayButton() {
		Button bPlay = (Button) findViewById(R.id.Play);
		bPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				StartPlay();
			}
		});
		
	}

	protected void StartPlay() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this, juegoActivity.class);
		startActivityForResult(i,1234);
	}
	
	@Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
          super.onActivityResult(requestCode, resultCode, data);
          if (requestCode==1234 & resultCode==RESULT_OK & data!=null) {
                 Bundle extras = data.getExtras();
                 int puntj1 =  extras.getInt("puntj1");
                 String nomj1 = extras.getString("nomj1");
                 int puntj2 = extras.getInt("puntj2");
                 String nomj2 = extras.getString("nomj2");
				 almacen.guardarPuntuacion(puntj1,nomj1, puntj2,nomj2, System.currentTimeMillis());
                 StartScore();
          }
	}
}