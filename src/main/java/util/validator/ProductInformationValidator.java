package util.validator;

public class ProductInformationValidator {

    private static final ProductInformationValidator INSTANCE = new ProductInformationValidator();

    public static ProductInformationValidator getInstance() {
        return INSTANCE;
    }

    private ProductInformationValidator() {}

    private static final String PRODUCT_NAME_FORMAT = ".{2,50}";

    public boolean validate(String productName, double price, int amount) {
        return productName.matches(PRODUCT_NAME_FORMAT) &&
                price > 0 && price < 10000 &&
                amount > 0 && amount < 100000;
    }
}