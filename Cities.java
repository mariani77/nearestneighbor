public class Cities 
{
	int cityNumber;
	Double xValue;
	Double yValue;

	/**
	 * 
	 * Constructor for Cities class
	 * @param cityNumber
	 * @param xValue
	 * @param yValue
	 */
	public Cities(int cityNumber, Double xValue, Double yValue) {
		super();
		this.cityNumber = cityNumber;
		this.xValue = xValue;
		this.yValue = yValue;
	}

	/**
	 * 
	 * This method finds the distance between two cities.
	 * @param x
	 * @param y
	 * @return
	 */
	protected Double getDist(Cities x, Cities y)
	{
		Double dist;
		Double x1 = x.getxValue();
		Double x2 = y.getxValue();
		Double y1 = x.getyValue();
		Double y2 = y.getyValue();

		dist = Math.sqrt(Math.pow(x2-x1, 2.0) + Math.pow(y2-y1, 2.0));
		return dist;
	}

	/**
	 * 
	 * @return
	 */
	protected int getCityNumber() {
		return cityNumber;
	}

	/**
	 * 
	 * @return
	 */
	protected Double getxValue() {
		return xValue;
	}

	/**
	 * 
	 * @return
	 */
	protected Double getyValue() {
		return yValue;
	}
}
