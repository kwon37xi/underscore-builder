package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;
import kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.Trim;
import kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts;

/**
 * Trim string contents.
 */
public class UnderscoreTrimTransformer implements UnderscoreTransformer<CharSequence> {

    private Trim trim;

    public UnderscoreTrimTransformer(TrimOpts trimOpts) {
        trim = new Trim();

        if (trimOpts != null) {
            trim.setPrefix(trimOpts.prefix());
            trim.setPrefixOverrides(trimOpts.prefixOverrides());
            trim.setSuffix(trimOpts.suffix());
            trim.setSuffixOverrides(trimOpts.suffixOverrides());
        }
    }

    @Override
    public CharSequence transform(CharSequence appendee) {
        if (appendee == null) {
            return "";
        }

        return trim.trim(appendee.toString());
    }
}
