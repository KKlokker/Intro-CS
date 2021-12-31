public class ClusteringAlgorithm {
	 	public static void main(String[] args){
			//points {x,y,type}
			double[][] points = new double[][]{{1,3,0},{2,2,0},{2,4,0},{3,3,0},{7,3,0},{7,7,1},{7,8,1},{8,8,1},{6,8,1},{7,9,1}};
			int clusters = 2;
			System.out.println(TD2(points,mean(points,2)));
		}
		
		//Calculate initialization of points with furthest first
		private static double[][] furthestFirst(double[][] points, int clusters) {
			double[] randomStart = points[(int)(Math.random()*points.length)];
			double[][] means = new double[clusters][2];
			means[0] = new double[2];
			means[0][0] = randomStart[0];//Only coordinates and not type
			means[0][1] = randomStart[1];
			for(int i = 1; i < clusters; i++) {
				double[] maximumDistance = new double[2];
				double maxDistance = 0;
				for(double[] v:points) {  //For every point
					double shortestDistance = distance(v,means[0],2);
					for(int j = 1; j < i;j++) //For every cluster
						shortestDistance = (distance(v,means[j],2) < shortestDistance) ? distance(v,means[j],2) : shortestDistance; //Checks for the shortest distance to every cluster
					if (maxDistance < shortestDistance) {
						maxDistance = shortestDistance;
						maximumDistance = new double[2];
						maximumDistance[0] = v[0];
						maximumDistance[1] = v[1]; 
					}
				
				}
				means[i]=maximumDistance;
			}
			return means;
		}
		
		//Assign random clusters to points
		private static void randomClusterAssignment(double[][] points, int clusters) {
			for(double[] v: points) 
				v[2] = (int)(Math.random()*clusters);
		}

		//Returns quality of clustering using TD2 
		private static double TD2(double[][] points, double[][] means) {
			double distance = 0.0;
			for(double[] v: points)
				distance = distance + Math.pow(distance(v, (means[(int)(v[2])]),2),2);
			return distance;
		}
		//Returns mean values after using Forgy And Lloyd method
		private static double[][] ForgyLloyd(double[][] points, double[][] means) {
			double[][] oldPoints = clone2DArray(points);
			do {
				oldPoints = clone2DArray(points);
				for(int i = 0; i < points.length; i++)
					points[i][2] = closestMean(points[i], means,2);				
				means = mean(points, means.length);
			}
			while(!deepEquals(points, oldPoints));
			return means;
		}
		
		//Returns mean values after using MacQueen method. Currently just in the order of the points
		private static double[][] MacQueen(double[][] points, double[][] means) {
			double[][] oldPoints = clone2DArray(points);
			do {
				oldPoints = clone2DArray(points);
				for(int i = 0; i < points.length; i++) {
					points[i][2] = closestMean(points[i], means,2);	//could be optimized if nothing is changed			
					means = mean(points, means.length);
				}
			}
			while(!deepEquals(points, oldPoints));
			return means;
		}

		//Calculate the distance using euclidean method with the given norm. Precon. Both points are the same vector dimension
		private static double distance(double[] point1, double[] point2, int euclidean) {
			double distance = 0.0;
			for(int i = 0; i<2; i++)
				distance = distance + Math.pow(Math.abs(point1[i]-point2[i]),euclidean);
			distance = Math.pow(distance, 1.0/euclidean);
			return distance;
		}

		//Calculate the mean values of point in a list
		private static double[][] mean(double[][] points, int clusters) {
			double[][] mean = new double[clusters][2];
			for(int i = 0; i<clusters;i++) {
				mean[i][0] = 0.0;
				mean[i][1] = 0.0;
			}
			double[] numberOfTypes = new double[clusters];
			for(int i = 0; i < clusters; i++) {
				numberOfTypes[i] = 0; 
			}
			//add all coordinates together in each cluster
			for(double[] i: points) {
				int type = (int)(i[2]);
				mean[type][0] = mean[type][0] + i[0];
				mean[type][1] = mean[type][1] + i[1];
				numberOfTypes[type]++;
			}
			//calculate means
			for(int i = 0; i < clusters; i++) {
				double divisor = (numberOfTypes[i]==0) ? 1 : numberOfTypes[i];
				mean[i][0] = mean[i][0]/divisor;
				mean[i][1] = mean[i][1]/divisor;
			}
			return mean;
		}

		//Find the closest mean to given point
		private static int closestMean(double[] point, double[][] means, int euclidean) {
			double shortestDistance = Double.MAX_VALUE;
			int closestMean = 0;
			for(int i = 0; i < means.length; i++){
				double distance = distance(point, means[i], euclidean);
				if(distance < shortestDistance) {
					shortestDistance = distance;
					closestMean = i;
				}
			}
			return closestMean;
		}

		//Compare two dimensional array. Precon. both arrays are same length
		private static boolean deepEquals(double[][] v,double[][] w) {
			int i = 0;
			int j = 0;
			while(i < v.length && j < v[i].length && v[i][j] == w[i][j]) {
				j++;
				if(j==v[i].length) {
					j = 0;
					i++;
				}
			}
			return (i==v.length);
		}

		//Clones two dimensional array
		private static double[][] clone2DArray(double[][] v) {
			double[][] clone = new double[v.length][v[0].length];
			for(int i = 0; i < v.length; i++)
				clone[i]=v[i].clone();
			return clone;				
		}
}
