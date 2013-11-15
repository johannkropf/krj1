package psirights.dom;

import java.util.List;

import psirights.model.Xawd;

public interface IView {

	int displayUsers(List<Xawd> users);

	void uses(RightsManager rightsmanager);
	
}
