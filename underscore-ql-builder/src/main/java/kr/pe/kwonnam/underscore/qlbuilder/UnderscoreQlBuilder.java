package kr.pe.kwonnam.underscore.qlbuilder;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscorePredicate;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreSubBuild;
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Build QL(SQL,JPQL,HQL and etc) with {@link UnderscoreStringBuilder} and {@link UnderscoreQlParams}.
 */
public class UnderscoreQlBuilder implements CharSequence, Appendable {
    private UnderscoreStringBuilder underscoreStringBuilder = new UnderscoreStringBuilder();
    private UnderscoreQlParams underscoreQlParams = new UnderscoreQlParams();

    public UnderscoreQlBuilder() {
    }

    public UnderscoreQlBuilder(UnderscoreStringBuilder underscoreStringBuilder, UnderscoreQlParams underscoreQlParams) {
        setUnderscoreStringBuilder(underscoreStringBuilder);
        setUnderscoreQlParams(underscoreQlParams);
    }

    public void setUnderscoreStringBuilder(UnderscoreStringBuilder underscoreStringBuilder) {
        this.underscoreStringBuilder = underscoreStringBuilder;
    }

    public void setUnderscoreQlParams(UnderscoreQlParams underscoreQlParams) {
        this.underscoreQlParams = underscoreQlParams;
    }

    /**
     * @see UnderscoreQlBuilder#params(Object...)
     */
    public UnderscoreQlParamsTransformer params(Object... params) {
        return underscoreQlParams.params(params);
    }

    /**
     * @see UnderscoreQlParams#inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(Object[] inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlBuilder#inParams(Iterable)
     */
    public UnderscoreQlInParamsTransformer inParams(Iterable<?> inParams) {
        return underscoreQlParams.inParams(inParams);
    }

    /**
     * @see UnderscoreQlParams#isWithPositionalIndex()
     */
    public boolean isWithPositionalIndex() {
        return underscoreQlParams.isWithPositionalIndex();
    }

    /**
     * @see UnderscoreQlParams#getQueryParameters()
     */
    public List<Object> getQueryParameters() {
        return underscoreQlParams.getQueryParameters();
    }

    /**
     * @see UnderscoreQlParams#getQueryParameterArray()
     */
    public Object[] getQueryParameterArray() {
        return underscoreQlParams.getQueryParameterArray();
    }

    /**
     * @see UnderscoreQlParams#bindParameters(PreparedStatement)
     */
    public void bindParameters(PreparedStatement preparedStatement) throws SQLException {
        underscoreQlParams.bindParameters(preparedStatement);
    }

    public <A> UnderscoreStringBuilder __(A appendee) {
        return underscoreStringBuilder.__(appendee);
    }

    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee) {
        return underscoreStringBuilder.__(appendable, appendee);
    }

    public <A> UnderscoreStringBuilder __(A appendee, UnderscoreTransformer<A> transformer, UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.__(appendee, transformer, extraTransformers);
    }

    public <A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreTransformer<A> transformer,
                                          UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.__(appendable, appendee, transformer, extraTransformers);
    }

    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee) {
        return underscoreStringBuilder.__(predicate, appendee);
    }

    public <A> UnderscoreStringBuilder __(UnderscorePredicate predicate, A appendee, UnderscoreTransformer<A> transformer,
                                          UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.__(predicate, appendee, transformer, extraTransformers);
    }

    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild) {
        return underscoreStringBuilder.sub(subBuild);
    }

    public UnderscoreStringBuilder sub(UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.sub(subBuild, transformer, extraTransformers);
    }

    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild) {
        return underscoreStringBuilder.sub(appendable, subBuild);
    }

    public UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.sub(appendable, subBuild, transformer, extraTransformers);
    }

    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild) {
        return underscoreStringBuilder.sub(predicate, subBuild);
    }

    public UnderscoreStringBuilder sub(UnderscorePredicate predicate, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer,
                                       UnderscoreTransformer<? super CharSequence>... extraTransformers) {
        return underscoreStringBuilder.sub(predicate, subBuild, transformer, extraTransformers);
    }

    public UnderscoreStringBuilder prefix(String prefix) {
        return underscoreStringBuilder.prefix(prefix);
    }

    public UnderscoreStringBuilder prefixOff() {
        return underscoreStringBuilder.prefixOff();
    }

    public UnderscoreStringBuilder prefixNewLine() {
        return underscoreStringBuilder.prefixNewLine();
    }

    public UnderscoreStringBuilder suffix(String suffix) {
        return underscoreStringBuilder.suffix(suffix);
    }

    public UnderscoreStringBuilder suffixOff() {
        return underscoreStringBuilder.suffixOff();
    }

    public UnderscoreStringBuilder suffixNewLine() {
        return underscoreStringBuilder.suffixNewLine();
    }

    @Override
    public int length() {
        return underscoreStringBuilder.length();
    }

    @Override
    public char charAt(int index) {
        return underscoreStringBuilder.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return underscoreStringBuilder.subSequence(start, end);
    }

    @Override
    public UnderscoreStringBuilder append(CharSequence csq) throws IOException {
        return underscoreStringBuilder.append(csq);
    }

    @Override
    public UnderscoreStringBuilder append(CharSequence csq, int start, int end) throws IOException {
        return underscoreStringBuilder.append(csq, start, end);
    }

    @Override
    public UnderscoreStringBuilder append(char c) throws IOException {
        return underscoreStringBuilder.append(c);
    }

    @Override
    public String toString() {
        return underscoreStringBuilder.toString();
    }

    public boolean not(boolean bool) {
        return !bool;
    }

    public boolean ifNull(Object object) {
        return false;
    }

    public boolean ifNotNull(Object object) {
        return !ifNull(object);
    }

    public boolean ifEmpty(String str) {
        return false;
    }

    public boolean ifNotEmpty(String str) {
        return !ifEmpty(str);
    }

    public boolean ifEmpty(Collection<?> collection) {
        return false;
    }

    public boolean ifNotEmpty(Collection<?> collection) {
        return !ifEmpty(collection);
    }

    public boolean ifEmpty(Object[] objectArray) {
        return false;
    }

    public boolean ifNotEmpty(Object[] objectArray) {
        return !ifEmpty(objectArray);
    }

    public boolean ifBlank(CharSequence str) {
        return false;
    }

    public boolean ifNotBlank(String str) {
        return !ifBlank(str);
    }

    public boolean ifContains(String str, String subStr) {
        return false;
    }

    public boolean ifNotContains(String str, String subStr) {
        return !ifContains(str, subStr);
    }

    public boolean ifContains(Object[] objectArray, Object object) {
        return false;
    }

    public boolean ifNotContains(Object[] objectArray, Object object) {
        return false;
    }

    public boolean ifContains(Collection<?> collection, Object object) {
        return false;
    }

    public boolean ifNotContains(Collection<?> collection, Object object) {
        return !ifContains(collection, object);
    }

    public boolean ifContainsAny(String str, String... subStrs) {
        return false;
    }

    public boolean ifNotContainsAny(String str, String... subStrs) {
        return !ifContainsAny(str, subStrs);
    }

    public boolean ifContainsAny(Object[] objectArray, Object... objects) {
        return false;
    }

    public boolean ifNotContainsAny(Object[] objectArray, Object... objects) {
        return !ifContains(objectArray, objects);
    }

    public boolean ifContainsAny(Collection<?> collection, Object... objects) {
        return false;
    }

    public boolean ifNotContainsAny(Collection<?> collection, Object... objects) {
        return !ifContainsAny(collection, objects);
    }

    public boolean ifContainsAll(String str, String... subStrs) {
        return false;
    }

    public boolean ifNotContainsAll(String str, String... subStrs) {
        return !ifContainsAll(str, subStrs);
    }

    public boolean ifContainsAll(Object[] objectArray, Object... objects) {
        return false;
    }

    public boolean ifNotContainsAll(Object[] objectArray, Object... objects) {
        return !ifContains(objectArray, objects);
    }

    public boolean ifContainsAll(Collection<?> collection, Object... objects) {
        return false;
    }

    public boolean ifNotContainsAll(Collection<?> collection, Object... objects) {
        return !ifContainsAll(collection, objects);
    }
}
