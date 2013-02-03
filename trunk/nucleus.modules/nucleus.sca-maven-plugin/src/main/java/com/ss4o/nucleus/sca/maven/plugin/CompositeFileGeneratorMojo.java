package com.ss4o.nucleus.sca.maven.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import com.ss4o.nucleus.sca.maven.plugin.CompositeFileGenerator.BindingInfo;

/**
 * Goal which touches a timestamp file.
 * 
 * @goal generate
 * 
 * @phase compile
 * @execute phase=compile
 */
public class CompositeFileGeneratorMojo extends AbstractMojo {
	/**
	 * @parameter expression="${project.build.outputDirectory}" default-value="${project.build.outputDirectory}"
	 */
	private File outputDirectory;
	private String applicationName;
	private String compsiteFileName;
	private String annotationClassName;
	private String bindingWebService;
	private String bindingJsonRpc;
	private String bindingJsonP;
	private String bindingREST;
	private String bindingATOM;

	/**
	 * @parameter expression="${project}" default-value="${project}"
	 */
	private MavenProject mavenProject;

	public void execute() throws MojoExecutionException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		for (Artifact artifact : mavenProject.getDependencyArtifacts()) {
			DynamicClassPathLoader.addClassPath(classLoader, artifact.getFile());
		}
		List<CompositeFileGenerator.BindingInfo> bindings = new ArrayList<CompositeFileGenerator.BindingInfo>();
		// TODO Implement Bindings...

		ClassScanner classScanner = new ClassScanner(new CompositeFileGenerator(applicationName, compsiteFileName, annotationClassName, bindings.toArray(new CompositeFileGenerator.BindingInfo[] {})), new JavaFileGenerator(annotationClassName));
		classScanner.scan();
	}
}
