package com.transactrules.accounts;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.transactrules.accounts.test.DoubleCalculation;
import net.openhft.compiler.CachedCompiler;
import net.openhft.compiler.CompilerUtils;
import org.junit.Test;
import com.github.mustachejava.MustacheFactory;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Administrator on 11/29/2016.
 */

public class CompilerTest {
    static final File parent;

    private static final String COM_TRANSACTRULES_ACCOUNTS_TEST_DOUBLE_CALCULATION_IMPL = "com.transactrules.accounts.test.DoubleCalculationImpl";

    static {
        File parent2 = new File("essence-file");
        if (parent2.exists()) {
            parent = parent2;

        } else {
            parent = new File(".");
        }
    }

    @Test
    public  void test_compiler() throws ClassNotFoundException {
        // CompilerUtils.setDebug(true);
        // added so the test passes in Maven.
        CompilerUtils.addClassPath("target/test-classes");
//        ClassLoader loader = CompilerTest.class.getClassLoader();
//        URLClassLoader urlClassLoader = new URLClassLoader(((URLClassLoader)loader).getURLs(), null);
//        Class fooBarTee1 = urlClassLoader.loadClass("eg.FooBarTee");

        // this writes the file to disk only when debugging is enabled.
        CachedCompiler cc = CompilerUtils.DEBUGGING ?
                new CachedCompiler(new File(parent, "src/test/java"), new File(parent, "target/compiled")) :
                CompilerUtils.CACHED_COMPILER;

        String sb = "package com.transactrules.accounts.test;\r\n" +
                "public class DoubleCalculationImpl implements DoubleCalculation {\r\n" +
                "    @Override\r\n" +
                "    public double calculate() {\r\n" +
                "        return 1+1;\r\n" +
                "    }\r\n" +
                "}\r\n";

        Class doubleCalcClass =  cc.loadFromJava(COM_TRANSACTRULES_ACCOUNTS_TEST_DOUBLE_CALCULATION_IMPL, sb);

        // add a debug break point here and step into this method.

        DoubleCalculation calc = null;
        try {
            calc = (DoubleCalculation) doubleCalcClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        assertThat(calc.calculate(), is(2.0));

    }

    @Test
    public void TestCodeGenerationTemplate()
    {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates//accountType.mustache");
        try {
            mustache.execute(new PrintWriter(System.out), AccountTypeFactory.createSavingsAccountType() ).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(true,is(true));

    }
}
