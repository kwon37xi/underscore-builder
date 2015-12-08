package kr.pe.kwonnam.underscore.stringbuilder.transformers;

import java.util.Arrays;
import java.util.List;

/**
 * Options for {@link UnderscoreTrimTransformer}.
 */
public class TrimOpts {
    private String prefix;
    private String suffix;
    private List<String> prefixOverrides;
    private List<String> suffixOverrides;

    public static TrimOpts trimOpts() {
        return new TrimOpts();
    }

    public TrimOpts prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public TrimOpts suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public TrimOpts prefixOverrides(String... overrides) {
        prefixOverrides = Arrays.asList(overrides);
        return this;
    }

    public TrimOpts suffixOverrides(String... overrides) {
        suffixOverrides = Arrays.asList(overrides);
        return this;
    }
}
