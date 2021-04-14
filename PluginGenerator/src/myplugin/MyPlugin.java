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
		repositoryOptions();
		serviceOptions();
		serviceImplOptions();
	}
	
	private void domainOptions() {
//		C:\Users\Dejan\Desktop\MBRS_20200
		GeneratorOptions go = new GeneratorOptions("c:/Temp", "domainclass", "templates", "{0}.java", true, "uns.ac.rs.mbrs.domain");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("DomainGenerator", go);
		go.setTemplateDir(pluginDir + File.separator + go.getTemplateDir());
	}
	
	private void repositoryOptions() {
		GeneratorOptions go = new GeneratorOptions("c:/Temp", "repositoryclass", "templates", "{0}Repository.java", true, "uns.ac.rs.mbrs.repository");
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


