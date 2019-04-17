import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Point {

   private  List<Double> pointList = new ArrayList<>();  // wspolrzedne
    int label;  // 0 albo 1
    double bias;
    Point(List<Double> listOfX){
        pointList=listOfX;
        pointList.add(-1.0);
    }

    Point(String line[]){
        for(int i=0;i<line.length-1;i++)
            pointList.add(Double.parseDouble(line[i]));
        if(line[line.length-1].equals("Iris-virginica") || line[line.length-1].equals("Iris-versicolor")){
            if(line[line.length-1].equals("Iris-virginica"))
                label=1;
            else
                label=0;
        }else {
            label = Integer.parseInt(line[line.length - 1]);
        }
        System.out.println(line[line.length-1]+"    "+label);
        bias=-1.0;
        pointList.add(bias);
      //  System.out.println(pointList+"  "+label);
    }

    public int getLabel(){ return label;}
    public List<Double> getPointList(){ return pointList;}
}
