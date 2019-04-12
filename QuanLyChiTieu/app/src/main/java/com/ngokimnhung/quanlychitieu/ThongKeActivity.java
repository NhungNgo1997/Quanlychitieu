package com.ngokimnhung.quanlychitieu;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public  class ThongKeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    String username="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);



        drawerLayout= findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navthongke);
        View headerView=navigationView.getHeaderView(0);

        TextView textView;
        textView=headerView.findViewById(R.id.user);
        Intent intent=getIntent();

        username = intent.getStringExtra("username");
        textView.setText(intent.getStringExtra("username")+" !");



        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new ThongKe_Fragment()).commit();
        navigationView.setCheckedItem(R.id.thongke);
        navigationView.setNavigationItemSelectedListener(ThongKeActivity.this);






    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.khoanthu:
                QuanLyThu_Fragment t=new QuanLyThu_Fragment();

                t.setArguments(getIntent().getExtras());

              getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,t).commit();
                break;
            case R.id.khoanchi:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new QuanLyChi_Fragment()).commit();
                break;
            case R.id.thongke:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new ThongKe_Fragment()).commit();
                break;
            case R.id.caidat:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new CaiDat_Fragment()).commit();
                break;
            case R.id.gioithieu:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new GioiThieu_Fragment()).commit();
                break;
            case R.id.thoat:
                finish();
                break;

            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new ThongKe_Fragment()).commit();
                break;


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}
