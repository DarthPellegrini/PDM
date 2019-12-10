package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main12_3ActivityAudioPlayer extends AppCompatActivity {

    private Main12_1ActivityDatabaseHelper helper;
    private TextView tvID, tvTitle, tvTime, tvLat, tvLon;
    private ImageButton btnPlay, btnStop;
    private OutputStream outStream = null;
    private MediaPlayer mediaPlayer;
    private Main12_6ActivityVisualizerView visualizerView;
    private Visualizer visualizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12_3_audio_player);

        setTitle("Audio Player");

        Bundle extra = getIntent().getExtras();

        helper = new Main12_1ActivityDatabaseHelper(getApplicationContext());

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT * FROM trabalho_audio WHERE id = %s", String.valueOf(extra.getInt("id"))), null);
        cursor.moveToFirst();

        tvID = findViewById(R.id.tvId);
        tvTitle = findViewById(R.id.tvTitle);
        tvTime = findViewById(R.id.tvTime);
        tvLat = findViewById(R.id.tvLat);
        tvLon = findViewById(R.id.tvLon);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        visualizerView = findViewById(R.id.visualizerview);

        tvID.setText(String.valueOf(cursor.getInt(0)));

        tvTitle.setText(cursor.getString(1));
        Toast.makeText(getApplicationContext(), cursor.getString(1), Toast.LENGTH_LONG).show();

        try {
            outStream = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp");
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            byteOutStream.write(cursor.getBlob(2));
            byteOutStream.writeTo(outStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tvTime.setText(cursor.getString(3));
        tvLat.setText(cursor.getString(4));
        tvLon.setText(cursor.getString(5));

        cursor.close();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setVolumeControlStream(AudioManager.STREAM_MUSIC);

                btnStop.setEnabled(true);
                btnPlay.setEnabled(false);

                mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp");
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.3gp"));
                setupVisualizer();
                visualizer.setEnabled(true);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        visualizer.setEnabled(false);
                    }
                });

                mediaPlayer.start();

                Toast.makeText(getApplicationContext(), "Reproduzindo a gravação...", Toast.LENGTH_LONG).show();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnPlay.setEnabled(true);
                    }
                });

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);

                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }
        });

    }

    private void setupVisualizer() {
        visualizer = new Visualizer(mediaPlayer.getAudioSessionId());
        visualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        visualizer.setDataCaptureListener(
                new Visualizer.OnDataCaptureListener() {
                    public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                        visualizerView.updateVisualizer(bytes);
                    }

                    public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
                    }
                }, Visualizer.getMaxCaptureRate() / 2, true, false);
    }

    public void onClickUpdateActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), Main12_2ActivityUpdate.class);
        intent.putExtra("id", tvID.getText().toString());
        intent.putExtra("title", tvTitle.getText().toString());
        intent.putExtra("timeOfRecording", tvTime.getText().toString());
        intent.putExtra("latitude", tvLat.getText().toString());
        intent.putExtra("longitude", tvLon.getText().toString());
        startActivity(intent);
    }


    public void delete(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();

        long resultado = db.delete("trabalho_audio", "id = ?", new String[] {tvID.getText().toString()});
        if (resultado != -1){
            Toast.makeText(this, "Gravação Deletado com Sucesso!", Toast.LENGTH_SHORT).show();
            super.finish();
        }else{
            Toast.makeText(this, "Erro ao Excluir!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        this.recreate();
    }
}
