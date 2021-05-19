package myplugin.generator.views;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import myplugin.generator.BasicGenerator;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;

public class IndexGenerator extends BasicGenerator {

	public IndexGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
		// TODO Auto-generated constructor stub
	}
	
	public void generate() {
		try {
			super.generate();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		
		List<FMClass> classes = FMModel.getInstance().getClasses();
		
		for (FMClass cl : classes) {
			
//			try {
//				Files.createDirectories(Paths.get("c:/Temp/"));
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
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
