/**
 * 
 */
package com.ss4o.nucleus.sca.maven.plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.util.Formatter;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor;

/**
 * @author tadasuke-win7
 *
 */
public class CompositeFileGenerator implements Visitor {
	public class BindingInfo {
		public String tagName;
		public String format;

		public BindingInfo(String tagName, String format) {
			this.tagName = tagName;
			this.format = format == null ? "" : format;
		}
	}

	private String applicationName;
	private Set<String> compositeSet;
	private BindingInfo[] bindings;
	private Class<? extends Annotation> annotationType;
	private String fileName;

	@SuppressWarnings("unchecked")
	public CompositeFileGenerator(String applicationName, String fileName, String annotationClassName, BindingInfo[] bindings) {
		this.applicationName = applicationName;
		this.fileName = fileName;
		this.bindings = bindings;
		try {
			annotationType = (Class<? extends Annotation>) Class.forName(annotationClassName);
		} catch (ClassNotFoundException e) {
		}
	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#initialize()
	 */
	@Override
	public void initialize() {
		compositeSet = new LinkedHashSet<String>();
	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#check(org.springframework.beans.factory.config.BeanDefinition)
	 */
	@Override
	public boolean check(BeanDefinition beanDefinition) {
		try {
			return AnnotationUtils.findAnnotationDeclaringClass(annotationType, Class.forName(beanDefinition.getBeanClassName())) != null;
		} catch (ClassNotFoundException e) {
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#visit(org.springframework.beans.factory.config.BeanDefinition)
	 */
	@Override
	public void visit(BeanDefinition beanDefinition) {
		compositeSet.add(beanDefinition.getBeanClassName());
	}

	/* (non-Javadoc)
	 * @see com.ss4o.nucleus.sca.maven.plugin.ClassScanner.Visitor#terminate()
	 */
	@Override
	public void terminate() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<composite xmlns=\"http://www.w3.org/2001/XMLSchema\" xmlns:tuscany=\"http://tuscany.apache.org/xmlns/sca/1.1\" xmlns:sca=\"http://docs.oasis-open.org/ns/opencsa/sca/200912\" targetNamespace=\"http://docs.oasis-open.org/ns/opencsa/sca/200912\" name=\"" + applicationName + "\">");
		for (String composite : compositeSet) {
			String shortName = ClassUtils.getShortName(composite);
			sb.append("<service name=\"" + shortName + "\" promote=\"" + shortName + "Component\">");
			sb.append("<interface.java interface=\"" + composite + "\"/>");
			for (BindingInfo binding : bindings) {
				if (binding.format != null) {
					sb.append("<").append(binding.tagName).append(" ");
					sb.append((new Formatter()).format(binding.format, shortName).toString());
					sb.append("\" />");
				} else {
					sb.append("<").append(binding.tagName).append("\" />");
				}
			}
			sb.append("</service>");
		}

		for (String composite : compositeSet) {
			String shortName = ClassUtils.getShortName(composite);
			sb.append("<component name=\"" + shortName + "Component\">");
			sb.append("<implementation.java class=\"" + composite + "SCAProxy\"/>");
			sb.append("</component>");
		}
		sb.append("</composite>");

		File file = new File(fileName);
		if (file.exists())
			file.delete();

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
			writer.write(sb.toString());
			writer.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
	}

}
