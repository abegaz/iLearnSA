package com.iLearnSA.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.iLearnSA.controller.LoggedPrintStream;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PractiseViewController{
	@FXML public TextArea codeInput;
	@FXML public TextArea codeOutput;

	public void submitBtnClicked()throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{

        // Create logged PrintStreams
        LoggedPrintStream lpsOut = LoggedPrintStream.create(System.out);
        LoggedPrintStream lpsErr = LoggedPrintStream.create(System.err);

        // Set them to stdout / stderr
        System.setOut(lpsOut);
        System.setErr(lpsErr);

        // Print some stuff

		String codeInputString = codeInput.getText();
		System.setProperty("java.home", "C:/Program Files/Java/jdk1.8.0_73");
		// Prepare source somehow.
		String source = "package test; " + codeInputString;

		// Save source in .java file.
		File root = new File("/java"); // On Windows running on C:\, this is C:\java.
		File sourceFile = new File(root, "test/Test.java");
		sourceFile.getParentFile().mkdirs();
		Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));

		// Compile source file.
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, sourceFile.getPath());

		// Load and instantiate compiled class.
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
		Class<?> cls = Class.forName("test.Test", true, classLoader);
		Object instance = cls.newInstance(); // Should print "world".
	    Method main = cls.getMethod("main", String[].class); // Should execute the main program
	    main.invoke(null, new Object[]{null});
//		System.out.println(instance); // Should print "test.Test@hashcode".


        System.out.flush();
//
//        System.err.println("Some error");
//        System.err.flush();

        // Restore System.out / System.err
        System.setOut(lpsOut.underlying);
        System.setErr(lpsErr.underlying);

        // Print the logged output
        System.out.println("----- Log for System.out: -----\n" + lpsOut.buf);
        System.out.println("----- Log for System.err: -----\n" + lpsErr.buf);

        String text = "----- Your output: -----\n" + lpsOut.buf.toString();
        String errorMsg = lpsErr.buf.toString();

        if(errorMsg.length() > 0)
        	codeOutput.setText(errorMsg);
        else
        	codeOutput.setText(text);
    }
}
