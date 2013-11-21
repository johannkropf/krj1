package psirights;

import psirights.adapter.DBRightsRepository;
import psirights.adapter.SwingViewAdapter;
import psirights.dom.IRightsRepository;
import psirights.dom.IView;
import psirights.dom.RightsManager;

public class PSI_Rights {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IView view = new SwingViewAdapter();
		IRightsRepository rightsRepo = new DBRightsRepository();  
		
		RightsManager rightsmanager = new RightsManager();

		rightsmanager.uses(view, rightsRepo);
		view.uses(rightsmanager);
		
	}

}
