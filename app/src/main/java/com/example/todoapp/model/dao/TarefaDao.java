package com.example.todoapp.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todoapp.model.database.TarefaDatabase;
import com.example.todoapp.model.entities.Tarefa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class TarefaDao {

    private final TarefaDatabase dbHelper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TarefaDao(Context context) {
        dbHelper = new TarefaDatabase(context);
    }

    public long inserir(Tarefa tarefa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", tarefa.getTitulo());
        values.put("descricao", tarefa.getDescricao());
        values.put("dataInicial", tarefa.getDataInicial().format(formatter));
        values.put("dataDeConclusao", tarefa.getDataDeConclusao().format(formatter));
        values.put("importante", tarefa.isImportante() ? 1 : 0);
        values.put("concluida", tarefa.isConcluida() ? 1 : 0);

        long id = db.insert("tarefas", null, values);
        db.close();
        return id;
    }

    public int atualizar(Tarefa tarefa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo", tarefa.getTitulo());
        values.put("descricao", tarefa.getDescricao());
        values.put("dataInicial", tarefa.getDataInicial().format(formatter));
        values.put("dataDeConclusao", tarefa.getDataDeConclusao().format(formatter));
        values.put("importante", tarefa.isImportante() ? 1 : 0);
        values.put("concluida", tarefa.isConcluida() ? 1 : 0);

        int count = db.update("tarefas", values, "id = ?", new String[]{String.valueOf(tarefa.getid())});
        db.close();
        return count;
    }

    public int deletar(Tarefa tarefa) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete("tarefas", "id = ?", new String[]{String.valueOf(tarefa.getid())});
        db.close();
        return count;
    }

    public ArrayList<Tarefa> listarTodas() {
        ArrayList<Tarefa> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("tarefas", null, null, null, null, null, "id DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
                String dataInicialStr = cursor.getString(cursor.getColumnIndexOrThrow("dataInicial"));
                String dataConclusaoStr = cursor.getString(cursor.getColumnIndexOrThrow("dataDeConclusao"));
                boolean importante = cursor.getInt(cursor.getColumnIndexOrThrow("importante")) == 1;
                boolean concluida = cursor.getInt(cursor.getColumnIndexOrThrow("concluida")) == 1;

                LocalDateTime dataInicial = LocalDateTime.parse(dataInicialStr, formatter);
                LocalDateTime dataConclusao = LocalDateTime.parse(dataConclusaoStr, formatter);

                Tarefa tarefa = new Tarefa(id, titulo, descricao, dataConclusao, importante, concluida);
                tarefa.setDataInicial(dataInicial);

                lista.add(tarefa);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }

    public Tarefa getById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("tarefas", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        Tarefa tarefa = null;

        if (cursor.moveToFirst()) {
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"));
            String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"));
            String dataInicialStr = cursor.getString(cursor.getColumnIndexOrThrow("dataInicial"));
            String dataConclusaoStr = cursor.getString(cursor.getColumnIndexOrThrow("dataDeConclusao"));
            boolean importante = cursor.getInt(cursor.getColumnIndexOrThrow("importante")) == 1;
            boolean concluida = cursor.getInt(cursor.getColumnIndexOrThrow("concluida")) == 1;

            LocalDateTime dataInicial = LocalDateTime.parse(dataInicialStr, formatter);
            LocalDateTime dataConclusao = LocalDateTime.parse(dataConclusaoStr, formatter);

            tarefa = new Tarefa(id, titulo, descricao, dataConclusao, importante, concluida);
            tarefa.setDataInicial(dataInicial);
        }

        cursor.close();
        db.close();
        return tarefa;
    }
}