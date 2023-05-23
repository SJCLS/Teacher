package com.example.teacher.model;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.teacher.activity.Resumo;
import com.example.teacher.config.ConfiguracaoFirebase;
import com.example.teacher.helper.Base64Custom;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class Agenda implements Serializable {
    private String id;
    private String curso;
    private String materia;
    private String local;
    private String data;
    private String hora;
    private String resumo;

    public Agenda() {
    }
    public Agenda(String id, String curso, String materia, String local, String data, String hora, String resumo) {
        this.id = id;
        this.curso = curso;
        this.materia = materia;
        this.local = local;
        this.data = data;
        this.hora = hora;
        this.resumo = resumo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public String getMateria() {
        return materia;
    }
    public void setMateria(String materia) {
        this.materia = materia;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getResumo() {
        return resumo;
    }
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
    public void salvar(String agendaId) {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("agendaProfessor")
                .child(idUsuario)
                .child(agendaId) // Utilize o ID recebido como parâmetro
                .setValue(this);
    }
    public void salvarResumo(String agendaId) {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("agendaProfessor")
                .child(idUsuario)
                .child(agendaId)
                .setValue(this);
    }
    public void excluirAgenda(String agendaId) {
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("agendaProfessor")
                .child(idUsuario)
                .child(agendaId)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // A agenda foi excluída com sucesso
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Ocorreu um erro ao excluir a agenda
                    }
                });
    }
    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", curso='" + curso + '\'' +
                ", materia='" + materia + '\'' +
                ", local='" + local + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", resumo='" + resumo + '\'' +
                '}';
    }
}
