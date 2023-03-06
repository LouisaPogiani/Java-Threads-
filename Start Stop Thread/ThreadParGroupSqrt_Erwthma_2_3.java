
public class ThreadParGroupSqrt_Erwthma_2_3 {

	public static void main(String args[])
	  {
	    int size = 100;
	    int numThreads=10;
	 
	    double[][] a = new double[size][size];
	    for(int i = 0; i < size; i++)
	    	for(int j = 0; j < size; j++)
			    a[i][j] = 1;
	    
	    double[] x = new double[size];
	    double[] y = new double[size];
	    for(int i = 0; i < size; i++) {
			x[i] = 1;
	        y[i] = 0;
	    }

	    long start = System.currentTimeMillis();
	    
	    SqrtGroupThread threads[] = new SqrtGroupThread[numThreads];
	    
	    for(int i = 0; i < numThreads; i++) 
		{
			threads[i] = new SqrtGroupThread(i, numThreads, x, y, a, size);
			threads[i].start();
		}
			
	  

	    long elapsedTimeMillis = System.currentTimeMillis()-start;
	    System.out.println("time in ms = "+ elapsedTimeMillis);

	    /* for debugging */
	    for(int i = 0; i < size; i++) 
	        System.out.println(y[i]); 
	  }
	}

	class SqrtGroupThread extends Thread
	{
		private double [][] table;
		private double [] tableX;
		private double [] tableY;
		private int myStart;
		private int myStop;
		private int size;

		// constructor
		public SqrtGroupThread(int myId, int numThreads, double[] x, double [] y, double [][] array, int size)
		{
			table = array;
			tableX= x;
			tableY = y;
			this.size=size;
			myStart = myId * (size / numThreads);
			myStop = myStart + (size / numThreads);
			if (myId == (numThreads - 1)) myStop = size;
		}

		// thread code
		public void run()
		{
			  for (int i= myStart; i < myStop; i++) {
			        double sum = 0;
			        for (int j = 0; j < size; j++) {
			            sum = sum + table[i][j]*tableX[j];
			        }
			        tableY[i] = sum;
			    }
		}
	}

