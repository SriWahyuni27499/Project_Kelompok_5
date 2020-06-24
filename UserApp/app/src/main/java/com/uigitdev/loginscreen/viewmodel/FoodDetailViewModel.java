package com.uigitdev.loginscreen.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.uigitdev.loginscreen.dbutilities.AppDatabase;
import com.uigitdev.loginscreen.model.CartItem;
import com.uigitdev.loginscreen.model.FoodDetails;
import com.uigitdev.loginscreen.services.repository.FoodRepository;

import java.util.List;

public class FoodDetailViewModel extends AndroidViewModel {

    private AppDatabase db;
    private LiveData<List<CartItem>> cartItemsLiveData;
    private LiveData<FoodDetails> foodDetailsLiveData;

    public FoodDetailViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        db = AppDatabase.getDatabase(getApplication().getApplicationContext());
        subscribeToCartChanges();
    }

    private void subscribeToCartChanges() {
        cartItemsLiveData = db.cartItemDao().getCartItems();
    }

    public void subscribeForFoodDetails(String name){
        foodDetailsLiveData = db.foodDetailsDao().getFood(name);
    }

    public LiveData<FoodDetails> getFoodDetailsLiveData(){
        return foodDetailsLiveData;
    }

    public LiveData<List<CartItem>> getCartItemsLiveData() {
        return cartItemsLiveData;
    }

    public void updateCart(FoodDetails foodDetails){
        FoodRepository.getInstance().updateCart(db,foodDetails);
        db.foodDetailsDao().save(foodDetails);
    }
}
