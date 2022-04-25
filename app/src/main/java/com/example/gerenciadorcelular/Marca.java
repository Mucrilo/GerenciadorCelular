package com.example.gerenciadorcelular;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Marca {

    @PrimaryKey(autoGenerate = true)
    int marcaID;
    String marca;

    public Marca() {
    }

    public Marca(int marcaID, String marca) {
        this.marcaID = marcaID;
        this.marca = marca;
    }

    public int getMarcaID() {
        return marcaID;
    }

    public void setMarcaID(int marcaID) {
        this.marcaID = marcaID;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return this.marcaID + ": " + this.marca;
    }
}

