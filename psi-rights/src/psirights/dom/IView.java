package psirights.dom;

import java.util.List;

import psirights.model.Operations;
import psirights.model.Rights;
import psirights.model.Xawd;

public interface IView {

	int showUsers(List<Rights> users);

	void uses(RightsManager rightsmanager);

    int showOperations(List<Operations> operations);
}
