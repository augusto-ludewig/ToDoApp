    package com.example.todoapp.model.database;

    import android.content.Context;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class TarefaDatabase extends SQLiteOpenHelper {

        private static final String DB_NAME = "toDoAppSqlite";
        private static final int DB_VERSION = 2;
        private static String DB_TABLE = "tarefas";
        private static String COL_ID = "id";
        private static String COL_TITULO = "titulo";
        private static String COL_DESCRICAO = "descricao";
        private static String COL_DATA_INICIAL = "dataInicial";
        private static String COL_DATA_CONCLUSAO = "dataDeConclusao";
        private static String COL_IMPORTANTE = "importante";
        private static String COL_CONCLUIDA = "concluida";

        public TarefaDatabase(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + " ("
                    + COL_ID + " Integer primary key autoincrement, "
                    + COL_TITULO + " TEXT, "
                    + COL_DESCRICAO + " TEXT, "
                    + COL_DATA_INICIAL + " TEXT, "
                    + COL_DATA_CONCLUSAO + " TEXT, "
                    + COL_IMPORTANTE + " Integer, "
                    + COL_CONCLUIDA + " Integer)";
            db.execSQL(query);

            String queryUsuario = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "usuario TEXT UNIQUE, "
                    + "senha TEXT)";
            db.execSQL(queryUsuario);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < 2) {
                db.execSQL("CREATE TABLE IF NOT EXISTS usuarios (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "usuario TEXT UNIQUE, " +
                        "senha TEXT)");
            }
        }
    }
