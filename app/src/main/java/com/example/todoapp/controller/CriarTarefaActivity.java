package com.example.todoapp.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.R;
import com.example.todoapp.model.dao.TarefaDao;
import com.example.todoapp.model.entities.Tarefa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class CriarTarefaActivity extends AppCompatActivity {

    private EditText inputTitulo, inputDescricao, inputDataConclusao;
    private Switch switchImportante;
    private Button botaoSalvar;

    private LocalDateTime dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_tarefa);

        inputTitulo = findViewById(R.id.inputTitulo);
        inputDescricao = findViewById(R.id.inputDescricao);
        inputDataConclusao = findViewById(R.id.inputDataConclusao);
        switchImportante = findViewById(R.id.switchImportante);
        botaoSalvar = findViewById(R.id.buttonSalvar);

        inputDataConclusao.setFocusable(false);
        inputDataConclusao.setOnClickListener(v -> abrirDatePicker());

        botaoSalvar.setOnClickListener(v -> salvarTarefa());
    }

    private void abrirDatePicker() {
        Calendar calendarioAtual = Calendar.getInstance();
        int ano = calendarioAtual.get(Calendar.YEAR);
        int mes = calendarioAtual.get(Calendar.MONTH);
        int dia = calendarioAtual.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    inputDataConclusao.setText(String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear));
                    dataSelecionada = LocalDateTime.of(LocalDate.of(selectedYear, selectedMonth + 1, selectedDay), LocalTime.of(12, 0));
                },
                ano, mes, dia
        );

        datePickerDialog.show();
    }

    private void salvarTarefa() {
        String titulo = inputTitulo.getText().toString();
        String descricao = inputDescricao.getText().toString();
        boolean importante = switchImportante.isChecked();

        if (titulo.isEmpty() || descricao.isEmpty() || dataSelecionada == null) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Tarefa novaTarefa = new Tarefa(titulo, descricao, dataSelecionada, importante, false);
        TarefaDao dao = new TarefaDao(this);
        long id = dao.inserir(novaTarefa);

        if (id > 0) {
            Toast.makeText(this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();
        }

    }

    public void cancelarCadastro(View view) {
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        finish();
    }


}
