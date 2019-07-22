package br.mp.mppa.codegenerator.tasks;

import java.io.File;

import org.thymeleaf.context.Context;

import lombok.extern.slf4j.Slf4j;

@Slf4j( topic = "TAKS 1 - Gerador de Pacotes" )
public class TaskCreatePackages extends Task{
	
	public TaskCreatePackages(String rootPath, String entityName, String entityIdType) {
		super(rootPath, entityName, entityIdType);
	}

	@Override
	public void run() {
		File rootDir = new File( getRootPath() );
		log.info( "Inicio" );
		if (rootDir.exists() && rootDir.isDirectory()) {
			createNewDirectory(rootDir, PACKAGE_DOMAIN);
			createNewDirectory(rootDir, PACKAGE_REPOSITORY);
			createNewDirectory(rootDir, PACKAGE_SERVICE);
			createNewDirectory(rootDir, PACKAGE_CONTROLLER);
		}
		log.info( "Fim" );
	}

	private void createNewDirectory(File rootDir, String newDirName) {
		File newDir = new File( rootDir, newDirName );
		if ( !newDir.exists() ) {
			newDir.mkdir();
			log.info( "Pacote " + newDirName + " criado." );
		}
		else {
			log.info( "Pacote " + newDirName + " ja existe." );
		}
	}

	@Override
	protected String getClassName() {
		return null;
	}

	@Override
	protected String getTemplateName() {
		return null;
	}

	@Override
	protected String getClassPackage() {
		return null;
	}

	@Override
	protected void configContextVariables(Context context) {
	}
}