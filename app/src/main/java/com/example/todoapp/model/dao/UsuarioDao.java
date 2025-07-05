package com.example.todoapp.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todoapp.model.database.TarefaDatabase;
import com.example.todoapp.model.entities.Usuario;

public class UsuarioDao {

    private final TarefaDatabase dbHelper;

    public UsuarioDao(Context context) {
        this.dbHelper = new TarefaDatabase(context);
    }

    // Inserir novo usuário no banco
    public long inserir(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", usuario.getUsuario());
        values.put("senha", usuario.getSenha());
        long id = db.insert("usuarios", null, values);
        db.close();
        return id;
    }

    // Verifica se o usuário e senha conferem
    public boolean autenticar(String usuario, String senha) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?",
                new String[]{usuario, senha}
        );
        boolean existe = cursor.moveToFirst();
        cursor.close();
        db.close();
        return existe;
    }

    // Verifica se já existe um usuário com o mesmo nome (evita duplicidade)
    public boolean usuarioExiste(String usuario) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM usuarios WHERE usuario = ?",
                new String[]{usuario}
        );
        boolean existe = cursor.moveToFirst();
        cursor.close();
        db.close();
        return existe;
    }
}
