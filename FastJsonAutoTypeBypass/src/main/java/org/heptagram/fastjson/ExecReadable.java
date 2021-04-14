package org.heptagram.fastjson;

import java.io.IOException;
import java.nio.CharBuffer;

public class ExecReadable implements AutoCloseable {
    private EvalReadable eval;

    public EvalReadable getEval() {
        return eval;
    }

    public void setEval(EvalReadable eval) {
        this.eval = eval;
    }

    @Override
    public void close() throws Exception {

    }
}

class EvalReadable implements Readable {
    private String cmd;

    public String getCmd() {
        System.out.println("EvalReadable getCmd() "+cmd);
        try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", cmd});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
