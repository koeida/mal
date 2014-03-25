package mal;

import java.io.IOException;

import mal.types.*;
import mal.readline;
import mal.reader;

public class step1_read_print {
    // read
    public static MalVal READ(String str) throws MalThrowable {
        return reader.read_str(str);
    }

    // eval
    public static MalVal EVAL(MalVal ast, String env) {
        return ast;
    }

    // print
    public static String PRINT(MalVal exp) {
        return types._pr_str(exp, true);
    }

    // REPL
    public static MalVal RE(String env, String str) throws MalThrowable {
        return EVAL(READ(str), env);
    }

    public static void main(String[] args) throws MalThrowable {
        String prompt = "user> ";

        if (args[0].equals("--raw")) {
            readline.mode = readline.Mode.JAVA;
        }
        while (true) {
            String line;
            try {
                line = readline.readline(prompt);
                if (line == null) { continue; }
            } catch (readline.EOFException e) {
                break;
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                break;
            }
            try {
                System.out.println(PRINT(RE(null, line)));
            } catch (MalContinue e) {
                continue;
            } catch (MalError e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            } catch (reader.ParseError e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
}