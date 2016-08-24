package core.nashorn;

import org.testng.annotations.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by svanlaar on 18/07/2016.
 */
public class nashornTutorial {

    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    public void say_js_hello() throws ScriptException {
        engine.eval("print('Hello World!');");
    }

    @Test
    public void js_read_from_file() throws FileNotFoundException, ScriptException, NoSuchMethodException {
        engine.eval(new FileReader("./src/main/js/PageObject.js"));

        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("printName", "Sven van Laar");

    }
}
