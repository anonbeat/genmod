/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.awt.Toolkit;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.Document;



/**
 * Module Class Table Model 
 * @author Juan Rios anonbeat@gmail.com
 */
class ClassesTableModel extends AbstractTableModel implements Reorderable {
    // Reference to the main form
    private MainForm mainForm;
    
    // Columns list to show
    private static final String columns[] = {
        "Name",
    };
    
    /**
     * Constructor that receives the reference to the main form
     * @param alumnos
     */
    ClassesTableModel( MainForm mainForm )
    {
        // Call the default constructor
        super();
        // save the reference
        this.mainForm = mainForm;
    }
    
    /**
     * Get the name of the column at position col
     * @param col is the position of the column to get
     * @return the name of the column
     */
    @Override
    public String getColumnName( int col ) { return columns[ col ]; }
    
    /**
     * 
     * @return the number of rows
     */
    @Override
    public int getRowCount() { return mainForm.getClasses().size(); }
    
    /**
     * 
     * @return the number of columns
     */
    @Override
    public int getColumnCount() { return columns.length; }
    
    /**
     * Get every data
     * @param row 
     * @param col 
     * @return a string containing the data to show at pos row, col
     */
    @Override
    public Object getValueAt( int row, int col ) {
        try {
            ModClass modClass = mainForm.getClasses().get( row );
            switch( col )
            {
                case 0 : return modClass.getName();
            }
        }
        catch( Exception e )
        {
        }
        return "";
    }
    
    /**
     * Direct edit not allowed
     * @param row 
     * @param col 
     * @return true to tell editing is allowed and false for not
     */
    @Override
    public boolean isCellEditable( int row, int col ) { return false; }
    
    /**
     * Reorder the table. Called from the drag and drop implementation
     * @param fromIndex
     * @param toIndex 
     */
    @Override
    public void reorder( int fromIndex, int toIndex ) 
    {
        //System.out.println( "Reorder " + fromIndex + " -> " + toIndex );
        mainForm.classesReorder( fromIndex, toIndex );
    }
}

/**
 *
 * @author Juan Rios
 */
public class MainForm extends javax.swing.JFrame {

    public static final String APP_NAME = "GenMod";
    public static final String APP_VERSION = "0.1";
    //
    private Module module;
    private ArrayList<ModClass> classes;
    private ClassesTableModel classesModel;
    private int selectedClass;
    //
    private boolean hasChanged;
    private String  fileName;
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        // Initialize the vars
        hasChanged = false;
        fileName = "";
        selectedClass = -1;
        module = new Module();
        classes = ( ArrayList<ModClass> ) module.getClasses().clone();
        
        // Create the components
        initComponents();
        // Set the app icon
        setFrameIcon();
        
        // Connect the events
        initEvents();
        // Get a reference to the table model to fire data changes
        classesModel = ( ClassesTableModel ) claTable.getModel();
        // Get the selection changed event to enable or disable editing buttons
        claTable.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionChanged();
            }
        } );
        // Set the drop behaivor
        claTable.setTransferHandler( new TableRowTransferHandler( claTable ) );

        // Center the window on screen.
        setLocationRelativeTo( null );
    }

    /**
     * Set the main frame icon.
     */
    private void setFrameIcon()
    {
       setIconImage( Toolkit.getDefaultToolkit().getImage( getClass().getResource( "genmod.png" ) ) ); 
    }
    
    /**
     * Methos called when the module class selection has changed
     */
    void selectionChanged()
    {
        boolean enabled = ( claTable.getSelectedRow() != -1 );
        delClaButton.setEnabled( enabled );
        ediClaButton.setEnabled( enabled );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        newButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        savButton = new javax.swing.JButton();
        sasButton = new javax.swing.JButton();
        aboButton = new javax.swing.JButton();
        exiButton = new javax.swing.JButton();
        genButton = new javax.swing.JButton();
        proPanel = new javax.swing.JPanel();
        namLabel = new javax.swing.JLabel();
        autLabel = new javax.swing.JLabel();
        webLabel = new javax.swing.JLabel();
        depLabel = new javax.swing.JLabel();
        verLabel = new javax.swing.JLabel();
        catLabel = new javax.swing.JLabel();
        insCheckBox = new javax.swing.JCheckBox();
        verTextField = new javax.swing.JTextField();
        webTextField = new javax.swing.JTextField();
        depTextField = new javax.swing.JTextField();
        namTextField = new javax.swing.JTextField();
        autTextField = new javax.swing.JTextField();
        catTextField = new javax.swing.JTextField();
        desScrollPane = new javax.swing.JScrollPane();
        desTextArea = new javax.swing.JTextArea();
        desLabel = new javax.swing.JLabel();
        claPanel = new javax.swing.JPanel();
        claScrollPane = new javax.swing.JScrollPane();
        claTable = new javax.swing.JTable();
        addClaButton = new javax.swing.JButton();
        delClaButton = new javax.swing.JButton();
        ediClaButton = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GenMod 0.1Beta");
        setMinimumSize(new java.awt.Dimension(700, 350));
        setName("MainFrame"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        newButton.setText("New");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        savButton.setText("Save");
        savButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savButtonActionPerformed(evt);
            }
        });

        sasButton.setText("Save As");
        sasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sasButtonActionPerformed(evt);
            }
        });

        aboButton.setText("About");
        aboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboButtonActionPerformed(evt);
            }
        });

        exiButton.setText("Exit");
        exiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exiButtonActionPerformed(evt);
            }
        });

        genButton.setText("Generate");
        genButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genButtonActionPerformed(evt);
            }
        });

        proPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Properties"));

        namLabel.setText("Name:");

        autLabel.setText("Author:");

        webLabel.setText("Website:");

        depLabel.setText("Depends:");

        verLabel.setText("Version:");

        catLabel.setText("Category:");

        insCheckBox.setText("Installable");
        insCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                insCheckBoxItemStateChanged(evt);
            }
        });

        desTextArea.setColumns(20);
        desTextArea.setRows(5);
        desScrollPane.setViewportView(desTextArea);

        desLabel.setText("Description:");

        javax.swing.GroupLayout proPanelLayout = new javax.swing.GroupLayout(proPanel);
        proPanel.setLayout(proPanelLayout);
        proPanelLayout.setHorizontalGroup(
            proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(proPanelLayout.createSequentialGroup()
                        .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(autLabel)
                            .addComponent(catLabel)
                            .addComponent(namLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(autTextField)
                            .addComponent(namTextField)
                            .addComponent(catTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(webLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(depLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(verTextField)
                            .addComponent(webTextField)
                            .addComponent(depTextField)))
                    .addGroup(proPanelLayout.createSequentialGroup()
                        .addComponent(desLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insCheckBox))
                    .addComponent(desScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                .addContainerGap())
        );
        proPanelLayout.setVerticalGroup(
            proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proPanelLayout.createSequentialGroup()
                .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namLabel)
                    .addComponent(verLabel)
                    .addComponent(verTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(autLabel)
                    .addComponent(webLabel)
                    .addComponent(webTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(catLabel)
                    .addComponent(depLabel)
                    .addComponent(depTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(proPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insCheckBox)
                    .addComponent(desLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        claPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Classes"));

        claTable.setModel( new ClassesTableModel( this ));
        claTable.setDragEnabled(true);
        claTable.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        claTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        claTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                claTableMouseClicked(evt);
            }
        });
        claScrollPane.setViewportView(claTable);

        addClaButton.setText("+");
        addClaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClaButtonActionPerformed(evt);
            }
        });

        delClaButton.setText("-");
        delClaButton.setEnabled(false);
        delClaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delClaButtonActionPerformed(evt);
            }
        });

        ediClaButton.setText("Edit");
        ediClaButton.setEnabled(false);
        ediClaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ediClaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout claPanelLayout = new javax.swing.GroupLayout(claPanel);
        claPanel.setLayout(claPanelLayout);
        claPanelLayout.setHorizontalGroup(
            claPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(claPanelLayout.createSequentialGroup()
                .addComponent(claScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(claPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delClaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addClaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ediClaButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        claPanelLayout.setVerticalGroup(
            claPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(claPanelLayout.createSequentialGroup()
                .addComponent(addClaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delClaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ediClaButton)
                .addGap(0, 13, Short.MAX_VALUE))
            .addComponent(claScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(claPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loadButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(savButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sasButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aboButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exiButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(genButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(savButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sasButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(genButton))
                    .addComponent(proPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(aboButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exiButton))
                    .addComponent(claPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("OpenERP Module Generator");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Used by the table model to get the reference to the classes list
     * @return 
     */
    public ArrayList<ModClass> getClasses()
    {
        return classes;
    }
    
    /**
     * Used by the table model to reorder one row from the table using dnd
     * @param fromIndex
     * @param toIndex 
     */
    public void classesReorder( int fromIndex, int toIndex )
    {
        // If we are going to move to a different position
        if( fromIndex != toIndex )
        {
            // Remove the from position from the list
            ModClass modClass = classes.remove( fromIndex );
            // As we removed an item if the to position was higher we need to decrement it to 
            // point to the new location without the from item
            if( toIndex > fromIndex )
            {
                toIndex--;
            }
            classes.add( toIndex, modClass );
            hasChanged = true;
        }
    }
    
    /**
     * Alerts the user for the unsaved changes to be saved.
     * @return false to cancell operation
     *          true to continue
     */
    private boolean checkSave()
    {
        // Comprobamos si debemos salvar
        if( hasChanged )
        {
            int result = JOptionPane.showConfirmDialog( this, 
                    "There are changes that need to be saved.\nDo you want to save it now?",
                    "Atention", 
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE );
            if( result == JOptionPane.YES_OPTION )
            {
                saveProject();
                return true;
            }
            else if( result == JOptionPane.NO_OPTION )
            {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * 
     * @param evt 
     */
    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        if( checkSave() )
        {
            module = new Module();
            classes.clear();
            setValues();
            classesModel.fireTableDataChanged();
            //
            hasChanged = false;
            fileName = "";
        }
    }//GEN-LAST:event_newButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void exiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exiButtonActionPerformed
        if( checkSave() )
        {
            System.exit( 0 );
        }
    }//GEN-LAST:event_exiButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void addClaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClaButtonActionPerformed
        ModClass modClass = new ModClass();
        ModClassDialog classDlg = new ModClassDialog( this, modClass );
        if( classDlg.isAccepted() )
        {
            classes.add( modClass );
            classesModel.fireTableRowsInserted( classes.size(), classes.size() );
            hasChanged = true;
        }
    }//GEN-LAST:event_addClaButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void delClaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delClaButtonActionPerformed
        int row = claTable.getSelectedRow();
        if( row != -1 )
        {
            if( JOptionPane.showConfirmDialog( this, 
                    "¿Are you sure to delete the selected class?", 
                    "Atention", 
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION )
            {
                classes.remove( row );
                classesModel.fireTableRowsDeleted( row, row );
                hasChanged = true;
            }
        }
    }//GEN-LAST:event_delClaButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void ediClaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ediClaButtonActionPerformed
        int row = claTable.getSelectedRow();
        if( row != -1 )
        {
            ModClass modClass = classes.get( row );
            ModClassDialog classDlg = new ModClassDialog( this, modClass );
            if( classDlg.isAccepted() )
            {
                classesModel.fireTableRowsUpdated( row, row );
                hasChanged = true;
            }
        }
    }//GEN-LAST:event_ediClaButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void claTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_claTableMouseClicked
        if( evt.getClickCount() == 2 ) // double clicked
        {
            ediClaButtonActionPerformed( null );
        }
    }//GEN-LAST:event_claTableMouseClicked

    /**
     * 
     * @param evt 
     */
    private void aboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboButtonActionPerformed
        JOptionPane.showMessageDialog( this, 
                "<html><body><center>By <b>Juan Rios</b><br/>" + 
                "anonbeat@gmail.com<br/><br/>" +
                APP_NAME + "&nbsp;" + APP_VERSION + "&nbsp;2013<br/><br/></center></body></html>" );
    }//GEN-LAST:event_aboButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exiButtonActionPerformed( null );
    }//GEN-LAST:event_formWindowClosing

    /**
     * 
     * @param evt 
     */
    private void savButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savButtonActionPerformed
        saveProject();
    }//GEN-LAST:event_savButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void sasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sasButtonActionPerformed
        saveProjectAs();
    }//GEN-LAST:event_sasButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void genButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genButtonActionPerformed
        // Ask the user the file to save to
        JFileChooser selFile = new JFileChooser( "." );
        int retval = selFile.showSaveDialog( this );
        if( retval == JFileChooser.APPROVE_OPTION ) 
        {
            // We create the File object to get the file name 
            File file = selFile.getSelectedFile();

            // If the file exists ask if we are allowed to overwrite it
            if( file.exists() )
            {
                int opcion = JOptionPane.showConfirmDialog( this, 
                        "The file already exists, Do you want to replace it?",
                        "Atention", 
                        JOptionPane.YES_NO_CANCEL_OPTION );

                // If the user cancel the operation just return
                if( opcion == JOptionPane.CANCEL_OPTION )
                {
                    return;
                }
                else if( opcion == JOptionPane.NO_OPTION )
                {
                    // The user selected to not overwrite the file so
                    // ask again for the file to write to
                    // We can do this by creating a loop but instead of that 
                    // just call the function again 
                    genButtonActionPerformed( evt );
                    return;
                }
            }
            // Here goes the generation code
            genModule( file );
        }
    }//GEN-LAST:event_genButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        if( checkSave() )
        {
            loadProject();
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void insCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_insCheckBoxItemStateChanged
        hasChanged = true;
    }//GEN-LAST:event_insCheckBoxItemStateChanged

    /**
     * Copy the editor values to the module object
     */
    private void getValues()
    {
        module.setName( namTextField.getText() );
        module.setVersion( verTextField.getText() );
        module.setAuthor( autTextField.getText() );
        module.setWeb( webTextField.getText() );
        module.setCategory( catTextField.getText() );
        module.setDepends( depTextField.getText() );
        module.setDescription( desTextArea.getText() );
        module.setInstallable( insCheckBox.isSelected() );
        module.setClasses( classes );
    }
    
    /**
     * Set the editor values from the module object
     */
    private void setValues()
    {
        namTextField.setText( module.getName() );
        verTextField.setText( module.getVersion() );
        autTextField.setText( module.getAuthor() );
        webTextField.setText( module.getWeb() );
        catTextField.setText( module.getCategory() );
        depTextField.setText( module.getDepends() );
        desTextArea.setText( module.getDescription() );
        insCheckBox.setSelected( module.isInstallable() );
        classes = ( ArrayList<ModClass> ) module.getClasses().clone();
        classesModel.fireTableDataChanged();
    }
    
    /**
     * 
     * @param document 
     */
    private void addChangedListener( Document document )
    {
        // Listen for changes in the text
        document.addDocumentListener( new DocumentListener() {
            public void changed() {
                hasChanged = true;
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
        });
    }
    
    /**
     * 
     * @param textField 
     */
    private void addChangedListener( JTextField textField )
    {
        addChangedListener( textField.getDocument() );
    }

    /**
     * 
     * @param textArea 
     */
    private void addChangedListener( JTextArea textArea )
    {
        addChangedListener( textArea.getDocument() );
    }

    /**
     * 
     */
    private void initEvents()
    {
        addChangedListener( namTextField );
        addChangedListener( verTextField );
        addChangedListener( autTextField );
        addChangedListener( webTextField );
        addChangedListener( catTextField );
        addChangedListener( depTextField );
        addChangedListener( desTextArea );
    }

    /**
     * Save the module to the file filename. If not file yet ask user to pick one
     */
    private void saveProject() {
        if( fileName.isEmpty() )
        {
            saveProjectAs();
        }
        else
        {
            try {
                FileOutputStream fos = new FileOutputStream( fileName );
                try ( XMLEncoder encoder = new XMLEncoder( fos ) ) 
                {
                    getValues();
                    encoder.writeObject( module );
                    hasChanged = false;
                    //
                    JOptionPane.showMessageDialog( this,
                            "Module saved correctly", 
                            "Information", 
                            JOptionPane.INFORMATION_MESSAGE );
                } 
                fos.close();
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, e.toString(), "Exception", JOptionPane.ERROR_MESSAGE );
            }
        }
    }

    /**
     * 
     */
    private void saveProjectAs()
    {
        // Ask the user the file to save to
        JFileChooser selFile = new JFileChooser( "." );
        int retval = selFile.showSaveDialog( this );
        if( retval == JFileChooser.APPROVE_OPTION ) 
        {
            // We create the File object to get the file name 
            File f = selFile.getSelectedFile();

            // Check if we are saving to the actual file or not
            if( !fileName.equals( f.getAbsolutePath() ) )
            {
                // If the file exists ask if we are allowed to overwrite it
                if( f.exists() )
                {
                    int opcion = JOptionPane.showConfirmDialog( this, 
                            "The file already exists, Do you want to replace it?",
                            "Atention", 
                            JOptionPane.YES_NO_CANCEL_OPTION );

                    // If the user cancel the operation just return
                    if( opcion == JOptionPane.CANCEL_OPTION )
                    {
                        return;
                    }
                    else if( opcion == JOptionPane.NO_OPTION )
                    {
                        // The user selected to not overwrite the file so
                        // ask again for the file to write to
                        // We can do this by creating a loop but instead of that 
                        // just call the function again 
                        saveProjectAs();
                        return;
                    }
                }
                
                // Get the name from the selected file
                fileName = f.getAbsolutePath();
            }

            // Now we can call to really save the project
            saveProject();
        }
    }
    
    /**
     * Loads the module from the xml file
     */
    private void loadProject()
    {
        // Ask the user for the file to load
        JFileChooser seleccionaArchivo = new JFileChooser( "." );
        int retval = seleccionaArchivo.showOpenDialog( this );
        if( retval == JFileChooser.APPROVE_OPTION ) 
        {
            // Get the selected file as a File object to get the full path
            File file = seleccionaArchivo.getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream( file );
                try ( XMLDecoder decoder = new XMLDecoder( fis ) ) 
                {
                    module = ( Module ) decoder.readObject();
                    setValues();
                    
                    // Save the path to save to that file
                    fileName = file.getAbsolutePath();
                    hasChanged = false;
                } 
            } 
            catch( Exception e ) 
            {
                JOptionPane.showMessageDialog( this, e.toString(), "Exception", JOptionPane.ERROR_MESSAGE );
            }
        }    
    }

    /**
     * Generate the module 
     * @param file 
     */
    private void genModule( File file )
    {
        ModGenerator generator = new ModGenerator( module );
        if( generator.generate( file.getAbsolutePath() ) )
        {
            JOptionPane.showMessageDialog( this, 
                    "Module generated correctly", 
                    "Information", 
                    JOptionPane.INFORMATION_MESSAGE );
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible( true );
            } }
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboButton;
    private javax.swing.JButton addClaButton;
    private javax.swing.JLabel autLabel;
    private javax.swing.JTextField autTextField;
    private javax.swing.JLabel catLabel;
    private javax.swing.JTextField catTextField;
    private javax.swing.JPanel claPanel;
    private javax.swing.JScrollPane claScrollPane;
    private javax.swing.JTable claTable;
    private javax.swing.JButton delClaButton;
    private javax.swing.JLabel depLabel;
    private javax.swing.JTextField depTextField;
    private javax.swing.JLabel desLabel;
    private javax.swing.JScrollPane desScrollPane;
    private javax.swing.JTextArea desTextArea;
    private javax.swing.JButton ediClaButton;
    private javax.swing.JButton exiButton;
    private javax.swing.JButton genButton;
    private javax.swing.JCheckBox insCheckBox;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton loadButton;
    private javax.swing.JLabel namLabel;
    private javax.swing.JTextField namTextField;
    private javax.swing.JButton newButton;
    private javax.swing.JPanel proPanel;
    private javax.swing.JButton sasButton;
    private javax.swing.JButton savButton;
    private javax.swing.JLabel verLabel;
    private javax.swing.JTextField verTextField;
    private javax.swing.JLabel webLabel;
    private javax.swing.JTextField webTextField;
    // End of variables declaration//GEN-END:variables

}
