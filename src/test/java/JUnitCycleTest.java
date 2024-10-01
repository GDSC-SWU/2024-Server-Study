import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    //처음 한 번만 - 데이터베이스 연결, 테스트 환경 초기화 등
    @BeforeAll
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }
    //테스트 케이스 시작 전 매번 - 테스트 메서드에서 사용하는 객체 초기화, 테스트에 필요한 값 넣을 때 등
    @BeforeEach
    public void beforeEach(){
        System.out.println("@befoerEach");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @Test
    public void test3(){
        System.out.println("test3");
    }
    //데이터베이스 연결 종료, 공통적으로 사용하는 자원 해제 등
    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }
    //테스트 이후 특정 데이터 삭제 등
    @AfterEach
    public void afterEach(){
        System.out.println("@AfterEach");
    }
}
