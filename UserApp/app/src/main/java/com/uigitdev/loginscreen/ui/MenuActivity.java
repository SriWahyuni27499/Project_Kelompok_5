package com.uigitdev.loginscreen.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uigitdev.loginscreen.R;
import com.uigitdev.loginscreen.model.CartItem;
import com.uigitdev.loginscreen.model.FoodDetails;
import com.uigitdev.loginscreen.ui.adapter.FoodListAdapter;
import com.uigitdev.loginscreen.utility.ObservableObject;
import com.uigitdev.loginscreen.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class MenuActivity extends AppCompatActivity implements java.util.Observer, PopupMenu.OnMenuItemClickListener {

    FoodViewModel foodViewModel;
    Observer<List<FoodDetails>> foodMenuObserver;
    Observer<List<CartItem>> cartObserver;
    Observer<Boolean> isFoodUpdateInProgressObserver;
    RecyclerView foodList;
    FoodListAdapter foodListAdapter;
    AppCompatButton bCart;
    LayoutAnimationController controller;
    ImageView infoImage;
    TextView tInfo,tTotalCost,tCartQuantity;
    Toolbar cartView;
    public static final String INTENT_UPDATE_FOOD = "UPDATE_FOOD";
    public static final String INTENT_UPDATE_LIST = "UPDATE_LIST";
    public static final String ACTION_SORT_BY_PRICE = "SORT_PRICE";
    public static final String ACTION_SORT_BY_RATING = "SORT_RATING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Base);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        foodViewModel = new ViewModelProvider (this).get(FoodViewModel.class);
        foodList = findViewById(R.id.food_list);
        tInfo = findViewById(R.id.t_loading);
        infoImage = findViewById(R.id.i_loading);
        cartView = findViewById(R.id.cart_view);
        tTotalCost = cartView.findViewById(R.id.t_total_price);
        tCartQuantity = cartView.findViewById(R.id.t_cart_count);
        bCart = cartView.findViewById(R.id.b_cart);
        bCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, CartActivity.class));
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        foodList.setLayoutManager(mLayoutManager);
        foodListAdapter = new FoodListAdapter(new ArrayList<FoodDetails>());
        controller = AnimationUtils.loadLayoutAnimation(foodList.getContext(), R.anim.layout_slide_from_bottom);
        foodList.setAdapter(foodListAdapter);
        foodList.scheduleLayoutAnimation();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        foodMenuObserver = new Observer<List<FoodDetails>>() {
            @Override
            public void onChanged(@Nullable List<FoodDetails> foodDetails) {
                if(foodDetails!=null){
                    foodListAdapter.setData(foodDetails);
                    foodListAdapter.notifyDataSetChanged();
                    runLayoutAnimation(foodList);
                }else{
                    Log.e("Food details","null");
                }
            }
        };
        isFoodUpdateInProgressObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean!=null && !aBoolean){
                    showProgress(false,true);
                    subscribeToFoodObserver();
                }else{
                    showProgress(true,false);
                }
            }
        };
        cartObserver = new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItems) {
                updateCartUI(cartItems);
            }
        };
        foodViewModel.isFoodUpdateInProgress().observe(this,isFoodUpdateInProgressObserver);
        ObservableObject.getInstance().addObserver(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_sort){
            showPopup(findViewById(R.id.action_sort));
        }
        return super.onOptionsItemSelected(item);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.actions, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.action_sort_price){
            foodViewModel.sortFood(ACTION_SORT_BY_PRICE);
        }else if(menuItem.getItemId() == R.id.action_sort_rating){
            foodViewModel.sortFood(ACTION_SORT_BY_RATING);
        }
        return false;
    }

    private void updateCartUI(List<CartItem> cartItems) {
        if(cartItems!=null && cartItems.size()>0){
            cartView.setVisibility(View.VISIBLE);
            Double cost = 0.0;
            int quantity = 0;
            for(CartItem cartItem:cartItems){
                cost = cost+(cartItem.getPrice()*cartItem.getQuantity());
                quantity = quantity+cartItem.getQuantity();
            }
            tCartQuantity.setText(String.valueOf(quantity));
            tTotalCost.setText(getString(R.string.rupiah_simbol)+ String.valueOf(cost));
        }else{
            cartView.setVisibility(View.GONE);
            tCartQuantity.setText("0");
            tTotalCost.setText(getString(R.string.rupiah_simbol)+"0");
        }
    }

    private void subscribeToFoodObserver() {
        if(!foodViewModel.getFoodDetailsMutableLiveData().hasObservers()) {
            foodViewModel.getFoodDetailsMutableLiveData().observe(MenuActivity.this, foodMenuObserver);
        }
        if(!foodViewModel.getCartItemsLiveData().hasObservers()){
            foodViewModel.getCartItemsLiveData().observe(this,cartObserver);
        }
    }

    private void showProgress(boolean show, boolean showList) {
        foodList.setVisibility(showList? View.VISIBLE: View.GONE);
        tInfo.setVisibility(show? View.VISIBLE: View.GONE);
        infoImage.setVisibility(show? View.VISIBLE: View.GONE);
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        if(recyclerView.getAdapter()!=null) {
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();
        }
    }

    @Override
    protected void onDestroy() {
        foodViewModel.getFoodDetailsMutableLiveData().removeObserver(foodMenuObserver);
        foodViewModel.isFoodUpdateInProgress().removeObserver(isFoodUpdateInProgressObserver);
        foodViewModel.getCartItemsLiveData().removeObserver(cartObserver);
        ObservableObject.getInstance().deleteObserver(this);
        Glide.get(this).clearMemory();
        super.onDestroy();
    }

    @Override
    public void update(Observable observable, Object o) {
        Intent intent = (Intent)o;
        if(intent!=null && intent.getAction() != null) {
            if (intent.getAction().equals(INTENT_UPDATE_FOOD)) {
                foodViewModel.updateCart(foodListAdapter.getItem(intent.getIntExtra("position",-1)));
            }else  if(intent.getAction().equals(INTENT_UPDATE_LIST)){
                foodListAdapter.notifyDataSetChanged();
            }
        }
    }
}
