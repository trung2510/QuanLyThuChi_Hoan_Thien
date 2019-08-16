package com.example.quanlythuchi;


import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.example.quanlythuchi.adapter.KhoanThuAdapter;
import com.example.quanlythuchi.model.Note;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    protected RecyclerView ryView;
    protected List<Note> noteList;
    protected KhoanThuAdapter adapter;






    //KhoanThu(Note)
    //KhoanChi(Note2)
    //LoaiThu(Note3)
    //LoaiChi(Note4)








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ryView = findViewById(R.id.rvThuChi);
        noteList = new ArrayList<>();
        adapter = new KhoanThuAdapter(MainActivity.this, noteList);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //thêm hình button cho actionbar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_24dp);

        drawer = findViewById(R.id.drawer_layout);

        //đặt màn hình mặc định là thống kê
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new ThongKeFragment()).commit();

        }


        //xét sự kiện khi ấn vào item menu
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        //phát sáng item khi chọn
                        //menuItem.setChecked(true);


                        //bắt sự kiện chọn 1 item
                        switch (menuItem.getItemId()) {
                            case R.id.nav_khoanthu:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new KhoanThuFragment()).commit();
                                break;
                            case R.id.nav_khoanchi:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new KhoanChiFragment()).commit();
                                break;
                            case R.id.nav_thongke:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new ThongKeFragment()).commit();
                                break;
                            case R.id.nav_gioithieu:
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new GioiThieuFragment()).commit();
                                break;
                            case R.id.nav_thoat:
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                LayoutInflater inflater = getLayoutInflater();
                                builder.setView(inflater.inflate(R.layout.alert_exit,null));
                                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        onBackPressed();
                                    }
                                });
                                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.show();
                                break;
                        }

                        //đóng navigation khi chọn 1 item
                        drawer.closeDrawers();

                        return true;
                    }
                }
        );


    }


    //xét sự kiện ấn vào hình button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
