package com.rz.frame.model;

import java.util.List;

public class Menu {

    private String menuUrl;
    private String icon;
    private String menuName;
    private List<Menu> subMeuns;

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<Menu> getSubMeuns() {
        return subMeuns;
    }

    public void setSubMeuns(List<Menu> subMeuns) {
        this.subMeuns = subMeuns;
    }
}
