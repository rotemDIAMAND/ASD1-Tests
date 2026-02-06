package test1;


import java.util.Arrays;
import java.util.List;

public class MainTrain3 {
    public static void main(String[] args) {
        testChain();
        testMap();
        testFallback();
        testPeek();
        testCompose();
		System.out.println("done");
    }

    private static void testChain() {
        Transformer<String, Integer> length = s->s.length();
        Transformer<String, Integer> chained = 
        length.chain(Arrays.asList(x->x*2, x->x+5, x->x*3));
        if (33!=chained.transform("ptm")){ // ptm->3->6->11->33
            System.out.println("testChain failed (-7)");
        }
    }

    private static void testMap() {
        Transformer<String, Integer> length = s->s.length();
        Transformer<List<String>, List<Integer>> lenList = length.map();
        List<Integer> li=lenList.transform(Arrays.asList("hello","world","!"));
        if(li==null || li.size()!=3 || li.get(0)!=5 || li.get(1)!=5 || li.get(2)!=1){
            System.out.println("testMap failed (-7)");
        }        
    }

    private static void testFallback() {
        Transformer<String, Integer> lengthOrNull = s->(s!=null?s.length():null);
        Transformer<String, Integer> safeLength = lengthOrNull.fallback(s->0);        
        if(safeLength.transform("hello")!=5 || safeLength.transform(null)!=0)
            System.out.println("testFallback failed (-7)");

    }

    private static void testPeek() {
        Transformer<Integer, Integer> timesTwo=x->x*2;
        int r[]={0};
        Transformer<Integer, Integer> debug = timesTwo.peek(x->r[0]=x);
        int x= debug.transform(4); 
        if(x!=8 || r[0]!=x)
            System.out.println("testPeek failed (-7)");
    }

    private static void testCompose() {
        Transformer<String, Integer> length = String::length;
        Transformer<Integer, Integer> timesTwo = x -> x * 2;
        Transformer<String, Integer> combined = timesTwo.compose(length);

        if (combined.transform("Hello") != 10) {
            System.out.println("testCompose failed (-7)");
        }
    }

}


