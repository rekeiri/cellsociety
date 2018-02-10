package cellsociety_team06;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell_Hexagon extends Cell{
	
	public Cell_Hexagon(String cellType, double centerXLocation, double centerYLocation, double sideLength, String[] properties, Color[] colors, int initialState){
		super(cellType, centerXLocation, centerYLocation, sideLength, properties, colors, initialState);
		makePolygon();
	}
	
	protected void makePolygon(){
		double[] mySixPoints = new double[]
				{	
					myCenterXLocation, myCenterYLocation - mySideLength, // very top point
					myCenterXLocation - Math.sqrt(3)*mySideLength/2, myCenterYLocation - mySideLength/2, // upper left point 
					myCenterXLocation + Math.sqrt(3)*mySideLength/2, myCenterYLocation - mySideLength/2, // upper right point
					myCenterXLocation - Math.sqrt(3)*mySideLength/2, myCenterYLocation + mySideLength/2, // lower left point 
					myCenterXLocation + Math.sqrt(3)*mySideLength/2, myCenterYLocation + mySideLength/2, // lower right point
					myCenterXLocation, myCenterYLocation + mySideLength // very bottom point
				};
		
		myPolygon = new Polygon(mySixPoints);
		myPolygon.setFill(myColors[currentState]);
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
	
	@Override
	public boolean checkTopAdjacency(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() + Math.sqrt(3)*mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + 1.5*mySideLength))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() - Math.sqrt(3)*mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() + 1.5*mySideLength))));
	}
	
	@Override 
	public boolean checkLeftAdjacency(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() + Math.sqrt(3)*mySideLength)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc()))));
	}
	
	@Override 
	public boolean checkRightAdjacency(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() - Math.sqrt(3)*mySideLength)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc()))));
	}
	
	@Override 
	public boolean checkBotAdjacency(Cell cell){
		return ((locationMatch(myCenterXLocation, (cell.showCenterXLoc() + Math.sqrt(3)*mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - 1.5*mySideLength))) ||
				(locationMatch(myCenterXLocation, (cell.showCenterXLoc() - Math.sqrt(3)*mySideLength/2)) && locationMatch(myCenterYLocation, (cell.showCenterYLoc() - 1.5*mySideLength))));
	}
	
	@Override
	public boolean checkSideAdjacency(Cell cell){
		return (checkTopAdjacency(cell) ||
				checkLeftAdjacency(cell) ||
				checkRightAdjacency(cell) ||
				checkBotAdjacency(cell)
		       );
	}
	
	@Override
	public boolean checkDiagonalAdjacency(Cell cell){
		return checkDiagonalAdjacency(cell);		
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