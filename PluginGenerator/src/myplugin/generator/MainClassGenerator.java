package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;

public class MainClassGenerator extends BasicGenerator {

	public MainClassGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}
	
	public void generate() {
		try {
			super.generate();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		Map<String, Object> context = new HashMap<String, Object>();
		
		try {
			Writer out = getWriter();
			if (out != null) {
				getTemplate().process(context,  out);
				out.flush();
			}
		} catch (TemplateException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());			
		}
	}
}
