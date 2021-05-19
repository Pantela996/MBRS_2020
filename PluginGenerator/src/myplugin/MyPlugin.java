package myplugin;

import java.io.File;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;


import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {
	
	String pluginDir = null; 
	
	public void init() {
		JOptionPane.showMessageDialog( null, "My Plugin init");
		
		pluginDir = getDescriptor().getPluginDirectory().getPath();
		
		// Creating submenu in the MagicDraw main menu 	
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();		
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));
		
		/** @Todo: load project options (@see myplugin.generator.options.ProjectOptions) from 
		 * ProjectOptions.xml and take ejb generator options */
		
		//for test purpose only:
//		GeneratorOptions ejbOptions = new GeneratorOptions("c:/temp", "ejbclass", "templates", "{0}.java", true, "ejb"); 				
//		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", ejbOptions);
//				
//		ejbOptions.setTemplateDir(pluginDir + File.separator + ejbOptions.getTemplateDir()); //apsolutna putanja

		domainOptions();
		enumOptions();
		dtoOptions();
		
		controllerOptions();
		serviceOptions();
		serviceImplOptions();
		repositoryOptions();
		
		viewsIndexOptions();
		viewsEditOptions();
	}
	
	private void domainOptions() {
//		C:\Users\Dejan\Desktop\MBRS_20200
		GeneratorOptions go = new GeneratorOptions("c:/Temp/mbrs/src/main/java", "domainclass", "templates", "{0}.java", true, "uns.ac.rs.mbrs.domain");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("DomainGenerator", go);
		System.out.print(pluginDir + File.separator + go.getTemplateDir());
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}
	
	private void enumOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp/mbrs/src/main/java", "enum", "templates", "{0}.java", true, "uns.ac.rs.mbrs.model"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EnumGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}
	
	private void dtoOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp/mbrs/src/main/java", "dtoclass", "templates", "{0}DTO.java", true, "uns.ac.rs.mbrs.dto"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("DTOGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}


	private void repositoryOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp/mbrs/src/main/java", "repositoryclass", "templates", "{0}Repository.java", true, "uns.ac.rs.mbrs.repository");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("RepositoryGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}
	
	private void serviceOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp", "serviceclass", "templates", "{0}Service.java", true, "uns.ac.rs.mbrs.service");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}

	
	private void serviceImplOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp", "serviceimplclass", "templates", "{0}ServiceImpl.java", true, "uns.ac.rs.mbrs.serviceImpl");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceImplGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}

	private void controllerOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp/mbrs/src/main/java", "controllerclass", "templates", "{0}Controller.java", true, "uns.ac.rs.mbrs.controller"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ControllerGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}
	
	private void viewsIndexOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp/mbrs/src/main", "indexview", "templates", "{0}/index.html", true, "resources.templates");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("IndexGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}

	private void viewsEditOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp/mbrs/src/main", "editview", "templates", "{0}/edit.html", true, "resources.templates");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EditGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}


	private NMAction[] getSubmenuActions()
	{
	   return new NMAction[]{
			new GenerateAction("Generate"),
	   };
	}
	
	public boolean close() {
		return true;
	}
	
	public boolean isSupported() {				
		return true;
	}
}


