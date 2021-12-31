public class FirstFit {
	 	public static void main(String[] args){
			double[] input = new double[]{0.2,0.4,0.6,0.8,0.6,0.4}; //TODO Make it look from the start and not just go to next bin 
			FF(4,input);			
		}

		private static void FF(int binsWanted, double[] input) {
			double[][] bins = new double[binsWanted][10];
			double[] overbin = new double[10];
			int overbinCounter = 0;
			int binCounter = 0;
			int internalCounter = 0;
			double internalTotal = 0;
			for(double d: input) {
				if(internalTotal + d > 1.0) {
					binCounter++;
					internalTotal = 0;
					internalCounter = 0;
				}
				if(binCounter == binsWanted) {
					overbin[overbinCounter] = d;
					overbinCounter++;
					if(overbinCounter == overbin.length)
						overbin = resize(overbin);
				}
				else {
					internalTotal = internalTotal + d;
					bins[binCounter][internalCounter] = d;
					internalCounter++;
					if(internalCounter == bins[binCounter].length)
						bins[binCounter] = resize(bins[binCounter]);
				}
			}
			for(double[] bin: bins) {
		       		System.out.print("Bin ");
				for(double d: bin)
					if(d != 0.0)
						System.out.print(String.format("%.2f",d) + " ");
				System.out.println();
			}	
			System.out.println("Overflow:");
			for(double d: overbin)
				if(d != 0.0)
					System.out.print(String.format("%.2f",d)+ " ");

			
		}

		private static double[] resize(double[] bin) {
			double[] newBin = new double[bin.length*2];
			for(int i = 0; i < bin.length; i++)
				newBin[i] = bin[i];
			return newBin;
		}
}
