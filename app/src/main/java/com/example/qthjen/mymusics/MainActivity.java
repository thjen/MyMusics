package com.example.qthjen.mymusics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Song> arraySong;
    ListView lv;
    CustomList customList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);

        arraySong = new ArrayList<Song>();

        arraySong.add(new Song("Anh đợi em nha", R.raw.anhdoiemnha));
        arraySong.add(new Song("Anh mơ", R.raw.anhmo));
        arraySong.add(new Song("Bức tranh từ nước mắt", R.raw.buctranhtunuocmat));
        arraySong.add(new Song("Lời anh chưa thể nói", R.raw.loianhchuathenoi));
        arraySong.add(new Song("Mò kim đáy bể", R.raw.mokimdaybe));
        arraySong.add(new Song("Gương mặt lạ lẫm", R.raw.guongmatlalam));
        arraySong.add(new Song("Tự lau nước mắt", R.raw.tulaunuocmat));
        arraySong.add(new Song("Tiếng sét trong anh", R.raw.tiengsettronganh));

        customList = new CustomList(MainActivity.this, R.layout.custom_listview, arraySong);
        lv.setAdapter(customList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, Body.class);
                intent.putExtra("song", arraySong.get(i).getmFile());
                intent.putExtra("title", arraySong.get(i).getmTitle());
                startActivity(intent);

            }
        });

    }


}
