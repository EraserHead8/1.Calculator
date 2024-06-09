enum RomanNumeral {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8),
    IX(9), X(10), XL(40), L(50), XC(90), C(100);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean isRoman(String s) {
        try {
            RomanNumeral.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String toRoman(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Результат работы меньше единицы, выбрасывается исключение.");
        }

        StringBuilder result = new StringBuilder();
        RomanNumeral[] values = RomanNumeral.values();

        for (int i = values.length - 1; i >= 0; i--) {
            while (number >= values[i].getValue()) {
                number -= values[i].getValue();
                result.append(values[i].name());
            }
        }

        return result.toString();
    }
}