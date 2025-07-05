package com.example.todoapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.TarefaAdapter;
import com.example.todoapp.model.dao.TarefaDao;
import com.example.todoapp.model.entities.Tarefa;

import java.time.LocalDateTime;
import java.util.ArrayList;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<Tarefa> listaTarefas;
    private TarefaAdapter adapter;
    private RecyclerView recyclerView;
    private TarefaDao tarefaDao;
    private ActivityResultLauncher<Intent> criarTarefaLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        tarefaDao = new TarefaDao(this);
        listaTarefas = tarefaDao.listarTodas();

        recyclerView = findViewById(R.id.recyclerViewListaTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TarefaAdapter(this, listaTarefas);
        recyclerView.setAdapter(adapter);

        criarTarefaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        listaTarefas.clear();
                        listaTarefas.addAll(tarefaDao.listarTodas());
                        adapter.notifyDataSetChanged();
                    }
                }
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    public void abrirTelaCriarTarefa(View view) {
        Intent intent = new Intent(SecondActivity.this, CriarTarefaActivity.class);
        criarTarefaLauncher.launch(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            listaTarefas.clear();
            listaTarefas.addAll(tarefaDao.listarTodas());
            adapter.notifyDataSetChanged();
        }
    }
}