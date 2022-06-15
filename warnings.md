
Last Updated: 15 June 2022
==
Number of tests run on above date: **168**

1) Use **JUnit4** (not JUnit5) for testing only. A Test should look like this:
```java
    import org.junit.Assert;
    import org.junit.Test;

    public class FourQueensTest {
     @Test
     public void solve() {
         Assert.assertEquals(3L, FourQueens.solve());
      }
    }
```

2) Windows is weird even in 2021. Do

`JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8 mvn clean install`

if you see the stupid

`unmappable character (0x8D) for encoding windows-1252`

error on maven compilation.

3) Do, for instance, `mvn test -Dtest=FourQueensTest`, to run a single test; specify the complete name of the test class, otherwise you may get a weird message: ` No tests were executed!`
