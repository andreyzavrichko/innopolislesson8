import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankAtmTest {

    //Добавить параметризацию и проверить остальные позитивные и негативные кейсы проверок карты и пароля
    @Test //Заменить на @ParametrizedTest
    //@ValueSource(список значений для подстановки)
    public void shouldCheckPositiveTest(int count) {
        final boolean res = BankATM.checkCardAndPassword("6228123123", 888888, count);
        Assertions.assertTrue(res);
    }

    @Test
    public void shouldCheckNegativeTest() {
        final boolean res = BankATM.checkCardAndPassword("62281231", 888888, 1);
        Assertions.assertFalse(res);
    }

    @Test
    public void shouldAuthPositiveTest() {
        final boolean res = BankATM.auth();
        Assertions.assertTrue(res);
    }


}
