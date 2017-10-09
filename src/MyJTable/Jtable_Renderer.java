/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MyJTable;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ian val
 */
public class Jtable_Renderer {
    
    
    
    public static  void Clear_TableData(JTable mytable){
        
        for (int a = mytable.getRowCount() - 1; a >= 0; a = a - 1) 
            ((DefaultTableModel) mytable.getModel()).removeRow(a);
        
    }
    
    
    public static void Jtable_Properties(JTable mytable, int col, int pos) {
        
        DefaultTableCellRenderer MyAlignment = new DefaultTableCellRenderer();
        
        
        switch (pos) {
            
            case 1:
                MyAlignment.setHorizontalAlignment(JLabel.RIGHT);
                break;
            
            case 2:
                MyAlignment.setHorizontalAlignment(JLabel.LEFT);
                break;
            
            case 3:
                MyAlignment.setHorizontalAlignment(JLabel.CENTER);
                break;
        }
        
        mytable.getColumnModel().getColumn(col).setCellRenderer(MyAlignment);
        
    }
    
    public static void Jtable_Renderer(JTable mytable) {
        
        TableCellRenderer rendererFromHeader = mytable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        
        mytable.getTableHeader().setFont(new java.awt.Font("ARIAL", java.awt.Font.BOLD, 14));
        // mytable.getTableHeader().setForeground(Color.BLUE);

        mytable.setShowHorizontalLines(true);
        mytable.setShowVerticalLines(true);
        mytable.getColumnModel().setColumnMargin(15);
        
        
        
    }
    
    public static double getSum(JTable mytable, int col) {
        double dtotal = 0;
        
        for (int a = 0; a < mytable.getRowCount(); a++) {
            dtotal = dtotal + Double.parseDouble(mytable.getValueAt(a, col).toString());
        }
        
        
        return dtotal;
        
    }
    
    public void AddPopMenu(JTable mytable, final JPopupMenu mymenu) {
        mytable.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());
                    
                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }
                    
                    mymenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
    }
    
    public void AddChekBox(JTable mytable) {
        
        TableColumn tc = mytable.getColumnModel().getColumn(0);
        tc.setHeaderRenderer(new CheckBoxHeader(new MyItemListener(mytable)));
        JCheckBox mychkbox = new JCheckBox(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do what ever you wanted to do here if the checkbox is checked or unchecked
            }
        });
        
        mychkbox.setHorizontalAlignment(SwingConstants.CENTER);
        tc.setCellEditor(new DefaultCellEditor(mychkbox));

        // set the cell rederer to show the checkbox in the table
        tc.setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkBox = new JCheckBox("", Boolean.valueOf(value.toString()));
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setBackground(Color.white);
                //  return new JCheckBox("", Boolean.valueOf(value.toString()));
                return checkBox;
            }
        });
        
        
        
    }
    
    class MyItemListener implements ItemListener {
        
        JTable mytable1;
        
        public MyItemListener(JTable mytable) {
            mytable1 = mytable;
        }
        
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = mytable1.getRowCount(); x < y; x++) {
                mytable1.setValueAt(new Boolean(checked), x, 0);
            }
        }
    }
    
    class CheckBoxHeader extends JCheckBox
            implements TableCellRenderer, MouseListener {
        
        protected CheckBoxHeader rendererComponent;
        protected int column;
        protected boolean mousePressed = false;
        
        public CheckBoxHeader(ItemListener itemListener) {
            rendererComponent = this;
            rendererComponent.addItemListener(itemListener);
        }
        
        public Component getTableCellRendererComponent(
                JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    rendererComponent.setForeground(header.getForeground());
                    rendererComponent.setBackground(header.getBackground());
                    rendererComponent.setFont(header.getFont());
                    header.addMouseListener(rendererComponent);
                }
            }
            setColumn(column);
            rendererComponent.setText("Select All");
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            return rendererComponent;
        }
        
        protected void setColumn(int column) {
            this.column = column;
        }
        
        public int getColumn() {
            return column;
        }
        
        protected void handleClickEvent(MouseEvent e) {
            if (mousePressed) {
                mousePressed = false;
                JTableHeader header = (JTableHeader) (e.getSource());
                JTable tableView = header.getTable();
                TableColumnModel columnModel = tableView.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = tableView.convertColumnIndexToModel(viewColumn);
                
                if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {
                    doClick();
                }
            }
        }
        
        public void mouseClicked(MouseEvent e) {
            handleClickEvent(e);
            ((JTableHeader) e.getSource()).repaint();
        }
        
        public void mousePressed(MouseEvent e) {
            mousePressed = true;
        }
        
        public void mouseReleased(MouseEvent e) {
        }
        
        public void mouseEntered(MouseEvent e) {
        }
        
        public void mouseExited(MouseEvent e) {
        }
    }
}
