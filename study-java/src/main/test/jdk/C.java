package jdk;
import java.io.InputStream.*;

class A {}

class B extends A {}

public class C {
  static void test(Object x) {
	  System.out.println();
    print("Testing x of type " + x.getClass());
    print("x instanceof A " + (x instanceof A));
    print("x instanceof B "+ (x instanceof B));
    print("A.isInstance(x) "+ A.class.isInstance(x));
    print("B.isInstance(x) " +B.class.isInstance(x));
    print("x.getClass() == A.class " +
      (x.getClass() == A.class));
    print("x.getClass() == B.class " +
      (x.getClass() == B.class));
    print("x.getClass().equals(A.class)) "+
      (x.getClass().equals(A.class)));
    print("x.getClass().equals(B.class)) " +
      (x.getClass().equals(B.class)));
  }
  private static void print(String string) {
	System.out.println(string);
	
}
public static void main(String[] args) {
    test(new A());
    test(new B());
  } 
}