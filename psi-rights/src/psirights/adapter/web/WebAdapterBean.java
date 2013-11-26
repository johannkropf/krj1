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
import psirights.dom.IRepository;
import psirights.dom.IView;
import psirights.dom.RightsManager;
import psirights.model.Operations;
import psirights.model.Rights;

@ManagedBean
@SessionScoped
public class WebAdapterBean implements IView {

	// private String menu;
	private List<String> menu;
	private RightsManager rightsmanager;
	private IRepository rightsRepo;

	public WebAdapterBean() {
		rightsmanager = new RightsManager();
		rightsRepo = new DBRepository();
		rightsmanager.uses(this, rightsRepo);
		menu = new ArrayList();
		this.setMenu("");
	}

	public List<String> getMenu() {
		return menu;
	}

	public void setMenu(String menu) {

		SAXReader reader = new SAXReader();
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();

		try {
			Document doc = reader.read(ec
					.getResourceAsStream("/WEB-INF/Menu.xml"));

			Element data = doc.getRootElement();
			Iterator<Element> childs = data.elementIterator();

			while (childs.hasNext()) {

				Element child = (Element) childs.next();
				System.out.println("Element: " + child.getName());
				this.menu.add(child.getName());
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "WebAdapterBean [menu=" + menu + "]";
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
