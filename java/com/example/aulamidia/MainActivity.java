package com.example.aulamidia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer md;
    private SeekBar seekVolume;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        md = MediaPlayer.create(this, R.raw.teste);
        inicializarSeekBarVolume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(md != null){
            md.stop();
            md.release();
            md = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(md.isPlaying())
            md.stop();
    }

    public void inicializarSeekBarVolume(){
        seekVolume = findViewById(R.id.seekBarVolume);
        //Configura o audio maneger
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); //recupera o serviço de audio do aparelho

        //recupera os valores de max e min do volume
        int volMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int volNow = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        //Configurar a seekBar
        seekVolume.setMax(volMax);
        seekVolume.setProgress(volNow);

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI); //a flag defini configurações adicionais
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    public void execSom(View view){
        if(md == null)
            md = MediaPlayer.create(this, R.raw.teste);

        md.start();
    }

    public void pause(View view){
        if(md.isPlaying()){
            md.pause();
        }
    }

    public void stop(View view){
        if(md.isPlaying()){
            md.stop();
            md = MediaPlayer.create(this, R.raw.teste);
        }
    }
}