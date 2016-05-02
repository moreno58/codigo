package mx.edu.utng.jqueryv1;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;
public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
//get rating bar object
		RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
//get text view
		TextView t=(TextView)findViewById(R.id.textResult);
//get score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		int cal=0;
//display score
		bar.setRating(score);
		switch (score)
		{
			case 0: t.setText("Pésimo, suerte para la próxima");
				break;
			case 1:
				cal = 2;
				t.setText("Muy mal, tu calificación es: "+cal);
				break;

			case 2:
				cal = 4;
				t.setText("Mal, Tu calificación es: "+cal);
				break;
			case 3:
				cal = 6;
				t.setText("Bien, Tu calificación es: "+cal);
				break;
			case 4:
				cal = 8;
				t.setText("Muy bien, tu calificación es: "+cal);
				break;
			case 5:
				cal = 10;
				t.setText("¡Excelente! Tu calificación es: "+10);
				break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_result, menu);
		return true;
	}
}
