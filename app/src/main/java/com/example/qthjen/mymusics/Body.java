package com.example.qthjen.mymusics;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Body extends AppCompatActivity {

    private ImageView ivStop, ivPrev, ivNext, ivBack, ivConcert;
    private TextView tvTitle, tvTime1, tvTime2;
    private SeekBar sb;
    private int position = 0;
    private MediaPlayer mediaPlayer;
    private MainActivity mainActivity;
    private Intent intent;
    private Animation animation;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        FindView();

        intent = getIntent();
        tvTitle.setText(intent.getStringExtra("title"));

        animation = AnimationUtils.loadAnimation(Body.this, R.anim.anim_imageview);

        CreateMedia();

        if ( mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            CreateMedia();
        } else {
            mediaPlayer.start();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Body.this, MainActivity.class));
                mediaPlayer.stop();
                mediaPlayer.release();
                CreateMedia();
            }
        });

        ivStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( mediaPlayer.isPlaying()) {

                    mediaPlayer.pause();
                    ivStop.setImageResource(R.drawable.startn);
                    animation = AnimationUtils.loadAnimation(Body.this, R.anim.anim_null);
                    ivConcert.setAnimation(animation);
                    animation = AnimationUtils.loadAnimation(Body.this, R.anim.anim_imageview);

                } else {
                    ivConcert.setAnimation(animation);
                    mediaPlayer.start();
                    ivStop.setImageResource(R.drawable.stopn);
                }

            }
        });

        mainActivity = new MainActivity();

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(Body.this, mainActivity.arraySong.get(position).getmFile());
                tvTitle.setText(mainActivity.arraySong.get(position).getmTitle());
                position++;

                if ( position > mainActivity.arraySong.size() - 1) {
                    position = 0;
                }
                setTimeTotal();
                setTimeTwo();
                ivConcert.setAnimation(animation);
                mediaPlayer.start();

            }
        });

        ivPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(Body.this, mainActivity.arraySong.get(position).getmFile());
                tvTitle.setText(mainActivity.arraySong.get(position).getmTitle());
                position--;

                if ( position < 0) {
                    position = mainActivity.arraySong.size() - 1;
                }
                setTimeTotal();
                setTimeTwo();
                ivConcert.setAnimation(animation);
                mediaPlayer.start();

            }
        });

        setTimeTotal();
        setTimeTwo();
        ivConcert.setAnimation(animation);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sb.getProgress());
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTimeTotal() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTime2.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sb.setMax(mediaPlayer.getDuration());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTimeTwo() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                tvTime1.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                sb.setProgress(mediaPlayer.getCurrentPosition());
                /** gọi lại hàm bằng từ khóa this để nó lặp đi lặp lại **/
                handler.postDelayed(this, 500);
            }
        }, 100);

    }

    private void FindView() {

        ivStop    = (ImageView) findViewById(R.id.ivStop);
        ivNext    = (ImageView) findViewById(R.id.ivNext);
        ivPrev    = (ImageView) findViewById(R.id.ivPrev);
        ivBack    = (ImageView) findViewById(R.id.ivBack);
        ivConcert = (ImageView) findViewById(R.id.ivConcert);
        tvTitle   = (TextView)  findViewById(R.id.tvTitle);
        tvTime1   = (TextView)  findViewById(R.id.tvTime1);
        tvTime2   = (TextView)  findViewById(R.id.tvTime2);
        sb        = (SeekBar)   findViewById(R.id.sb);

    }

    private void CreateMedia() {

        mediaPlayer = MediaPlayer.create(Body.this, intent.getIntExtra("song", R.raw.anhdoiemnha));
    }

}
