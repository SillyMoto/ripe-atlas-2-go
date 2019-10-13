/*
 * Copyright (C) 2019 SillyMoto authors
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
package de.beuth.master.ripeatlas2go;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import de.beuth.master.services.ArrayListAdapter;

/**
 * <h1>Main Activity!</h1>
 * <p>
 * This activity is the start page of the app.
 * It implements a NavigationView to display a navigation menu.
 * The important menu items are shown as a card view in the main content.
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String MSMS = "measurements";
    final String API_KEYS = "apiKeys";
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View viewMsm = findViewById(R.id.card_view_msm);
        viewMsm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MeasurementActivity.class);
                startActivity(i);
            }
        });
        View viewCdt = findViewById(R.id.card_view_credits);
        viewCdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreditActivity.class);
                startActivity(i);
            }
        });
        View viewApk = findViewById(R.id.card_view_api_keys);
        viewApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ApiKeysActivity.class);
                startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * This Menu implements two options to delete your added data.
     * You can delete your added measurements.
     * You can delete your added api keys.
     *
     * @param item id of the clicked item
     * @return true if delete data is successful
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete_msm) {
            ArrayListAdapter.saveMsmArrayList(null, MSMS, this);
            return true;
        }

        if (id == R.id.action_delete_keys) {
            ArrayListAdapter.saveApiKeyArrayList(null, API_KEYS, this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        this.item = item;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_measurements){
            Intent i = new Intent(MainActivity.this, MeasurementActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_credits) {
            Intent i = new Intent(MainActivity.this, CreditActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_api_keys) {
            Intent i = new Intent(MainActivity.this, ApiKeysActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String shareText = "Checkout the Android App for RIPE Atlas -- ";
            String shareURL = "https://github.com/SillyMoto/potential-octo-spoon";
            i.putExtra(Intent.EXTRA_TITLE, "RIPE Atlas 2 Go");
            i.putExtra(Intent.EXTRA_TEXT, shareText + shareURL);
            startActivity(Intent.createChooser(i, "Share Android App"));
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
