package psirights.adapter;

import org.dom4j.Element;

public class MenuInfo {

    public String menuName;
    public String menuObject;
    public int id;
    public Element element;

    public MenuInfo(String menu, String object, int id, Element element) {
        menuName = menu;
        menuObject = object;
        this.id = id;
        this.element = element;
    }

    public String toString() {
        return menuName;
    }

    public String getMenuObject() {
        return menuObject;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
