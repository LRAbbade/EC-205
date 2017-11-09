package model;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class Table extends JTable
{
	public Table(Object[][] data, Object[] columnNames) 
	{
		super(data, columnNames);
		super.setFont(new Font("Myriad Pro", Font.PLAIN, 12));
        super.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setColumnSelectionAllowed(false);
        super.setRowSelectionAllowed(true);
	}
	
	/**
	 * Set the sizes in pixels for each column of table. If sizes is greater than the number of columns,
	 * it will ignore the extra values.
	 * If there's space left, the table will resize while keeping relative sizes.
	 * @param sizes = an array describing the size of each column
	 * @throws NullPointerException if sizes if null
	 */
	public void setTableColumns(int[] sizes) throws NullPointerException
	{
		for (int i = 0; i < sizes.length; i++) 
		{
			try {
				super.getColumnModel().getColumn(i).setPreferredWidth(sizes[i]);
			} catch (ArrayIndexOutOfBoundsException e1) {
				break;
			}
		}
	}
	
	/**
	 * Disables cell editing
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) 
	{
		return false;
	}
	
}
