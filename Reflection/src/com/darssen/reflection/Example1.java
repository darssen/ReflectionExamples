package com.darssen.reflection;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



/**
 * Example1 for reflection in Java
 *
 * @author Darssen
 */
public class Example1 implements Serializable{
	enum E { A, B }
	static Object o = new Object() { 
        public void m() {} 
    };
    

	public static void main(String [] args)
	{
		Class a = "foo".getClass();
		System.out.println(a.getName());
		
		/*Commented out next two lines because IDE returns null for Sistem.console()*/ 
		//Class b = System.console().getClass();
		//System.out.println(b.getName());	
		
		Class c = E.A.getClass();
		System.out.println(c.getName());
		
		byte[] bytes = new byte[1024];
		Class d = bytes.getClass();
		System.out.println(d.getName());
		
		Set<String> s = new HashSet<String>();
		Class e = s.getClass();
		System.out.println(e.getName());
		
		Class f = int[][][].class;
		System.out.println(f.getName());
		
		final Class g = o.getClass().getEnclosingClass();
		System.out.println(g.getName());
		
	}
	
}
