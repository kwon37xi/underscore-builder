# Underscore Builders for Java
Build strings and SQL/JPQL/HQL more conveniently.

## Requirements
* Java 6 or later

## Underscore String Builder
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-string-builder/badge.svg)](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-string-builder)

[UnderscoreStringBuilder](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/UnderscoreStringBuilder.java) is a replacement for [java.util.StringBuilder](https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html).
Actually I made this for building dynamic SQL/JPQL/HQL with pure java codes. Please refer to `UnderscoreQlParams`.

### Dependency Configuration
#### Gradle
```groovy
compile "kr.pe.kwonnam.underscorebuilder:underscore-string-builder:${version}"
```
#### Maven
```xml
<dependency>
    <groupId>kr.pe.kwonnam.underscorebuilder</groupId>
    <artifactId>underscore-string-builder</artifactId>
    <version>${version}</version>
</dependency>
```

### Core API
There are two core methods in [UnderscoreStringBuilder](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/UnderscoreStringBuilder.java).

* `<A> UnderscoreStringBuilder __(boolean appendable, A appendee, UnderscoreTransformer<A> transformer, UnderscoreTransformer<? super CharSequence>... extraTransformers)`
* `UnderscoreStringBuilder sub(boolean appendable, UnderscoreSubBuild subBuild, UnderscoreTransformer<? super UnderscoreStringBuilder> transformer, UnderscoreTransformer<? super CharSequence>... extraTransformers)`

These two methods append the `appendee` or the result of `subBuild` to the `UnderscoreStringBuilder` character sequence only when `appendable == true`, also transform the `appendde` with `transformer` and `extraTransformers`.

* `prefix*()` : prefix the `appendde` every time until `prefixOff()` called.
* `suffix*()` : suffix the `appendee` every time unti `suffixOff()` called.

* [UnderscoreStringBuilderTransformers](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/UnderscoreStringBuilderTransformers.java) : Built-in transformers.
  * join
  * format // String.format
  * dateFormat // with SimpleDateFormat
  * wrap
  * multiply
  * trim : see [Trim](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/transformers/trim/Trim.java) and [TrimOpts](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-string-builder/src/main/java/kr/pe/kwonnam/underscore/stringbuilder/transformers/trim/TrimOpts.java)

### Usage examples
```java
import kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilder;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.*;

UnderscoreStringBuilder underscoreStringBuilder = new UnderscoreStringBuilder();

int[] ints = new int[] { 1, 2, 3, 4, 5};

underscoreStringBuilder
    .__("hello! ") // append "hello! "
    .__(user.getName() != null, user.getName()) // append user.getName() when user.getName() is not null
    .__(", ", join(ints)) // append "1, 2, 3, 4, 5"
    .__("=", multiply(20, "-"), wrap("[", "]")) // append "[=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=]"
    .__("Hello [%10s] [%7d]~", format("World", 12345)) // append ""Hello [     World] [  12345]~"
    .sub(user != null, new UnderscoreSubBuild() { // append underscoreSubBuilder result when user is not null
           @Override
           public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
               underscoreSubBuilder
                    .suffix("\n") // suffix every appendee with "\n"
                    .__(user.getName())
                    .__(user.getBirthday(), dateFormat("yyyy/MM/dd"));
           }
       });
```

## Underscore QL Params
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-ql-params/badge.svg)](https://maven-badges.herokuapp.com/maven-central/kr.pe.kwonnam.underscorebuilder/underscore-ql-params)

With [UnderscoreQlparams](https://github.com/kwon37xi/underscore-builder/blob/master/underscore-ql-params/src/main/java/kr/pe/kwonnam/underscore/qlparams/UnderscoreQlParams.java) and `UnderscoreStringBuilder`, you can buile SQL/JPQL/HQL with  more conveniently.

### Dependency Configuration
#### Gradle
```groovy
compile '"kr.pe.kwonnam.underscorebuilder:underscore-ql-params:${version}"
```
#### Maven
```xml
<dependency>
    <groupId>kr.pe.kwonnam.underscorebuilder</groupId>
    <artifactId>underscore-ql-params</artifactId>
    <version>${version}</version>
</dependency>
```

### Core API
  * `UnderscoreQlParamsTransformer params(Object... params)`
  * `UnderscoreQlInParamsTransformer inParams(Iterable<?> inParams|Object[] inParams|...)`
  * `List<Object> getQueryParameters()` : Query Parameters
  * `void bindParameters(PreparedStatement preparedStatement)` : bind query parameters to `PreparedStatement`
  *
### Usage examples
```java
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.join;
import static kr.pe.kwonnam.underscore.stringbuilder.UnderscoreStringBuilderTransformers.trim;
import static kr.pe.kwonnam.underscore.stringbuilder.transformers.trim.TrimOpts.trimOpts;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

User user = new User();
user.setUserId(10001L);
user.setName("UnderscoreQlParams");
user.setBirthday(new SimpleDateFormat("yyyy/MM/dd").parse("2015/12/11"));
user.setEmail("someone@email.com");

List<String> zipCodes = Arrays.asList("12345", "56789", "58391");

UnderscoreQlParams qlParams = new UnderscoreQlParams(); // If you want JPQL indexed positional parameters, use UnderscoreQlParams.withPositionalIndex()
UnderscoreStringBuilder usb = new UnderscoreStringBuilder()
    .__("SELECT ").suffixNewLine()
    .__(", ", join(User.COLUMNS))
    .__("FROM users as u")
    .sub(user != null, new UnderscoreSubBuild() {
        @Override
        public void subbuild(UnderscoreStringBuilder underscoreSubBuilder) {
            underscoreSubBuilder.prefix("\n   ")
                .__(user.getUserId() != null, "AND user_id = %s", qlParams.params(user.getUserId()))
                .__(isNotEmpty(user.getName()), "AND name = %s", qlParams.params(user.getName()))
                .__(user.getBirthday() != null, "AND birthday = %s", qlParams.params(user.getBirthday()))
                .__(CollectionUtils.isNotEmpty(zipCodes), "AND zip_code in (%s)", qlParams.inParams(zipCodes));
        }
    }, trim(trimOpts().prefix("WHERE ").prefixOverrides("AND ", "OR ")))
    .__("LIMIT %s", qlParams.params(10));

log.info("UnderscoreStringBuilder with UnderscoreQlParams : {}", usb.toString());
==>
"SELECT user_id, name, email, birthday, mobile_phone, home_phone, address, zip_code
FROM users as u
WHERE user_id = ?
   AND name = ?
   AND birthday = ?
   AND zip_code in (?, ?, ?)
LIMIT ?"

log.info("Query Parameters : {}", qlParams.getQueryParameters());
==>
[10001, UnderscoreQlParams, Fri Dec 11 00:00:00 KST 2015, 12345, 56789, 58391, 10]
```
