package mx.edu.utng.jqueryv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeanette Moreno on 16/03/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "triviaQuiz";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
        //db.close();
    }
    private void addQuestions()
    {
        //Preguntas del quiz uno
        Question q1=new Question("¿Que es jQuery Mobile?","Un juego de programación","Marco de trabajo para escribir aplicaciones web móviles", "Marco de trabajo para crear documentos", "Marco de trabajo para escribir aplicaciones web móviles");
        this.addQuestion(q1);
        Question q2=new Question("¿Cuál de los siguientes es correcta? ", "jQuery es una biblioteca JavaScript", "jQuery es una biblioteca JSON", "DOS", "jQuery es una biblioteca JavaScript");
        this.addQuestion(q2);
        Question q3=new Question("¿Qué signo usa jQuery como un acceso directo para jQuery? ", "el signo $", "FLASH","el signo ? ","el signo $");
        this.addQuestion(q3);
        Question q4=new Question("Mira el selector siguiente: $ ( \"div\"). ¿Qué se elige?","El primer segmento", "Todos los elementos div", "El ultimo elemento","Todos los elementos div");
        this.addQuestion(q4);
        Question q5=new Question("¿Es una biblioteca jQuery para secuencias de comandos de secuencias de comandos del cliente o servidor?","scripts de servidor","Python","secuencias de comandos de cliente","secuencias de comandos de cliente");
        this.addQuestion(q5);
        //Preguntas del quiz dos
        Question q6=new Question("¿Es posible el uso de jQuery junto con AJAX?","Si","No","Dependiendo la ocasion","Si");
        this.addQuestion(q6);
        Question q7=new Question("El método html() de jQuery funciona tanto para documentos HTML y XML","Cierto","Falso","Solo html","Falso");
        this.addQuestion(q7);
        Question q8=new Question("¿Cuál es el código de jQuery correcta para establecer el color de fondo de todos los elementos p a rojo?",". $ ( \"P\") CSS ( \"background-color\", \"rojo\");","  . $ ( \"P\") style ( \"background-color\", \"rojo\");",
                ". $ ( \"P\") manipular ( \"background-color\", \"rojo\");",". $ ( \"P\") CSS ( \"background-color\", \"rojo\");");
        this.addQuestion(q8);
        Question q9=new Question("¿Con jQuery, mira el siguiente selector: $ ( \"div.intro\"). ¿Qué se elige?","Todos los elementos div con id = \"intro\"","Todos los elementos div con class = \"intro\"","El primer elemento div con class = \"intro\"","Todos los elementos div con class = \"intro\"");
        this.addQuestion(q9);
        Question q10=new Question("¿Qué método jQuery se utiliza para ocultar los elementos seleccionados?","disable()","enable()","ocultar()","disable()");
        this.addQuestion(q10);
        //Preguntas para el quiz tres
        Question q11=new Question(". ¿Qué método jQuery se utiliza para establecer una o más propiedades de estilo para los elementos seleccionados?","html()","css()","style()","css()");
        this.addQuestion(q11);
        Question q12=new Question("¿Qué método se utiliza jQuery para realizar una petición HTTP asíncrona?","jQuery.ajaxSetup()","jQuery.ajaxAsync()","jQuery.ajax()","jQuery.ajax()");
        this.addQuestion(q12);
        Question q13=new Question("¿Cuál es el código de jQuery correcta de hacer todos los elementos div 100 píxeles de altura?","$ ( \"div\").heigth(100)","$ ( \"div\").height=\"100\"","$ ( \"div\").YPos(100)","$ ( \"div\").heigth(100)");
        this.addQuestion(q13);
        Question q14=new Question("¿Qué afirmación es verdadera?","Para utilizar jQuery, puede hacer referencia a una biblioteca jQuery alojado en Google","Para utilizar jQuery, usted no tiene que hacer nada. La mayoría de los navegadores (Internet Explorer, Chrome, Firefox y Opera) tienen la librería","Para utilizar jQuery, usted debe comprar la biblioteca jQuery en www.jquery.com","Para utilizar jQuery, puede hacer referencia a una biblioteca jQuery alojado en Google");
        this.addQuestion(q14);
        Question q15=new Question("¿Qué lenguaje de script está escrito en jQuery?","C++","DO#","JavaScript","JavaScript");
        this.addQuestion(q15);
        //Preguntas para el quiz cuatro
        Question q16=new Question("¿Qué función jQuery se utiliza para evitar que el código se ejecute, antes de que el documento haya terminado de cargarse?","$(body).onload()","$(document)ready()","$(document).load()","$(document)ready()");
        this.addQuestion(q16);
        Question q17=new Question("¿Qué método jQuery se debe utilizar para hacer frente a los conflictos de nombres?","noNameConflict()","conflict()","noconflict()","noconflict()");
        this.addQuestion(q17);
        Question q18=new Question("¿Qué método se utiliza jQuery para cambiar entre añadir / eliminar una o más clases (por CSS) de los elementos seleccionados?","switchClass()","toggleClass()","altClass()","toggleClass()");
        this.addQuestion(q18);
        Question q19=new Question("Observa el siguiente selector: $ ( \"div p\"). ¿Qué se elige?","Todos los elementos P dentro de un elemento div","El primer elemento p dentro de un elemento div","Todos los elementos div con elemento PA","Todos los elementos P dentro de un elemento div");
        this.addQuestion(q19);
        Question q20=new Question("¿Es jQuery un estándar W3C?","No","Si","No se","No");
        this.addQuestion(q20);
        //Preguntas del quiz final
        Question q21=new Question("Observa el siguiente selector: $ ( \"# p intro\"). ¿Qué se elige?","Todos los p elementos con class = \"intro\"","El elemento p con id = \"intro\"","Los dos anteriores","El elemento p con id = \"intro\"");
        this.addQuestion(q21);
        Question q22=new Question("¿Qué signo usa jQuery como un acceso directo para jQuery? ", "el signo $", "FLASH","el signo ? ","el signo $");
        this.addQuestion(q22);
        Question q23=new Question("¿Qué método jQuery se utiliza para eliminar los elementos seleccionados?","remove()","detach()","Ambos métodos se pueden utilizar","Ambos métodos se pueden utilizar");
        this.addQuestion(q23);
        Question q24=new Question("Observa el siguiente selector: $ ( \":disabled\"). ¿Qué se elige?","Todos los elementos que no contienen el texto \"desactivado\"","Todos los elementos ocultos","Todos los elementos de entrada deshabilitados","Todos los elementos ocultos");
        this.addQuestion(q24);
        Question q25=new Question("¿Qué método jQuery devuelve el elemento de matriz directa del elemento seleccionado?","antepasado()","parent()","parents()","parent()");
        this.addQuestion(q25);
        Question q26=new Question("El método anímate() jQuery se puede utilizar para animar cualquier propiedad CSS?","Solo propiedades que contienen valores numéricos","Todas las propiedades excepto las propiedades abreviadas","Si","Solo propiedades que contienen valores numéricos");
        this.addQuestion(q26);
        Question q27=new Question("¿Es una biblioteca jQuery para secuencias de comandos de secuencias de comandos del cliente o servidor?","scripts de servidor","Python","secuencias de comandos de cliente","secuencias de comandos de cliente");
        this.addQuestion(q27);
        Question q28=new Question("El método html() de jQuery funciona tanto para documentos HTML y XML","Cierto","Falso","Solo html","Falso");
        this.addQuestion(q28);
        Question q29=new Question("¿Qué método se utiliza jQuery para realizar una petición HTTP asíncrona?","jQuery.ajaxSetup()","jQuery.ajaxAsync()","jQuery.ajax()","jQuery.ajax()");
        this.addQuestion(q29);
        Question q30=new Question("¿Qué método jQuery se debe utilizar para hacer frente a los conflictos de nombres?","noNameConflict()","conflict()","noconflict()","noconflict()");
        this.addQuestion(q30);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

}
