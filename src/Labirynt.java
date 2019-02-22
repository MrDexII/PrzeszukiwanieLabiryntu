import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Labirynt {
	
	public static final int WSZERZ = 1;
	public static final int WGLAB = 2;
	
	public int metoda = 2;
	private List<String> start = Arrays.asList("0","0");
	private int[] koniec = new int[] {0, 4};
	private List<String> n = Arrays.asList("7","7");
	private String[][] krawedzie;
	private String[][] plansza = new String[8][8];
	int[][] poprzednicy1 = new int[8][8];
	int[][] poprzednicy2 = new int[8][8];
	int[][] poprzednicy3 = new int[8][8];
	
	
	
	public Labirynt(String[][] krawedzie, int metoda) {
		this.krawedzie = krawedzie;
		this.metoda = metoda;
	}

	public String[][] start() {
		
		List<String> O = new ArrayList<String>();
		O.addAll(start);
		O.add("NaN");
		O.add("NaN");
		O.add("NaN");
		initPoprzednicy();
		
		List<List<String>> O2= new ArrayList<List<String>>();
		O2.add(O);
		
		init(plansza,"0");
		
		int it = 1;
		plansza[Integer.parseInt(O2.get(0).get(0))][Integer.parseInt(O2.get(0).get(1))]=Integer.toString(it);
		
		List<List<String>> sasiedzi = new ArrayList<List<String>>();
		
		while (!O2.isEmpty()) {
			//System.out.println(it + " " + O2);
			it++;
			sasiedzi = znajdzSosadow(O2,krawedzie,n,plansza);
			
			switch (metoda) {
			case WSZERZ:
			{
				if(it == 2)
					System.out.println("Metoda wszerz");
				
				for (int i = 0; i < sasiedzi.size(); i++) {
					O2.add(sasiedzi.get(i));
				}
				O2.remove(0); 
				O2 = oczysc_O2(O2,plansza);
				break;
			}
			case WGLAB:
			{
				if(it == 2)
					System.out.println("Metoda w g��b 2");
				
				List<List<String>> temp = new ArrayList<List<String>>();
				temp = sasiedzi;
				O2.remove(0); 
				
				for (int i = 0; i < O2.size(); i++) {
					temp.add(O2.get(i));
				}
				O2=temp;
				O2 = oczysc_O2(O2,plansza);
				break;
			}
			case 3:
			{
				if(it == 2)
					System.out.println("Metoda w g��b 3");
				
				List<List<String>> temp = new ArrayList<List<String>>();
				temp = sasiedzi;
				O2.remove(0); 
				
				for (int i = 0; i < O2.size(); i++) {
					temp.add(O2.get(i));
				}
				O2=temp;
				//O2 = oczysc_O2(O2,plansza);
				break;
			}
			default:
				break;
			}
			if (O2.isEmpty()) {
				break;
			}
			
			plansza[Integer.parseInt(O2.get(0).get(0))][Integer.parseInt(O2.get(0).get(1))] = Integer.toString(it);
			
			if (poprzednicy3[Integer.parseInt(O2.get(0).get(0))][Integer.parseInt(O2.get(0).get(1))] > poprzednicy3[Integer.parseInt(O2.get(0).get(3))][Integer.parseInt(O2.get(0).get(4))] + 1) {
				poprzednicy1[Integer.parseInt(O2.get(0).get(0))][Integer.parseInt(O2.get(0).get(1))] = Integer.parseInt(O2.get(0).get(3));
				poprzednicy2[Integer.parseInt(O2.get(0).get(0))][Integer.parseInt(O2.get(0).get(1))] = Integer.parseInt(O2.get(0).get(4));
				poprzednicy3[Integer.parseInt(O2.get(0).get(0))][Integer.parseInt(O2.get(0).get(1))] = poprzednicy3[Integer.parseInt(O2.get(0).get(3))][Integer.parseInt(O2.get(0).get(4))] + 1;
			}
		}
		
		return plansza;
	}
	public String[][] sprawyKoncowe() {
		
		String[][] pomoc = new String[8][8];
		init(pomoc,"999");
		
		if (Integer.parseInt(plansza[koniec[0]][koniec[1]]) > 0) {
			System.out.println("Sukces");
			
			int iloscKrokow = poprzednicy3[koniec[0]][koniec[1]];
			int a = 0;
			int koniec1=0;
			int koniec2=0;
			while(iloscKrokow > 0) 
			{
				a++;
				pomoc[koniec[0]][koniec[1]] = Integer.toString(a);
				koniec1=poprzednicy1[koniec[0]][koniec[1]];
				koniec2=poprzednicy2[koniec[0]][koniec[1]];
				koniec[0]= koniec1;
				koniec[1]= koniec2;
				iloscKrokow=poprzednicy3[koniec[0]][koniec[1]];
			}
			
		}else {
			System.out.println("Nie znaleziono drogi");
		}
		
		int w = 0;
		for (String[] is : pomoc) {
			for (String i : is) {
				if (!(i == "999")) {
					if(Integer.parseInt(i) > w) {
						w = Integer.parseInt(i);
					}
				}else {
					continue;
				}
			}
		}
		
		for (int i = 0; i < pomoc.length; i++) {
			for (int j = 0; j < pomoc.length; j++) {
				if (!(pomoc[i][j] == "999")) {
					pomoc[i][j] = Integer.toString(Math.abs(Integer.parseInt(pomoc[i][j])-w-1));
				}else 
					continue;
			}
		}
		pomoc[Integer.parseInt(start.get(0))][Integer.parseInt(start.get(1))]=Integer.toString(0);
		
		
		return pomoc;
	}
	
	private void initPoprzednicy() {
		init(poprzednicy1,999);
		init(poprzednicy2,999);
		init(poprzednicy3,999);
		
		poprzednicy1[0][0] = 0;
		poprzednicy2[0][0] = 0;
		poprzednicy3[0][0] = 0;
	}

	private static List<List<String>> oczysc_O2(List<List<String>> o2, String[][] plansza) {
		int counter = 0;
		List<Integer> toReamouwe = new ArrayList<Integer>();
		for (int i = 0; i < o2.size(); i++) {
			if (Integer.parseInt(plansza[Integer.parseInt(o2.get(i).get(0))][Integer.parseInt(o2.get(i).get(1))]) > 0) {
				toReamouwe.add(counter);
			}
			counter++;
		}
		
		if (!toReamouwe.isEmpty()) {
			int a = 0;
			for (int integer : toReamouwe) {
				o2.remove(integer-a);
				if (toReamouwe.size() > 1) {
					a++;
				}
			}
		}
		return o2;
	}


	private static List<List<String>> znajdzSosadow(List<List<String>> o22, String[][] krawedzie,
			List<String> n, String[][] plansza) {
		
		List<List<String>> sasiedzi= new ArrayList<List<String>>();
		
		int[] p= new int[2];
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < p.length; j++) {
				p[j]= Integer.parseInt(o22.get(i).get(j));
			}
		}
		
		LinkedList<Object> sasiedzi1 = new LinkedList<Object>();
		//gora
		if ((p[0]-1) > -1 && moznaPrzejsc(p,new int[]{p[0]-1, p[1]}, krawedzie) && plansza[p[0]-1][p[1]] == "0"){
			sasiedzi1.add((p[0]-1) + "");
			sasiedzi1.add(p[1] + "");
			sasiedzi1.add("NaN" + "");
			sasiedzi1.add(p[0] + "");
			sasiedzi1.add(p[1] + "");
		}
		//lewo
		if (p[1]-1 > -1 && moznaPrzejsc(p, new int[]{p[0], p[1]-1},krawedzie) && plansza[p[0]][p[1]-1] == "0"){
			sasiedzi1.add(p[0] + "");
			sasiedzi1.add((p[1]-1) + "");
			sasiedzi1.add("NaN" + "");
			sasiedzi1.add(p[0] + "");
			sasiedzi1.add(p[1] + "");
		}
		//dol
		if (p[0]+1 <= Integer.parseInt(n.get(0)) && moznaPrzejsc(p,new int[]{p[0]+1,p[1]},krawedzie) && plansza[p[0]+1][p[1]] == "0"){
			sasiedzi1.add((p[0]+1) + "");
			sasiedzi1.add(p[1] + "");
			sasiedzi1.add("NaN" + "");
			sasiedzi1.add(p[0] + "");
			sasiedzi1.add(p[1] + "");
		}
		//prawo
		if (p[1]+1 <= Integer.parseInt(n.get(1)) && moznaPrzejsc(p,new int[]{p[0],p[1]+1},krawedzie) && plansza[p[0]][p[1]+1] == "0"){
			sasiedzi1.add(p[0] + "");
			sasiedzi1.add(p[1]+1 + "");
			sasiedzi1.add("NaN" + "");
			sasiedzi1.add(p[0] + "");
			sasiedzi1.add(p[1] + "");
		}
		for (int i = 0; i < sasiedzi1.size()/5; i++) {
			sasiedzi.add(new ArrayList<String>());
		}
		for (int i = 0; i < sasiedzi.size(); i++) {
			for (int j = 0; j < 5; j++) {
				sasiedzi.get(i).add((String) sasiedzi1.poll());				
			}
		}
		
		return sasiedzi;
	}

	private static boolean moznaPrzejsc(int[] p1, int[] p2,
			String[][] krawedzie) {
			int[][] krawedzie1 = new int[krawedzie.length][2];
			for (int i = 0; i < krawedzie.length; i++) {
				for (int j = 0; j < 2; j++) {
					krawedzie1[i][j]= Integer.parseInt(krawedzie[i][j]);
				}
			}
			
			int[][] krawedzie2 = new int[krawedzie.length][2];
			for (int i = 0; i < krawedzie.length; i++) {
				for (int j = 3; j < 5; j++) {
					krawedzie2[i][j-3]= Integer.parseInt(krawedzie[i][j]);
				}
			}	
			
			boolean pomoc = (porownaj(p1,krawedzie1,p2, krawedzie2) & porownaj(p2, krawedzie1, p1, krawedzie2));
		return pomoc;
	}

	private static boolean porownaj(int[] o1, int[][] krawedzie1, int[] o2, int[][] krawedzie2) {
		
		for (int i = 0; i < krawedzie1.length; i++) {
			if (o1[0] == krawedzie1[i][0] & o1[1] == krawedzie1[i][1]) {
				if (o2[0] == krawedzie2[i][0] & o2[1] == krawedzie2[i][1]) {
					return false;
				}
			}
		}
		
		return true;
	}

	private static void init(String[][] plansza, String string) {
		
		for (int i = 0; i < plansza.length; i++) {
			for (int j = 0; j < plansza[i].length; j++) {
				plansza[i][j] = string;
			}
		}
	}
	
	private static void init(int[][] poprzednicy1, int liczba) {
		
		for (int i = 0; i < poprzednicy1.length; i++) {
			for (int j = 0; j < poprzednicy1[i].length; j++) {
				poprzednicy1[i][j] = liczba;
			}
		}
	}
}