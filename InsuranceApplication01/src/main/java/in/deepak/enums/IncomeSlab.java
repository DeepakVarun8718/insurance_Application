package in.deepak.enums;

public enum IncomeSlab {

	LESS_THAN_2("Less than 2"),
    TWO_TO_FOUR("2-4"),
    FOUR_TO_SEVEN("4-7"),
    SEVEN_TO_TEN("7-10"),
    TEN_PLUS("10+");

    private final String value;

    IncomeSlab(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
