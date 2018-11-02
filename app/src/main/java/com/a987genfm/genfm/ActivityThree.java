package com.a987genfm.genfm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ActivityThree extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonWeb;
    private FloatingActionButton floatingActionButtonFacebook;
    private FloatingActionButton floatingActionButtonTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        getSupportActionBar().hide();

        //bottom
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_arrow_back:
                       // finish();
//                        Intent intent0 = new Intent(ActivityThree.this, MainActivity.class);
//                        intent0.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivity(intent0);
                        break;

                    case R.id.ic_books:
                       // finish();
                        Intent intent2 = new Intent(ActivityThree.this, ActivityTwo.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:

                        break;

                    case R.id.ic_backup:
                        //finish();
                        Intent intent4 = new Intent(ActivityThree.this, ActivityFour.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
        //agar come back in main
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        //floating button
        floatingActionButtonWeb = (FloatingActionButton) findViewById(R.id.floatingActionButtonWeb);
        floatingActionButtonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityThree.this, WebsiteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });

        floatingActionButtonFacebook = (FloatingActionButton) findViewById(R.id.floatingActionButtonFB);
        floatingActionButtonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityThree.this, FacebookActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });

        floatingActionButtonTwitter = (FloatingActionButton) findViewById(R.id.floatingActionButtonTW);
        floatingActionButtonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityThree.this, TwiterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });


    }
}
