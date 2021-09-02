package com.deka_32864.miusix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private final static String MEDIA_PATH = Environment.getExternalStorageDirectory().getPath()+"/";

    private ArrayList<String> songList = new ArrayList<>();

    private MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("List Lagu");
        Log.e("Media path",MEDIA_PATH);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        else
        {
            getAllAudioFiles();
        }
        openDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, ProfileActivity2.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getAllAudioFiles()
    {

        if (MEDIA_PATH != null)
        {
            File mainFile = new File(MEDIA_PATH);
            File[] fileList = mainFile.listFiles();
            for (File file : fileList)
            {
                Log.e("Media path",file.toString());

                if (file.isDirectory())
                {
                    scanDirectory(file);
                }
                else
                {
                    String path = file.getAbsolutePath();
                    if (path.endsWith(".mp3"))
                    {
                        songList.add(path);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }

        adapter = new MusicAdapter(songList,MainActivity.this);
        recyclerView.setAdapter(adapter);

    }

    public void scanDirectory(File directory)
    {
        if (directory != null)
        {
            File[] fileList = directory.listFiles();
            for (File file : fileList)
            {
                Log.e("Media path",file.toString());

                if (file.isDirectory())
                {
                    scanDirectory(file);
                }
                else
                {
                    String path = file.getAbsolutePath();
                    if (path.endsWith(".mp3"))
                    {
                        songList.add(path);
                    }
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            getAllAudioFiles();
        }
    }

    private void openDialog() {
        DialogBox dialog =  new DialogBox();
        dialog.show(getSupportFragmentManager(), "dialog");
    }
}