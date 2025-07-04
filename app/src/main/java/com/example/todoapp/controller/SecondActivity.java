package com.example.todoapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.TarefaAdapter;
import com.example.todoapp.datasource.MockData;
import com.example.todoapp.model.entities.Tarefa;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<Tarefa> listaTarefas;
    private TarefaAdapter adapter;
    private RecyclerView recyclerView;


    EditText inputTextoAtividade;
    /*
    DatePicker datePicker = findViewById(R.id.datePicker);
    TimePicker timePicker = findViewById(R.id.timePicker);
    Switch switchRepetir = findViewById(R.id.switchRepetir);
    Switch switchImportante = findViewById(R.id.switchImportante);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        recyclerView = findViewById(R.id.recyclerViewListaTarefas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TarefaAdapter(MockData.getInstance().tarefas);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    int counter = 0;
    public void adicionarTafera(View v){

        /*
        String tituloAtividade = inputTextoAtividade.getText().toString();  // título da atividade
        DatePicker datePicker = findViewById(R.id.datePicker);
        TimePicker timePicker = findViewById(R.id.timePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1; // Janeiro = 0
        int year = datePicker.getYear();
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        LocalDateTime dataConclusao = LocalDateTime.of(year, month, day, hour, minute); // data de conclusão

        boolean importante = switchImportante.isChecked();*/

        String tituloAtividade = "Atividade " + counter;  // título da atividade
        String descricaoAtividade = "Descrição " + counter;
        LocalDateTime dataConclusao = LocalDateTime.of(2025, 8, 22, 12, 15); // data de conclusão
        boolean importante = true;
        boolean concluida = false;

        counter++;
        MockData.getInstance().tarefas.add(0,
                new Tarefa(tituloAtividade, descricaoAtividade, dataConclusao, importante, concluida)
        );
        adapter.notifyItemInserted(0);

    }

    /*
    public void abrirTelaCriarTarefa(){
        Intent intent = new Intent(SecondActivity.this, item_.class);
        intent.putExtra("", ""); //Se for passar algo para a segunda tela, passar por aqui atrvés de chave e valor
        startActivity(intent);
    }*/
}