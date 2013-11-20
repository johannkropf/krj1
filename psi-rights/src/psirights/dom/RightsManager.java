package psirights.dom;

import java.util.List;

import psirights.model.Rights;

public class RightsManager {

	private IView view;
	private IRightsRepository rightsRepository;
    private List<Rights> rights;
    
	public RightsManager() {
	}

	public void uses(IView view, IRightsRepository rightsRepo) {
		this.view = view;
		this.rightsRepository = rightsRepo;
	}

	public int retrieveUsers(String psiObject, String psiOperations ) {
		this.rights = this.rightsRepository.findUsersForOperations(psiObject, psiOperations);
		return rights.size();
	}

	public void displayUsers() {
		view.displayUsers(this.rights);
		return;
	}

	public Object getView() {
		return this.view;
	}

	public Object getRepo() {
		return this.rightsRepository;
	}

	
}
