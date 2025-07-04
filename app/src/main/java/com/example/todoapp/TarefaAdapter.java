package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.datasource.MockData;
import com.example.todoapp.model.entities.Tarefa;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    private List<Tarefa> tarefas;

    public TarefaAdapter(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    static class TarefaViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;

        public TarefaViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxAtividade);
            textView = itemView.findViewById(R.id.textViewTarefa);
        }
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(
                R.layout.recycle_view_item,
                parent,
                false);
        return new TarefaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);

        holder.checkBox.setChecked(tarefa.isConcluida());
        holder.textView.setText(tarefa.getTitulo());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //.txtData.setText("Data limite: " + tarefa.getDataDeConclusao().format(formatter)); colocar prazo de conclusão

        //holder.txtImportante.setText(tarefa.isImportante() ? "Importante" : "Normal");
        //holder.txtConcluida.setText(tarefa.isConcluida() ? "Concluída" : "Pendente");
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }
}
