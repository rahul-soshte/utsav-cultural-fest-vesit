package com.hunterlab.hunter.utsav18;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.hunterlab.hunter.utsav18.AboutUs.AboutUs_main;

import com.hunterlab.hunter.utsav18.Events_Utsav.Events_main_Utsav;
import com.hunterlab.hunter.utsav18.FbNews.Fb_main;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Events_main_Utsav.OnFragmentInteractionListener {
    BottomBar bottomBar;
    Fragment fragment=null;
    static boolean tab=false;
    static String cat="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("News | Utsav '18");

        bottomBar = (BottomBar)findViewById(R.id.bottombar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){

                    case R.id.tab_events:
                        Events_main_Utsav events_main = new Events_main_Utsav();
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, events_main).commit();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.rgb(27,94,32));
                            window.setNavigationBarColor(Color.rgb(27,94,32));
                        }

                        toolbar.setBackground(new ColorDrawable(Color.rgb(76,175,80)));
                        toolbar.setTitle("Events | UTSAV '18");
                        break;

                    case R.id.tab_news:
                        Fb_main fb_main = new Fb_main();
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, fb_main).commit();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.rgb(26,35,126));
                            window.setNavigationBarColor(Color.rgb(26,35,126));
                        }

                        toolbar.setBackground(new ColorDrawable(Color.rgb(63,81,181)));
                        toolbar.setTitle("News | UTSAV '18");
                        break;
                    case R.id.tab_about:
                        AboutUs_main aboutUs_main = new AboutUs_main();
                        getFragmentManager().beginTransaction().replace(R.id.main_fragment, aboutUs_main).commit();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.rgb(69,90,100));
                            window.setNavigationBarColor(Color.rgb(69,90,100));
                        }

                        toolbar.setBackground(new ColorDrawable(Color.rgb(96,125,139)));
                        toolbar.setTitle("About Us | UTSAV '18");
                        break;


                }
            }
        });
        if(!tab) {
            bottomBar.selectTabWithId(R.id.tab_news);
        }else{
            bottomBar.selectTabWithId(R.id.tab_events);
        }
    }
    /**
     * Creates a menu in the Action Bar from the menu file
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu;this adds item to the action bar if present
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * switch statement for different code executions for different menu items.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.developer:

                Intent intent=new Intent(MainActivity.this,DevelopersActivity.class);
                startActivity(intent);
                return true;

            default:return super.onOptionsItemSelected(item);
        }

    }
    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}
