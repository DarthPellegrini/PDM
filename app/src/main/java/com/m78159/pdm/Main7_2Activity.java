package com.m78159.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Spinner;

public class Main7_2Activity extends AppCompatActivity {

    private String[] colorNames = {"Escolha uma cor" ,"Preto", "Cinza Escuro",
                                    "Cinza", "Cinza Claro", "Branco",
                                    "Vermelho", "Verde", "Azul",
                                    "Magenta", "Ciano", "Amarelo"};
    private int[] colors = {Color.TRANSPARENT,Color.BLACK, Color.DKGRAY,
                            Color.GRAY, Color.LTGRAY, Color.WHITE,
                            Color.RED, Color.GREEN, Color.BLUE,
                            Color.MAGENTA, Color.CYAN, Color.YELLOW};

    @Override
    @SuppressWarnings("RedundantCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7_2);

        Spinner spinner = (Spinner) findViewById(R.id.colorSpinner);
        spinner.setAdapter(new Main7_2ActivityAdapter(this,R.layout.activity_main7_2_adapter, colorNames, colors));
    }
}
