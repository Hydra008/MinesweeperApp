package com.codegodfather.mine;

/**
 * Created by godfather on 2017-10-20.
 */

public class Cell {
    private String value;
    private int position;
    private boolean isBomb;
    private boolean isFlagged;
    private boolean isClicked;
    private boolean isRevealed;

    public Cell(String value, int position,  boolean isFlagged, boolean isClicked, boolean isRevealed) {
        this.value = value;
        this.position = position;
        this.isFlagged = isFlagged;
        this.isClicked = isClicked;
        this.isRevealed = isRevealed;
    }

    public Cell(String value, int position) {
        this.value = value;
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }
}
