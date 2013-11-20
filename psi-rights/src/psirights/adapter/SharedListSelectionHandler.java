package psirights.adapter;


import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SharedListSelectionHandler implements ListSelectionListener {

	SwingViewAdapter view;
	
	public SharedListSelectionHandler(SwingViewAdapter m) {
		view = m;
    }
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();

        if (lsm.isSelectionEmpty()) {
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
//                	System.out.println(" " + i);
                }
            }
        }
        view.leftTableChanged();
    }

}

