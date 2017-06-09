package com.example.caoziyu.adventure_journey;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.caoziyu.adventure_journey.db.MissionLab;
import com.example.caoziyu.adventure_journey.db.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private FragmentTransaction myFragmentTransaction;
    private Fragment homeFragment, missionModeFragment;
    private Toolbar toolbar;
    private Context mcontext=this;
    private List<UserData> userData=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        initUserData();
        toolbar.setTitle(userData.get(0).getUserName() + "'s Journey");
//        toolbar.setBackground(getDrawable(android.R.color.transparent));
        LayoutInflater inflater = LayoutInflater.from(this);
        View navView = inflater.inflate(R.layout.nav_header_main, null);
        TextView userName = (TextView) navView.findViewById(R.id.user_name_nav_bar);
        TextView userLevel = (TextView) navView.findViewById(R.id.user_level_nav_bar);
        userName.setText(userData.get(0).getUserName());
        userLevel.setText("Lv." + userData.get(0).getLevel());
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        myFragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (homeFragment == null){
            homeFragment = new HomeFragment();
            myFragmentTransaction.add(R.id.main_content,homeFragment);
            myFragmentTransaction.commit();
        }
        else
        {
            hideFragment(myFragmentTransaction);
            myFragmentTransaction.show(homeFragment);
            myFragmentTransaction.commit();
        }




    }

    public void initUserData(){//初始化用户信息
        Log.d("initUserData","Start Initiation");
        MissionLab missionLab=MissionLab.get(mcontext);
        if(missionLab.getUserdatas().size()!=0){}
        else {
            UserData ud=new UserData();
            missionLab.addUserdata(ud);
        }
        userData=missionLab.getUserdatas();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        myFragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(myFragmentTransaction);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            if (homeFragment == null)
            {
                homeFragment = new HomeFragment();
                myFragmentTransaction.add(R.id.main_content, homeFragment);
            }
            else
            {
                myFragmentTransaction.show(homeFragment);
            }
//            toolbar.setBackground(getDrawable(android.R.color.transparent));
        }
        else if (id == R.id.nav_missions)
        {
            if (missionModeFragment == null)
            {
                missionModeFragment = new MissionModeFragment();
                myFragmentTransaction.add(R.id.main_content, missionModeFragment);
            }
            else
            {
                myFragmentTransaction.show(missionModeFragment);
            }
//            toolbar.setBackground(getDrawable(R.color.colorPrimary));
        } else if (id == R.id.nav_logs)
        {

        } else if (id == R.id.nav_exit)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        myFragmentTransaction.commit();
        return true;
    }

    private void hideFragment(FragmentTransaction myFragmentTransaction)
    {
        if (homeFragment != null){
            myFragmentTransaction.hide(homeFragment);
        }
        if (missionModeFragment != null){
            myFragmentTransaction.hide(missionModeFragment);
        }
    }
}
