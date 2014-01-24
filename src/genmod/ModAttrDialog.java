/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;


/**
 * Clase to show the attribute selections
 * @author Juan Rios anonbeat@gmail.com
 */
class SelectionTableModel extends AbstractTableModel implements Reorderable {
    // Reference to the selections
    private ArrayList<AttrSelection> selections;

    // The column list to show
    private static final String columns[] = {
        "Value",
        "Name"
    };
    
    /**
     * Constractor that receives the selections reference
     * @param alumnos
     */
    SelectionTableModel( ArrayList<AttrSelection> selections )
    {
        // Call the default constructor
        super();
        // Save the reference received
        this.selections = selections;
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
     * @return the number of selections
     */
    @Override
    public int getRowCount() { return selections.size(); }
    
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
            AttrSelection selection = selections.get( row );
            switch( col )
            {
                case 0 : return selection.getValue();
                case 1 : return selection.getName();
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
        // If we are going to move to a different position
        if( fromIndex != toIndex )
        {
            // Remove the from position from the list
            AttrSelection selection = selections.remove( fromIndex );
            // As we removed an item if the to position was higher we need to decrement it to 
            // point to the new location without the from item
            if( toIndex > fromIndex )
            {
                toIndex--;
            }
            selections.add( toIndex, selection );
        }
    }
}

/**
 * Dialog to edit the class attributes
 * @author Juan Rios
 */
public class ModAttrDialog extends javax.swing.JDialog {

    private boolean accepted;
    private ModAttr modAttr;
    private ArrayList<AttrSelection> selections;
    private SelectionTableModel selTableModel;
    
    /**
     * Creates new form ModAttrDialog
     */
    public ModAttrDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        accepted = false;
        
        initComponents();
    }

    /**
     * 
     * @param parent
     * @param modAttr 
     */
    public ModAttrDialog(java.awt.Frame parent, ModAttr modAttr ) {
        super(parent, true );
        //
        this.modAttr = modAttr;
        accepted = false;
        selections = ( ArrayList<AttrSelection> ) modAttr.getSelections().clone();
        //
        initComponents();
        selPanel.setVisible( false );
        relPanel.setVisible( false );
        this.pack();
        //
        selTableModel = ( SelectionTableModel ) selTable.getModel();
        // Fill the fields with the attribute data
        namTextField.setText( modAttr.getName() );
        fieTextField.setText( modAttr.getFieldName() );
        typComboBox.setSelectedIndex( modAttr.getType() );
        sizSpinner.setValue( modAttr.getSize() );
        desTextField.setText( modAttr.getDescription() );
        strTextField.setText( modAttr.getString() );
        defTextField.setText( modAttr.getInitVal() );
        traCheckBox.setSelected( modAttr.isTranslate() );
        reqCheckBox.setSelected( modAttr.isRequired() );
        reaCheckBox.setSelected( modAttr.isReadOnly() );
        relOtNaTextField.setText( modAttr.getRelOthName() );
        relReIdTextField.setText( modAttr.getRelRelId() );
        relObIdTextField.setText( modAttr.getRelObjId() );
        relOtIdTextField.setText( modAttr.getRelOthId() );
        //
        selTable.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionChanged();
            }
        } );
        selTable.setTransferHandler( new TableRowTransferHandler( selTable ) );
        //
        setLocationRelativeTo( parent );
        //
        setVisible( true );
    }

    /**
     * 
     */
    void selectionChanged()
    {
        boolean isEnabled = ( typComboBox.getSelectedIndex() == ModAttr.ATTR_TYPE_SELECTION );
        int row = selTable.getSelectedRow();
        delSelButton.setEnabled( isEnabled && ( row != -1 ) );
        ediSelButton.setEnabled( isEnabled && ( row != -1 ) );
    }
    
    /**
     * @return the accepted
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        namLabel = new javax.swing.JLabel();
        namTextField = new javax.swing.JTextField();
        fieLabel = new javax.swing.JLabel();
        fieTextField = new javax.swing.JTextField();
        typLabel = new javax.swing.JLabel();
        typComboBox = new javax.swing.JComboBox();
        sizLabel = new javax.swing.JLabel();
        sizSpinner = new javax.swing.JSpinner();
        desLabel = new javax.swing.JLabel();
        desTextField = new javax.swing.JTextField();
        strLabel = new javax.swing.JLabel();
        strTextField = new javax.swing.JTextField();
        defLabel = new javax.swing.JLabel();
        defTextField = new javax.swing.JTextField();
        traCheckBox = new javax.swing.JCheckBox();
        reqCheckBox = new javax.swing.JCheckBox();
        reaCheckBox = new javax.swing.JCheckBox();
        selPanel = new javax.swing.JPanel();
        selScrollPane = new javax.swing.JScrollPane();
        selTable = new javax.swing.JTable();
        addSelButton = new javax.swing.JButton();
        delSelButton = new javax.swing.JButton();
        ediSelButton = new javax.swing.JButton();
        relPanel = new javax.swing.JPanel();
        relOtNaLabel = new javax.swing.JLabel();
        relOtNaTextField = new javax.swing.JTextField();
        relReIdLabel = new javax.swing.JLabel();
        relReIdTextField = new javax.swing.JTextField();
        relObIdLabel = new javax.swing.JLabel();
        relObIdTextField = new javax.swing.JTextField();
        relOtIdLabel = new javax.swing.JLabel();
        relOtIdTextField = new javax.swing.JTextField();
        accButton = new javax.swing.JButton();
        canButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attribute Editor");

        namLabel.setText("Name:");

        fieLabel.setText("Field Name:");

        typLabel.setText("Type:");

        typComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Boolean", "Integer", "Float", "Char", "Text", "Date", "DateTime", "Binary", "Selection", "Many2One", "One2Many", "Many2Many" }));
        typComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typComboBoxActionPerformed(evt);
            }
        });

        sizLabel.setText("Size:");

        desLabel.setText("Description:");

        strLabel.setText("String:");

        defLabel.setText("Default:");

        traCheckBox.setText("Translate:");
        traCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        reqCheckBox.setText("Required:");
        reqCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        reaCheckBox.setText("ReadOnly:");
        reaCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        selPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Selections"));

        selTable.setModel( new SelectionTableModel( selections ));
        selTable.setDragEnabled(true);
        selTable.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        selTable.setEnabled(false);
        selTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        selTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selTableMouseClicked(evt);
            }
        });
        selScrollPane.setViewportView(selTable);

        addSelButton.setText("+");
        addSelButton.setEnabled(false);
        addSelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSelButtonActionPerformed(evt);
            }
        });

        delSelButton.setText("-");
        delSelButton.setEnabled(false);
        delSelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delSelButtonActionPerformed(evt);
            }
        });

        ediSelButton.setText("Edit");
        ediSelButton.setEnabled(false);
        ediSelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ediSelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selPanelLayout = new javax.swing.GroupLayout(selPanel);
        selPanel.setLayout(selPanelLayout);
        selPanelLayout.setHorizontalGroup(
            selPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(selPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ediSelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delSelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addSelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(selPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(selPanelLayout.createSequentialGroup()
                    .addComponent(selScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addGap(87, 87, 87)))
        );
        selPanelLayout.setVerticalGroup(
            selPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selPanelLayout.createSequentialGroup()
                .addComponent(addSelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delSelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ediSelButton)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(selPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(selScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
        );

        relPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Relations"));

        relOtNaLabel.setText("Other Name:");

        relReIdLabel.setText("Relation:");

        relObIdLabel.setText("Obj. Id:");

        relOtIdLabel.setText("Other Id:");

        javax.swing.GroupLayout relPanelLayout = new javax.swing.GroupLayout(relPanel);
        relPanel.setLayout(relPanelLayout);
        relPanelLayout.setHorizontalGroup(
            relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relPanelLayout.createSequentialGroup()
                .addGroup(relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(relPanelLayout.createSequentialGroup()
                        .addComponent(relOtNaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(relOtNaTextField))
                    .addGroup(relPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(relObIdLabel)
                            .addComponent(relReIdLabel)
                            .addComponent(relOtIdLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(relObIdTextField)
                            .addComponent(relReIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                            .addComponent(relOtIdTextField))))
                .addContainerGap())
        );
        relPanelLayout.setVerticalGroup(
            relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relPanelLayout.createSequentialGroup()
                .addGroup(relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relOtNaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relOtNaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relReIdLabel)
                    .addComponent(relReIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relObIdLabel)
                    .addComponent(relObIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(relPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relOtIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relOtIdLabel)))
        );

        accButton.setText("Accept");
        accButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accButtonActionPerformed(evt);
            }
        });

        canButton.setText("Cancel");
        canButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(desLabel)
                            .addComponent(strLabel)
                            .addComponent(defLabel)
                            .addComponent(typLabel)
                            .addComponent(fieLabel)
                            .addComponent(namLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(desTextField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(strTextField)
                                    .addComponent(defTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(namTextField)
                            .addComponent(fieTextField)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(traCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(reqCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(reaCheckBox))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(typComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(sizLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sizSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(selPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(relPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(canButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namLabel)
                    .addComponent(namTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typLabel)
                    .addComponent(sizLabel)
                    .addComponent(sizSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strLabel)
                    .addComponent(strTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defLabel)
                    .addComponent(defTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(traCheckBox)
                    .addComponent(reqCheckBox)
                    .addComponent(reaCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(relPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accButton)
                    .addComponent(canButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt 
     */
    private void accButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accButtonActionPerformed
        accepted = true;
        //
        modAttr.setName( namTextField.getText() );
        modAttr.setFieldName( fieTextField.getText() );
        modAttr.setType( typComboBox.getSelectedIndex() );
        modAttr.setSize( ( Integer ) sizSpinner.getValue() );
        modAttr.setDescription( desTextField.getText() );
        modAttr.setString( strTextField.getText() );
        modAttr.setInitVal( defTextField.getText() );
        modAttr.setTranslate( traCheckBox.isSelected() );
        modAttr.setRequired( reqCheckBox.isSelected() );
        modAttr.setReadOnly( reaCheckBox.isSelected() );
        modAttr.setSelections( selections );
        modAttr.setRelOthName( relOtNaTextField.getText() );
        modAttr.setRelRelId( relReIdTextField.getText() );
        modAttr.setRelObjId( relObIdTextField.getText() );
        modAttr.setRelOthId( relOtIdTextField.getText() );
        // Close the window
        setVisible( false );
    }//GEN-LAST:event_accButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void canButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canButtonActionPerformed
        setVisible( false );
    }//GEN-LAST:event_canButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void typComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typComboBoxActionPerformed
        // Check if the type selected is SELECTION to enable selection editing
        boolean selEnabled = ( typComboBox.getSelectedIndex() == ModAttr.ATTR_TYPE_SELECTION );
        boolean relEnabled = ( typComboBox.getSelectedIndex() > ModAttr.ATTR_TYPE_SELECTION );
        selPanel.setVisible( selEnabled );
        if( selEnabled )
        {
            int selRow = selTable.getSelectedRow();
            selTable.setEnabled( selEnabled );
            addSelButton.setEnabled( selEnabled );
            delSelButton.setEnabled( selEnabled && ( selRow != -1 ) );
            ediSelButton.setEnabled( selEnabled && ( selRow != -1 ) );
        }
        relPanel.setVisible( relEnabled );
        if( relEnabled )
        {
            int relType = typComboBox.getSelectedIndex();
            relReIdTextField.setEnabled( relType > ModAttr.ATTR_TYPE_MANY2ONE );
            relObIdTextField.setEnabled( relType == ModAttr.ATTR_TYPE_MANY2MANY );
            relOtIdTextField.setEnabled( relType == ModAttr.ATTR_TYPE_MANY2MANY );
        }
        this.pack();
    }//GEN-LAST:event_typComboBoxActionPerformed

    /**
     * 
     * @param selection
     * @return 
     */
    private boolean editSelection( AttrSelection selection )
    {
        JTextField valueField = new JTextField();
        JTextField nameField = new JTextField();
        valueField.setText( selection.getValue() );
        nameField.setText( selection.getName() );
        Object[] objs = { "value: ", valueField, "name: ", nameField };
        Object opciones[] = { "Accept", "Cancel" };
        if( JOptionPane.showOptionDialog( this, 
                objs, 
                "Please enter the selection entry",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, 
                null, opciones, objs ) == JOptionPane.OK_OPTION )
        {
            selection.setValue( valueField.getText() );
            selection.setName( nameField.getText() );
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param evt 
     */
    private void addSelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSelButtonActionPerformed
        AttrSelection selection = new AttrSelection();
        if( editSelection( selection ) )
        {
            selections.add( selection );
            selTableModel.fireTableRowsInserted( selections.size(), selections.size() );
        }
    }//GEN-LAST:event_addSelButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void delSelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delSelButtonActionPerformed
        int row = selTable.getSelectedRow();
        if( row != -1 )
        {
            if( JOptionPane.showConfirmDialog( this, 
                "Are you sure to delete the selection entry?",
                "Atention", 
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION )
            {
                selections.remove( row );
                selTableModel.fireTableRowsDeleted( row, row );
            }
        }
    }//GEN-LAST:event_delSelButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void ediSelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ediSelButtonActionPerformed
        int row = selTable.getSelectedRow();
        if( row != -1 )
        {
            AttrSelection selection = selections.get( row );
            if( editSelection( selection ) )
            {
                selTableModel.fireTableRowsUpdated( row, row );
            }
        }
    }//GEN-LAST:event_ediSelButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void selTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selTableMouseClicked
        if( evt.getClickCount() == 2 ) // double clicked
        {
            ediSelButtonActionPerformed( null );
        }
    }//GEN-LAST:event_selTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accButton;
    private javax.swing.JButton addSelButton;
    private javax.swing.JButton canButton;
    private javax.swing.JLabel defLabel;
    private javax.swing.JTextField defTextField;
    private javax.swing.JButton delSelButton;
    private javax.swing.JLabel desLabel;
    private javax.swing.JTextField desTextField;
    private javax.swing.JButton ediSelButton;
    private javax.swing.JLabel fieLabel;
    private javax.swing.JTextField fieTextField;
    private javax.swing.JLabel namLabel;
    private javax.swing.JTextField namTextField;
    private javax.swing.JCheckBox reaCheckBox;
    private javax.swing.JLabel relObIdLabel;
    private javax.swing.JTextField relObIdTextField;
    private javax.swing.JLabel relOtIdLabel;
    private javax.swing.JTextField relOtIdTextField;
    private javax.swing.JLabel relOtNaLabel;
    private javax.swing.JTextField relOtNaTextField;
    private javax.swing.JPanel relPanel;
    private javax.swing.JLabel relReIdLabel;
    private javax.swing.JTextField relReIdTextField;
    private javax.swing.JCheckBox reqCheckBox;
    private javax.swing.JPanel selPanel;
    private javax.swing.JScrollPane selScrollPane;
    private javax.swing.JTable selTable;
    private javax.swing.JLabel sizLabel;
    private javax.swing.JSpinner sizSpinner;
    private javax.swing.JLabel strLabel;
    private javax.swing.JTextField strTextField;
    private javax.swing.JCheckBox traCheckBox;
    private javax.swing.JComboBox typComboBox;
    private javax.swing.JLabel typLabel;
    // End of variables declaration//GEN-END:variables

}
