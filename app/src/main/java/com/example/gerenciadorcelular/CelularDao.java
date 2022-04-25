package com.example.gerenciadorcelular;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CelularDao {

    @Query("SELECT * FROM Celular WHERE celularID = :id LIMIT 1")
    Celular get(int id);

    @Query("SELECT * FROM Celular")
    List<Celular> getAll();

    @Query("SELECT * FROM Celular WHERE celularID IN (:celularId)")
    List<Celular> loadAllByIds(int[] celularId);

    @Query("SELECT * FROM Celular WHERE modelo LIKE :name LIMIT 1")
    Celular findByName(String name);

    @Query("UPDATE Celular SET modelo =:model WHERE celularID == :celularId")
    void updateName(String model, int celularId);

    @Insert
    void insertAll(Celular... celular);

    @Delete
    void delete(Celular celular);
}
