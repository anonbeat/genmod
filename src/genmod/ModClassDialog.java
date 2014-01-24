/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.util.ArrayList;
import java.awt.Frame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;



/**
 * Attribute Class Table Model 
 * @author Juan Rios anonbeat@gmail.com
 */
class AttributesTableModel extends AbstractTableModel implements Reorderable {
    // Reference to the attribute list
    private ArrayList<ModAttr> attributes;

    // Column names to show
    private static final String columns[] = {
        "Name",
        "Type",
        "Size"
    };
    
    /**
     * Constructor that receives the reference to the attribute list
     * @param alumnos
     */
    AttributesTableModel( ArrayList<ModAttr> attributes )
    {
        // Call the default constructor
        super();
        // save the reference
        this.attributes = attributes;
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
    public int getRowCount() { return attributes.size(); }
    
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
            ModAttr attribute = attributes.get( row );
            switch( col )
            {
                case 0 : return attribute.getName();
                case 1 : return ModAttr.ATTR_TYPE_NAMES[ attribute.getType() ];
                case 2 : return attribute.getSize() > 0 ? attribute.getSize() : "";
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
            ModAttr attribute = attributes.remove( fromIndex );
            // As we removed an item if the to position was higher we need to decrement it to 
            // point to the new location without the from item
            if( toIndex > fromIndex )
            {
                toIndex--;
            }
            attributes.add( toIndex, attribute );
        }
    }
}


/**
 *
 * @author Juan Rios
 */
public class ModClassDialog extends javax.swing.JDialog {
    
    private ModClass modClass;
    private ArrayList<ModAttr> attributes;
    private AttributesTableModel attModel;
    //
    private boolean accepted;
    
    /**
     * Creates new form ModClassDialog
     */
    public ModClassDialog( Frame parent, boolean modal ) 
    {
        super(parent, modal);
        accepted = false;
        initComponents();
    }

    /**
     * 
     * @param parent
     * @param modClass 
     */
    public ModClassDialog( Frame parent, ModClass modClass )
    {
        super( parent, true );
        accepted = false;
        //
        this.modClass = modClass;
        attributes = ( ArrayList<ModAttr> ) modClass.getAttributes().clone();
        //
        initComponents();
        //
        attModel = ( AttributesTableModel ) attTable.getModel();
        //
        namTextField.setText( modClass.getName() );
        desTextField.setText( modClass.getDescription() );
        ordTextField.setText( modClass.getOrder() );
        menTextField.setText( modClass.getMenuPath() );
        //
        attTable.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectionChanged();
            }
        } );
        attTable.setTransferHandler( new TableRowTransferHandler( attTable ) );
        //
        setLocationRelativeTo( parent );
        // Show the dialog
        setVisible( true );
    }

    /**
     * 
     */
    private void selectionChanged() 
    {
        int row = attTable.getSelectedRow();
        delAttButton.setEnabled( row != -1 );
        ediAttButton.setEnabled( row != -1 );
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
        desLabel = new javax.swing.JLabel();
        desTextField = new javax.swing.JTextField();
        ordLabel = new javax.swing.JLabel();
        ordTextField = new javax.swing.JTextField();
        attPanel = new javax.swing.JPanel();
        attScrollPane = new javax.swing.JScrollPane();
        attTable = new javax.swing.JTable();
        addAttButton = new javax.swing.JButton();
        delAttButton = new javax.swing.JButton();
        ediAttButton = new javax.swing.JButton();
        canButton = new javax.swing.JButton();
        accButton = new javax.swing.JButton();
        menLabel = new javax.swing.JLabel();
        menTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Class Editor");
        setMinimumSize(new java.awt.Dimension(450, 280));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        namLabel.setText("Name:");

        desLabel.setText("Description:");

        ordLabel.setText("Order:");

        attPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Attributes"));

        attTable.setModel( new AttributesTableModel( attributes ));
        attTable.setDragEnabled(true);
        attTable.setDropMode(javax.swing.DropMode.INSERT_ROWS);
        attTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        attTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                attTableMouseClicked(evt);
            }
        });
        attScrollPane.setViewportView(attTable);

        addAttButton.setText("+");
        addAttButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAttButtonActionPerformed(evt);
            }
        });

        delAttButton.setText("-");
        delAttButton.setEnabled(false);
        delAttButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delAttButtonActionPerformed(evt);
            }
        });

        ediAttButton.setText("Edit");
        ediAttButton.setEnabled(false);
        ediAttButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ediAttButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout attPanelLayout = new javax.swing.GroupLayout(attPanel);
        attPanel.setLayout(attPanelLayout);
        attPanelLayout.setHorizontalGroup(
            attPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attPanelLayout.createSequentialGroup()
                .addComponent(attScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(attPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ediAttButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delAttButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addAttButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        attPanelLayout.setVerticalGroup(
            attPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(attPanelLayout.createSequentialGroup()
                .addComponent(addAttButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delAttButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ediAttButton)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        canButton.setText("Cancel");
        canButton.setSelected(true);
        canButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canButtonActionPerformed(evt);
            }
        });

        accButton.setText("Accept");
        accButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accButtonActionPerformed(evt);
            }
        });

        menLabel.setText("Menu Path:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ordLabel)
                            .addComponent(desLabel)
                            .addComponent(namLabel)
                            .addComponent(menLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namTextField)
                            .addComponent(desTextField)
                            .addComponent(ordTextField)
                            .addComponent(menTextField)))
                    .addComponent(attPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 230, Short.MAX_VALUE)
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
                    .addComponent(desLabel)
                    .addComponent(desTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ordLabel)
                    .addComponent(ordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menLabel)
                    .addComponent(menTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(attPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        modClass.setName( namTextField.getText() );
        modClass.setDescription( desTextField.getText() );
        modClass.setOrder( ordTextField.getText() );
        modClass.setAttributes( attributes );
        modClass.setMenuPath( menTextField.getText() );
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
    private void addAttButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAttButtonActionPerformed
        ModAttr modAttr = new ModAttr();
        ModAttrDialog attrDlg = new ModAttrDialog( ( java.awt.Frame ) this.getParent(), modAttr );
        if( attrDlg.isAccepted() )
        {
            attributes.add( modAttr );
            attModel.fireTableRowsInserted( attributes.size(), attributes.size() );
        }
    }//GEN-LAST:event_addAttButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void delAttButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delAttButtonActionPerformed
        int row = attTable.getSelectedRow();
        if( row != -1 )
        {
            if( JOptionPane.showConfirmDialog( this, 
                    "Are you sure to delete the selected attribute?", 
                    "Atention", 
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION )
            {
                attributes.remove( row );
                attModel.fireTableRowsDeleted( row, row );
            }
        }
    }//GEN-LAST:event_delAttButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        canButtonActionPerformed( null );
    }//GEN-LAST:event_formWindowClosing

    /**
     * 
     * @param evt 
     */
    private void ediAttButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ediAttButtonActionPerformed
        int row = attTable.getSelectedRow();
        if( row != -1 )
        {
            ModAttr modAttr = attributes.get( row );
            ModAttrDialog attrDlg = new ModAttrDialog( ( java.awt.Frame ) this.getParent(), modAttr );
            if( attrDlg.isAccepted() )
            {
                attModel.fireTableRowsUpdated( row, row );
            }
        }
    }//GEN-LAST:event_ediAttButtonActionPerformed

    /**
     * 
     * @param evt 
     */
    private void attTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_attTableMouseClicked
        if( evt.getClickCount() == 2 ) // double clicked
        {
            ediAttButtonActionPerformed( null );
        }
    }//GEN-LAST:event_attTableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accButton;
    private javax.swing.JButton addAttButton;
    private javax.swing.JPanel attPanel;
    private javax.swing.JScrollPane attScrollPane;
    private javax.swing.JTable attTable;
    private javax.swing.JButton canButton;
    private javax.swing.JButton delAttButton;
    private javax.swing.JLabel desLabel;
    private javax.swing.JTextField desTextField;
    private javax.swing.JButton ediAttButton;
    private javax.swing.JLabel menLabel;
    private javax.swing.JTextField menTextField;
    private javax.swing.JLabel namLabel;
    private javax.swing.JTextField namTextField;
    private javax.swing.JLabel ordLabel;
    private javax.swing.JTextField ordTextField;
    // End of variables declaration//GEN-END:variables

}
