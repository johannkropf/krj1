package psirights.adapter;

import org.dom4j.Element;

public class MenuInfo {

    public String menuName;
    public String menuObject;
    public String id;
//    public Element element;

    public MenuInfo(String menu, String object, String id /*, Element element*/) {
        this.menuName = menu;
        this.menuObject = object;
        this.id = id;
//        this.element = element;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public Element getElement() {
//        return element;
//    }
//
//    public void setElement(Element element) {
//        this.element = element;
//    }
}
