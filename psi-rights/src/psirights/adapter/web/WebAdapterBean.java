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
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import psirights.adapter.DBRepository;
import psirights.model.MenuInfo;
import psirights.dom.IRepository;
import psirights.dom.IView;
import psirights.dom.RightsManager;
import psirights.model.Operations;
import psirights.model.Rights;

@ManagedBean
@SessionScoped
public class WebAdapterBean implements IView {

    private RightsManager controller;
    private IRepository rightsRepo;
    private List<MenuInfo> menuInfos;
    private List<Operations> operations;
    private List<Operations> operationsSelected;
    private String menuId;
    private String object;
    
    public WebAdapterBean() {
        controller = new RightsManager();
        rightsRepo = new DBRepository();
        controller.uses(this, rightsRepo);
        
        menuInfos = new ArrayList<MenuInfo>();
        menuId = "1";
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        System.out.println("setMenuId --> " + menuId);
        this.menuId = menuId;
    }

    public void readMenu() {
        Element data = null;

        try {
            SAXReader reader = new SAXReader();
            ExternalContext ec = FacesContext.getCurrentInstance()
                    .getExternalContext();
            Document doc = reader.read(ec
                    .getResourceAsStream("/WEB-INF/Menu.xml"));

            if (menuId.equals("1")) {
                data = doc.getRootElement();
            } else {
                data = findElementById(doc.getRootElement());
            }

            Iterator<Element> childs = data.elementIterator();

            menuInfos.clear();

            while (childs.hasNext()) {

                Element child = (Element) childs.next();

                String obj = child.attributeValue("object", "");
                String txt = obj.isEmpty() ? child.getText() + " ..." : child.getText();
                
                MenuInfo menuInfo = new MenuInfo(txt, obj, child.attributeValue("id", ""));
//                MenuInfo menuInfo = new MenuInfo(child.getText(), child.attributeValue(
//                        "object", ""), child.attributeValue("id", ""));
                menuInfos.add(menuInfo);
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

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Operations> getOperations() {
        controller.showOperations(this.getObject());
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

    public List<Operations> getOperationsSelected() {
        return operationsSelected;
    }

    public void setOperationsSelected(List<Operations> operationsSelected) {
        this.operationsSelected = operationsSelected;
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
        this.operations = operations;
        return 0;
    }

    private Element findElementById(Element root) {

        for (int i = 0, size = root.nodeCount(); i < size; i++) {
            Node node = root.node(i);

            if (node instanceof Element) {
                Element element = (Element) node;
                String id = element.attributeValue("id");

                if ((id != null) && id.equals(this.menuId)) {
                    return element;
                } else {
                    element = findElementById(element);

                    if (element != null) {
                        return element;
                    }
                }
            }
        }
        return null;
    }
}

/*
 @ManagedProperty or <f:viewParam> to set GET parameters as bean properties.
 */
