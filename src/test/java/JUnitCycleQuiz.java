import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitCycleQuiz {
    @Test
    public void JUnitQuiz3(){
        System.out.println("This is first test");
    }
    @Test
    public void JUnitQuiz4(){
        System.out.println("This is second test");
    }
    @BeforeEach
    public void beforeEachHello(){
        System.out.println("Hello!");
    }
    @AfterAll
    static void afterAllBye(){
        System.out.println("Bye!");
    }
}
