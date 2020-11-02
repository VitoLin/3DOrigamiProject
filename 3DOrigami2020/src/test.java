import java.util.HashMap;

public class test {
    public static void main(String[]args)
    {
        HashMap<Integer, Integer> test = new HashMap<Integer, Integer>();

        test.put( 1, 0);
        System.out.println( test.get(1) );
        int nval = test.get(1) + 4;
        test.put( 1, nval );
        System.out.println( test.get(1) );
    }
}
