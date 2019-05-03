package com.repol.jd.example.db.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

import com.repol.jd.example.models.ContactModel;

/**
 * Created on 1/5/2562.
 */
@Dao
public interface ContactDAO {
    @Insert
    public void insert(ContactModel... items);

    @Update
    public void update(ContactModel... items);

    @Delete
    public void delete(ContactModel item);

    @Query("SELECT * FROM contacts ORDER BY name")
    public DataSource.Factory<Integer, ContactModel> getContacts();

    @Query("SELECT * FROM contacts WHERE id = :id")
    public ContactModel getContactById(Long id);
}
