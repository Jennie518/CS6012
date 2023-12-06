package assignment08;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PathFinderTest {

    private final String testInputFile = "/Users/zhanyijun/Desktop/CS6012/assignment/src/assignment08/maze/classic.txt";
    private final String testOutputFile = "test_output.txt";
    private final String expectedOutputFile = "/Users/zhanyijun/Desktop/CS6012/assignment/src/assignment08/maze/classicSol.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Ensure the output file is deleted before each test
        Files.deleteIfExists(Paths.get(testOutputFile));
    }

    @Test
    public void testSolveMaze() throws IOException {
        PathFinder.solveMaze(testInputFile, testOutputFile);

        String actualOutput = new String(Files.readAllBytes(Paths.get(testOutputFile)));
        String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFile)));

        assertEquals(expectedOutput.trim(), actualOutput.trim(), "The solved maze does not match the expected solution.");
    }

    @Test
    public void testFileNotFound() {
        String nonExistentFile = "nonexistent.txt";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            PathFinder.solveMaze(nonExistentFile, testOutputFile);
        });

        assertTrue(exception.getCause() instanceof FileNotFoundException);
    }

    // Additional test methods for other scenarios
}
