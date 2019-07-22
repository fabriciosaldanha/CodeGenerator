package br.mp.mppa.codegenerator;

import java.io.File;

import br.mp.mppa.codegenerator.tasks.TaskCreatePackages;
import br.mp.mppa.codegenerator.tasks.TaskProcessControllerTemplate;
import br.mp.mppa.codegenerator.tasks.TaskProcessEntityTemplate;
import br.mp.mppa.codegenerator.tasks.TaskProcessRepositoryTemplate;
import br.mp.mppa.codegenerator.tasks.TaskProcessServiceTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeGeneratorApplication {

	private String rootPackage;
	private String entityName;
	private String entityIdType;

	public CodeGeneratorApplication( String[] args ) {
		super();
		processParams( args );
		validateParams();
		processTemplates();
	}

	private void processTemplates() {
		TaskCreatePackages createPackagesTask = new TaskCreatePackages( rootPackage, entityName, entityIdType );
		TaskProcessEntityTemplate taskProcessEntityTemplate = new TaskProcessEntityTemplate( rootPackage, entityName, entityIdType );
		TaskProcessRepositoryTemplate taskProcessRepositoryTemplate = new TaskProcessRepositoryTemplate( rootPackage, entityName, entityIdType );
		TaskProcessServiceTemplate taskProcessServiceTemplate = new TaskProcessServiceTemplate( rootPackage, entityName, entityIdType );
		TaskProcessControllerTemplate taskProcessControllerTemplate = new TaskProcessControllerTemplate( rootPackage, entityName, entityIdType );

		createPackagesTask.run();
		taskProcessEntityTemplate.run();
		taskProcessRepositoryTemplate.run();
		taskProcessServiceTemplate.run();
		taskProcessControllerTemplate.run();
	}
	
	private void validateParams() {
		if ( rootPackage == null ) {
			throw new RuntimeException("É necessário passar o parametro -p");
		}
		if ( entityName == null ) {
			throw new RuntimeException("É necessário passar o parametro -e");
		}
		if ( entityIdType == null ) {
			throw new RuntimeException("É necessário passar o parametro -t");
		}
	}

	private void processParams(String[] args) {
		for (String arg : args) {
			String[] splited = arg.split("=");
			if ( "-p".equals( splited[0] ) ) {
				String root = System.getenv("PWD");
				String[] pes = splited[1].split(".");
				for (String p : pes) {
					root = root + File.pathSeparator + p; 
				}
				rootPackage = root;
			}
			else if ( "-e".equals( splited[0] ) ) {
				entityName = splited[1];
			}
			else if ( "-t".equals( splited[0] ) ) {
				entityIdType = splited[1];
			}
		}
	}

	public static void main(String[] args) {
		new CodeGeneratorApplication(args);
	}
}