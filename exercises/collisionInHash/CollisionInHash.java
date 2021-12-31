public class CollisionInHash {
	 	public static void main(String[] args){
			if(args.length != 2){
				System.out.println("Usage: java CollisionInHash <elements> <spaces>");				
				return;
			}
			int elements = Integer.parseInt(args[0]);
			int length = Integer.parseInt(args[1]);
			System.out.println("Chance of collision: " + collisionPercent(elements, length));
		}

		private static double collisionPercent(int elements, int length){
			if (elements == 0)
				return 1;
			return collisionPercent(elements-1,length)*((length-elements+0.0)/length);
		}
}
