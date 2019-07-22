package br.mp.mppa.codegenerator.tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public abstract class Task {
	
	protected static final String PACKAGE_DOMAIN = "domain";
	protected static final String PACKAGE_REPOSITORY = "repository";
	protected static final String PACKAGE_SERVICE = "service";
	protected static final String PACKAGE_CONTROLLER = "controller";
	
	@Getter(value = AccessLevel.PROTECTED)
	private String rootPath;
	
	@Getter(value = AccessLevel.PROTECTED)
	private String entityTypeName;
	
	@Getter(value = AccessLevel.PROTECTED)
	private String entityIdType;
	
	@SneakyThrows
	public void run() {
		log.info( "Criando Classe " + getClassFileName() );
		File rootDir = new File( rootPath );
		File packageDir = new File( rootDir, getClassPackage() );
		File classFile = new File( packageDir, getClassFileName() );
		
		if ( !classFile.exists() ) {
			ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
			resolver.setTemplateMode( TemplateMode.TEXT );
			resolver.setSuffix(".template");
			TemplateEngine engine = new TemplateEngine();
			engine.setTemplateResolver(resolver);
			
			StringWriter writer = new StringWriter();
			
			Context context = new Context();
			
			configContext(rootDir, context);
			
			engine.process(getTemplateName(), context, writer);
			
			@Cleanup FileWriter fw = new FileWriter(classFile, Charsets.UTF_8);
			fw.write( writer.toString() );
			
			log.info( "Classe " + entityTypeName + ".class criada." );
		}
		else {
			log.info( "Classe " + entityTypeName + ".class já existe." );
		}
	}
	
	private void configContext(File rootDir, Context context) {
		context.setVariable( "rootPackage", getRootPackage(rootDir));
		context.setVariable( "className", getClassName());
		context.setVariable( "entityIdType", entityIdType);
		context.setVariable( "entityName", entityTypeName);
		configContextVariables( context );
	}
	
	protected String getRootPackage(File rootDir) {
		return rootDir.getName().toLowerCase();
	}
	
	protected String getClassFileName() {
		return getClassName() + ".java";
	}
	
	protected String getEntityVar(){
		return CaseFormat.UPPER_CAMEL.to( CaseFormat.LOWER_CAMEL, entityTypeName );
	}
	
	protected abstract String getClassName();
	protected abstract String getTemplateName();
	protected abstract String getClassPackage();
	protected abstract void configContextVariables(Context context);
}