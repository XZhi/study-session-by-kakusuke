/**
 * 
 */
package com.ss4o.nucleus.sca.maven.plugin;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

/**
 * @author tadasuke-win7
 * 
 */
public class ClassScanner {
	private Set<Visitor> visitors = new LinkedHashSet<ClassScanner.Visitor>();

	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

	protected final Log logger = LogFactory.getLog(getClass());

	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

	public ClassScanner(Visitor... visitors) {
		for (Visitor visitor : visitors) {
			if (!this.visitors.contains(visitor))
				this.visitors.add(visitor);
		}
	}

	public void addVisitors(Visitor... visitors) {
		for (Visitor visitor : visitors) {
			if (!this.visitors.contains(visitor))
				this.visitors.add(visitor);
		}
	}

	/**
	 * Scan the class path for candidate components.
	 * 
	 * @param basePackage
	 *            the package to check for annotated classes
	 * @return a corresponding Set of autodetected bean definitions
	 */
	public void findCandidateComponents(String basePackage) {
		try {
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/" + DEFAULT_RESOURCE_PATTERN;
			Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
			boolean traceEnabled = logger.isTraceEnabled();
			for (Visitor visitor : visitors) {
				visitor.initialize();
			}

			for (Resource resource : resources) {
				if (traceEnabled) {
					logger.trace("Scanning " + resource);
				}
				if (resource.isReadable()) {
					try {
						MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
						ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
						sbd.setResource(resource);
						sbd.setSource(resource);
						for (Visitor visitor : visitors) {
							if (visitor.check(sbd))
								visitor.visit(sbd);
						}
					} catch (Throwable ex) {
						throw new BeanDefinitionStoreException("Failed to read candidate component class: " + resource, ex);
					}
				}
			}

			for (Visitor visitor : visitors) {
				visitor.terminate();
			}
		} catch (IOException ex) {
			throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
		}
	}

	public interface Visitor {
		void initialize();

		boolean check(BeanDefinition beanDefinition);

		void visit(BeanDefinition beanDefinition);

		void terminate();
	}
}
