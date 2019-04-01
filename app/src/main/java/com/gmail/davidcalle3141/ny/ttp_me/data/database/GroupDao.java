package com.gmail.davidcalle3141.ny.ttp_me.data.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface GroupDao {
    @Query("SELECT * FROM `Group`")
    LiveData<List<Group>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Group... groups);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Group group);

    @Delete
    void delete(Group group);
}
