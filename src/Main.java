import java.awt.EventQueue;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new UI();
			}
		});
	}
}

/*
		
0,0,NaN,0,1
1,0,NaN,1,1
2,0,NaN,2,1
3,0,NaN,3,1
2,1,NaN,2,2
3,1,NaN,3,2
4,1,NaN,4,2
5,1,NaN,5,2
6,1,NaN,6,2
6,1,NaN,7,1
6,2,NaN,7,2
6,3,NaN,7,3
6,4,NaN,7,4
6,5,NaN,7,5
6,6,NaN,7,6
6,7,NaN,7,7
5,3,NaN,6,3
5,4,NaN,6,4
5,5,NaN,6,5
5,6,NaN,6,6
5,6,NaN,5,7
4,6,NaN,4,7
4,3,NaN,5,3
4,4,NaN,5,4
4,4,NaN,4,5
3,4,NaN,3,5
2,4,NaN,2,5
1,5,NaN,2,5
1,6,NaN,2,6
2,6,NaN,2,7
1,6,NaN,1,7
1,6,NaN,0,6
0,5,NaN,1,5
0,4,NaN,1,4
0,3,NaN,0,4
1,3,NaN,1,4
2,3,NaN,2,4
3,3,NaN,3,4
*/