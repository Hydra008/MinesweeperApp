package com.codegodfather.mine;

import java.util.Random;

/**
 * Created by godfather on 2017-10-14.
 */

public class GameGenerator {
    private int grid[][];
    private int bombs;
    private int gridSize;

    public GameGenerator(int gridSize,int bombs){
        this.bombs=bombs;
        this.gridSize=gridSize;
    }
    public int[][] createGame() {
        grid = new int[gridSize][gridSize];
        RandomBombAssigner();
        //printGrid(grid);
        return grid;
    }
    //Randomly Assigning bomb as -1
    private int[][] RandomBombAssigner() {
        int numberOfBomb = 0 ;
        int xCoordinate;
        int yCoordinate;
        for(int k = numberOfBomb;numberOfBomb<bombs;k++)
        {
            Random random = new Random();
            xCoordinate = random.nextInt((gridSize - 1) + 1);
            yCoordinate = random.nextInt((gridSize - 1) + 1);
            if(grid[xCoordinate][yCoordinate]!= -1)
            {
                grid[xCoordinate][yCoordinate] = -1 ;
                numberOfBomb++;
            }
        }
        grid = calculateNeighbours(grid);
        return grid;

    }
    //calculating weight for each cell in grid
    private int[][] calculateNeighbours(int[][] grid) {
        for(int i=0;i<=gridSize-1;i++)
        {
            for(int j=0;j<=gridSize-1;j++)
            {
                grid[i][j]=calculateHints(grid,i,j);
            }
        }
        return grid;
    }

    private int calculateHints(int[][] grid, int i, int j) {
        if (grid[i][j]==-1)
        {
            return -1;
        }
        else
        {
            int count =0;
            count =count+ topLeft(grid,i,j);
            count =count + top(grid,i,j);
            count =count + topRight(grid,i,j);
            count =count + left(grid,i,j);
            count =count + right(grid,i,j);
            count =count + bottomLeft(grid,i,j);
            count =count + bottom(grid,i,j);
            count =count +  bottomRight(grid,i,j);
            return count;
        }

    }

    private int bottomRight(final int[][] grid, int i, int j) {
        int count = 0;
        int xCoordinate = i+1;
        int yCoordinate = j-1;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;

            }
        }

        return count;
    }

    private int bottom(final int[][] grid, int i, int j) {
        int count = 0;
        int xCoordinate = i;
        int yCoordinate = j-1;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;
            }
        }

        return count;
    }

    private int bottomLeft(final int[][] grid, int i, int j) {
        int count = 0;
        int xCoordinate = i-1;
        int yCoordinate = j-1;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;
            }
        }

        return count;
    }

    private int right(final int[][] grid, int i, int j) {
        int count = 0;
        int xCoordinate = i+1;
        int yCoordinate = j;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;
            }
        }

        return count;
    }

    private int left(final int[][] grid, int i, int j) {
        int count = 0;
        int xCoordinate = i-1;
        int yCoordinate = j;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;
            }
        }

        return count;
    }

    private int topRight(final int[][] grid, int i, int j) {
        int count = 0;
        int xCoordinate = i+1;
        int yCoordinate = j+1;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;
            }
        }

        return count;
    }

    private int top( final int[][] grid, int i, int j) {
        int count = 0;
        int xCoordinate = i;
        int yCoordinate = j+1;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;
            }
        }
        return count;
    }

    private int topLeft(final int[][] grid,int i, int j) {
        int count = 0;
        int xCoordinate = i-1;
        int yCoordinate = j+1;
        if(xCoordinate>=0  && xCoordinate<gridSize  && yCoordinate>=0 && yCoordinate<gridSize)
        {
            if(grid[xCoordinate][yCoordinate]==-1)
            {
                count++;
            }
        }
        return count;
    }


    public void printGrid(final int [][]grid) {
        for (int i = 0;i<gridSize;i++)
        {
            for(int j=0;j<gridSize;j++)
            {
                System.out.print(grid[i][j]);
                System.out.println();
            }
        }
    }
}
