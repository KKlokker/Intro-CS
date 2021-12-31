public class SquareModulo {
	 	public static void main(String[] args){
			int count = 0;
			int i = 2;
			while(count < 4) {
				if(i*i%143==1){ 
					System.out.println(i);
					count++;
				}
				i++;
			}			
		}
}
