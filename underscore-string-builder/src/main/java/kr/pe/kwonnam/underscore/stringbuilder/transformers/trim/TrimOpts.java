package kr.pe.kwonnam.underscore.stringbuilder.transformers.trim;

import kr.pe.kwonnam.underscore.stringbuilder.transformers.UnderscoreTrimTransformer;

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

    public String prefix() {
        return prefix;
    }

    /**
     * @see Trim#setPrefix(String)
     */
    public TrimOpts prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String suffix() {
        return suffix;
    }

    /**
     * @see Trim#setSuffix(String)
     */
    public TrimOpts suffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

    public List<String> prefixOverrides() {
        return prefixOverrides;
    }

    /**
     * @see Trim#setPrefixOverrides(List)
     */
    public TrimOpts prefixOverrides(String... prefixOverrides) {
        this.prefixOverrides = Arrays.asList(prefixOverrides);
        return this;
    }

    public List<String> suffixOverrides() {
        return suffixOverrides;
    }

    /**
     * @see Trim#setSuffixOverrides(List)
     */
    public TrimOpts suffixOverrides(String... suffixOverrides) {
        this.suffixOverrides = Arrays.asList(suffixOverrides);
        return this;
    }
}
