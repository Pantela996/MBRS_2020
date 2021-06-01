package myplugin;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.AppPropertiesGenerator;
import myplugin.generator.ControllerGenerator;
import myplugin.generator.DTOGenerator;
import myplugin.generator.DomainGenerator;
import myplugin.generator.EJBGenerator;
import myplugin.generator.EnumGenerator;
import myplugin.generator.PomGenerator;
import myplugin.generator.RepositoryGenerator;
import myplugin.generator.ServiceGenerator;
import myplugin.generator.ServiceImplGenerator;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;
import myplugin.generator.views.IndexGenerator;
import myplugin.generator.views.EditGenerator;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction {

	public GenerateAction(String name) {
		super("", name, null, null);
	}

	public void actionPerformed(ActionEvent evt) {

		if (Application.getInstance().getProject() == null)
			return;
		Package root = Application.getInstance().getProject().getModel();

		if (root == null)
			return;

//		ModelAnalyzer analyzer = new ModelAnalyzer(root, "ejb");	

		ModelAnalyzer analyzer = null;
		GeneratorOptions generatorOptions = null;
		try {
			generateDomain(analyzer, root, generatorOptions);
			generateEnumeration(analyzer, root, generatorOptions);
			generateDto(analyzer, root, generatorOptions);

			generateController(analyzer, root, generatorOptions);
			generateService(analyzer, root, generatorOptions);
			generateServiceImpl(analyzer, root, generatorOptions);
			generateRepository(analyzer, root, generatorOptions);

			generateIndexView(analyzer, root, generatorOptions);
			generateEditView(analyzer, root, generatorOptions);
			
			generatePom(generatorOptions);
			generateAppProperties(generatorOptions);

			exportToXml();
		} catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void generateDomain(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ac.rs.mbrs.domain");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("DomainGenerator");
		DomainGenerator domainGenerator = new DomainGenerator(go);
		domainGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}

	private void generateDto(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ac.rs.mbrs.dto");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("DTOGenerator");
		DTOGenerator dtoGenerator = new DTOGenerator(go);
		dtoGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}

	private void generateEnumeration(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ac.rs.mbrs.domain");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EnumGenerator");
		EnumGenerator enumGenerator = new EnumGenerator(go);
		enumGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());

	}

	private void generateRepository(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ac.rs.mbrs.repository");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepositoryGenerator");
		RepositoryGenerator repositoryGenerator = new RepositoryGenerator(go);
		repositoryGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}

	private void generateController(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ac.rs.mbrs.controller");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ControllerGenerator");
		ControllerGenerator controllerGenerator = new ControllerGenerator(go);
		controllerGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}

	private void generateIndexView(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "resources.templates");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("IndexGenerator");
		IndexGenerator indexGenerator = new IndexGenerator(go);
		indexGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}

	private void generateEditView(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "resources.templates");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EditGenerator");
		EditGenerator editGenerator = new EditGenerator(go);
		editGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}

	private void generateService(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ac.rs.mbrs.service");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceGenerator");
		ServiceGenerator enumGenerator = new ServiceGenerator(go);
		enumGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}

	private void generateServiceImpl(ModelAnalyzer analyzer, Package root, GeneratorOptions go) throws AnalyzeException {
		analyzer = new ModelAnalyzer(root, "uns.ac.rs.mbrs.serviceImpl");
		analyzer.prepareModel();
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceImplGenerator");
		ServiceImplGenerator enumGenerator = new ServiceImplGenerator(go);
		enumGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath() + ", package: " + go.getFilePackage());
	}
	
	private void generatePom(GeneratorOptions go) {
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("PomGenerator");
		PomGenerator pomGenerator = new PomGenerator(go);
		pomGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath());
	}
	
	private void generateAppProperties(GeneratorOptions go) {
		go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("AppPropertiesGenerator");
		AppPropertiesGenerator appPropertiesGenerator = new AppPropertiesGenerator(go);
		appPropertiesGenerator.generate();
		JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: "
				+ go.getOutputPath());
	}

	private void exportToXml() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") == JOptionPane.OK_OPTION) {
			JFileChooser jfc = new JFileChooser();
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();

				XStream xstream = new XStream(new DomDriver());
				BufferedWriter out;
				try {
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF8"));
					xstream.toXML(FMModel.getInstance().getClasses(), out);
					xstream.toXML(FMModel.getInstance().getEnumerations(), out);

				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}

}