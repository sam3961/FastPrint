package com.app.fastprint.ui.category.commericalPrinting.viewModel;

public
class Model {
    private boolean isSelected=false;
    private String name;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
