package com.example.gerenciadorcelular;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Marca.class,
        parentColumns = "marcaID", childColumns = "marcaID"))
public class Celular {

    @PrimaryKey(autoGenerate = true)
    int celularID;
    int marcaID;
    String modelo;

    public Celular(){
    }

    public Celular(int celularID, int marcaID, String modelo) {
        this.celularID  = celularID;
        this.marcaID = marcaID;
        this.modelo = modelo;
    }

    public int getCelularID() {

        return celularID;

    }

    public void setCelularID(int celularID) {

        this.celularID = celularID;
    }

    public int getMarcaID() {

        return marcaID;
    }

    public void setMarcaID(int marcaID) {

        this.marcaID = marcaID;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return getCelularID() + ": " + getModelo();
    }
}
