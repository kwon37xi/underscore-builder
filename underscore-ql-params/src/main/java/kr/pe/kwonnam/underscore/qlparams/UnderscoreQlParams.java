package kr.pe.kwonnam.underscore.qlparams;

import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreTransformer;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Underscore QL Parameter Holder and related {@link UnderscoreTransformer}s.
 */
public class UnderscoreQlParams {
    /**
     * Whether generate positional parameter with index or not. default false
     */
    private boolean withPositionalIndex;

    /**
     * Query Parameters
     */
    private List<Object> queryParameters;

    /**
     * Constructor.
     * <br>
     * <code>withPositionalIndex = false</code>
     */
    public UnderscoreQlParams() {
        this(false);
    }

    /**
     * Constructor.
     *
     * @param withPositionalIndex whether generate positional parameter with index(for JPQL/HQL) or not.
     */
    public UnderscoreQlParams(boolean withPositionalIndex) {
        this.withPositionalIndex = withPositionalIndex;
        queryParameters = new ArrayList<Object>();
    }

    /**
     * instantiate UnderscoreQlParams with positional index option
     *
     * @return this
     */
    public static UnderscoreQlParams withPositionalIndex() {
        return new UnderscoreQlParams(true);
    }

    public boolean isWithPositionalIndex() {
        return withPositionalIndex;
    }

    /**
     * Query Parameters as List
     *
     * @return query parameters
     */
    public List<Object> getQueryParameters() {
        return Collections.unmodifiableList(queryParameters);
    }

    /**
     * Query Parameters as array
     *
     * @return query parameters
     */
    public Object[] getQueryParameterArray() {
        return queryParameters.toArray(new Object[queryParameters.size()]);
    }

    public int getCurrentPositionalIndex() {
        return queryParameters.size();
    }

    /**
     * add a query parameter.
     *
     * @param param a query parameter
     * @return positional parameter string(<code>? or ?n</code>)
     */
    protected String addParam(Object param) {
        queryParameters.add(param);
        if (withPositionalIndex) {
            return "?" + getCurrentPositionalIndex();
        }
        return "?";
    }

    /**
     * Transform params to query parameters.
     * <br>
     * Usage: <br>
     * <pre>
     * UnderscoreQlParams qlParams = new UnderscoreQlParams();
     * underscoreStringBuilder.__("AND user_id = %s AND user_name = %s ", qlParams.params("scott", "tiger"));
     * </pre>
     *
     * @param params query parameters. Any collection or array in this argument will be regareded as just one parameter.
     * @return UnderscoreQlParamsTransformer that will generate query string and parameters.
     */
    public UnderscoreQlParamsTransformer params(Object... params) {
        return new UnderscoreQlParamsTransformer(this, params);
    }

    /**
     * Transform inParams to query parameters for IN cluase.
     * <br>
     * Usage: <br>
     * <pre>
     * UnderscoreQlParams qlParams = new UnderscoreQlParams();
     * String[] userIds = new String[] {"scott", "mark", "jane"};
     * underscoreStringBuilder.__("AND user_id IN (%s)", qlParams.inParams(userIds));
     * </pre>
     *
     * @param inParams query parameters for one IN clause
     * @return UnderscoreQlParameterTrasformer that will generate query string and parameters.
     */
    public UnderscoreQlInParamsTransformer inParams(Object[] inParams) {
        return new UnderscoreQlInParamsTransformer(this, inParams);
    }

    /**
     * inParams for primitive booleans
     *
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(boolean[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * inParams for primitive bytes
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(byte[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * inParams for primitive chars
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(char[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * inParams for primitive  shorts
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(short[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * inParams for primitive ints
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(int[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * inParams for primitive longs
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(long[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * inParams for primitive floats
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(float[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * inParams for primitive doubles
     * @see #inParams(Object[])
     */
    public UnderscoreQlInParamsTransformer inParams(double[] inParams) {
        return inParams(ArrayUtils.toObject(inParams));
    }

    /**
     * Transform inParams to query parameters for IN cluase.
     * <br>
     * Usage: <br>
     * <pre>
     * UnderscoreQlParams qlParams = new UnderscoreQlParams();
     * List&lt;String&gt; userIds = new ArrayList&lt;String&gt;(); // ["scott", "mark", "jane"]
     * underscoreStringBuilder.__("AND user_id IN (%s)", qlParams.inParams(userIds);
     * </pre>
     *
     * @param inParams query parameters for one IN clause
     * @return UnderscoreQlParameterTrasformer that will generate query string and parameters.
     */
    public UnderscoreQlInParamsTransformer inParams(Iterable<?> inParams) {
        return new UnderscoreQlInParamsTransformer(this, inParams);
    }

    /**
     * bind query parameters to {@link PreparedStatement}.
     *
     * @param preparedStatement JDBC PreparedStatement
     * @throws SQLException sql exception
     */
    public void bindParameters(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement == null) {
            throw new IllegalArgumentException("preparedStatement must not be null.");
        }

        for (int i = 0; i < queryParameters.size(); i++) {
            preparedStatement.setObject(i + 1, queryParameters.get(i));
        }
    }
}
