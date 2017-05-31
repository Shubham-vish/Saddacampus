/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity{

    Toolbar toolbar, searchtollbar;
    Menu search_menu;
    MenuItem item_search;
    int count =0;

    public static final int NUM_LIST_ITEMS = 100;
    private GreenAdapter mAdapter;
    private RecyclerView mNumberList;

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        final View notificaitons = menu.findItem(R.id.actionNotifications).getActionView();

        TextView txtViewCount = (TextView) notificaitons.findViewById(R.id.txtCount);
        updateHotCount(count++,notificaitons);
        txtViewCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHotCount(count++,v);
            }
        });
        notificaitons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    TODO
            }
        });





        return super.onCreateOptionsMenu(menu);
    }
    public void updateHotCount(final int new_hot_number,View view) {
        TextView txtViewCount = (TextView) view.findViewById(R.id.txtCount);
        int c = new_hot_number;
        if (c < 0) return;
        else if(c==0){
            txtViewCount.setVisibility(View.VISIBLE);
            txtViewCount.setText(Integer.toString(c));
        }
        else {
            txtViewCount.setVisibility(View.VISIBLE);
            txtViewCount.setText(Integer.toString(c));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_status:
                Toast.makeText(this, "Home Status Click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    circleReveal(R.id.searchtoolbar,1,true,true);
                else
                    searchtollbar.setVisibility(View.VISIBLE);

                item_search.expandActionView();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Home Settings Click ", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSearchtollbar()
    {
        searchtollbar = (Toolbar) findViewById(R.id.searchtoolbar);
        if (searchtollbar != null) {
            searchtollbar.inflateMenu(R.menu.menu_search);
            search_menu=searchtollbar.getMenu();

            searchtollbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    else
                        searchtollbar.setVisibility(View.GONE);
                }
            });

            item_search = search_menu.findItem(R.id.action_filter_search);

            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    // Do something when collapsed
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.searchtoolbar,1,true,false);
                    }
                    else
                        searchtollbar.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {
                    // Do something when expanded
                    return true;
                }
            });

            initSearchView();


        } else
            Log.d("toolbar", "setSearchtollbar: NULL");
    }

    public void initSearchView()
    {
        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();

        // Enable/Disable Submit button in the keyboard

        searchView.setSubmitButtonEnabled(false);

        // Change search close button image

        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_action_search);


        // set hint and the text colors

        EditText txtSearch = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint("Search..");
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.primary_color));


        // set the cursor

        AutoCompleteTextView searchTextView = (AutoCompleteTextView) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.ic_action_search); //This sets the cursor resource ID to 0 or @null which will make it visible on white background
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            public void callSearch(String query) {
                //Do searching
                Log.i("query", "" + query);

            }

        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow)
    {
        final View myView = findViewById(viewID);

        int width=myView.getWidth();

        if(posFromRight>0)
            width-=(posFromRight*getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material))-(getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)/ 2);
        if(containsOverflow)
            width-=getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx=width;
        int cy=myView.getHeight()/2;

        Animator anim;
        if(isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0,(float)width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float)width, 0);

        anim.setDuration((long)220);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if(!isShow)
                {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if(isShow)
            myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSearchtollbar();



        ////Horizontal Scroller
        mNumberList = (RecyclerView)findViewById(R.id.rv_numbers);
        LinearLayoutManager la = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        mNumberList.setLayoutManager(la);
        mNumberList.setHasFixedSize(true);
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
        mNumberList.setAdapter(mAdapter);
        //////Horizontal Scroller
        ViewPager viewp = (ViewPager) findViewById(R.id.view_pager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewp.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewp);



    }

}

