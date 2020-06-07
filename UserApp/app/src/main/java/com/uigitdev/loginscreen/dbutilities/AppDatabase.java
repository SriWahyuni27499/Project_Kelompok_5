package com.uigitdev.loginscreen.dbutilities;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.uigitdev.loginscreen.model.CartItem;
import com.uigitdev.loginscreen.model.FoodDetails;


/**
 * Created by Marty on 12/29/2017.
 */
@Database(entities = {FoodDetails.class, CartItem.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract FoodDetailsDao foodDetailsDao();
    public abstract CartItemDao cartItemDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"yummy").fallbackToDestructiveMigration().allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
