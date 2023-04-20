package com.aggent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Classname: ClassFileTranstation
 * @Description: TODO
 * @Date: 2023/4/13 6:00 下午
 * @author: stopping
 */
public class ClassFileTranstation implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("loader class name : " + className);
        return new byte[0];
    }
}
