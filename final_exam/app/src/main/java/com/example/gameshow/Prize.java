package com.example.gameshow;

public class Prize {

    public String name;
    public int ID, door;
    public boolean isSelected, hasDoor;

    public Prize(String _name, int _ID) {
        String name = _name;
        int ID = _ID;
        int door = -1;
        isSelected = false;
        hasDoor = false;
    }

    public void setDoor(int door) {
        this.door = door;
        hasDoor = true;
    }

    public boolean hasDoor(){
        return this.hasDoor;
    }

    public int getDoor(){
        return door;
    }

    public void setSelected() {
        this.isSelected = true;
    }

    public void reset() {
        isSelected = false;
        hasDoor = false;
        door = -1;
    }

    public String getName() {
        return this.name;
    }

    public boolean getSelected() {
        return this.isSelected;
    }
}
