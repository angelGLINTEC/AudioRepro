package com.glintec.app.vlcmine;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay, btnPause, btnFWD, btnLoop, btnStop;
    private MediaPlayer mp;
    private int posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay=findViewById(R.id.btnPLAY);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound();
            }
        });
        btnPause = findViewById(R.id.btnPAUSE);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseSound();
            }
        });

        btnFWD = findViewById(R.id.btnFORWARD);
        btnFWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardSound();
            }
        });

        btnStop = findViewById(R.id.btnSTOP);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSound();
            }
        });

        btnLoop = findViewById(R.id.btnLOOP);
        View.OnClickListener clLoop = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                loopSound();
            }
        };
        btnLoop.setOnClickListener(clLoop);
    }

    public void playSound(){
        deleteSound();
        mp = MediaPlayer.create(this, R.raw.rock);
        mp.start();
        String modo = btnLoop.getText().toString();
        if("LOOP".equals(modo)){
            mp.setLooping(true);
        }else{
            mp.setLooping(false);
        }

    }
    public void stopSound(){
        if(mp != null){
            mp.stop();
            posicion = 0;
        }
    }

    public void forwardSound(){
        if(mp!=null && mp.isPlaying()==false){
            mp.seekTo(posicion);
            mp.start();
        }
    }

    public void pauseSound(){
        if(mp!=null && mp.isPlaying()){
            posicion = mp.getCurrentPosition();
            mp.pause();
        }
    }

    public void loopSound() {
        stopSound();
        String modo = btnLoop.getText().toString();
        if("LOOP".equals(modo)){
            btnLoop.setText("NO LOOP");
        }else{
            btnLoop.setText("LOOP");
        }
    }

    public void deleteSound() {
        if(mp != null){
            mp.release();
        }
    }
}
