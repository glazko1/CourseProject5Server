package util.validator;

public class OrderInformationValidator {

    private static final OrderInformationValidator INSTANCE = new OrderInformationValidator();

    public static OrderInformationValidator getInstance() {
        return INSTANCE;
    }

    private OrderInformationValidator() {}

    private static final String REGION_FORMAT = ".{2,15}";
    private static final String LOCALITY_FORMAT = ".{2,50}";
    private static final String STREET_FORMAT = ".{2,100}";
    private static final String HOUSE_FORMAT = "[1-9][0-9]{0,2}";
    private static final String FLAT_FORMAT = "[1-9][0-9]{0,3}";

    public boolean validate(String region, String locality, String street, int house, int flat) {
        return region.matches(REGION_FORMAT) && locality.matches(LOCALITY_FORMAT) &&
                street.matches(STREET_FORMAT) && house > 0 && house < 1000 &&
                flat > 0 && flat < 10000;
    }
}