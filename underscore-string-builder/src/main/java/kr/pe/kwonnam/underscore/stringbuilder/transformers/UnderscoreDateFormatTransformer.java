package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.UnderscoreTransformer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Convert Date to String with {@link java.text.SimpleDateFormat}.
 */
public class UnderscoreDateFormatTransformer implements UnderscoreTransformer<Date> {

    private String dateFormat;

    public UnderscoreDateFormatTransformer(String dateFormat) {
        if (dateFormat == null) {
            throw new IllegalArgumentException("dateFormat must not be null.");
        }
        this.dateFormat = dateFormat;
    }

    @Override
    public CharSequence transform(Date appended) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(appended);
    }
}
