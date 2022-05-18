package vn.hangdinh.bohoshop.comparator;
import vn.hangdinh.bohoshop.model.Product;
import java.util.Comparator;

public class ComparatorNameASC implements Comparator<Product> {
   public int compare (Product o1,Product o2){
       return o1.getTitle().compareTo(o2.getTitle());
    }
}
