import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int kN=0;
        boolean f=true;
        int b=0;
    ReadFile trainFile = new ReadFile("resources\\example1\\train.txt");
    ReadFile testFile = new ReadFile("resources\\example1\\test.txt");
    Perceptron brain = new Perceptron(trainFile.getPointList(),testFile.getPointList());
    brain.setRandomWeigths();
    Scanner scan = new Scanner(System.in);
        while(b!=1000 && brain.f) {
               brain.train(brain.getTrainList());
               f= brain.f;
               b++;
        }
        System.out.println("\n");
        brain.f=true;
        f=true;
        System.out.println("1.Zbior testowy.\n2.Wprowadz wlasny wektor");
        kN = scan.nextInt();
        switch(kN){
                     case 1:
                      brain.test(brain.getTestList());
                  break;
                     case 2:
                        List<Double> tmpList=new ArrayList<>();
                        for(int i=0;i<brain.length;i++)
                            tmpList.add(scan.nextDouble());
                        Point p=new Point(tmpList);
                        int tm = brain.guess(p.getPointList());
                        System.out.println(tm);
                        break;
    }
    }
}
