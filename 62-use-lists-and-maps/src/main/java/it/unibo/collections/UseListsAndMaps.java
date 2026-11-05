package it.unibo.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */

public final class UseListsAndMaps {

    static final long POP_AFRICA = 1_110_635_000L;
    static final long POP_EUROPE = 742_452_000L;
    static final long POP_AMERICAS = 972_005_000L;
    static final long POP_ASIA = 4_298_723_000L;
    static final long POP_OCEANIA = 38_304_000L;
    static final long POP_ANTARCTICA = 0L;
    private static final int ELEMS = 100_000;
    private static final int READS = 1000;

    private UseListsAndMaps() {  //void constructor
    }

     /*
     * 5) Measure the performance of inserting new elements in the head of
     * the collection: measure the time required to add 100.000 elements as
     * first element of the collection for both ArrayList and LinkedList,
     * using the previous lists. In order to measure times, use as example
     * TestPerformance.java.
     */
    
    private static void testInsert(final List<Integer> list){
        long time = System.nanoTime();
        for(int i = 0; i < ELEMS; i++){
            list.add(list.size()-list.size(), i);
        }
        final var millis = TimeUnit.NANOSECONDS.toMillis(time); //ms are 0 sometimes, so this is gonna print out also nanoseconds
        IO.println("ms: " + millis);
    }
        
    /*
     * 6) Measure the performance of reading 1000 times an element whose
     * position is in the middle of the collection for both ArrayList and
     * LinkedList, using the collections of point 5. In order to measure
     * times, use as example TestPerformance.java.
     */
    private static void testRead(List<Integer> list){
        long time = System.nanoTime();
        for(int i = 1; i<=READS; i++){ 
            list.get(list.size()/2);                
        }
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        IO.println("ms: " + millis);
        final var nanos = TimeUnit.MILLISECONDS.toNanos(time);
        IO.println("ns: " + nanos);
        }
    /**
     * Program entry point.
     */
    public static void main() {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        ArrayList<Integer> array = new ArrayList<>();
        for (int i=1000; i<2000; i++) {
            array.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
         LinkedList<Integer> array2 = new LinkedList<>(array); //i populate the lnked list with the array list's elements
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int temporary = array.get(array.size() - 1); //it contains the last one
        array.set((array.size() - 1), array.get(array.size() - array.size())); //overwriting the last item with the first one
        array.set(array.size() - array.size(), temporary); //overwriting the first element with the last one, already saved in "temporary"
        //if the instructions hadn't specified otherwise, i would have used GetFirst and GetLast
       
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer element : array) {
            IO.println(element);
        }

        //required testing output

        IO.println("\nInsert test:\n");
        IO.println("ArrayList:");
        testInsert(array);
        IO.println("LinkedList:");
        testInsert(array2);

        IO.println("\nRead test:\n");
        IO.println("ArrayList:");
        testRead(array);
        IO.println("LinkedList:");
        testRead(array2);
       
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        
        Map<String, Long> continentsMap = new HashMap<>();
        continentsMap.put("Africa", POP_AFRICA);
        continentsMap.put("Americas", POP_AMERICAS);
        continentsMap.put("Europe", POP_EUROPE);
        continentsMap.put("Oceania", POP_OCEANIA);
        continentsMap.put("Asia", POP_ASIA);
        continentsMap.put("Antarctica", POP_ANTARCTICA);
        /*
         * 8) Compute the population of the world
         */

        Collection<Long> worldPopulation = continentsMap.values();
        long totWorldPopulation=0;
        for (Long elem : worldPopulation) {
            totWorldPopulation+=elem;
        }
        IO.println("The total population of the world is: " + totWorldPopulation);
    }
}
