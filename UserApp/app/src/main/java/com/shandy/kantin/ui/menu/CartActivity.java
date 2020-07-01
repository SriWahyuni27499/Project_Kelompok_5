package com.shandy.kantin.ui.menu;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.shandy.kantin.R;
import com.shandy.kantin.model.CartItem;
import com.shandy.kantin.ui.adapter.CartListAdapter;
import com.shandy.kantin.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartList;
    TextView tDiscount,hDiscount,tItemsCost,tDelivery,hDelivery,tGrandTotal;
    TextInputLayout eCouponLayout;
    AppCompatButton bApply;
    AppCompatImageView iRemoveCoupon;
    CartViewModel cartViewModel;
    Observer<List<CartItem>> cartObserver;
    Observer<Double> costObserver;
    Observer<String> errorObserver;
    CartListAdapter cartListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setTitle(R.string.your_cart);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        cartList = findViewById(R.id.cart_list);
        tItemsCost = findViewById(R.id.t_total);
        tDelivery = findViewById(R.id.t_delivery);
        hDelivery = findViewById(R.id.h_delivery);
        tGrandTotal = findViewById(R.id.t_grand_total);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cartList.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        cartObserver = new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItems) {
                cartListAdapter.setData(cartItems);
                cartListAdapter.notifyDataSetChanged();
                if(cartItems!=null && cartItems.size()==0){
                    finish();
                }
            }
        };
        costObserver = new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double aDouble) {
                updateUI(aDouble);
            }
        };
        errorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                if(error!=null && error.isEmpty()){
                    eCouponLayout.setError(null);
                    eCouponLayout.setErrorEnabled(false);
                }else{
                    eCouponLayout.setError(error);
                    eCouponLayout.setErrorEnabled(true);
                }
            }
        };
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        cartListAdapter = new CartListAdapter(new ArrayList<CartItem>(),cartViewModel);
        cartList.setAdapter(cartListAdapter);
        cartViewModel.getCartItemsLiveData().observe(this,cartObserver);
        cartViewModel.getGrandTotal().observe(this,costObserver);
        cartViewModel.getErrorString().observe(this,errorObserver);
    }

    private void updateUI(Double grandTotal) {
        tItemsCost.setText(getString(R.string.rupiah_simbol)+" "+cartViewModel.getTotalCost());
        tGrandTotal.setText(getString(R.string.rupiah_simbol)+" "+ String.valueOf(grandTotal));
        if(cartViewModel.getDeliveryCost()>0){
            hDelivery.setText(getString(R.string.delivery_charges));
            tDelivery.setText(" + "+getString(R.string.rupiah_simbol)+" "+ String.valueOf(cartViewModel.getDeliveryCost()));
            tDelivery.setPaintFlags(0);
        }else{
            hDelivery.setText(getString(R.string.delivery_charges)+" ( Free )");
            tDelivery.setText(" + "+getString(R.string.rupiah_simbol)+" 3000");
            tDelivery.setPaintFlags(tDelivery.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    @Override
    protected void onDestroy() {
        cartViewModel.getCartItemsLiveData().removeObserver(cartObserver);
        cartViewModel.getGrandTotal().removeObserver(costObserver);
        cartViewModel.getErrorString().removeObserver(errorObserver);
        super.onDestroy();
    }
}
