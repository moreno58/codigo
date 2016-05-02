package mx.edu.utng.jqueryv1.grafica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // Información de la tabla
    public static final String TABLE_MEMBER = "calificacion";
    public static final String CALIFICACION_ID = "_id";
    public static final String CALIFICACION_NOMBRE = "calificacion";

    // información del a base de datos
    static final String DB_NAME = "DB_CALIFICACION";
    static final int DB_VERSION = 1;

    // Información de la base de datos
    private static final String CREATE_TABLE = "create table "
            + TABLE_MEMBER + "(" + CALIFICACION_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CALIFICACION_NOMBRE + " TEXT NOT NULL);";

    private static final String INSERTAR1 = "INSERT INTO " + TABLE_MEMBER + " VALUES('1','0')";
    private static final String INSERTAR2 = "INSERT INTO " + TABLE_MEMBER + " VALUES('2','0')";
    private static final String INSERTAR3 = "INSERT INTO " + TABLE_MEMBER + " VALUES('3','0')";
    private static final String INSERTAR4 = "INSERT INTO " + TABLE_MEMBER + " VALUES('4','0')";
    private static final String INSERTAR5 = "INSERT INTO " + TABLE_MEMBER + " VALUES('5','0')";
    //private static final String INSERTAR6 =  "INSERT INTO " + TABLE_MEMBER+ " VALUES('6','0','0')";
    //private static final String INSERTAR7 =  "INSERT INTO " + TABLE_MEMBER+ " VALUES('7','0','0')";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(INSERTAR1);
        db.execSQL(INSERTAR2);
        db.execSQL(INSERTAR3);
        db.execSQL(INSERTAR4);
        db.execSQL(INSERTAR5);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        onCreate(db);
    }
}