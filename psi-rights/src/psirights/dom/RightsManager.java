package psirights.dom;

import java.util.List;

import psirights.model.Xawd;

public class RightsManager {

	private IView view;
	private IRightsRepository rightsRepository;
    private List<Xawd> users;
    
	public RightsManager() {
	}

	public void uses(IView view, IRightsRepository rightsRepo) {
		this.view = view;
		this.rightsRepository = rightsRepo;
	}

	public int retrieveUsers(String psiObject, String psiOperations ) {
		this.users = this.rightsRepository.findUsersForOperations(psiObject, psiOperations);
		return users.size();
	}

	public void displayUsers() {
		view.displayUsers(this.users);
		return;
	}

	public Object getView() {
		return this.view;
	}

	public Object getRepo() {
		return this.rightsRepository;
	}

	
}
