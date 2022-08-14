package mockito;

public class ValidNumber {
    public ValidNumber() {
    }

    public boolean check(Object o) {
        if (o instanceof Integer) {
            return (Integer) o < 10 && (Integer) o >= 0;
        }
        return false;
    }

    public boolean checkZero(Object o) {
        if (o instanceof Integer) {
            if ((Integer) o == 0) {
                throw new ArithmeticException("Zero is not allowed");
            }
            return true;
        }
        return false;
    }

    public int doubleToInt(Object o) {
        if (o instanceof Double) {
            return ((Double) o).intValue();
        }
        return 0;
    }
}
