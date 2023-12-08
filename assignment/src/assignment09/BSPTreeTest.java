package assignment09;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BSPTreeTest {
    private static final int N = 1000; // Number of segments for the tests
    private Random rand;

    @Before
    public void setUp() {
        rand = new Random(42); // fixed seed for reproducibility
    }

    @Test
    public void testBulkConstruction() {
        // Generate worst-case vertical segments (e.g., with increasing x coordinates)
        List<Segment> segments = IntStream.range(0, N)
                .mapToObj(i -> new Segment(i, 0, i, 10))
                .collect(Collectors.toList());

        long startTime = System.nanoTime();
        BSPTree bulkTree = new BSPTree(new ArrayList<>(segments));
        long endTime = System.nanoTime();
        long durationBulk = (endTime - startTime);

        // Print out the time taken for bulk construction
        System.out.println("Bulk construction time (nanoseconds): " + durationBulk);
    }

    @Test
    public void testIndividualInsertions() {
        // Generate worst-case vertical segments (e.g., with increasing x coordinates)
        List<Segment> segments = IntStream.range(0, N)
                .mapToObj(i -> new Segment(i, 0, i, 10))
                .collect(Collectors.toList());

        BSPTree tree = new BSPTree();
        long startTime = System.nanoTime();
        segments.forEach(tree::insert);
        long endTime = System.nanoTime();
        long durationIndividual = (endTime - startTime);

        // Print out the time taken for individual insertions
        System.out.println("Individual insertions time (nanoseconds): " + durationIndividual);
    }



    private BSPTree setupTreeWithSegments() {
        // This method should create a BSP tree and populate it with a set of segments
        // For the purpose of this test, we are returning an empty tree
        // Replace this with actual segment setup as needed
        return new BSPTree();
    }
}
