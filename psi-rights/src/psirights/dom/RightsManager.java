package psirights.dom;

import java.util.List;

import psirights.model.Operations;
import psirights.model.Rights;

public class RightsManager {

	private IView view;
	private IRightsRepository rightsRepository;
    private List<Rights> rights;
    private IOperationsRepository operationsRepository;
    private List<Operations> operations;

	public RightsManager() {
	}

	public void uses(IView view, IRightsRepository rightsRepo) {
		this.view = view;
		this.rightsRepository = rightsRepo;
	}

	public Object getView() {
		return this.view;
	}

	public Object getRepo() {
		return this.rightsRepository;
	}

	public int showOperations(String psiObject) {
		this.operations = this.operationsRepository.findOperationsForObject(psiObject);
        view.showOperations(this.operations);
		return this.operations.size();
		
	}

	public int showUsers(String psiObject, String psiOperations) {
		this.rights = this.rightsRepository.findUsersForOperations(psiObject, psiOperations);
		view.showUsers(this.rights);
		return this.rights.size();
	}

	
}
