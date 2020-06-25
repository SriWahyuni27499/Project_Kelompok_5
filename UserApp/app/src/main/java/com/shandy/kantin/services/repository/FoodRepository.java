package com.shandy.kantin.services.repository;

import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.shandy.kantin.dbutilities.AppDatabase;
import com.shandy.kantin.model.FoodDetails;
import com.shandy.kantin.services.APIClient;
import com.shandy.kantin.services.APIServices;
import com.shandy.kantin.worker.SaveFoodMenu;
import com.shandy.kantin.worker.UpdateCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRepository {

    private static FoodRepository instance;
    private static final String TAG = "FoodRepository";

    private APIServices APIServices = APIClient.getClient().create(APIServices.class);

    public MutableLiveData<Boolean> getFoodMenu(final Context context){

        final MutableLiveData<Boolean> isFoodCallOngoing = new MutableLiveData<>();
        isFoodCallOngoing.setValue(true);

        APIServices.getFoodData().enqueue(new Callback<List<FoodDetails>>() {
            @Override
            public void onResponse(Call<List<FoodDetails>> call, Response<List<FoodDetails>> response) {
                if(response.isSuccessful()) {
                    new SaveFoodMenu(AppDatabase.getDatabase(context), response.body()).execute();
                    isFoodCallOngoing.setValue(false);
                }else{
                    Log.e(TAG,"response not successful");
                }
            }

            @Override
            public void onFailure(Call<List<FoodDetails>> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
        return isFoodCallOngoing;
    }

    public static FoodRepository getInstance() {
        if(instance == null){
            synchronized (FoodRepository.class){
                if(instance == null){
                    instance = new FoodRepository();
                }
            }
        }
        return instance;
    }

    public void updateCart(final AppDatabase db, FoodDetails foodDetails) {
        new UpdateCart(db).execute(foodDetails);
    }
}
