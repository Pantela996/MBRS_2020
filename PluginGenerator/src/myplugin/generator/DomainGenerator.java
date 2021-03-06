package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.options.GeneratorOptions;

public class DomainGenerator extends BasicGenerator {

	public DomainGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
		// TODO Auto-generated constructor stub
	}
	
	public void generate() {
		try {
			super.generate();
		} catch (IOException e) {
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		List<FMClass> classes = FMModel.getInstance().getClasses();

		for (FMClass cl : classes) {
		
			Map<String, Object> context = new HashMap<String, Object>(); 
			context.clear();
			Boolean needsArrayList = false;
			ArrayList<String> imports = new ArrayList<>();
			for (FMProperty prop : cl.getProperties()) {
				if (prop.getType().getTypePackage().equals("Data")) {
					imports.add(cl.getTypePackage()+"."+prop.getType().getName());
					System.out.println(prop.getType().getTypePackage());
				}
				if (!needsArrayList && (prop.getAssociation().contains("@OneToMany") || prop.getAssociation().contains("@ManyToMany"))) {
					needsArrayList = true;
					imports.add("java.util.ArrayList");
					imports.add("java.util.List");
				}
			}

			context.put("imports", imports);

			try {
				Writer out = getWriter(cl.getName(), cl.getTypePackage());
				if (out != null) {
					context.put("class", cl);
					context.put("properties", cl.getProperties());
//					context.put("importedPackages", cl.getImportedPackages());
//					context.put("extendClass", cl.getBaseClassifier());
					getTemplate().process(context, out);
					out.flush();
				}
			} catch (TemplateException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}

}
