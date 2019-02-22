import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class UI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panel, resultPanel;
	private JLabel metodaLable;
	JButton button;
	private JFileChooser fc;
	private List<String> wszystkieKrawedzie;
	private JRadioButton radiowszerz, radiowglab;
	private ButtonGroup radioGroup;
	private Labirynt labirynt;
	private Labirynt labirynt2;
	private JTable table, table1;
	
	private int metoda = 1; 
	
	public String[][] krawedzie;
	private String[] nazwyKolumn;
	
	public UI() {
		super("Przeszukiwanie Labiryntu");
		init();
	}
	
	private void init() {
		
		setSize(300, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(0,1));
		
		panel = new JPanel(new GridLayout(0, 1));
		resultPanel = new JPanel(new GridLayout(0, 1));
		metodaLable = new JLabel("Wybierz metode przeszukiwania.");
		button = new JButton("Zaladuj Labirynt");
		radiowszerz = new JRadioButton("W szerz", true);
		radiowglab = new JRadioButton("W glab");
		radioGroup = new ButtonGroup();
		
		button.addActionListener(new ActionListener() {
		
		int check = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					wszystkieKrawedzie = new ArrayList<String>();
					wszystkieKrawedzie = getMaze();
					
					krawedzie = new String[wszystkieKrawedzie.size()][5];
					int counter = 0;
					for (String string : wszystkieKrawedzie) {
						String[] temp = new String[5];
						temp = string.split(",");
						for (int i = 0; i < temp.length; i++) {
							krawedzie[counter][i]=temp[i];
						}
						counter++;
					}
					if (!(krawedzie.length == 0)) {
						if (!(check == 0)) {
							resultPanel.remove(table);
							resultPanel.remove(table1);
							resultPanel.revalidate();
							resultPanel.repaint();
						}
						if (metoda == 2) {
							labirynt = new Labirynt(krawedzie, metoda);
							labirynt2 = new Labirynt(krawedzie, 3);
						}else {
							labirynt = new Labirynt(krawedzie, metoda);
						}
						nazwyKolumn = new String[8];
						for (int i = 0; i < nazwyKolumn.length; i++) {
							nazwyKolumn[i] = "cos";
						}
						
						if (metoda == 2) {
							table = (JTable) stworzTabele(labirynt.start(), nazwyKolumn, table);
							resultPanel.add(table);
							
							labirynt2.start();
							table1 = (JTable) stworzTabele(labirynt2.sprawyKoncowe(), nazwyKolumn, table1);
							resultPanel.add(table1);
						}else {
							table = (JTable) stworzTabele(labirynt.start(), nazwyKolumn, table);
							resultPanel.add(table);
							
							table1 = (JTable) stworzTabele(labirynt.sprawyKoncowe(), nazwyKolumn, table1);
							resultPanel.add(table1);
						}
						
						resultPanel.setAlignmentX(Component.BOTTOM_ALIGNMENT);
						add(resultPanel);
						resultPanel.revalidate();
						resultPanel.repaint();
						check++;
					}
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}		

		});
		
		radiowszerz.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				metoda = 1;
			}
		});
		
		radiowglab.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				metoda = 2;	
			}
		});
		
		radioGroup.add(radiowszerz);
		radioGroup.add(radiowglab);

		panel.add(metodaLable);
		panel.add(radiowszerz);
		panel.add(radiowglab);
		panel.add(button);
		
		panel.setAlignmentX(Component.TOP_ALIGNMENT);
		add(panel);
		
		setVisible(true);
	}
	
	private List<String> getMaze() throws IOException {
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
		int result = fc.showOpenDialog(panel);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			
			try {
				List<String> wszystkieKrawedzie= new ArrayList<String>();
			    String line = br.readLine();
			    
			    while (line != null) {
			    	wszystkieKrawedzie.add(line);
			        line = br.readLine();
			    }
			    return wszystkieKrawedzie;
			    
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			    br.close();
			}
		}
		return null;
	}
	
	private Component stworzTabele(String[][] dane, String[] nazwyKolumn, JTable table) {		
		table = new JTable(dane, nazwyKolumn);
		table.setDefaultRenderer(Object.class, new BorderColorRenderer(krawedzie));
		((DefaultTableCellRenderer)table.getDefaultRenderer(String.class)).setHorizontalAlignment(SwingConstants.CENTER);
		table.setShowGrid(false);
		table.setCellSelectionEnabled(false);
		TableColumn column = null;
		for (int j = 0; j < table.getColumnCount(); j++) {
			column = table.getColumnModel().getColumn(j);
			column.setPreferredWidth(30);
		}
		return table;
	}
	
}