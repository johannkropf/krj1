package psirights.dom;

import java.util.List;

import psirights.model.Rights;
import psirights.model.Xawd;

public interface IView {

	int showUsers(List<Rights> users);
	int showOperations();

	void uses(RightsManager rightsmanager);
	
}
