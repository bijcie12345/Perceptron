import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {

    String path;
    int length=0,quantity;
    List<Point> l=new ArrayList<>();
    ReadFile(String path)  {
        this.path=path;
        Read();
    }

    public void Read()  {
        try {
            String line[];
            Scanner s = new Scanner(new File(path));
            while (s.hasNextLine()) {
                quantity++;
                line = s.nextLine().toString().split(",");
                if(getLength() == 0)
                    setLength(line);
                l.add(new Point(line));
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    private void setLength(String line[]){
        length=line.length;
    }
    public int getLength(){
        return length;
    }
    public List<Point> getPointList(){
        return l;
    }
    public int getQuantity() {
        return quantity;
    }
}
