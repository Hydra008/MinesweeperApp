package com.codegodfather.mine;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public GridView gridView;
    int[][] grid;
    int count=0;
    int flag=0;
    int revealed=1;
    int size = 9;
    int bombs=9;;
    final String [] item = new String[size*size] ;
    final String[] values= new String[size*size];
    CountDownTimer c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;
        GameGenerator gameGenerator = new GameGenerator(size,bombs);
        //grid will consists of 2d Array with values of neighbours
        grid = gameGenerator.createGame();

        //the below loops will print  the box like shape and convert the values of 2d Array in a single array called values
        int k =0;
        for(int i =0;i<grid.length;i++)
        {
            for(int j=0;j<grid.length;j++)
            {
                item[k]="|_|";
                values[k]=Integer.toString(grid[i][j]);
                k++;
            }
        }
        gridView= (GridView) findViewById(R.id.grid);
        final TextView timer = (TextView) findViewById(R.id.Timer);
        final CustomGridAdopter gridAdopter = new CustomGridAdopter(MainActivity.this,item);
        c=startTimer(); //begins timer
        gridView.setAdapter(gridAdopter);
        Button beginner = (Button) findViewById(R.id.Beginner);

        View.OnClickListener beginnerListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity.super.recreate();
                revealed=1;
                flag=0;
                count=0;
                c.cancel();// will cancel previously made timers and counters
                bombs=9; //10% of bombs
                gridView.setEnabled(true);
                GameGenerator gameGenerator = new GameGenerator(size,bombs);
                grid = gameGenerator.createGame();
                int k=0;
                for(int i =0;i<grid.length;i++)
                {
                    for(int j=0;j<grid.length;j++)
                    {
                        item[k]="|_|";
                        values[k]=Integer.toString(grid[i][j]);
                        k++;
                    }
                }
                gridView.setAdapter(gridAdopter);
                c=startTimer(); //begins new Timer

            }
        };
        beginner.setOnClickListener(beginnerListener);
        Button intermediate = (Button) findViewById(R.id.Intermediate);
        View.OnClickListener intermediateListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.cancel();
                revealed=1;
                flag=0;
                count=0;
                bombs=27;//30% of grid size
                gridView.setEnabled(true);
                GameGenerator gameGenerator = new GameGenerator(size,bombs);
                grid = gameGenerator.createGame();
                int k=0;
                for(int i =0;i<grid.length;i++)
                {
                    for(int j=0;j<grid.length;j++)
                    {
                        item[k]="|_|";
                        values[k]=Integer.toString(grid[i][j]);
                        k++;
                    }
                }
                gridView.setAdapter(gridAdopter);
                c =startTimer();
            }
        };
        intermediate.setOnClickListener(intermediateListener);
        Button expert = (Button) findViewById(R.id.Expert);
        View.OnClickListener expertListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bombs=41;// 50% of grid size
                revealed=1;
                flag=0;
                count=0;
                c.cancel();
                gridView.setEnabled(true);
                GameGenerator gameGenerator = new GameGenerator(size,bombs);
                grid = gameGenerator.createGame();
                int k=0;
                for(int i =0;i<grid.length;i++)
                {
                    for(int j=0;j<grid.length;j++)
                    {
                        item[k]="|_|";
                        values[k]=Integer.toString(grid[i][j]);
                        k++;
                    }
                }
                gridView.setAdapter(gridAdopter);
                c =startTimer();

            }
        };
        expert.setOnClickListener(expertListener);
        Button playAgain = (Button) findViewById(R.id.Playagain);
        View.OnClickListener playAgainListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bombs=41;
                Context context = getApplicationContext();
                CharSequence text = "Select the Game Difficulty Level";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        };
        playAgain.setOnClickListener(playAgainListener);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(values[i].equalsIgnoreCase("-1"))
                {
                    item[i]="*"; //converts bombs to *
                    for(int j=0;j<values.length;j++)
                    {
                        if(values[j].equalsIgnoreCase("-1"))
                        {
                            item[j]="*";
                            gridView.setAdapter(gridAdopter);
                            gridView.setEnabled(false); //locks the grid for further use
                        }
                    }
                }
                else
                {
                    item[i]=values[i];
                    revealed++;
                    if(item[i].equalsIgnoreCase("0"))
                    {
                        //System.out.println(i);
                        int x = i%9; //get x coordinate of 2d array
                        int y = i/9; //get y coordinate of 2d array
                        //System.out.println("x : "+x +"y :  "+y);
                        //top left
                        if(x-1>=0 && y-1>=0 &&x-1<9 && y-1< 9) {
                            if (grid[x - 1][y - 1] != -1) {
                                item[i - 10] = values[i-10];
                                revealed++;
                            }
                        }
                        //top
                        if(x>=0 && y-1>=0 &&x<9 && y-1< 9) {
                            if (grid[x ][y - 1] !=-1) {
                                item[i - 9] = values[i-9];
                                revealed++;
                            }
                        }
                        //top-Right
                        if(x+1>=0 && y-1>=0 &&x+1<9 && y-1< 9) {
                            System.out.println("x : "+x +"y :  "+y);
                            System.out.println(i);
                            if (grid[x+1 ][y - 1] != -1) {
                                item[i - 8] = values[i-8];
                                revealed++;
                            }
                        }
                        //Left
                        if(x-1>=0 && y>=0 &&x-1<9 && y< 9) {
                            if (grid[x-1 ][y] != -1) {
                                item[i - 1] = values[i-1];
                                revealed++;
                            }
                        }
                        //Right
                        if(x+1>=0 && y>=0 &&x+1<9 && y< 9) {
                            if (grid[x+1 ][y] != -1) {
                                item[i +1] = values[i+1];
                                revealed++;
                            }
                        }
                        //bottom left
                        if(x-1>=0 && y+1>=0 &&x-1<9 && y+1< 9) {
                            if (grid[x-1 ][y+1] != -1) {
                                item[i +8] = values[i+8];
                                revealed++;
                            }
                        }
                        //bottom
                        if(x>=0 && y+1>=0 &&x<9 && y+1< 9) {
                            if (grid[x ][y+1] != -1) {
                                item[i +9] = values[i+9];
                                revealed++;
                            }
                        }
                        //bottom right
                        if(x+1>=0 && y+1>=0 &&x+1<9 && y+1< 9) {
                            if (grid[x+1 ][y+1] != -1) {
                                item[i +10] = values[i+10];
                                revealed++;
                            }
                        }
                    }
                }

                gridView.setAdapter(gridAdopter);
                checkGameStatus(values[i],flag,bombs,revealed,size,i);
            }


        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item[i]="F"; //converts gridcell to flag
                flag++;
                checkGameStatus(item[i],flag,bombs,revealed,size,i);
                gridView.setAdapter(gridAdopter);
                return true;
            }
        });
    }

    private CountDownTimer startTimer() {

            CountDownTimer c = new CountDownTimer(700000, 1000) {
                TextView timer = (TextView) findViewById(R.id.Timer);


                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished/1000;
                    int second = (int) (millisUntilFinished / 1000) % 60 ;
                    int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);

                    timer.setText("Time Left"+" " +minutes + " Mins " + second +" sec");
                }

                public void onFinish() {
                    timer.setText("Game Over");

                }

            }.start();

        return c;
    }

    private void checkGameStatus(String value, int flag,int bombs,int revealed,int size,int loop) {
        //ensures
        if(values[loop].equalsIgnoreCase("-1"))
        {
            count++;
        }
        //if value of click == bomb then game lost
        //if flags>bomb then game lost
        if(value.equalsIgnoreCase("-1") || flag>bombs )
        {
            
            System.out.println("Game Lost");
            Context context = getApplicationContext();
            CharSequence text = "Game Lost!";
            System.out.println(revealed+" you lost");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        //if all flags are equal to value of bombs
        //and if revealed == grid size
        //revealed 120 checks for repeated clicks on same grid cells which increases the counter
        if(revealed>(size*size)-bombs && flag==bombs && revealed<120)
        {
                Context context = getApplicationContext();
                CharSequence text = "Game Won";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                System.out.print(revealed + "you won");
                System.out.println("Game Won");

        }
        else
        {
            //to check all flags are boms
            if(count==bombs) {
                System.out.println(revealed + " " + count + " " + flag);
                System.out.println("Game won");
            }
        }
    }

}
