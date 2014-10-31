import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Location
{
	BufferedReader reader;
	static LinkedList<Cities> cities = new LinkedList<Cities>();
	static String temp;
	static String[] tempArray;
	static String cityNumber;
	static double xValue;
	static double yValue;
	static Cities city;

	/**
	 * @param fileName
	 * @return List<Cities>
	 * @throws IOException
	 */
	static LinkedList<Cities> getCities(File fileName) throws IOException
	{
		//Sets up reader
		try{

			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String  thisLine = null;
			Boolean coordSection = false;
			while ((thisLine = reader.readLine()) != null) 
			{
				if(coordSection)
				{
					if(!(thisLine.equals("EOF")))
					{
						thisLine = thisLine.trim();
						tempArray = thisLine.split("\\s+");
						//splits temp into an array so we can access the data
						//All the data is formatted as City Number, x value, y value
						cityNumber = tempArray[0];  //the first information of the coordinates is the city number
						int cityNum = Integer.parseInt(cityNumber);
						xValue = Double.parseDouble(tempArray[1]); //second is the x coordinate
						yValue = Double.parseDouble(tempArray[2]);	//last is the y coordinate
						cities.add(city = new Cities(cityNum, xValue, yValue)); //this uses our city class to
						//make a new city which consists of a city number, xValue, yValue. Then adds it to an array 
						//Cities for later use.
					}
				}
				if(thisLine.equals("NODE_COORD_SECTION"))
				{
					coordSection = true;
				}
			}
			reader.close();  //closes reader
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return cities;
	}

}
