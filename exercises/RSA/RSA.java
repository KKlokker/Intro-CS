import java.util.concurrent.ThreadLocalRandom;
public class RSA {
	 	public static void main(String[] args){
			int[] PK = new int[]{1517,13};
			int m = 43;
			int[] encrypt = encrypt(m,PK);
			System.out.println(encrypt[0] + " " + encrypt[1]);
			System.out.println(Exp(43,13,1517));
			System.out.println(multiInverse(49,221));
			String ceasar = "YMNX HWDUYTLWFR NX JFXD YT IJHNUMJW.";
			ceasarBruteForce(ceasar);
			for(int i = 0; i < 3;i++)
				System.out.println(i + " " + xgcd(17,1440, 1, 0,0,1)[i]);
			System.out.println(recursiveCounterOdd);
			recursiveCounter = 0;
			recursiveCounterOdd = 0;
			System.out.println( encrypt(423, new int[]{1517,17})[0]);
			System.out.println("The decrypt is " + decrypt(encrypt(423,new int[]{1517,17})[0], new int[]{1517,593})[0]);
			System.out.println(recursiveCounter + " " + recursiveCounterOdd);	
		}
		public static int recursiveCounter = 0;	
		public static int recursiveCounterOdd = 0;
		private static int s = 0;
		private static int t = 0;

		public static int[] xgcd(int a, int b, int s1, int s2, int t1, int t2){
			int q = a/b;
			int r = a - q * b;
			int s3 = s1 - q * s2;
			int t3 = t1 - q * t2;
	
			s = s2;
			t = t2;
	
			return (r == 0)? new int[]{Math.abs(b),s2,s3} : xgcd(b, r, s2, s3, t2, t3);
		}

		public static void ceasarBruteForce(String m) {
			for(int j = 0; j < 27; j++){
				for(int i = 0; i < m.length(); i++){
					char c = m.charAt(i);
					if(c != ' '){
						c = Character.toUpperCase(c);
						c = (char)((int)c + j);
						if(c> 90)
							c = (char)((int)c-25);
					}
					System.out.print(c);
				}
				System.out.println(j);
			}
		}

		//n mod m
		public static int multiInverse(int n, int m) {
			int i = 1;
			while(((n*i)%m)!=1 && i < 1000)
				i++;
			return i;
		}	
		//a^k%n
		public static int Exp(int a,int k,int n) {
			if (k<0) return -1;
			if (k==0) return 1;
			if (k == 1) return (a%n);
			if (k%2==1) {recursiveCounterOdd++; return ((a*Exp(a,k-1,n))%n);}//odd
			else { recursiveCounter++; return ( (int)(Math.pow(Exp(a,k/2,n),2))%n);}
		}

		public static int[] encrypt(int m, int[] PKA) {
			int message = Exp(m,PKA[1],PKA[0]);
			return (new int[]{message, PKA[0]});
		}

		public static int[] decrypt(int c, int[] SKA) {
			int message = Exp(c,SKA[1],SKA[0]);
			return (new int[]{message, SKA[0]});
		}

		//reutrn if n is prime and r is the amount of test
		public static boolean isPrime(int n, int r) {
			int k = 1;

			while(((n-1)%(int)Math.pow(2,k+1)) == 0){
				k++;
			}
			int m = (n-1)/(int)(Math.pow(2,k));
			int random = ThreadLocalRandom.current().nextInt(1, n); //the maximum is here n-1
			int b = Exp(random,m,n);
			if(b==1 || b==-1) return true;
			if(k==1) return false;
			return MillerRabin(b,m,n,r);
		}

		public static boolean MillerRabin(int a, int m, int n, int r) {
			if(r==0 || a > 214748364)
				return true;

			if(a==1)
				return false;
			if(a==n-1) {
				return isPrime(n,r-1);
			}	
			else {
				int b = Exp((int)Math.pow(a,2),m,n);
				return MillerRabin(b,m,n,r);
			}
		}

}
