package com.kelique.rcapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.btnTambahpsrt)
    Button btnTambahpsrt;
    @InjectView(R.id.btnEcash)
    Button btnEcash;
    @InjectView(R.id.btnLiatpserta)
    Button btnLiatpserta;
    @InjectView(R.id.btnBukatbank)
    Button btnBukatbank;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Di isi apa ya?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mAuth.getInstance().signOut();
            finish();
//            Toast.makeText(this, "Anda Sudah Keluar", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_foto_settings) {
            startActivity(new Intent(MainActivity.this, UploadFoto.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Tbhanggota) {
            LhtanggotaFragment fragmentatu = new LhtanggotaFragment();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.drawer_layout, fragmentatu).commit();
            // Handle the camera action
        } else if (id == R.id.nav_Lhtanggota) {

        } else if (id == R.id.nav_eCash) {

        } else if (id == R.id.nav_Tbank) {

        } else if (id == R.id.nav_telPust) {

        } else if (id == R.id.nav_Pesan) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick({R.id.btnTambahpsrt, R.id.btnEcash, R.id.btnLiatpserta, R.id.btnBukatbank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnTambahpsrt:
                startActivity(new Intent(MainActivity.this, TambahAktifity.class));
                break;
            case R.id.btnEcash:
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.ptdam.emoney");
                if (intent != null) {
                    // We found the activity now start the activity
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    // Bring user to the market or let them choose an app?
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + "com.ptdam.emoney"));
                    startActivity(intent);
                }
                break;
            case R.id.btnLiatpserta:
                startActivity(new Intent(MainActivity.this, TransaksiActivity.class));
                break;
            case R.id.btnBukatbank:
                Intent brintent = getPackageManager().getLaunchIntentForPackage("bri.delivery.brimobile");
                if (brintent != null) {
                    // We found the activity now start the activity
                    brintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(brintent);
                } else {
                    // Bring user to the market or let them choose an app?
                    brintent = new Intent(Intent.ACTION_VIEW);
                    brintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    brintent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + "bri.delivery.brimobile"));
                    startActivity(brintent);
                }
                break;
        }
    }
}
