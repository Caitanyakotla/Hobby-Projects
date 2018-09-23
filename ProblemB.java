import java.util.*;
import java.lang.*;


class ProblemB
{  
    public static void main(String args[])
    {
        ArrayList<Double> xCoordinates = new ArrayList<Double>();
        ArrayList<Double> yCoordinates = new ArrayList<Double>();
        
        /*Creating Scanner Object to read input from User*/
        Scanner in = new Scanner(System.in);
        /*Reading number of people you ask for directions*/
        String s = in.nextLine();
        int n = Integer.valueOf(s);
        
        double distance;
        double angleRadians = 0;
        
        double xCoordinatesSum = 0;
        double yCoordinatesSum = 0;
        double xCoordinatesAverage = 0;
        double yCoordinatesAverage = 0;
        double maxDistance = 0;
        /*Continue loop until n becomes 0*/
        while(n != 0){
            /*Reading one line of input*/
            String line = in.nextLine();
            
            /*Split the line into words*/
            String[] words = line.split(" ");
            
            /*Initial x Coordinate*/
            double x = Double.valueOf(words[0]);
            /*Initial y Coordinate*/
            double y = Double.valueOf(words[1]);
            /*Initial angle at start*/
            double angle = Double.valueOf(words[3]);
            
            /*Iterate through remaining instruction in the line*/
            for(int i=4; i < words.length; i= i+2){
                /*If the instruction is walk*/
                if(words[i].equals("walk")){
                    /*Length of distance to walk*/
                    distance = Double.valueOf(words[i+1]);
                    /*Convert the angle in degrees to radians*/
                    angleRadians = Math.toRadians(angle);
                    
                    /*Update x and y Coordinates based on Distance and Angle*/
                    x += distance * Math.cos(angleRadians);
                    y += distance * Math.sin(angleRadians);
                }
                else if(words[i].equals("turn")){
                    angle += Double.valueOf(words[i+1]);
                }
                
            }
            n--;
            xCoordinates.add(x);
            yCoordinates.add(y);
            xCoordinatesSum += x;
            yCoordinatesSum += y;
            
            if(n == 0){
                /*Calculating average Coordinates*/
                xCoordinatesAverage = xCoordinatesSum/xCoordinates.size();
                yCoordinatesAverage = yCoordinatesSum/yCoordinates.size();
                
                /*Calculating distance from worst directions*/
                maxDistance = CalculateDistance(xCoordinates,yCoordinates,xCoordinatesAverage,yCoordinatesAverage);
                
                /*Print the results*/
                System.out.println(xCoordinatesAverage+" "+yCoordinatesAverage+" "+maxDistance);
                
                /*Clearing the ArrayLists for next */
                xCoordinates.clear();
                yCoordinates.clear();
                
                /*Clearing the sum of Coordinates*/
                xCoordinatesSum = 0;
                yCoordinatesSum = 0;
                
                /*Reading n again*/
                s = in.nextLine();
                n = Integer.valueOf(s); /* If n !=0 loop again else break*/
            }
        }
    }

    /*Method to calculate the distance of worst directions*/
    public static double CalculateDistance( ArrayList<Double> x, ArrayList<Double> y, double xAverage, double yAverage){
        double distance = 0;
        double max = 0;
        double xDistance = 0;
        double yDistance = 0;
        for(int i=0; i < x.size() ; i++){
                xDistance = x.get(i)-xAverage;
                yDistance = y.get(i)-yAverage;
                distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
                if(distance > max)
                    max = distance;
        }
        return max;
    }
    
}
