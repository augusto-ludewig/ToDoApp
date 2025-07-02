package com.example.todoapp.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoapp.R;
import com.example.todoapp.model.Tarefa;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    EditText inputTextoAtividade;
    EditText inputDataConclusão;
    EditText inputRepeticao;
    EditText inputLembrete;
    //Switch switchImportante = findViewById(R.id.switchImportante);
    ArrayList<String> tarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    int counter = 0;
    void updateList(View v){
        tarefas.add("Item " + counter++);
        updateListView();
    }

    public void updateListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                SecondActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                tarefas
        );
    }

    public void adicionarTafera(View v){
        String tituloAtividade = inputTextoAtividade.getText().toString();
        LocalDateTime dataInicio = LocalDateTime.now();

        String conclusao = inputDataConclusão.getText().toString();
        //LocalDateTime dataConclusao = LocalDateTime.of(conclusao);

        String lembrete = inputLembrete.getText().toString();
        String repetir = inputRepeticao.getText().toString();
        //boolean importante = switchImportante.isChecked();

        //Tarefa task = new Tarefa(tituloAtividade, dataInicio, dataConclusao, repetir, importante, 1);
    }
}