/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
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
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Andrej
 */
public class MainFrame extends javax.swing.JFrame {

    private VideoTableModel tableModel;
    private VideoManagerImpl videoManagerImpl;
    private Context context = new Context();

    /**
     * Creates new form MainFrame
     */
    public static boolean checkXml() {
        File file = new File("videolibrary.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
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
        /*
         * final Toolkit toolkit = Toolkit.getDefaultToolkit(); final Dimension
         * screenSize = toolkit.getScreenSize(); final int width = 1200; final
         * int height = 600; final int x = (screenSize.width - width) / 2; final
         * int y = (screenSize.height - height) / 2; setBounds(x, y, width,
         * height);
         *
         * setResizable (false);
         */

        tableModel = (VideoTableModel) videoTable.getModel();
        videoTable.removeColumn(videoTable.getColumn("Id"));
        videoManagerImpl = new VideoManagerImpl();

        String a = null;
        try {
            a = new org.basex.core.cmd.List().execute(context);
        } catch (BaseXException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!a.contains("VideoDes2DB")) {
            try {
                BaseXDriver.createCollection(context);

            } catch (BaseXException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    BaseXDriver.closeCollection(context);
                } catch (BaseXException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
        getAllVideosButtonActionPerformed(null);
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
        deleteVideoButton = new javax.swing.JButton();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        getAllVideosButton = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        jSeparator13 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JToolBar.Separator();
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
        searchByCountryButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator14 = new javax.swing.JToolBar.Separator();
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
        deleteVideoMenuButton = new javax.swing.JMenuItem();
        getAllVideosMenuButton = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        searchByIdMenuItem = new javax.swing.JMenuItem();
        searchByTitleMenuItem = new javax.swing.JMenuItem();
        searchByDirectorMenuItem = new javax.swing.JMenuItem();
        searchByActorMenuItem = new javax.swing.JMenuItem();
        searchByYearMenuItem = new javax.swing.JMenuItem();
        searchByGenreMenuItem = new javax.swing.JMenuItem();
        searchByCountryMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Video Library");
        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));

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

        deleteVideoButton.setText("Delete Video");
        deleteVideoButton.setFocusable(false);
        deleteVideoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteVideoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteVideoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVideoButtonActionPerformed(evt);
            }
        });
        toolBar.add(deleteVideoButton);
        toolBar.add(jSeparator12);
        toolBar.add(jSeparator1);

        getAllVideosButton.setText("Get All Movies");
        getAllVideosButton.setFocusable(false);
        getAllVideosButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getAllVideosButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getAllVideosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAllVideosButtonActionPerformed(evt);
            }
        });
        toolBar.add(getAllVideosButton);
        toolBar.add(jSeparator11);
        toolBar.add(jSeparator13);

        jLabel1.setText("Search by:");
        toolBar.add(jLabel1);
        toolBar.add(jSeparator15);

        searchByIdButton.setText("Id");
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

        searchByTitleButton.setText("Title");
        searchByTitleButton.setFocusable(false);
        searchByTitleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByTitleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchByTitleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByTitleButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchByTitleButton);
        toolBar.add(jSeparator7);

        searchByDirectorButton.setText("Director");
        searchByDirectorButton.setFocusable(false);
        searchByDirectorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByDirectorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchByDirectorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByDirectorButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchByDirectorButton);
        toolBar.add(jSeparator6);

        searchByActorButton.setText("Actor");
        searchByActorButton.setFocusable(false);
        searchByActorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByActorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchByActorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByActorButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchByActorButton);
        toolBar.add(jSeparator5);

        searchByYearButton.setText("Year");
        searchByYearButton.setFocusable(false);
        searchByYearButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByYearButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchByYearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByYearButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchByYearButton);
        toolBar.add(jSeparator4);

        searchByGenreButton.setText("Genre");
        searchByGenreButton.setFocusable(false);
        searchByGenreButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByGenreButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchByGenreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByGenreButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchByGenreButton);
        toolBar.add(jSeparator10);

        searchByCountryButton.setText("Country");
        searchByCountryButton.setFocusable(false);
        searchByCountryButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        searchByCountryButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        searchByCountryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByCountryButtonActionPerformed(evt);
            }
        });
        toolBar.add(searchByCountryButton);
        toolBar.add(jSeparator2);
        toolBar.add(jSeparator14);

        importFromODFButton.setText("Import from ODS");
        importFromODFButton.setFocusable(false);
        importFromODFButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importFromODFButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        importFromODFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFromODFButtonActionPerformed(evt);
            }
        });
        toolBar.add(importFromODFButton);
        toolBar.add(jSeparator3);

        exportToODFButton.setText("Export to ODS");
        exportToODFButton.setFocusable(false);
        exportToODFButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportToODFButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportToODFButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToODFButtonActionPerformed(evt);
            }
        });
        toolBar.add(exportToODFButton);

        videoTable.setModel(new VideoTableModel());
        videoTable.getTableHeader().setReorderingAllowed(false);
        videoTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                videoTableMouseMoved(evt);
            }
        });
        scrollPanel.setViewportView(videoTable);

        staticLabel.setText("Number of videos: ");

        numberOfVideosLabel.setText("0");

        fileMenu.setText("File");

        importFromODFMenuButton.setText("Import from ODF");
        importFromODFMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFromODFMenuButtonActionPerformed(evt);
            }
        });
        fileMenu.add(importFromODFMenuButton);

        exportToODFMenuButton.setText("Export to ODF");
        exportToODFMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToODFMenuButtonActionPerformed(evt);
            }
        });
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
        addVideoMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVideoMenuButtonActionPerformed(evt);
            }
        });
        videoMenu.add(addVideoMenuButton);

        deleteVideoMenuButton.setText("Delete Video");
        deleteVideoMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVideoMenuButtonActionPerformed(evt);
            }
        });
        videoMenu.add(deleteVideoMenuButton);

        getAllVideosMenuButton.setText("Get All Videos");
        getAllVideosMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAllVideosMenuButtonActionPerformed(evt);
            }
        });
        videoMenu.add(getAllVideosMenuButton);

        jMenu1.setText("Search by ...");

        searchByIdMenuItem.setText("Id");
        searchByIdMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByIdMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchByIdMenuItem);

        searchByTitleMenuItem.setText("Title");
        searchByTitleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByTitleMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchByTitleMenuItem);

        searchByDirectorMenuItem.setText("Director");
        searchByDirectorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByDirectorMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchByDirectorMenuItem);

        searchByActorMenuItem.setText("Actor");
        searchByActorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByActorMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchByActorMenuItem);

        searchByYearMenuItem.setText("Year");
        searchByYearMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByYearMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchByYearMenuItem);

        searchByGenreMenuItem.setText("Genre");
        searchByGenreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByGenreMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchByGenreMenuItem);

        searchByCountryMenuItem.setText("Country");
        searchByCountryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByCountryMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(searchByCountryMenuItem);

        videoMenu.add(jMenu1);

        menuBar.add(videoMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(staticLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numberOfVideosLabel)
                .addContainerGap())
            .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                    .addComponent(numberOfVideosLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVideoButtonActionPerformed
        AddVideoDialog dialog = new AddVideoDialog(this, true);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getVideo() != null) {
            if (!videoManagerImpl.addVideo(dialog.getVideo())) {
                JOptionPane.showMessageDialog(this, "XML is not valid", "XML not valid", JOptionPane.ERROR_MESSAGE);
            } else {
                getAllVideosButtonActionPerformed(null);
            }
        }
    }//GEN-LAST:event_addVideoButtonActionPerformed

    private void searchByIdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByIdButtonActionPerformed
        SearchNumberDialog dialog = new SearchNumberDialog(this, true);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setText("id");
        dialog.setVisible(true);
        if (dialog.getResult() != -1) {
            Video video = videoManagerImpl.getVideoById(dialog.getResult());
            if (video != null) {
                tableModel.removeAll();
                tableModel.addVideo(video);
                numberOfVideosLabel.setText("" + tableModel.getRowCount());
            } else {
                JOptionPane.showMessageDialog(this, "Video with id " + dialog.getResult() + " not found", "Not found", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchByIdButtonActionPerformed

    private void exitMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuButtonActionPerformed

    private void videoTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_videoTableMouseMoved
        Point p = evt.getPoint();
        int row = videoTable.rowAtPoint(p);
        int column = videoTable.columnAtPoint(p);
        videoTable.setToolTipText(String.valueOf(tableModel.getValueAt(row, column)));
    }//GEN-LAST:event_videoTableMouseMoved

    private void addVideoMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVideoMenuButtonActionPerformed
        addVideoButtonActionPerformed(evt);
    }//GEN-LAST:event_addVideoMenuButtonActionPerformed

    private void searchByIdMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByIdMenuItemActionPerformed
        searchByIdButtonActionPerformed(evt);
    }//GEN-LAST:event_searchByIdMenuItemActionPerformed

    private void searchByTitleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByTitleMenuItemActionPerformed
        searchByTitleButtonActionPerformed(evt);
    }//GEN-LAST:event_searchByTitleMenuItemActionPerformed

    private void searchByDirectorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByDirectorMenuItemActionPerformed
        searchByDirectorButtonActionPerformed(evt);
    }//GEN-LAST:event_searchByDirectorMenuItemActionPerformed

    private void searchByActorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByActorMenuItemActionPerformed
        searchByActorButtonActionPerformed(evt);
    }//GEN-LAST:event_searchByActorMenuItemActionPerformed

    private void searchByYearMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByYearMenuItemActionPerformed
        searchByYearButtonActionPerformed(evt);
    }//GEN-LAST:event_searchByYearMenuItemActionPerformed

    private void searchByGenreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByGenreMenuItemActionPerformed
        searchByGenreButtonActionPerformed(evt);
    }//GEN-LAST:event_searchByGenreMenuItemActionPerformed

    private void searchByCountryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByCountryMenuItemActionPerformed
        searchByCountryButtonActionPerformed(evt);
    }//GEN-LAST:event_searchByCountryMenuItemActionPerformed

    private void searchByTitleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByTitleButtonActionPerformed
        SearchStringDialog dialog = new SearchStringDialog(this, true);
        dialog.setText("title");
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getResult() != null) {
            GetVideosByWorker worker = new GetVideosByWorker();
            worker.setSearch(dialog.getResult());
            worker.setType("title");
            worker.execute();
        }
    }//GEN-LAST:event_searchByTitleButtonActionPerformed

    private void searchByDirectorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByDirectorButtonActionPerformed
        SearchStringDialog dialog = new SearchStringDialog(this, true);
        dialog.setText("director");
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getResult() != null) {
            GetVideosByWorker worker = new GetVideosByWorker();
            worker.setSearch(dialog.getResult());
            worker.setType("director");
            worker.execute();
        }
    }//GEN-LAST:event_searchByDirectorButtonActionPerformed

    private void searchByActorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByActorButtonActionPerformed
        SearchStringDialog dialog = new SearchStringDialog(this, true);
        dialog.setText("actor");
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getResult() != null) {
            GetVideosByWorker worker = new GetVideosByWorker();
            worker.setSearch(dialog.getResult());
            worker.setType("actor");
            worker.execute();
        }
    }//GEN-LAST:event_searchByActorButtonActionPerformed

    private void searchByYearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByYearButtonActionPerformed
        SearchNumberDialog dialog = new SearchNumberDialog(this, true);
        dialog.setText("year");
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getResult() != -1) {
            GetVideosByWorker worker = new GetVideosByWorker();
            worker.setSearch(dialog.getResult() + "");
            worker.setType("year");
            worker.execute();
        }
    }//GEN-LAST:event_searchByYearButtonActionPerformed

    private void searchByGenreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByGenreButtonActionPerformed
        GenreDialog dialog = new GenreDialog(this, true);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getGenres() != null) {
            GetVideosByGenreWorker worker = new GetVideosByGenreWorker();
            worker.setGenres(dialog.getGenres());
            worker.execute();
        }
    }//GEN-LAST:event_searchByGenreButtonActionPerformed

    private void searchByCountryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByCountryButtonActionPerformed
        SearchStringDialog dialog = new SearchStringDialog(this, true);
        dialog.setText("country");
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getResult() != null) {
            GetVideosByWorker worker = new GetVideosByWorker();
            worker.setSearch(dialog.getResult());
            worker.setType("country");
            worker.execute();
        }
    }//GEN-LAST:event_searchByCountryButtonActionPerformed

    private void deleteVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVideoButtonActionPerformed
        int rows[] = videoTable.getSelectedRows();
        if (rows.length > 0) {
            if (JOptionPane.showConfirmDialog(this, "Really delete selected videos?", "Really delete?", 2) == 0) {
                for (int i = rows.length - 1; i >= 0; i--) {
                    videoManagerImpl.deleteVideo((int) (tableModel.getValueAt(rows[i], 7)));
                    tableModel.removeVideo(rows[i]);
                    tableModel.fireTableDataChanged();
                }
                numberOfVideosLabel.setText("" + tableModel.getRowCount());
            }
        }
    }//GEN-LAST:event_deleteVideoButtonActionPerformed

    private void getAllVideosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAllVideosButtonActionPerformed
        GetAllVideosWorker worker = new GetAllVideosWorker();
        worker.execute();
    }//GEN-LAST:event_getAllVideosButtonActionPerformed

    private void deleteVideoMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVideoMenuButtonActionPerformed
        deleteVideoButtonActionPerformed(evt);
    }//GEN-LAST:event_deleteVideoMenuButtonActionPerformed

    private void getAllVideosMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAllVideosMenuButtonActionPerformed
        getAllVideosButtonActionPerformed(evt);
    }//GEN-LAST:event_getAllVideosMenuButtonActionPerformed

    private void importFromODFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importFromODFButtonActionPerformed
        SearchStringDialog dialog = new SearchStringDialog(this, true);
        dialog.setText("filename");
        dialog.setTitleText("Import from ODF file");
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getResult() != null) {
            ImportManagement im = new ImportManagementImpl();
            im.importFromOdf(new File(dialog.getResult()));
            if (im.getVideos() != null) {
                for (Video v : im.getVideos()) {
                    tableModel.addVideo(v);
                    videoManagerImpl.addVideo(v);
                }
                numberOfVideosLabel.setText("" + tableModel.getRowCount());
            }
        }
    }//GEN-LAST:event_importFromODFButtonActionPerformed

    private void exportToODFButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToODFButtonActionPerformed
        SearchStringDialog dialog = new SearchStringDialog(this, true);
        dialog.setText("filename");
        dialog.setTitleText("Export to ODF file");
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - dialog.getWidth()) / 2;
        final int y = (screenSize.height - dialog.getHeight()) / 2;
        dialog.setLocation(x, y);
        dialog.setVisible(true);
        if (dialog.getResult() != null) {
            ExportManagement em = new ExportManagementImpl();
            em.exportToOdf(new File(dialog.getResult()));
        }
    }//GEN-LAST:event_exportToODFButtonActionPerformed

    private void importFromODFMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importFromODFMenuButtonActionPerformed
        importFromODFButtonActionPerformed(evt);
    }//GEN-LAST:event_importFromODFMenuButtonActionPerformed

    private void exportToODFMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportToODFMenuButtonActionPerformed
        exportToODFButtonActionPerformed(evt);
    }//GEN-LAST:event_exportToODFMenuButtonActionPerformed

    private class GetAllVideosWorker extends SwingWorker<Integer, Video> {
        @Override
        protected Integer doInBackground() throws Exception {
            tableModel.removeAll();
            List<Video> temp = videoManagerImpl.getAllVideos();
            if (temp != null) {
                for (Video v : temp) {
                    publish(v);
                }
                return temp.size();
            }
            return -1;
        }

        protected void process(List<Video> vids) {
            if (vids != null) {
                for (Video v : vids) {
                    tableModel.addVideo(v);
                }
            }
        }

        protected void done() {
            numberOfVideosLabel.setText("" + tableModel.getRowCount());
        }
    }

    private class GetVideosByWorker extends SwingWorker<Integer, Video> {
        private String search, type;

        public void setSearch(String search) {
            this.search = search;
        }
        public void setType(String type) {
            this.type = type;
        }

        @Override
        protected Integer doInBackground() throws Exception {
            tableModel.removeAll();
            List<Video> temp = null;
            switch (type) {
                case "title": {
                    temp = videoManagerImpl.getVideoByTitle(search);
                    break;
                }
                case "actor": {
                    temp = videoManagerImpl.getVideoByActor(search);
                    break;
                }
                case "director": {
                    temp = videoManagerImpl.getVideoByDirector(search);
                    break;
                }
                case "year": {
                    temp = videoManagerImpl.getVideoByYear(Integer.parseInt(search));
                    break;
                }
                case "country": {
                    temp = videoManagerImpl.getVideoByCountry(search);
                    break;
                }
            }
            if (temp != null) {
                for (Video v : temp) {
                    publish(v);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No videos found", "Not found", JOptionPane.WARNING_MESSAGE);
            }
            return 0;
        }

        protected void process(List<Video> vids) {
            if (vids != null) {
                for (Video v : vids) {
                    tableModel.addVideo(v);
                }
            }
        }

        protected void done() {
            numberOfVideosLabel.setText("" + tableModel.getRowCount());
        }
    }

    private class GetVideosByGenreWorker extends SwingWorker<Integer, Video> {

        private List<Genre> genres;

        public void setGenres(List<Genre> genres) {
            this.genres = genres;
        }

        @Override
        protected Integer doInBackground() throws Exception {
            List<Video> temp = new ArrayList<Video>();
            for (Genre g : genres) {
                List<Video> temp2 = videoManagerImpl.getVideoByGenre(g);
                if (temp2 != null) {
                    temp.addAll(temp2);
                }
            }
            
            for (int i = 0; i < temp.size(); i++) {
                for (int j = 0; j < temp.size(); j++) {
                    if (i == j) {
                    } else if (temp.get(j).equals(temp.get(i))) {
                        temp.remove(j);
                    }
                }
            }
            tableModel.removeAll();
            if (temp.size() > 0) {
                for (Video v : temp) {
                    publish(v);
                }
            }
            return -1;
        }

        protected void process(List<Video> vids) {
            if (vids != null) {
                for (Video v : vids) {
                    tableModel.addVideo(v);
                }
            }
        }

        protected void done() {
            numberOfVideosLabel.setText("" + tableModel.getRowCount());
        }
    }

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
    private javax.swing.JButton deleteVideoButton;
    private javax.swing.JMenuItem deleteVideoMenuButton;
    private javax.swing.JMenuItem exitMenuButton;
    private javax.swing.JButton exportToODFButton;
    private javax.swing.JMenuItem exportToODFMenuButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton getAllVideosButton;
    private javax.swing.JMenuItem getAllVideosMenuButton;
    private javax.swing.JButton importFromODFButton;
    private javax.swing.JMenuItem importFromODFMenuButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JToolBar.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
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
    private javax.swing.JMenuItem searchByActorMenuItem;
    private javax.swing.JButton searchByCountryButton;
    private javax.swing.JMenuItem searchByCountryMenuItem;
    private javax.swing.JButton searchByDirectorButton;
    private javax.swing.JMenuItem searchByDirectorMenuItem;
    private javax.swing.JButton searchByGenreButton;
    private javax.swing.JMenuItem searchByGenreMenuItem;
    private javax.swing.JButton searchByIdButton;
    private javax.swing.JMenuItem searchByIdMenuItem;
    private javax.swing.JButton searchByTitleButton;
    private javax.swing.JMenuItem searchByTitleMenuItem;
    private javax.swing.JButton searchByYearButton;
    private javax.swing.JMenuItem searchByYearMenuItem;
    private javax.swing.JLabel staticLabel;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JMenu videoMenu;
    private javax.swing.JTable videoTable;
    // End of variables declaration//GEN-END:variables
}
