/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genmod;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragSource;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

/**
 *
 * @author Juan Rios anonbeat@gmail.com
 */
public class TableRowTransferHandler extends TransferHandler 
{
    protected final DataFlavor localObjectFlavor = new ActivationDataFlavor( Integer.class, DataFlavor.javaJVMLocalObjectMimeType, "Integer Row Index" );
    protected JTable table = null;

   public TableRowTransferHandler( JTable table ) 
   {
      this.table = table;
   }

    @Override
    public boolean canImport( TransferHandler.TransferSupport info ) 
    {
        boolean b = info.getComponent() == table && info.isDrop() && info.isDataFlavorSupported( localObjectFlavor );
        table.setCursor( b ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop );
        return b;
    }

    @Override
    protected Transferable createTransferable( JComponent c )
    {
        assert ( c == table );
        return new DataHandler( new Integer( table.getSelectedRow() ), localObjectFlavor.getMimeType() );
    }

    @Override
    protected void exportDone( JComponent c, Transferable t, int act ) 
    {
        if( act == TransferHandler.MOVE ) 
        {
            table.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
        }
    }

    @Override
    public int getSourceActions( JComponent c )
    {
        return TransferHandler.COPY_OR_MOVE;
    }

    @Override
    public boolean importData( TransferHandler.TransferSupport info ) 
    {
        JTable target = ( JTable ) info.getComponent();
        JTable.DropLocation dl = ( JTable.DropLocation ) info.getDropLocation();
        int index = dl.getRow();
        int max = table.getModel().getRowCount();
        if( ( index < 0 ) || ( index > max ) )
        {
            index = max;
        }
        target.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
        try {
            Integer rowFrom = ( Integer ) info.getTransferable().getTransferData( localObjectFlavor );
            if( ( rowFrom != -1 ) && ( rowFrom != index ) ) 
            {
                ( ( Reorderable ) table.getModel() ).reorder( rowFrom, index );
                if( index > rowFrom ) 
                {
                    index--;
                }
                target.getSelectionModel().addSelectionInterval( index, index );
                return true;
            }
        } 
        catch( Exception e ) 
        {
            e.printStackTrace();
        }
        return false;
    }
}
