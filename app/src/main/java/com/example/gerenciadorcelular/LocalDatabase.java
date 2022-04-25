package com.example.gerenciadorcelular;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Marca.class, Celular.class}, version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    private static LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "ControleCelulares").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract CelularDao celularModel();

    public abstract MarcaDao marcaModel();
}
