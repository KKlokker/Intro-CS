import java.io.FileWriter;
import java.io.IOException;

public class TowerProblem {
	 	public static void main(String[] args) throws IOException{
			FileWriter output = new FileWriter("output.txt");
			nQueens(3,output);
			output.close();

		}

		public static void nTower(int n, FileWriter output) throws IOException{
			for(int s = 1; s <= n*n; s++)
				for(int i = 1; i <= n; i++) {
					for(int j = 1; j <= n; j++) 
						if(((s-1)/n + 1 == i || (s+n-1)% n +1== j) && s != pair2int(n,i,j))
							output.write("" +s + " " + pair2int(n,i,j) + " 0\n"); 					}
			for(int i = 0; i< n;i++){
				for(int j = 0; j < n; j++)
					output.write((i*n+j+1) + " ");
				output.write("0\n");
			}
		}

		public static void nQueens(int n, FileWriter output) throws IOException{
			for(int s = 1; s <= n*n; s++)
				for(int i = 1; i <= n; i++) 
					for(int j = 1; j <= n; j++) {
						int[] sPoints = int2pair(n,s);
						if((sPoints[0] == i || sPoints[1]== j || Math.abs(Points[0]-i) ==Math.abs(sPoints[1]-j)) && s != pair2int(n,i,j))
							output.write("" +s + " " + pair2int(n,i,j) + " 0\n"); 
					
					}
			for(int i = 0; i< n;i++){
				for(int j = 0; j < n; j++)
					output.write((i*n+j+1) + " ");
				output.write("0\n");
			}
		}

		public static int pair2int(int n, int r, int c) {
			return n*(r-1)+c;
		}

		public static int[] int2pair(int n, int r) {
			int row = (r/n)+1;
			int[] pair = new int[]{row,r-n*row};
			return pair;
		}


}
