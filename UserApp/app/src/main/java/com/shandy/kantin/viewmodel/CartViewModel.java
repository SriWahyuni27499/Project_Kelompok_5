package com.shandy.kantin.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shandy.kantin.dbutilities.AppDatabase;
import com.shandy.kantin.model.CartItem;
import com.shandy.kantin.utility.ObservableObject;

import java.util.List;

import static com.shandy.kantin.ui.MenuActivity.INTENT_UPDATE_LIST;

public class CartViewModel extends AndroidViewModel {

    private AppDatabase db;
    private Double totalCost=0.0,discount=0.0,deliveryCost=0.0;
    private MutableLiveData<Double> grandTotal = new MutableLiveData<>();
    private MediatorLiveData<List<CartItem>> mediatorLiveData = new MediatorLiveData<>();
    private String couponApplied="";
    private MutableLiveData<String> errorString = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        db = AppDatabase.getDatabase(getApplication().getApplicationContext());
        subscribeToCartChanges();
    }

    private void subscribeToCartChanges() {
        LiveData<List<CartItem>> cartItemsLiveData = db.cartItemDao().getCartItems();
        mediatorLiveData.addSource(cartItemsLiveData, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItems) {
                mediatorLiveData.setValue(cartItems);
                calculateGrandTotalCost();
            }
        });
    }

    private void calculateGrandTotalCost() {
        List<CartItem> cartItemList = mediatorLiveData.getValue();
        totalCost = 0.0;
        if(cartItemList!=null) {
            for (CartItem cartItem : cartItemList) {
                totalCost = totalCost+(cartItem.getPrice()*cartItem.getQuantity());
            }

            grandTotal.setValue(totalCost + deliveryCost);
        }
    }


    public Double getDeliveryCost(){
        return deliveryCost;
    }

    public Double getTotalCost(){
        return totalCost;
    }

    public MutableLiveData<Double> getGrandTotal(){
        return grandTotal;
    }


    public MediatorLiveData<List<CartItem>> getCartItemsLiveData() {
        return mediatorLiveData;
    }

    public void removeItem(String name){
        db.cartItemDao().deleteCartItem(name);
        ObservableObject.getInstance().updateValue(new Intent(INTENT_UPDATE_LIST));
    }

    public MutableLiveData<String> getErrorString(){
        return errorString;
    }
}
