package pls.scraper.input;

import org.springframework.stereotype.Component;

@Component
/**
 * Convert HTML field containing unit price or calories to corresponding representation
 *
 * Note: relies on unit price being specified in £X.YY format, will fail if price is £X without the pennies
 */
public class NumberValueConverter {

    public int toNumberIgnoreNonDigits(String value) {
        try {
            return Integer.parseInt(value.replaceAll("\\D", ""));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("'" + value + "' doesn't contain a parseable number");
        }
    }


}
