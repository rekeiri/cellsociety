package cellsociety_team06;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell_Triangle extends Cell{
	private boolean upwardTriangle;
	
	public Cell_Triangle(String cellType, double centerXLocation, double centerYLocation, double sideLength, String[] properties, Color[] colors, int initialState, boolean upward){
		super(cellType, centerXLocation, centerYLocation, sideLength, properties, colors, initialState);
		upwardTriangle = upward;
		makePolygon();
	}
	
	public Cell_Triangle(String cellType, double centerXLocation, double centerYLocation, double sideLength, String[] properties, Color[] colors, int initialState, double initialEnergyInput, boolean upward){
		super(cellType, centerXLocation, centerYLocation, sideLength, properties, colors, initialState, initialEnergyInput);
		upwardTriangle = upward;
		makePolygon();
	}
	
	public Cell_Triangle(String cellType, double centerXLocation, double centerYLocation, double sideLength, String[] properties, Color[] colors, int initialState, double initialEnergyInput, boolean vision, boolean upward){
		super(cellType, centerXLocation, centerYLocation, sideLength, properties, colors, initialState, initialEnergyInput, vision);
		upwardTriangle = upward;
		makePolygon();
	}
	
	protected void makePolygon(){
		double[] myThreePoints;
		if (upwardTriangle){
			myThreePoints = new double[]
					{	
						myCenterXLocation, myCenterYLocation - Math.sqrt(3)*mySideLength/4, // very top point
						myCenterXLocation - mySideLength/2, myCenterYLocation + Math.sqrt(3)*mySideLength/4, // left point 
						myCenterXLocation + mySideLength/2, myCenterYLocation + Math.sqrt(3)*mySideLength/4 // right point
					};
		}
		else {
			myThreePoints = new double[]
					{	
						myCenterXLocation, myCenterYLocation + Math.sqrt(3)*mySideLength/4, // very bottom point
						myCenterXLocation - mySideLength/2, myCenterYLocation - Math.sqrt(3)*mySideLength/4, // left point 
						myCenterXLocation + mySideLength/2, myCenterYLocation - Math.sqrt(3)*mySideLength/4 // right point
					};
		}
		myPolygon = new Polygon(myThreePoints);
		myPolygon.setFill(myColors[currentState]);
		myPolygon.setStroke(Color.GREY);
		myPolygon.setStrokeWidth(mySideLength/50);
	}
	
	public void resetEnergy(){
		myEnergy = initialEnergy;
	}
	
	public void setEnergy(double value){
		myEnergy = value;
	}
	
	public void changeEnergy(double value){
		myEnergy = myEnergy + value;
	}
	
	public boolean checkTopAdjacency(Cell cell){
		if (!upwardTriangle)
			return (locationMatch(myCenterXLocation, cell.showCenterXLoc()) && locationMatch(myCenterYLocation, cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2));
		else return false;
	}
	
	public boolean checkBotAdjacency(Cell cell){
		if (upwardTriangle)
			return (locationMatch(myCenterXLocation, cell.showCenterXLoc()) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2)));
		else return false;
	}
	
	public boolean checkRightAdjacency(Cell cell){
		return (locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength/2)) && locationMatch(myCenterYLocation, cell.showCenterYLoc()));
	}
	
	public boolean checkLeftAdjacency(Cell cell){
		return (locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc())));
	}
	
	@Override
	public boolean checkSideAdjacency(Cell cell){
		return (checkLeftAdjacency(cell) || checkRightAdjacency(cell) || checkBotAdjacency(cell) || checkTopAdjacency(cell));
		/*if (upwardTriangle)
			return ((myCenterXLocation == (cell.showCenterXLoc() ) && myCenterYLocation == (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2)) ||
					(myCenterXLocation == (cell.showCenterXLoc() - mySideLength/2) && myCenterYLocation == (cell.showCenterYLoc())) ||
					(myCenterXLocation == (cell.showCenterXLoc() + mySideLength/2) && myCenterYLocation == (cell.showCenterYLoc()))
					);
		else 
			return ((myCenterXLocation == (cell.showCenterXLoc() ) && myCenterYLocation == (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2)) ||
					(myCenterXLocation == (cell.showCenterXLoc() - mySideLength/2) && myCenterYLocation == (cell.showCenterYLoc())) ||
					(myCenterXLocation == (cell.showCenterXLoc() + mySideLength/2) && myCenterYLocation == (cell.showCenterYLoc()))
					);*/
	}
	
	private boolean upwardTriangleDiagonalCheck_Top(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, cell.showCenterXLoc()) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2))));
	}
	
	private boolean upwardTriangleDiagonalCheck_Bot(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2))));
	}
	
	private boolean upwardTriangleDiagonalCheck_Middle(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength)) && locationMatch(myCenterYLocation, cell.showCenterYLoc())) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength)) && locationMatch(myCenterYLocation, cell.showCenterYLoc())));
	}
	
	@Override
	public boolean checkDiagonalAdjacency(Cell cell){
		if (upwardTriangle)
			return (upwardTriangleDiagonalCheck_Top(cell) ||
					upwardTriangleDiagonalCheck_Middle(cell) ||
					upwardTriangleDiagonalCheck_Bot(cell)
					);
		else 
			return (downwardTriangleDiagonalCheck_Bot(cell) ||
					downwardTriangleDiagonalCheck_Middle(cell) ||
					downwardTriangleDiagonalCheck_Top(cell)
					);
	}
	
	private boolean downwardTriangleDiagonalCheck_Bot(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, cell.showCenterXLoc()) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - Math.sqrt(3)*mySideLength/2))));
	}
	
	private boolean downwardTriangleDiagonalCheck_Top(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + Math.sqrt(3)*mySideLength/2))));
	}
	
	private boolean downwardTriangleDiagonalCheck_Middle(Cell cell){
		return (locationMatch(myCenterXLocation, (cell.showCenterXLoc() - mySideLength)) && locationMatch(myCenterYLocation, cell.showCenterYLoc())) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() + mySideLength)) && locationMatch(myCenterYLocation, cell.showCenterYLoc()));
	}
	
	public double showEnergy(){
		return myEnergy;
	}
	
	public void resetChronon(){
		myChronon = 0;
	}
	
	public void updateChronon(double value){
		myChronon = value;
	}
	
	public double showChronon(){
		return myChronon;
	}
	
	public int showCurrentState(){
		return currentState;
	}
	
	public String showCurrentProperty(){
		return myProperties[currentState];
	}
	
	public int showFutureState(){
		return futureState;
	}
	
	public String showFutureProperty(){
		return myProperties[futureState];
	}
	
	public void update(){
		currentState = futureState;
		myPolygon.setFill(myColors[currentState]);
	}
	
	public void setFutureState(int nextState){
		futureState = nextState;
	}
	
}
