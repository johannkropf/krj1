package psirights;

import psirights.adapter.DBRepository;
import psirights.adapter.SwingViewAdapter;
import psirights.dom.IRepository;
import psirights.dom.IView;
import psirights.dom.RightsManager;

public class PSI_Rights {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IView view = new SwingViewAdapter();
		IRepository rightsRepo = new DBRepository();
		
		RightsManager rightsmanager = new RightsManager();

		rightsmanager.uses(view, rightsRepo);
		view.uses(rightsmanager);
		
	}

}
