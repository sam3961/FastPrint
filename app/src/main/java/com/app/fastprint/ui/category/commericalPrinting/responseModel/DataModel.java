package com.app.fastprint.ui.category.commericalPrinting.responseModel;

public
class DataModel {
    public boolean isClicked=false;
    public int  index;

    public DataModel(boolean isClicked, int index) {
        this.isClicked = isClicked;
        this.index = index;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
