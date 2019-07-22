package br.mp.mppa.codegenerator.tasks;

import org.thymeleaf.context.Context;

public class TaskProcessRepositoryTemplate extends Task {

	public TaskProcessRepositoryTemplate(String rootPath, String entityName, String entityIdType) {
		super(rootPath, entityName, entityIdType);
	}

	@Override
	protected String getClassName() {
		return getEntityTypeName()+"Repository";
	}

	@Override
	protected String getTemplateName() {
		return "repository";
	}

	@Override
	protected String getClassPackage() {
		return PACKAGE_REPOSITORY;
	}

	@Override
	protected void configContextVariables(Context context) {
	}
}
