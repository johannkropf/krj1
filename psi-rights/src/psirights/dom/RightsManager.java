package psirights.dom;

import java.util.ArrayList;
import java.util.List;

import psirights.model.Operations;
import psirights.model.Rights;

public class RightsManager {

    private IView view;
    private IRepository repository;
    private List<Rights> rights;
    private List<Operations> operations;

    public RightsManager() {
    }

    public void uses(IView view, IRepository rightsRepo) {
        this.view = view;
        this.repository = rightsRepo;
        this.rights = new ArrayList<Rights>();
        this.operations = new ArrayList<Operations>();
    }

    public Object getView() {
        return this.view;
    }

    public Object getRepo() {
        return this.repository;
    }

    public int showOperations(String psiObject) {
        this.operations.clear();
        if (!psiObject.isEmpty()) {
            this.operations = this.repository.findOperationsForObject(psiObject);
        }
        view.showOperations(this.operations);
        return this.operations.size();
    }

    public int showUsers(String psiObject, List<String> psiOperations) {
        this.rights.clear();
        if (!psiObject.isEmpty() && !psiOperations.isEmpty()) {
            this.rights = this.repository.findUsersForOperations(psiObject, psiOperations);
        }
        view.showUsers(this.rights);
        return this.rights.size();
    }


}
