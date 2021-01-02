package com.dark_phoenix09.khabarilal.bookmarkDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface bookmarkDAO {

    @Insert
    public void InsertBookmark(model model);

    @Query("DELETE FROM bookmark WHERE u IS :u")
    public void deleteBookmark(String u);

    @Query("SELECT * FROM bookmark")
    public List<model> getBookmark();

}
