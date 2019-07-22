package br.mp.mppa.codegenerator.tasks;

import org.thymeleaf.context.Context;

public class TaskProcessControllerTemplate extends Task {

	public TaskProcessControllerTemplate(String rootPath, String entityName, String entityIdType) {
		super(rootPath, entityName, entityIdType);
	}

	@Override
	protected String getClassName() {
		return getEntityTypeName()+"Controller";
	}

	@Override
	protected String getTemplateName() {
		return "controller";
	}

	@Override
	protected String getClassPackage() {
		return PACKAGE_CONTROLLER;
	}

	@Override
	protected void configContextVariables(Context context) {
	}

}
