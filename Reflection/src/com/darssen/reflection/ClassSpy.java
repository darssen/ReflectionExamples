package com.darssen.reflection;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Member;
import static java.lang.System.out;

import java.lang.annotation.Annotation;


enum ClassMember { CONSTRUCTOR, FIELD, METHOD, CLASS, ANNOTATIONS, ALL }

@Deprecated
public class ClassSpy {
	public static void main(String... args) {
		try {
			Class<?> c = Class.forName(args[0]);
			out.format("Class:%n  %s%n%n", c.getCanonicalName());

			Package p = c.getPackage();
			out.format("Package:%n  %s%n%n",
					(p != null ? p.getName() : "-- No Package --"));

			for (int i = 1; i < args.length; i++) {
				switch (ClassMember.valueOf(args[i])) {
				case CONSTRUCTOR:
					printMembers(c.getConstructors(), "Constructor");
					break;
				case FIELD:
					printMembers(c.getDeclaredFields(), "Fields");
					break;
				case ANNOTATIONS:
					printAnnotations(c.getDeclaredAnnotations(), "Annotations");
					break;
				case METHOD:
					printMembers(c.getMethods(), "Methods");
					break;
				case CLASS:
					printClasses(c);
					break;
				case ALL:
					printMembers(c.getConstructors(), "Constuctors");
					printMembers(c.getDeclaredFields(), "Fields");
					printMembers(c.getMethods(), "Methods");
					printClasses(c);
					break;
				default:
					assert false;
				}
			}

			// production code should handle these exceptions more gracefully
		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		}
	}
	private static void printMembers(Member[] mbrs, String s) {
		out.format("%s:%n", s);
		for (Member mbr : mbrs) {
			if (mbr instanceof Field){
				Field f = (Field)mbr;
				out.format("  %s%n", f.toGenericString());
				System.out.format("Type: %s%n", f.getType());
				out.format("Fields in Class '%s' containing modifiers:  %s%n",
					       f.getClass().getName(),
					       Modifier.toString(f.getModifiers()));
				out.format("%-8s [ synthetic=%-5b enum_constant=%-5b ]%n",
					       f.getName(), f.isSynthetic(),
					       f.isEnumConstant());
			    System.out.format("GenericType: %s%n", f.getGenericType());
				out.format("  -- declared in: %s%n", f.getDeclaringClass());
			}
			else if (mbr instanceof Constructor){
				Constructor c = (Constructor)mbr;
				out.format("  %s%n", c.toGenericString());
				out.format("  -- declared in: %s%n", c.getDeclaringClass());
			}else if (mbr instanceof Method){
				Method m = ((Method)mbr);
				out.format("  %s%n", m.toGenericString());
				out.format("  -- declared in: %s%n", m.getDeclaringClass());
			}
		}
		if (mbrs.length == 0)
			out.format("  -- No %s --%n", s);
		out.format("%n");
	}

	private static void printAnnotations(Annotation[] annotations, String s){
		out.format("%s:%n", s);
		for(Annotation annotation:annotations){
			out.format("  %s%n", annotation.toString());
		}
		
		
	}
	private static void printClasses(Class<?> c) {
		out.format("Classes:%n");
		Class<?>[] clss = c.getClasses();
		for (Class<?> cls : clss)
			out.format("  %s%n", cls.getCanonicalName());
		if (clss.length == 0)
			out.format("  -- No member interfaces, classes, or enums --%n");
		out.format("%n");
	}
}