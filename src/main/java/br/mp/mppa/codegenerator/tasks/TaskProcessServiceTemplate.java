package br.mp.mppa.codegenerator.tasks;

import org.thymeleaf.context.Context;

public class TaskProcessServiceTemplate extends Task {

	public TaskProcessServiceTemplate(String rootPath, String entityName, String entityIdType) {
		super(rootPath, entityName, entityIdType);
	}

	@Override
	protected String getClassName() {
		return getEntityTypeName()+"Service";
	}

	@Override
	protected String getTemplateName() {
		return "service";
	}

	@Override
	protected String getClassPackage() {
		return PACKAGE_SERVICE;
	}

	@Override
	protected void configContextVariables(Context context) {
		context.setVariable("repositoryType", getEntityTypeName()+"Repository" );
		context.setVariable("repositoryVar", getEntityVar()+"Repository" );
		context.setVariable("entityVar", getEntityVar());
	}

}
