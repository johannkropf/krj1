package psirights.adapter;

public class MenuInfo {
	public String menuName;
    public String menuObject;

    public MenuInfo(String menu, String object) {
        menuName = menu;
        menuObject = object;
    }

    public String toString() {
        return menuName;
    }

	public String getMenuObject() {
		return menuObject;
	}
    
    
}
