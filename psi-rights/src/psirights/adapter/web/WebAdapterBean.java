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
    private List<MenuInfo> currentMenu;
    private int menuId;
//    Document doc;
    private int id;

    public WebAdapterBean() {
        rightsmanager = new RightsManager();
        rightsRepo = new DBRepository();
        rightsmanager.uses(this, rightsRepo);
        menuInfos = new ArrayList<MenuInfo>();
        currentMenu = new ArrayList<MenuInfo>();

//        doc = null;
        menuId = -1;

        //this.readMenu();
//        buildMenu();
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        System.out.println("setMenuId --> " + Integer.toString(menuId));
        this.menuId = menuId;
    }

    private void buildMenu() {
        menuInfos.clear();

        try {
            SAXReader reader = new SAXReader();
            ExternalContext ec = FacesContext.getCurrentInstance()
                    .getExternalContext();
            Document doc = reader.read(ec
                    .getResourceAsStream("/WEB-INF/Menu.xml"));
            this.id = 0;
            build(doc.getRootElement());

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private void build(Element e) {
        MenuInfo menuInfo = new MenuInfo(e.getText(), e.attributeValue(
                "object", ""), "id");

        System.out.println("Element: " + e.getUniquePath());
        System.out.println("Element1: " + e.getQualifiedName());

        menuInfos.add(menuInfo);
        this.id++;

        for (Object o : e.elements()) {
            Element child = (Element) o;
            build(child);
        }
    }

    public void readMenu() {
        Element data=null;

        try {
            SAXReader reader = new SAXReader();
            ExternalContext ec = FacesContext.getCurrentInstance()
                    .getExternalContext();
            Document doc = reader.read(ec
                    .getResourceAsStream("/WEB-INF/Menu.xml"));

            menuId = -1;
            
            if (menuId == -1) {
                data = doc.getRootElement();
            } else {
 //               data = menuInfos.get(this.menuId).getElement();
 //               System.out.println("ungleich -1 " + menuInfos.get(this.menuId).getElement().toString());
            }

            Iterator<Element> childs = data.elementIterator();

            int id = 0;
            menuInfos.clear();

            while (childs.hasNext()) {

                Element child = (Element) childs.next();

                MenuInfo menuInfo = new MenuInfo(child.getText(), child.attributeValue(
                        "object", ""), child.attributeValue("id", ""));
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
/*
public Element elementByID(String elementID) {
        for (int i = 0, size = nodeCount(); i < size; i++) {
            Node node = node(i);

            if (node instanceof Element) {
                Element element = (Element) node;
                String id = elementID(element);

                if ((id != null) && id.equals(elementID)) {
                    return element;
                } else {
                    element = element.elementByID(elementID);

                    if (element != null) {
                        return element;
                    }
                }
            }
        }

        return null;
    }

    protected String elementID(Element element) {
        // XXX: there will be other ways of finding the ID
        // XXX: should probably have an IDResolver or something
        return element.attributeValue("ID");
    }
*/