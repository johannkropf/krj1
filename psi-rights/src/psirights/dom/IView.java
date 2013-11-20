package psirights.dom;

import java.util.List;

import psirights.model.Rights;
import psirights.model.Xawd;

public interface IView {

	int displayUsers(List<Rights> users);

	void uses(RightsManager rightsmanager);
	
}
