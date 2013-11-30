package psirights.adapter.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import psirights.adapter.DBRepository;
import psirights.adapter.MenuInfo;
import psirights.dom.IRepository;
import psirights.dom.IView;
import psirights.dom.RightsManager;
import psirights.model.Operations;
import psirights.model.Rights;

@ManagedBean
@SessionScoped
public class WebAdapterBean implements IView {

    private RightsManager rightsmanager;
    private IRepository rightsRepo;
    private List<MenuInfo> menuInfos;
    private int menuId;
    Document doc;

    public WebAdapterBean() {
        rightsmanager = new RightsManager();
        rightsRepo = new DBRepository();
        rightsmanager.uses(this, rightsRepo);
        menuInfos = new ArrayList<MenuInfo>();
        doc = null;
        menuId = -1;

        this.readMenu();
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        System.out.println("setMenuId --> " + Integer.toString(menuId));
        this.menuId = menuId;
    }

    public void readMenu() {
        Element data;

        try {
            if (doc == null) {
                SAXReader reader = new SAXReader();
                ExternalContext ec = FacesContext.getCurrentInstance()
                        .getExternalContext();
                doc = reader.read(ec
                        .getResourceAsStream("/WEB-INF/Menu.xml"));
            }

            if (menuId == -1) {
                data = doc.getRootElement();
            } else {
                data = menuInfos.get(this.menuId).getElement();
                System.out.println("ungleich -1 " + menuInfos.get(this.menuId).getElement().toString());
            }

            Iterator<Element> childs = data.elementIterator();

            int id = 0;
            menuInfos.clear();

            while (childs.hasNext()) {

                Element child = (Element) childs.next();

                MenuInfo menuInfo = new MenuInfo(child.getText(), child.attributeValue(
                        "object", ""), id, child);
                menuInfos.add(menuInfo);
                id++;
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public List<MenuInfo> getMenuInfos() {
        readMenu();
        return menuInfos;
    }

    public void setMenuInfos(List<MenuInfo> menuInfos) {
        this.menuInfos = menuInfos;
    }

    @Override
    public int showUsers(List<Rights> users) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void uses(RightsManager rightsmanager) {
        // TODO Auto-generated method stub
    }

    @Override
    public int showOperations(List<Operations> operations) {
        // TODO Auto-generated method stub
        return 0;
    }
}

/*
 @ManagedProperty or <f:viewParam> to set GET parameters as bean properties.
 */
