import java.io.*;
import java.util.*;

/**
 * @authors: Nick Mariani, Rick Olenick
 */
class NearestNeighborTSP
{
	private static LinkedList<Cities> cities = new LinkedList<Cities>(); //list that holds the cities
	private static LinkedList<Cities> remaining = new LinkedList<Cities>(); //list that holds remaining cities to be checked
	private static LinkedList<Cities> bestCycle = new LinkedList<Cities>(); //list that holds best cycle
	private static LinkedList<Cities> currentCycle = new LinkedList<Cities>(); //list that holds the current tour
	protected static Cities point = new Cities(0, null, null); //create new city
	private static double lowestTotal = Double.MAX_VALUE; // best total cost
	private static double dist = 0.0; //distance between cities
	private static int removeIndex = 0; //this index will be removed from remaining cities in the future
	private static double best = 0.0; //best cost for each cycle

	/**
	 * main method
	 * Creates output file
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		boolean found = false;
		String file = null;
		
		while(!found)
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //new BufferedReader will read the filename
			System.out.print("Enter filename: ");
			file = reader.readLine();
			File fileName = new File(file);
			
			if(fileName.exists())
			{
				reader.close();
				cities = Location.getCities(fileName);
				found = true;
			} 
			else
			{
				System.out.println("File " + file + " does not exist in this directory. Please try again.");
			}
		}
		
		NearestNeighborTSP nn = new NearestNeighborTSP();
		double start = System.currentTimeMillis(); //start time
		nn.nearestNeighbor(cities);  
		double end = System.currentTimeMillis(); //end time

		System.out.println("NAME: " + file);                	 //print header
		System.out.println("TYPE: TOUR");                        //print header
		System.out.println("DIMENSION: " + cities.size());       //print header
		System.out.println("TOUR_SECTION");                      //print header

		for(int i = 0; i < bestCycle.size() - 1; i++) //print tours in order
		{
			System.out.println(bestCycle.get(i).getCityNumber());
		}
		System.out.println(-1); // -1 means done
		System.out.println("Time elapsed: " + (end - start)/1000 + " seconds."); //print time in sec
		System.out.println("Total cost: " + lowestTotal);
	} 

	/**
	 * This method generates the best possible Nearest Neighbor tour.
	 * @param cities
	 */
	private void nearestNeighbor(LinkedList<Cities> cities)
	{       
		for(int i = 0; i < cities.size(); i++) 
		{
			best = 0.0;// current best tour cost
			Cities startCity = cities.get(i); //the cycle starts with this city
			Cities currentCity = cities.get(i); 
			currentCycle = new LinkedList<Cities>(); //reset currentCycle
			remaining = new LinkedList<Cities>(); //reset remaining cities
			currentCycle.add(startCity);

			for(int j = 0; j < cities.size(); j++)
			{
				if(cities.get(j).getCityNumber() == cities.get(i).getCityNumber())
				{ 
					//do nothing
				}
				else
				{	
					remaining.add(cities.get(j)); //populate the list with every city besides the start
				}
			}

			getNextCity(startCity, currentCity);

			if(best < lowestTotal) //best cost so far
			{
				lowestTotal = best;
				bestCycle = currentCycle; //best cycle so far
			}
		}
	}

	/**
	 * This method finds the closest city to its specific location.
	 * Each closest city will be added to the cycle list.
	 * @param startCity
	 * @param currentCity
	 */
	private void getNextCity(Cities startCity, Cities currentCity)
	{
		while(remaining.size() != 0) //remaining is not empty
		{
			Cities nextCity = null; //reset nextCity
			double lowestCost = Double.MAX_VALUE; //reset lowest cost to max
			dist = 0.0; //reset distance

			for(int k = 0; k <= remaining.size() - 1; k++)
			{
				dist = point.getDist(currentCity, remaining.get(k)); //get distance between the two cities

				if(dist < lowestCost) //if the current distance is less than the lowest cost
				{
					lowestCost = dist; //the distance is the new lowest cost
					nextCity = remaining.get(k); //this city comes next in the cycle
					removeIndex = k; //store index to remove from remaining in future
				}
				else
				{
					//do nothing
				}
			}
			currentCycle.add(nextCity); //add the closest city to cycle
			currentCity = nextCity; //this is now the current city
			remaining.remove(removeIndex); //remove current city from remaining
			best += lowestCost; 
		}		
		dist = point.getDist(startCity, currentCity); //distance between start and end
		currentCycle.add(startCity);
		best += dist; //best tour cost with this starting value
	}
}
