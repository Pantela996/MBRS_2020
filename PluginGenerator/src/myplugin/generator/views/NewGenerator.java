package myplugin.generator.views;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;
import myplugin.generator.BasicGenerator;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;

public class NewGenerator extends BasicGenerator {

	public NewGenerator(GeneratorOptions generatorOptions) {
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
			
			try {
				Writer out = getWriter(decapitalize(cl.getName()), "resources/templates");
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
