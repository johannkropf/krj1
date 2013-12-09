package psirights.model;

public class MenuInfo {

    public String menuName;
    public String menuObject;
    public String id;

    public MenuInfo(String menu, String object, String id ) {
        this.menuName = menu;
        this.menuObject = object;
        this.id = id;
    }

    public String toString() {
        return menuName;
    }

    public String getMenuObject() {
        if (menuObject == null)
            menuObject = "";
        System.out.println("MenuObject: >" + this.menuObject + "<");
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

}
