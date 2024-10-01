import org.junit.jupiter.api.*;

public class JUnitCycleQuiz {
    @BeforeEach
    public void beforeEach(){
        System.out.println("Hello!");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("Goodbye!");
    }

    @Test
    public void junitQuiz3() {
        System.out.println("This is the first test.");
    }

    @Test
    public void junitQuiz4() {
        System.out.println("This is the second test.");
    }
}
