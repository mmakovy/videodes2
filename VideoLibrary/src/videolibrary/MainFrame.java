/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrej
 */
public class MainFrame extends javax.swing.JFrame {
    /**
     * Creates new form MainFrame
     */
    
    public static boolean checkXml() {
        File file = new File("videolibrary.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        }
        catch (ParserConfigurationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!(file.exists())) {
           Document doc = docBuilder.newDocument();
                
           Element rootElement = doc.createElement("Videos");
           doc.appendChild(rootElement);
                
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
	   Transformer transformer = null;
           try {
               transformer = transformerFactory.newTransformer();
           } catch (TransformerConfigurationException ex) {
               Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
           DOMSource source = new DOMSource(doc);
           StreamResult result = new StreamResult(file);
           try {
               transformer.transform(source, result);
           } catch (TransformerException ex) {
               Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
           }
            return true;
        }
        
        try {
            Document doc = docBuilder.parse(file);
        
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            Schema schema = factory.newSchema(new File("videoXmlSchema.xsd"));
            
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));
            System.out.println();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            return false;
          } catch (SAXException ex) {
              Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
              return false;
              
          }
    }
    
    
    public MainFrame() {
        initComponents();
        if (!checkXml()) {
            JOptionPane.showMessageDialog(this, "XML File is not valid","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        addVideoButton = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JToolBar.Separator();
        deleteButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        searchByIdButton = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        searchByTitleButton = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        searchByDirectorButton = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        searchByActorButton = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        searchByYearButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        searchByGenreButton = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        importFromODFButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        exportToODFButton = new javax.swing.JButton();
        scrollPanel = new javax.swing.JScrollPane();
        videoTable = new javax.swing.JTable();
        staticLabel = new javax.swing.JLabel();
        numberOfVideosLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        importFromODFMenuButton = new javax.swing.JMenuItem();
        exportToODFMenuButton = new javax.swing.JMenuItem();
        exitMenuButton = new javax.swing.JMenuItem();
        videoMenu = new javax.swing.JMenu();
        addVideoMenuButton = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Library");

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        addVideoButton.setText("Add Video");
        addVideoButton.setFocusable(false);
        addVideoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addVideoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addVideoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVideoButtonActionPerformed(evt);
            }
        });
        toolBar.add(addVideoButton);
        toolBar.add(jSeparator9);

        deleteButton.setText("Delete Video");
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(deleteButton);
        toolBar.add(jSeparator1);

        searchByIdButton.setText("Search by Id");
        searchByIdButton.setFocusable(false);
        searchByIdButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByIdButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchByIdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByIdButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchByIdButton);
        toolBar.add(jSeparator8);

        searchByTitleButton.setText("Search by Title");
        searchByTitleButton.setFocusable(false);
        searchByTitleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByTitleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(searchByTitleButton);
        toolBar.add(jSeparator7);

        searchByDirectorButton.setText("Search by Director");
        searchByDirectorButton.setFocusable(false);
        searchByDirectorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByDirectorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(searchByDirectorButton);
        toolBar.add(jSeparator6);

        searchByActorButton.setText("Search by Actor");
        searchByActorButton.setFocusable(false);
        searchByActorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByActorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(searchByActorButton);
        toolBar.add(jSeparator5);

        searchByYearButton.setText("Search by Year");
        searchByYearButton.setFocusable(false);
        searchByYearButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByYearButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(searchByYearButton);
        toolBar.add(jSeparator4);

        searchByGenreButton.setText("Search by Genre");
        searchByGenreButton.setFocusable(false);
        searchByGenreButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByGenreButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(searchByGenreButton);
        toolBar.add(jSeparator10);

        jButton1.setText("Search by Country");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(jButton1);
        toolBar.add(jSeparator2);

        importFromODFButton.setText("Import from ODF");
        importFromODFButton.setFocusable(false);
        importFromODFButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importFromODFButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(importFromODFButton);
        toolBar.add(jSeparator3);

        exportToODFButton.setText("Export to ODF");
        exportToODFButton.setFocusable(false);
        exportToODFButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportToODFButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(exportToODFButton);

        videoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title", "Director(s)", "Actor(s)", "Year", "Country", "Genre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        videoTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPanel.setViewportView(videoTable);

        staticLabel.setText("Number of videos: ");

        numberOfVideosLabel.setText("0");

        fileMenu.setText("File");

        importFromODFMenuButton.setText("Import from ODF");
        fileMenu.add(importFromODFMenuButton);

        exportToODFMenuButton.setText("Export to ODF");
        fileMenu.add(exportToODFMenuButton);

        exitMenuButton.setText("Exit");
        exitMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuButtonActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuButton);

        menuBar.add(fileMenu);

        videoMenu.setText("Video");

        addVideoMenuButton.setText("Add Video");
        videoMenu.add(addVideoMenuButton);

        jMenu1.setText("Search by ...");

        jMenuItem1.setText("Id");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Title");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Director");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Actor");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("Year");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Genre");
        jMenu1.add(jMenuItem6);

        jMenuItem7.setText("Country");
        jMenu1.add(jMenuItem7);

        videoMenu.add(jMenu1);

        menuBar.add(videoMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(staticLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberOfVideosLabel)
                .addContainerGap())
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(staticLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numberOfVideosLabel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVideoButtonActionPerformed
        AddVideoDialog dialog = new AddVideoDialog(this, true);
        dialog.setVisible(true); 
    }//GEN-LAST:event_addVideoButtonActionPerformed

    private void searchByIdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByIdButtonActionPerformed
        SearchVideoDialog dialog = new SearchVideoDialog(this, true);
        dialog.setVisible(true); 
    }//GEN-LAST:event_searchByIdButtonActionPerformed

    private void exitMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addVideoButton;
    private javax.swing.JMenuItem addVideoMenuButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenuItem exitMenuButton;
    private javax.swing.JButton exportToODFButton;
    private javax.swing.JMenuItem exportToODFMenuButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton importFromODFButton;
    private javax.swing.JMenuItem importFromODFMenuButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar.Separator jSeparator9;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel numberOfVideosLabel;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JButton searchByActorButton;
    private javax.swing.JButton searchByDirectorButton;
    private javax.swing.JButton searchByGenreButton;
    private javax.swing.JButton searchByIdButton;
    private javax.swing.JButton searchByTitleButton;
    private javax.swing.JButton searchByYearButton;
    private javax.swing.JLabel staticLabel;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JMenu videoMenu;
    private javax.swing.JTable videoTable;
    // End of variables declaration//GEN-END:variables
}
