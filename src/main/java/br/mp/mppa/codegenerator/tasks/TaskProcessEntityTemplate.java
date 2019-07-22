package br.mp.mppa.codegenerator.tasks;

import org.thymeleaf.context.Context;

import com.google.common.base.CaseFormat;

public class TaskProcessEntityTemplate extends Task{

	public TaskProcessEntityTemplate(String rootPath, 
			String entityName, String entityIdType) {
		super(rootPath, entityName, entityIdType);
	}

	@Override
	protected String getTemplateName() {
		return "entity";
	}

	@Override
	protected String getClassPackage() {
		return PACKAGE_DOMAIN;
	}
	
	@Override
	protected void configContextVariables(Context context) {
		context.setVariable( "TableName", CaseFormat.UPPER_CAMEL.to( CaseFormat.UPPER_UNDERSCORE, getEntityTypeName() ));
	}

	@Override
	protected String getClassName() {
		return getEntityTypeName();
	}
}