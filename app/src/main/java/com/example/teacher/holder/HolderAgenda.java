package com.example.teacher.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacher.R;
public class HolderAgenda extends RecyclerView.ViewHolder {
    public TextView txtNome, txtMateria, txtLocal, txtData, txtHora;
    public Button btnEditar, btnDeletar;
    public HolderAgenda(@NonNull View itemView) {
        super(itemView);
        txtNome = itemView.findViewById(R.id.txtNome);
        txtMateria = itemView.findViewById(R.id.txtMateria);
        txtLocal = itemView.findViewById(R.id.txtLocal);
        txtData = itemView.findViewById(R.id.txtData);
        txtHora = itemView.findViewById(R.id.txtHora);
        btnEditar = itemView.findViewById(R.id.btnEditar);
        btnDeletar = itemView.findViewById(R.id.btnDeletar);
    }
}