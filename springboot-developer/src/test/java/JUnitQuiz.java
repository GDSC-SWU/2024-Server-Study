import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JUnitQuiz {
    @Test
    public void junitTest() {
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        // 1. number1이 양수인지 확인
        assertThat(number1).isPositive();

        // 2. number2은 0인지 확인
        assertThat(number2).isZero();

        // 3. number3이 음수인지 확인
        assertThat(number3).isNegative();

        // 4. number1은 number2보다 큰지 확인
        assertThat(number1).isGreaterThan(number2);

        // 5. number3은 number2보다 작은지 확인
        assertThat(number3).isLessThan(number2);
    }
}
