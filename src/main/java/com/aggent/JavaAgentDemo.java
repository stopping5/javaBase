package com.aggent;

import java.lang.instrument.Instrumentation;

/**
 * @Classname: JavaAgentDemo
 * @Description: java agent 测试
 * @Date: 2023/4/13 5:09 下午
 * @author: stopping
 */
public class JavaAgentDemo {
    /**
     * premain javaagent 入口
     * */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("I am JavaAgent");
        instrumentation.addTransformer(new ClassFileTranstation());
    }
}
