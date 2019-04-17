import java.util.Collections;
import java.util.List;

public class Perceptron {

    double[] weights;
    double lr=0.1;
    List<Point> trainList;
    List<Point> testList;
    int totalError=0;
    double threshold=0.1;
    boolean f=true;
    int tp=0,fn=0,fp=0,tn=0;
    int length=0;
    double precisionD=0,recallD=0,fD=0;


    Perceptron(List<Point> trainList,List<Point> testList){
        this.trainList=trainList;
        this.testList=testList;
    }
    Perceptron(List<Point> trainList){
        this.trainList=trainList;
    }

    void setRandomWeigths(){
        int length=trainList.get(0).getPointList().size();
        this.length=length-1;
        weights=new double[length];
        double kp=Math.random();
        for(int i=0;i<weights.length-1;i++)
            weights[i] =kp;
        System.out.println(kp);
        weights[weights.length-1]=threshold;
    }


    int guess(List<Double> list){
        double sum = 0;
        for(int i=0;i<list.size()-1;i++){
            sum+=list.get(i)*weights[i];
        }
        sum-=weights[list.size()-1];
        int output = sign(sum);
        return output;
    }
    void test(List<Point> list){
        zeroMatrix();
        totalError=0;
        for(int i=0;i<list.size();i++) {
            int guess = guess(list.get(i).getPointList());
            this.addMatrix(list.get(i).label,guess);
        }
        precision();
        recall();
        F();
        System.out.print("F: "+getF()+"  ,Precyzja: "+getPrecisionD()+",   Pelosc: "+getRecallD());
        dokladnosc();
        System.out.println("tp "+tp+" tn "+tn+" fn "+fn+" fp "+fp);

    }

    void train(List<Point> list){
        zeroMatrix();
        totalError=0;
        for(int i=0;i<list.size();i++) {
            int guess = guess(list.get(i).getPointList());
            this.addMatrix(list.get(i).label,guess);
            int error = list.get(i).getLabel() - guess;
            totalError+=Math.abs(error);
            for (int p = 0; p < weights.length-1; p++) {
                weights[p] += error * list.get(i).getPointList().get(p) * lr;//inputss[i]
            }
            weights[weights.length-1]-=lr*error;
        }
        Collections.shuffle(list);
        precision();
        recall();
        F();
        System.out.print("F: "+getF()+"  ,Precyzja: "+getPrecisionD()+",   Pelosc: "+getRecallD());
        dokladnosc();

        // System.out.println("totalError: "+totalError+"       "+weights[weights.length-1]);
        for(int w=0;w<weights.length;w++)
            if(totalError==0) {
                f=false;
            }

    }

    // the activation function
    int sign(double n){
        if(n>=0)  //1 to theta
            return 1;
        else
            return 0;
    }

    public void addMatrix(int l,int g){
        if(l == 1){
            if(g==1)
                tp++;
            else
                fn++;
        }else{
            if(g==1)
                fp++;
            else
                tn++;
        }
    }

    public void dokladnosc(){System.out.println("  Dokladnosc:   "+(tp+fp)/(double)(tp+fp+fn+tn));}
    public void F(){ fD=(2*getPrecisionD()*getRecallD())/(getRecallD()+getPrecisionD()); }
    public void precision(){ precisionD=(tp/(double)(tp+fp)); }
    public void recall(){ recallD= (tp / (double) (tp + fn)); }
    public void zeroMatrix(){ tp=0;fn=0;fp=0;tn=0; fD=0; }
    public List<Point> getTrainList(){ return trainList;}
    public List<Point> getTestList() { return testList; }
    public double getPrecisionD(){return precisionD;}
    public double getRecallD(){return recallD;}
    public double getF(){return fD;}
}
