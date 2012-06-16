/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Andrej
 */
public class AddVideoDialog extends javax.swing.JDialog {
    private ButtonGroup bg = new ButtonGroup();
    private Video video = null;
    /**
     * Creates new form AddVideoDialog
     */
    public AddVideoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        bg.add(manualAddRadioButton);
        bg.add(imdbImportRadioButton);
        initList();
    }

    public void initList() {
        for (Genre g: Genre.values()) {
            ((DefaultListModel)(genresListLeft.getModel())).addElement(g);
        }
    }
    
    public Video getVideo() {
        return video;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        directorsLabel = new javax.swing.JLabel();
        directorsTextField = new javax.swing.JTextField();
        actorsLabel = new javax.swing.JLabel();
        actorsTextField = new javax.swing.JTextField();
        yearLabel = new javax.swing.JLabel();
        yearComboBox = new javax.swing.JComboBox();
        countryLabel = new javax.swing.JLabel();
        countryTextField = new javax.swing.JTextField();
        genreScrollPanelRight = new javax.swing.JScrollPane();
        genresListRight = new javax.swing.JList();
        genresLabel = new javax.swing.JLabel();
        genreScrollPanelLeft = new javax.swing.JScrollPane();
        genresListLeft = new javax.swing.JList();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ratingComboBox = new javax.swing.JComboBox();
        manualAddRadioButton = new javax.swing.JRadioButton();
        imdbImportRadioButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add movie");
        setBackground(java.awt.Color.white);

        titleLabel.setText("Title:");

        directorsLabel.setText("Director(s):");

        directorsTextField.setToolTipText("More directors separate with comma");

        actorsLabel.setText("Actor(s):");

        actorsTextField.setToolTipText("More authors separate with comma");

        yearLabel.setText("Year:");

        yearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1800", "1801", "1802", "1803", "1804", "1805", "1806", "1807", "1808", "1809", "1810", "1811", "1812", "1813", "1814", "1815", "1816", "1817", "1818", "1819", "1820", "1821", "1822", "1823", "1824", "1825", "1826", "1827", "1828", "1829", "1830", "1831", "1832", "1833", "1834", "1835", "1836", "1837", "1838", "1839", "1840", "1841", "1842", "1843", "1844", "1845", "1846", "1847", "1848", "1849", "1850", "1851", "1852", "1853", "1854", "1855", "1856", "1857", "1858", "1859", "1860", "1861", "1862", "1863", "1864", "1865", "1866", "1867", "1868", "1869", "1870", "1871", "1872", "1873", "1874", "1875", "1876", "1877", "1878", "1879", "1880", "1881", "1882", "1883", "1884", "1885", "1886", "1887", "1888", "1889", "1890", "1891", "1892", "1893", "1894", "1895", "1896", "1897", "1898", "1899", "1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012" }));

        countryLabel.setText("Country:");

        countryTextField.setToolTipText("More countries separate with comma");

        genresListRight.setModel(new DefaultListModel());
        genresListRight.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        genresListRight.setVisibleRowCount(5);
        genresListRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genresListRightMouseClicked(evt);
            }
        });
        genreScrollPanelRight.setViewportView(genresListRight);

        genresLabel.setText("Genres: (click to add)");

        genresListLeft.setModel(new DefaultListModel());
        genresListLeft.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        genresListLeft.setVerifyInputWhenFocusTarget(false);
        genresListLeft.setVisibleRowCount(5);
        genresListLeft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genresListLeftMouseClicked(evt);
            }
        });
        genreScrollPanelLeft.setViewportView(genresListLeft);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Rating:");

        ratingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        manualAddRadioButton.setSelected(true);
        manualAddRadioButton.setText("Manual add");
        manualAddRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manualAddRadioButtonActionPerformed(evt);
            }
        });

        imdbImportRadioButton.setText("Import from IMDB");
        imdbImportRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imdbImportRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(okButton)
                        .addGap(133, 133, 133)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(titleLabel)
                                    .addComponent(directorsLabel)
                                    .addComponent(actorsLabel)
                                    .addComponent(yearLabel)
                                    .addComponent(countryLabel)
                                    .addComponent(jLabel1))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ratingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(directorsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(actorsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(genreScrollPanelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(genreScrollPanelRight, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(genresLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(manualAddRadioButton)
                        .addGap(27, 27, 27)
                        .addComponent(imdbImportRadioButton)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manualAddRadioButton)
                    .addComponent(imdbImportRadioButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(directorsLabel)
                    .addComponent(directorsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(actorsLabel)
                    .addComponent(actorsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(yearLabel)
                    .addComponent(yearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(countryLabel)
                    .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ratingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(genresLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(genreScrollPanelRight, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(genreScrollPanelLeft))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (checkValues()) {
            if (imdbImportRadioButton.isSelected()) {
                ImdbDownloader imdbd = new ImdbDownloaderImpl();
                if (imdbd.download(titleTextField.getText())) {
                    video = imdbd.getVideo();
                    video.setCountries(getTextField(countryTextField));
                    this.dispose();
                } 
                else {
                    JOptionPane.showMessageDialog(this, "No video with title " + titleTextField.getText() + "found on IMDB","Not found", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
               video = new Video();
               video.setTitle(getTitle());
               video.setDirectors(getTextField(directorsTextField));
               video.setActors(getTextField(actorsTextField));
               video.setCountries(getTextField(countryTextField));
               video.setYear(getInt(yearComboBox));
               video.setRating(getInt(ratingComboBox));
               video.setGenres(getGenres());
               this.dispose();
            }
            
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void genresListLeftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genresListLeftMouseClicked
        int index = genresListLeft.getSelectedIndex();
        if (index != -1) {
            Genre g = (Genre) ((DefaultListModel)(genresListLeft.getModel())).getElementAt(index);
            ((DefaultListModel)(genresListLeft.getModel())).remove(index);
            ((DefaultListModel)(genresListRight.getModel())).addElement(g); 
        }
    }//GEN-LAST:event_genresListLeftMouseClicked

    private void genresListRightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genresListRightMouseClicked
        int index = genresListRight.getSelectedIndex();
        if (index != -1) {
            Genre g = (Genre) ((DefaultListModel)(genresListRight.getModel())).getElementAt(index);
            ((DefaultListModel)(genresListRight.getModel())).remove(index);
            ((DefaultListModel)(genresListLeft.getModel())).addElement(g); 
        }
    }//GEN-LAST:event_genresListRightMouseClicked

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void manualAddRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manualAddRadioButtonActionPerformed
        changeState(true);
    }//GEN-LAST:event_manualAddRadioButtonActionPerformed

    private void imdbImportRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imdbImportRadioButtonActionPerformed
        changeState(false);
    }//GEN-LAST:event_imdbImportRadioButtonActionPerformed

    public void changeState(boolean state) {
        directorsTextField.setEnabled(state);
        actorsTextField.setEnabled(state);
        yearComboBox.setEnabled(state);
        ratingComboBox.setEnabled(state);
        genresListLeft.setEnabled(state);
        genresListRight.setEnabled(state);
    }
    
    /**
     * @param args the command line arguments
     */
    public boolean checkValues() {  
        if (imdbImportRadioButton.isSelected()) {
            if (titleTextField.getText().equals("")) {
               JOptionPane.showMessageDialog(this, "Title field is empty","No title", JOptionPane.ERROR_MESSAGE);
               return false;
            }
            if (countryTextField.getText().equals("")) {
               JOptionPane.showMessageDialog(this, "Countries field is empty","No coutries", JOptionPane.ERROR_MESSAGE);
               return false;
            }
            return true;
        }
        else {
           if (titleTextField.getText().equals("")) {
               JOptionPane.showMessageDialog(this, "Empty title","No title", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if (directorsTextField.getText().equals("")) {
               JOptionPane.showMessageDialog(this, "Directors field is empty","No directors", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if (actorsTextField.getText().equals("")) {
               JOptionPane.showMessageDialog(this, "Actors field is empty","No actors", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if (countryTextField.getText().equals("")) {
               JOptionPane.showMessageDialog(this, "Countries field is empty","No coutries", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if (genresListRight.getModel().getSize() == 0) {
               JOptionPane.showMessageDialog(this, "No genres selected","No genres", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if (getTextField(directorsTextField).isEmpty()) {
               JOptionPane.showMessageDialog(this, "Directors field is not valid","No directors", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if (getTextField(actorsTextField).isEmpty()) {
               JOptionPane.showMessageDialog(this, "Actors field is not valid","No actors", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           if (getTextField(countryTextField).isEmpty()) {
               JOptionPane.showMessageDialog(this, "Countries field is not valid","No countries", JOptionPane.ERROR_MESSAGE);
               return false;
           }
           return true;
        }
    }
    
    public String getTitle() {
        return titleTextField.getText();
    }
    
    public List<Genre> getGenres() {
        List<Genre> temp = new ArrayList<Genre>();
        for (int i = 0; i < genresListRight.getModel().getSize(); i++) {
            Genre g = (Genre)((DefaultListModel)genresListRight.getModel()).get(i);
            temp.add(g);
        }
        return temp;
    }
    public List<String> getTextField(JTextField tf) {
        List<String> temp = new ArrayList<String>();
        String input = tf.getText();
        String parse[] = input.split(",");
        List<String> parsed = new ArrayList<String>();
        for (int i = 0; i < parse.length; i++) {
            parse[i] = parse[i].trim();
            if (!parse[i].equals("")) parsed.add(parse[i]);
        }
        temp.addAll(parsed);
        return Collections.unmodifiableList(temp);
    }
    
    public int getInt(JComboBox cb) {
        int result = 0;
        try {
            result = Integer.parseInt((String)cb.getSelectedItem());
        }
        catch (NumberFormatException ex) {}
        return result;
    }
    
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
            java.util.logging.Logger.getLogger(AddVideoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddVideoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddVideoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddVideoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                AddVideoDialog dialog = new AddVideoDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actorsLabel;
    private javax.swing.JTextField actorsTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JTextField countryTextField;
    private javax.swing.JLabel directorsLabel;
    private javax.swing.JTextField directorsTextField;
    private javax.swing.JScrollPane genreScrollPanelLeft;
    private javax.swing.JScrollPane genreScrollPanelRight;
    private javax.swing.JLabel genresLabel;
    private javax.swing.JList genresListLeft;
    private javax.swing.JList genresListRight;
    private javax.swing.JRadioButton imdbImportRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton manualAddRadioButton;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox ratingComboBox;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    private javax.swing.JComboBox yearComboBox;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration//GEN-END:variables
}
