/**
 * 
 */
package com.ss4o.nucleus.core;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;

/**
 * @author kakusuke
 * 
 */
public class NucleusPrototypeScopeResolver implements ScopeMetadataResolver {

	/* (非 Javadoc)
	 * @see org.springframework.context.annotation.ScopeMetadataResolver#resolveScopeMetadata(org.springframework.beans.factory.config.BeanDefinition)
	 */
	@Override
	public ScopeMetadata resolveScopeMetadata(BeanDefinition definition) {
		ScopeMetadata scopeMetadata = new ScopeMetadata();
		scopeMetadata.setScopeName(BeanDefinition.SCOPE_PROTOTYPE);
		return scopeMetadata;
	}

}
