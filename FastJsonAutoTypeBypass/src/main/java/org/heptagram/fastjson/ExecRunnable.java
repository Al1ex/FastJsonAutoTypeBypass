package org.heptagram.fastjson;

import java.io.IOException;

public class ExecRunnable implements AutoCloseable {
    private EvalRunnable eval;

    public EvalRunnable getEval() {
        return eval;
    }

    public void setEval(EvalRunnable eval) {
        this.eval = eval;
    }

    @Override
    public void close() throws Exception {

    }
}

class EvalRunnable implements Runnable {
    private String cmd;

    public String getCmd() {
        System.out.println("EvalRunnable getCmd() "+cmd);
        try {
            Runtime.getRuntime().exec(new String[]{"cmd","/c",cmd});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void run() {

    }
}