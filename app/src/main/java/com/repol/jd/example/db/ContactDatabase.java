package com.repol.jd.example.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.repol.jd.example.db.dao.ContactDAO;
import com.repol.jd.example.models.ContactModel;

/**
 * Created on 1/5/2562.
 */
@Database(entities = {ContactModel.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();

    private static ContactDatabase INSTANCE;

    public static ContactDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ContactDatabase.class, "contactdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
