package mx.edu.utng.jqueryv1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mx.edu.utng.jqueryv1.grafica.SQLControlador;

public class QuizCuatroActivity extends AppCompatActivity {
    List<Question> quesList;
    int score=0;
    int qid=15;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;
    TextView time;
    private final int TIEMPO_ESPERA = 60000;
    SQLControlador dbcal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        dbcal = new SQLControlador(getApplication());
        time = (TextView) findViewById(R.id.txv_tiempo);
        esperar();
        DbHelper db=new DbHelper(this);
        quesList=db.getAllQuestions();
        currentQ=quesList.get(qid);
        txtQuestion=(TextView)findViewById(R.id.textView1);
        final RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
        rda=(RadioButton)findViewById(R.id.radio0);
        rdb=(RadioButton)findViewById(R.id.radio1);
        rdc=(RadioButton)findViewById(R.id.radio2);
        butNext=(Button)findViewById(R.id.button1);
        grp.clearCheck();
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbcal.abrirBaseDeDatos();

                RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
                if (!(rda.isChecked() || rdb.isChecked() || rdc.isChecked())) {
                    Toast.makeText(QuizCuatroActivity.this, "No has contestado la pregunta", Toast.LENGTH_SHORT).show();
                } else {

                    Log.d("yourans", currentQ.getANSWER() + " " + answer.getText());
                    if (currentQ.getANSWER().equals(answer.getText())) {
                        score++;
                        Log.d("score", "Your score" + score);
                    }
                    if (qid < 20) {
                        currentQ = quesList.get(qid);
                        setQuestionView();
                    } else {
                        Intent intent = new Intent(QuizCuatroActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("score", score); //Your score
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                }
                grp.clearCheck();

                int calificacion = (score * 2);
                dbcal.actualizarDatos(4, String.valueOf(calificacion));
                dbcal.cerrar();
            }
        });
    }
    public void esperar() {
        new CountDownTimer(TIEMPO_ESPERA, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Tiempo: " + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                time.setText("Tiempo Agotado");
                finish();
            }
        }.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_quiz, menu);
        return true;
    }
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }
}
