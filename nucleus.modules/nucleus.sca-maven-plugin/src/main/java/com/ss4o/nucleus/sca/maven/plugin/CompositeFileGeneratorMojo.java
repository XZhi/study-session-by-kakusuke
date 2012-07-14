package com.ss4o.nucleus.sca.maven.plugin;

import java.io.File;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

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
	
	/**
	 * @parameter expression="${project}" default-value="${project}"
	 */
	private MavenProject mavenProject;

	public void execute() throws MojoExecutionException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		for (Artifact artifact : mavenProject.getDependencyArtifacts()) {
			DynamicClassPathLoader.addClassPath(classLoader, artifact.getFile());
		}
		// TODO Implement ClassScanner
	}
}
