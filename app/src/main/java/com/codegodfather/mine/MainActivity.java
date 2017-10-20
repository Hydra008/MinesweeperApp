package com.codegodfather.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    public GridView gridView;
    int[][] grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameGenerator gameGenerator = new GameGenerator(10,10);
        grid = gameGenerator.createGame();
        int [] item = new int [100];
        System.out.println(grid.length);
        int k =0;
        for(int i =0;i<grid.length;i++)
        {
            for(int j=0;j<grid.length;j++)
            {

                item[k]=grid[i][j];
                k++;
            }
        }
        gridView= (GridView) findViewById(R.id.grid);
        final CustomGridAdopter gridAdopter = new CustomGridAdopter(MainActivity.this,item);


        gridView.setAdapter(gridAdopter);
    }
}
