import java.util.List;
import java.util.ArrayList;

public class Myrden_Brandon_P1 {
    List<Integer> arr = new ArrayList<>();

    int size = 0;
    Integer m = null;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Integer minimum(){
        if(isEmpty()){
            return null;
        }
        else {
            return m;
        }
    }

    public void insert(Integer e){
        if(isEmpty()){
            arr.add(0, e);
            m = arr.get(0);
            size++;
        }
        else {
            for(int i=arr.size()-1; i>=0; i--){
                if(e<arr.get(i)){
                    if(arr.get(arr.size()-1).equals(arr.get(i))){
                        arr.add(i+1,arr.get(i));
                        arr.set(i,e);
                        size++;
                    }
                    else {
                        arr.set(i + 1, arr.get(i));
                        arr.set(i, e);
                    }
                }
                else {
                    if(arr.get(arr.size()-1).equals(arr.get(i))){
                        arr.add(i+1, e);
                        size++;
                        m = arr.get(0);
                        break;
                    }
                    else {
                        arr.set(i+1, e);
                        m = arr.get(0);
                        break;
                    }
                }
            }
        }
    }

    public Integer remove(){
        if(isEmpty()){
            return null;
        }
        else {
            Integer removed = m;
            m = arr.get(1);
            arr.remove(0);
            size--;
            return removed;
        }
    }
}

