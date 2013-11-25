package psirights.adapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
import psirights.model.Operations;
import psirights.model.Rights;

public class SwingViewAdapter implements IView {
    private String psiObject;
    private List<String> psiOperations;
    private JFrame frame;
    private JTree menuTree;
    private JXTable jTable1;
    private JXTable jTableFunc;
    private RightsManager controller;

    public SwingViewAdapter() {
        psiOperations = new ArrayList<String>();
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
        jMenuAbout.setText("�ber...");
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
                new Highlighter[]{AlternateRowHighlighter.beige}));
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
                new Highlighter[]{AlternateRowHighlighter.beige}));
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

    // Menuebaum wurde angeklickt
    private void menuTree_mouseClicked(MouseEvent e) {
        int selRow = menuTree.getRowForLocation(e.getX(), e.getY());
        // TreePath selPath = menuTree.getPathForLocation(e.getX(), e.getY());

        this.setPsiObject("");

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

                // Info an Controller,
                showWaitCursor();
                controller.showOperations(this.getPsiObject());
                showDefaultCursor();
            }
        }
    }

    // Tabelle Anwenderoperationen - Auswahl geaendert
    // wird aufgerufen von SharedListSelectionHandler
    public void leftTableChanged() {
        int[] selection = jTableFunc.getSelectedRows();
        for (int i = 0; i < selection.length; i++) {
            selection[i] = jTableFunc.convertRowIndexToModel(selection[i]);
        }

        Arrays.sort(selection);

        this.psiOperations.clear();

        for (int x = selection.length - 1; x >= 0; x--) {

            String sel = jTableFunc.getModel().getValueAt(selection[x], 0).toString();
            this.psiOperations.add(sel);
        }

        // Info an controller
        showWaitCursor();
        controller.showUsers(this.getPsiObject(), this.getPsiOperations());
        showDefaultCursor();
    }

    public String getPsiObject() {
        return psiObject;
    }

    public void setPsiObject(String psiObject) {
        this.psiObject = psiObject;
    }

    public List<String> getPsiOperations() {
        return psiOperations;
    }

    public void setPsiOperations(List<String> psiOperations) {
        this.psiOperations = psiOperations;
    }

    @Override
    public void uses(RightsManager rightsmanager) {
        this.controller = rightsmanager;

    }

    @Override
    public int showUsers(List<Rights> users) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();

        tableHeaders.add("User");
        tableHeaders.add("Name");
        tableHeaders.add("Rolle");
        tableHeaders.add("Kompetenz");
        tableHeaders.add("Werk");
        tableHeaders.add("Objekt");
        tableHeaders.add("Methode");

        for (Rights user : users) {
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(user.getXawdname());
            oneRow.add(user.getXawdbez());
            oneRow.add(user.getRolle());
            oneRow.add(user.getKompetenz());
            oneRow.add(user.getWerk());
            oneRow.add(user.getObjekt());
            oneRow.add(user.getMethode());
            tableData.add(oneRow);
        }
        jTable1.setModel(new DefaultTableModel(tableData, tableHeaders));

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(150);
        DefaultTableCellRenderer myRenderer = new DefaultTableCellRenderer();
        myRenderer.setHorizontalAlignment(JLabel.CENTER);

        return users.size();
    }

    @Override
    public int showOperations(List<Operations> operations) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();

        tableHeaders.add("Methode");

        for (Operations operation : operations) {
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(operation.getMethode());
            tableData.add(oneRow);
        }
        jTableFunc.setModel(new DefaultTableModel(tableData, tableHeaders));

        jTableFunc.getColumnModel().getColumn(0).setPreferredWidth(250);

        return operations.size();
    }

    private void showWaitCursor() {
        Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
        frame.setCursor(hourglassCursor);
    }

    private void showDefaultCursor() {
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        frame.setCursor(normalCursor);
    }
}
