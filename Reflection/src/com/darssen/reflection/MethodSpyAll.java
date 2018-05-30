package com.darssen.reflection;

/* Copied from MethodSpy.java, but shows all methods in a class
 * 
 * Samples of how to call (Basically needs the class to inspect as argument)
 * 		java MethodSpy java.lang.Class
 * */
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import static java.lang.System.out;

import java.beans.Transient;
import java.lang.annotation.Annotation;


public class MethodSpyAll {
    private static final String  fmt = "%24s: %s%n";

    // for the morbidly curious
    <E extends RuntimeException> void genericThrow() throws E {}
    @Transient
    @Deprecated
    public static void main(String... args) {
	try {
	    Class<?> c = Class.forName(args[0]);
	    Method[] allMethods = c.getDeclaredMethods();
	    for (Method m : allMethods) {
		out.format("%s%n", m.toGenericString());
		
		
		StringBuilder sb = new StringBuilder("");
		String delim ="";
		for (Annotation annotations: m.getDeclaredAnnotations()){
			sb.append(delim).append(annotations);
		    delim = ",";
		}
		out.format(fmt,"Annotations", sb);

		out.format(fmt, "ReturnType", m.getReturnType());
		out.format(fmt, "GenericReturnType", m.getGenericReturnType());

		Class<?>[] pType  = m.getParameterTypes();
		Type[] gpType = m.getGenericParameterTypes();
		for (int i = 0; i < pType.length; i++) {
		    out.format(fmt,"ParameterType", pType[i]);
		    out.format(fmt,"GenericParameterType", gpType[i]);
		}

		Class<?>[] xType  = m.getExceptionTypes();
		Type[] gxType = m.getGenericExceptionTypes();
		for (int i = 0; i < xType.length; i++) {
		    out.format(fmt,"ExceptionType", xType[i]);
		    out.format(fmt,"GenericExceptionType", gxType[i]);
		}
	    }

        // production code should handle these exceptions more gracefully
	} catch (ClassNotFoundException x) {
	    x.printStackTrace();
	}
    }
}