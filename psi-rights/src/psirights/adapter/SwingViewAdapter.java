package psirights.adapter;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterPipeline;

import psirights.dom.IView;
import psirights.dom.RightsManager;
import psirights.model.Rights;

public class SwingViewAdapter implements IView {
	private String psiObject;
	private String psiOperations;
	private JFrame frame;
	private JTree menuTree;
	private JXTable jTable1;
	private JXTable jTableFunc;
	private RightsManager controller;

	public SwingViewAdapter() {
		frame = new JFrame();
		frame.setTitle("PSI-Rechte");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);

		JMenuBar menu = buildMenu();
		frame.setJMenuBar(menu);

		JComponent panel = buildPanel();
		frame.getContentPane().add(panel);

		frame.setVisible(true);
	}

	private JMenuBar buildMenu() {
		JMenuBar jMenuBar1 = new JMenuBar();

		JMenu jMenuFile = jMenuBar1.add(new JMenu("Datei"));

		JMenu jMenuHelp = jMenuBar1.add(new JMenu("?"));

		JMenuItem jMenuAbout = new JMenuItem();
		jMenuHelp.add(jMenuAbout);
		jMenuAbout.setText("Über...");
		jMenuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// About();
			}
		});

		JMenuItem jMenuItemPrint = jMenuFile.add(new JMenuItem("Drucken"));
		jMenuItemPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Drucken();
			}
		});

		jMenuFile.add(new JSeparator());

		JMenuItem jMenuItemExit = new JMenuItem("Beenden", KeyEvent.VK_X);
		jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				Event.ALT_MASK, false));

		jMenuFile.add(jMenuItemExit);
		jMenuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return jMenuBar1;
	}

	// GUI aufbauen
	private JComponent buildPanel() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, buildFindPanel(), buildTablePanel());
		splitPane.setDividerLocation(300);

		return splitPane;
	}

	private JComponent buildFindPanel() {

		initComponents();

		JScrollPane scrollpane = new JScrollPane(menuTree);
		scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				true, scrollpane, buildLeftTablePanel());
		splitPane.setDividerLocation(0.5);
		splitPane.setResizeWeight(0.5);

		return splitPane;
	}

	// Tabelle Anwenderoperationen aufbauen
	private JComponent buildLeftTablePanel() {
		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setBorder(new EmptyBorder(0, 0, 0, 0));
		// modelFunc = new DBTableModel();
		jTableFunc = new JXTable();
		// jTableFunc.setModel(modelFunc);
		jTableFunc.setAutoResizeMode(0);
		jTableFunc.setHighlighters(new HighlighterPipeline(
				new Highlighter[] { AlternateRowHighlighter.beige }));
		jTableFunc.setRowSelectionAllowed(true);

		ListSelectionModel TableFuncSelectionModel = jTableFunc
				.getSelectionModel();
		TableFuncSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler(this));

		jScrollPane1.setViewportView(jTableFunc);
		return jScrollPane1;
	}

	// Rechte Tabelle aufbauen
	private JComponent buildTablePanel() {
		JScrollPane jScrollPane1 = new JScrollPane();
		// model = new DBTableModel();
		jTable1 = new JXTable();
		// jTable1.setModel(model);
		jTable1.setAutoResizeMode(0);
		jTable1.setHighlighters(new HighlighterPipeline(
				new Highlighter[] { AlternateRowHighlighter.beige }));
		jScrollPane1.setViewportView(jTable1);

		return jScrollPane1;
	}

	private void initComponents() {

		try {
			menuTree = buildTree("Menu.xml");

			menuTree.addMouseListener(
					new java.awt.event.MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							menuTree_mouseClicked(e);
						}
					}
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// XML für Menübaum einlesen
	public JTree buildTree(String pathToXml) throws Exception {
		SAXReader reader = new SAXReader();

		Document doc = reader.read(pathToXml);
		return new JTree(build(doc.getRootElement()));
	}

	public DefaultMutableTreeNode build(Element e) {
		MenuInfo menuInfo = new MenuInfo(e.getText(), e.attributeValue(
				"object", ""));

		DefaultMutableTreeNode result = new DefaultMutableTreeNode(menuInfo);

		for (Object o : e.elements()) {
			Element child = (Element) o;
			result.add(build(child));
		}

		return result;
	}

	// Menübaum wurde angeklickt
	private void menuTree_mouseClicked(MouseEvent e) {
		int selRow = menuTree.getRowForLocation(e.getX(), e.getY());
		// TreePath selPath = menuTree.getPathForLocation(e.getX(), e.getY());

		if (selRow != -1) {

			DefaultMutableTreeNode node;
			MenuInfo menuInfo;
			node = (DefaultMutableTreeNode) menuTree
					.getLastSelectedPathComponent();
			if (node == null) {
				return;
			} else {
				menuInfo = (MenuInfo) node.getUserObject();

				this.setPsiObject(menuInfo.getMenuObject());

				// TODO Info an Controller,
				// displayOperations(menuInfo.getMenuObject());
				controller.showOperations(this.getPsiObject());

			}
		}
	}

	// Tabelle Anwenderoperationen - Auswahl geändert
	// wird aufgerufen von SharedListSelectionHandler
	public void leftTableChanged() {

		int[] selection = jTableFunc.getSelectedRows();
		for (int i = 0; i < selection.length; i++) {
			selection[i] = jTableFunc.convertRowIndexToModel(selection[i]);
		}

		Arrays.sort(selection);

		this.setPsiOperations("");

		for (int x = selection.length - 1; x >= 0; x--) {

			if (!this.getPsiOperations().isEmpty()) {
				this.setPsiOperations(this.getPsiOperations() + ", ");
			}

			// this.setPsiOperations( this.getPsiOperations() + "'"
			// + modelFunc.getValueAt(selection[x], 0) + "'");
		}

		// TODO Info an controller - displayUser();
		controller.showUsers(this.getPsiObject(), this.getPsiOperations());

	}

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
		this.controller = rightsmanager;

	}

	// Anwenderoperationen anzeigen aufgrund des gewählten Menüeintrages
	private void displayOperations(String object) {
		/*
		 * Vector datavFunc = null;
		 * 
		 * Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
		 * frame.setCursor(hourglassCursor);
		 * 
		 * datavFunc = dm.getData(
		 * "select xoprmethode from xopr where xoprobj = '" + object +
		 * "' order by xoprmethode");
		 * 
		 * modelFunc.setDataVector(datavFunc, dm.getColumnNames());
		 * 
		 * jTableFunc.getColumnModel().getColumn(0).setPreferredWidth(250);
		 * 
		 * Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		 * frame.setCursor(normalCursor);
		 */
	}

	@Override
	public int showUsers(List<Rights> users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int showOperations() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
