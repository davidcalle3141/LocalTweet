package com.gmail.davidcalle3141.ny.ttp_me.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

public class BottomNavBehaviour extends CoordinatorLayout.Behavior<BottomNavigationView> {

    public BottomNavBehaviour(){
        super();
    }

    public BottomNavBehaviour(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull BottomNavigationView child, @NonNull View dependency) {
        return dependency instanceof FrameLayout;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if(dy < 0){
            showBottomNavView(child);
        }else if (dy>0){
            hideBottomNavView(child);
        }
    }

    private void hideBottomNavView(BottomNavigationView child) {
        child.animate().translationY(child.getHeight());
    }

    private void showBottomNavView(BottomNavigationView child) {
        child.animate().translationY(0);
    }
}
