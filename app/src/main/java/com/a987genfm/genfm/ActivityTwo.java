package com.a987genfm.genfm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        getSupportActionBar().hide();

        //bottom
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_arrow_back:
                      //  finish();
//                        Intent intent0 = new Intent(ActivityTwo.this, MainActivity.class);
//                        intent0.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent0);
                        break;



                    case R.id.ic_books:

                        break;

                    case R.id.ic_center_focus:
                       // finish();
                        Intent intent3 = new Intent(ActivityTwo.this, ActivityThree.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:
                        //finish();
                        Intent intent4 = new Intent(ActivityTwo.this, ActivityFour.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
        //agar come back in main
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }




}
