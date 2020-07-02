package com.kelompok5.kantin.activity.map;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.kelompok5.kantin.R;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private FrameLayout frameLayout, circleFrameLayout;
    private ProgressBar progress;
    private TextView textView;
    private int circleRadius;
    private boolean isMoving = false;
    private SupportMapFragment mapFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initViews();
    }

    private void initViews() {
        frameLayout = findViewById(R.id.map_container);

        circleFrameLayout = frameLayout.findViewById(R.id.pin_view_circle);
        textView = circleFrameLayout.findViewById(R.id.textView);
        progress = circleFrameLayout.findViewById(R.id.profile_loader);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void moveMapCamera() {
        if (mGoogleMap == null) {
            return;
        }

        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(-8.594277, 116.113241));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        mGoogleMap.moveCamera(center);
        mGoogleMap.animateCamera(zoom);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;

        mGoogleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                isMoving = true;
                textView.setVisibility(View.GONE);
                progress.setVisibility(View.GONE);
                Drawable mDrawable;
                if (Build.VERSION.SDK_INT >= 21)
                    mDrawable = getApplicationContext().getResources().getDrawable(R.drawable.circle_background_moving, null);
                else
                    mDrawable = getApplicationContext().getResources().getDrawable(R.drawable.circle_background_moving);

                circleFrameLayout.setBackground(mDrawable);
                resizeLayout(false);
            }


        });

        mGoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener(){

            @Override
            public void onCameraIdle() {

                isMoving = false;
                textView.setVisibility(View.INVISIBLE);
                progress.setVisibility(View.VISIBLE);
                resizeLayout(true);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Drawable mDrawable;
                        if (Build.VERSION.SDK_INT >= 21)
                            mDrawable = getApplicationContext().getResources().getDrawable(R.drawable.circle_background, null);
                        else
                            mDrawable = getApplicationContext().getResources().getDrawable(R.drawable.circle_background);

                        if (!isMoving) {
                            circleFrameLayout.setBackground(mDrawable);
                            textView.setVisibility(View.VISIBLE);
                            progress.setVisibility(View.GONE);
                        }
                    }

                }, 1500);
            }

        });

        MapsInitializer.initialize(this);
        moveMapCamera();

    }

    private void resizeLayout(boolean backToNormalSize) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) circleFrameLayout.getLayoutParams();

        ViewTreeObserver vto = circleFrameLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                circleFrameLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                circleRadius = circleFrameLayout.getMeasuredWidth();
            }
        });

        if (backToNormalSize) {
            params.width = WRAP_CONTENT;
            params.height = WRAP_CONTENT;
            params.topMargin = 0;

        } else {
            params.topMargin = (int) (circleRadius * 0.3);
            params.height = circleRadius - circleRadius / 3;
            params.width = circleRadius - circleRadius / 3;
        }

        circleFrameLayout.setLayoutParams(params);
    }

}
