package com.transactrules.accounts;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.transactrules.accounts.configuration.AccountType;
import com.transactrules.accounts.runtime.accounts.Account;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by 313798977 on 2017/08/11.
 */
public class CodeGenTest {
    @Test
    public void TestCodeGenerationTemplate() {
        StringWriter writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates//accountType.mustache");
        try {
            mustache.execute(writer, AccountTypeFactory.createSavingsAccountType()).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertThat(true, is(true));

    }

    @Test
    public void EvaluateJS() throws URISyntaxException, IOException, ScriptException, NoSuchMethodException {

        StringWriter writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("templates//accountType.mustache");

        try {
            mustache.execute(writer,TestUtility.CreateLoanGivenAccountType()).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

        engine.eval(writer.toString());

        Invocable invocable = (Invocable) engine;

        Account account = new Account();

        AccountType accountType = TestUtility.CreateLoanGivenAccountType();

        account.initialize(accountType);

        Object result = invocable.invokeFunction("createLoanGiven", account, accountType);
        System.out.println(result);
        System.out.println(result.getClass());
    }
}
