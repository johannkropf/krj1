package psirights.adapter;

import java.util.List;

import psirights.dom.IView;
import psirights.dom.RightsManager;
import psirights.model.Xawd;

public class SwingViewAdapter implements IView {
	private String psiObject;
	private String psiOperations;
	
	public String getPsiObject() {
		return psiObject;
	}

	public void setPsiObject(String psiObject) {
		this.psiObject = psiObject;
	}

	public String getPsiOperations() {
		return psiOperations;
	}

	public void setPsiOperations(String psiOperations) {
		this.psiOperations = psiOperations;
	}

	@Override
	public void uses(RightsManager rightsmanager) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int displayUsers(List<Xawd> users) {
		// TODO Auto-generated method stub
		return 0;
	}

}
