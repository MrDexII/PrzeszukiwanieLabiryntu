import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

class BorderColorRenderer extends DefaultTableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String[][] krawedzie;
	Border border;
	Color colorBlack = Color.BLACK;
	
	public BorderColorRenderer( String[][] krawedzie) {
		this.krawedzie = krawedzie;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		border = BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack);
			//row >
		for (int i = 0; i < krawedzie.length; i++) {
				
			if ((Integer.parseInt(krawedzie[i][0]) > Integer.parseInt(krawedzie[i][3])) && (row == Integer.parseInt(krawedzie[i][0]))) {
				//column >
				if ((Integer.parseInt(krawedzie[i][1]) > Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					//border = BorderFactory.createMatteBorder(top, left, bottom, right, color);
					border = BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack);
				//column <					
				}else if ((Integer.parseInt(krawedzie[i][1]) < Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack);
				//column =
				}else if ((Integer.parseInt(krawedzie[i][1]) == Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(3, 0, 0, 0, colorBlack);
				}
			//row <				
			}else if ((Integer.parseInt(krawedzie[i][0]) < Integer.parseInt(krawedzie[i][3])) && (row == Integer.parseInt(krawedzie[i][0]))) {
				//column >
				if ((Integer.parseInt(krawedzie[i][1]) > Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack);
				//column <
				}else if ((Integer.parseInt(krawedzie[i][1]) < Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack);
				//column =
				}else if ((Integer.parseInt(krawedzie[i][1]) == Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(0, 0, 3, 0, colorBlack);
				}
			//row =
			}else if((Integer.parseInt(krawedzie[i][0]) == Integer.parseInt(krawedzie[i][3])) && (row == Integer.parseInt(krawedzie[i][0]))) {
				//column >
				if ((Integer.parseInt(krawedzie[i][1]) > Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack);
				//column <
				}else if ((Integer.parseInt(krawedzie[i][1]) < Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(0, 0, 0, 3, colorBlack);
				//column =
				}else if ((Integer.parseInt(krawedzie[i][1]) == Integer.parseInt(krawedzie[i][4])) && (column == Integer.parseInt(krawedzie[i][1]))) {
					border = BorderFactory.createMatteBorder(0, 0, 0, 0, colorBlack);
				}
			}
		}
		if ((row == 6 && column == 1) ||(row == 4 && column == 4)|| (row == 5 && column == 6) || (row == 1 && column == 6)) {
			border = BorderFactory.createMatteBorder(0, 0, 3, 3, colorBlack);
		}

		JComponent comp = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);
		comp.setBorder(border);
		
		if(row == 0 && column == 0) {
			comp.setBackground(Color.GREEN);
		}else if (row == 0 && column == 4) {
			comp.setBackground(Color.ORANGE);
		}else {
			comp.setBackground(Color.WHITE);
		}
		return comp;
	}
}
