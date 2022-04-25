package com.example.gerenciadorcelular;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MarcaDao {

    @Query("SELECT * FROM Marca WHERE marcaID = :id LIMIT 1")
    Marca get(int id);

    @Query("SELECT * FROM Marca")
    List<Marca> getAll();

    @Insert
    void insertAll(Marca... marca);

    @Update
    void update(Marca marcas);

    @Delete
    void delete(Marca marcas);
}
