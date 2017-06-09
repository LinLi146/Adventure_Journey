package com.example.caoziyu.adventure_journey;

import android.content.Context;
import android.os.Bundle;
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
import android.widget.Toast;

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
    private long exitTime = 0;
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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        initUserData();
        toolbar.setTitle(userData.get(0).getUserName() + "'s Journey");
        LayoutInflater inflater = LayoutInflater.from(this);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View navView = navigationView.getHeaderView(0);
        TextView userName = (TextView) navView.findViewById(R.id.user_name_nav_bar);
        TextView userLevel = (TextView) navView.findViewById(R.id.user_level_nav_bar);
        userName.setText(userData.get(0).getUserName());
        userLevel.setText("Lv." + userData.get(0).getLevel());
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
            if(System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "Press back once more to exit.", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
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
