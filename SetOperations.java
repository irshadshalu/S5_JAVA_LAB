import java.util.*;

public class SetOperations {
  public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
    Set<T> tmp = new TreeSet<T>(setA);
    tmp.addAll(setB);
    return tmp;
  }

  public static <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
    Set<T> tmp = new TreeSet<T>();
    for (T x : setA)
      if (setB.contains(x))
        tmp.add(x);
    return tmp;
  }

  public static <T> Set<T> difference(Set<T> setA, Set<T> setB) {
    Set<T> tmp = new TreeSet<T>(setA);
    tmp.removeAll(setB);
    return tmp;
  }

  public static <T> Set<T> symDifference(Set<T> setA, Set<T> setB) {
    Set<T> tmpA;
    Set<T> tmpB;

    tmpA = union(setA, setB);
    tmpB = intersection(setA, setB);
    return difference(tmpA, tmpB);
  }

  public static <T> boolean isSubset(Set<T> setA, Set<T> setB) {
    return setB.containsAll(setA);
  }

  public static <T> boolean isSuperset(Set<T> setA, Set<T> setB) {
    return setA.containsAll(setB);
  }
  public static void sout(String s){
    System.out.println(s);
  }

  public static void main(String args[]) {
    
    Scanner in = new Scanner(System.in);
    int n1,n2;

    TreeSet<Integer> set1 = new TreeSet<Integer>();
    TreeSet<Integer> set2 = new TreeSet<Integer>();

    sout(" Number of elements in Set 1: ");
    n1 = in.nextInt();
    sout(" Enter elements in set 1: ");
    for(int i = 0;i < n1; i++)
      set1.add(in.nextInt());

    sout(" Number of elements in Set 2: ");
    n2 = in.nextInt();
    sout(" Enter elements in set 2: ");
    for(int i = 0;i < n1; i++)
      set2.add(in.nextInt());

    sout("set1: " + set1);
    sout("set2: " + set2);

    sout("Union: " + union(set1, set2));
    sout("Intersection: " + intersection(set1, set2));
    sout("Difference (set1 - set2): " + difference(set1, set2));
    sout("Symmetric Difference: " + symDifference(set1, set2));
  }
}
