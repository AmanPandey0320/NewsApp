package com.dark_phoenix09.khabarilal.bookmarkDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {model.class},version = 1)
public  abstract class Mydatabase extends RoomDatabase {

    public abstract bookmarkDAO bookmarkDAO();

}
