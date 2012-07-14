/**
 * 
 */
package com.ss4o.nucleus.sca.maven.plugin;

import java.util.HashSet;
import java.util.Set;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

/**
 * @author tadasuke-win7
 * 
 */
public class ClassScanner {
	private Handler[] handlers;
	private StandardJavaFileManager standardJavaFileManager;
	private Set<JavaFileObject.Kind> kind;

	@SuppressWarnings("serial")
	public ClassScanner(Handler... handlers) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

		standardJavaFileManager = compiler.getStandardFileManager(diagnostics, null, null);

		kind = new HashSet<JavaFileObject.Kind>() {
			{
				add(JavaFileObject.Kind.CLASS);
			}
		};

		this.handlers = handlers;
	}

	public void scan() {
		try {
			for (Handler handler : handlers) {
				handler.initialize();
			}
			for (JavaFileObject javaFileObject : standardJavaFileManager.list(StandardLocation.CLASS_PATH, "", kind, false)) {
				Class<?> clazz = Class.forName(javaFileObject.getName());
				for (Handler handler : handlers) {
					if (handler.check(clazz))
						handler.handle(clazz);
				}
			}
			for (Handler handler : handlers) {
				handler.terminate();
			}
		} catch (Exception e) {
		}
	}

	public interface Handler {
		void initialize();

		boolean check(Class<?> clazz);

		void handle(Class<?> clazz);

		void terminate();
	}
}
