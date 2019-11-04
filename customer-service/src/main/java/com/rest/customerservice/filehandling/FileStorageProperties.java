package com.rest.customerservice.filehandling;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * The @ConfigurationProperties(prefix = "file") annotation does its job on application startup and binds all the properties 
 * with prefix file to the corresponding fields of the POJO class.
 * 
 * @author sarang_korde
 *
 */

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}
