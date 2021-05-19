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
import myplugin.generator.options.GeneratorOptions;

public class ControllerGenerator extends BasicGenerator {
	
	public ControllerGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}
	
	public void generate() {
		try {
			super.generate();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		List<FMClass> classes = FMModel.getInstance().getClasses();
		
		for (FMClass cl : classes) {
			
			Map<String, Object> context = new HashMap<String, Object>();
			context.clear();
			ArrayList<String> imports = new ArrayList<>();
			
			context.put("imports", imports);
			
			try {
				Writer out = getWriter(cl.getName(), cl.getTypePackage());
				if (out != null) {
					context.put("class", cl);
					
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
