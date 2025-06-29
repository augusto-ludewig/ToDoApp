package com.example.todoapp.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.todoapp.R;

import java.time.LocalDateTime;

public class SecondActivity extends AppCompatActivity {

    EditText inputTextoAtividade;
    EditText inputDataConclusão;
    EditText inputRepeticao;
    EditText inputLembrete;

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

    public void adicionarTafera(View v){
        String atividade = inputTextoAtividade.getText().toString();
        String dataInicio = LocalDateTime.now().toString();
        String dataConclusao = inputDataConclusão.getText().toString();
        String lembrete = inputLembrete.getText().toString();
        String repetir = inputRepeticao.getText().toString();

        //Task tarefa = new Task();
    }
}