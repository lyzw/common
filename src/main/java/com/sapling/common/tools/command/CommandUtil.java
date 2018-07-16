package com.sapling.common.tools.command;

import com.sapling.common.tools.io.NormalIOUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 命令行工具类
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/11/1.
 * @since v1.0
 */
public class CommandUtil {

    public static void main(String[] args) throws IOException, InterruptedException {
        CommandExecResult result = CommandUtil.simpleCommand("javac -version", "GB2312");
        System.out.println("result:" + result.getResult());
        System.out.println("error:" + result.getError());
        System.out.println("exit code: " + result.getExitCode());
    }


    /**
     * 执行简单的命令行语句
     *
     * @param command 命令行
     * @param charset 字符集
     * @return 命令行执行的结果
     * @throws IOException          异常
     * @throws InterruptedException 中断异常
     */
    public static CommandExecResult simpleCommand(String command, String charset)
            throws IOException, InterruptedException {
        CommandExecResult result = new CommandExecResult();
        Process process = Runtime.getRuntime().exec(command);
        //标准输出
        InputStream stdInputStream = process.getInputStream();
        //异常数据
        InputStream errInputStream = process.getErrorStream();
        process.waitFor();
        result.setExitCode(process.exitValue());
        result.setResult(new String(NormalIOUtil.toBytes(stdInputStream), charset));
        result.setError(new String(NormalIOUtil.toBytes(errInputStream), charset));
        try {
            stdInputStream.close();
            errInputStream.close();
            process.destroy();
        } catch (IOException e) {

        }
        return result;
    }


    /**
     * 命令行操作结果
     */
    static class CommandExecResult {
        Integer exitCode;
        String result;
        String error;

        public Integer getExitCode() {
            return exitCode;
        }

        public void setExitCode(Integer exitCode) {
            this.exitCode = exitCode;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
