package testTemplates;

import java.util.List;

import myplugin.generator.EJBGenerator;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.FMType;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** TestPackageGeneration: Class for package generation testing
 * @ToDo: Create another test class that loads metadata saved by MagicDraw plugin 
 * ( @see myplugin.GenerateAction#exportToXml() ) and activate code generation. 
 * This is the way to perform code generation testing without
 *  need to restart MagicDraw 
 *  */

public class TestPackageGeneration {
	
	public TestPackageGeneration(){
		
	}
	
	private void initModel() {		
		
		List<FMClass> classes = FMModel.getInstance().getClasses();
		
		classes.clear();
		
		FMClass cl = new FMClass ("Preduzece", "ejb.orgsema", "public");
		cl.addProperty(new FMProperty("sifraPreduzeca", new FMType("string", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty("nazivPreduzeca", new FMType("string", "String"), "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMClass ("Materijal", "ejb.magacin", "public");
		cl.addProperty(new FMProperty("sifraMaterijala", new FMType("string", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty("nazivMaterijala", new FMType("string", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty("slozen",new FMType("Boolean", "Boolean"), "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMClass ("Odeljenje", "ejb.orgsema", "public");
		cl.addProperty(new FMProperty("sifra", new FMType("string", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty("naziv", new FMType("string", "String"), "private", 1, 1));
		
		classes.add(cl);
		
		cl = new FMClass ("Osoba", "ejb", "public");
		cl.addProperty(new FMProperty("prezime", new FMType("string", "String"), "private", 1, 1));		
		cl.addProperty(new FMProperty("ime", new FMType("string", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty("datumRodjenja", new FMType("Date", "Date"), "private", 0, 1));
		cl.addProperty(new FMProperty("clanoviPorodice", new FMType("Osoba", "Osoba"), "private", 0, -1));	
		cl.addProperty(new FMProperty("vestina", new FMType("string", "String"), "private", 1, 3));
		
		classes.add(cl);
		
		cl = new FMClass ("Kartica", "ejb.magacin.kartica", "public");
		cl.addProperty(new FMProperty("sifraKartice", new FMType("string", "String"), "private", 1, 1));
		cl.addProperty(new FMProperty("nazivKartice", new FMType("string", "String"), "private", 1, 1));
		
		classes.add(cl);		
	}
	
	public void testGenerator() {
		initModel();		
		GeneratorOptions go = ProjectOptions.getProjectOptions().getGeneratorOptions().get("EJBGenerator");	
		EJBGenerator g = new EJBGenerator(go);
		g.generate();
	}
	
	public static void main(String[] args) {
		TestPackageGeneration tg = new TestPackageGeneration();
		/** @Todo: load project options from xml file */
		
		//for test purpose only:
		GeneratorOptions ejbOptions = new GeneratorOptions("c:/temp", "ejbclass", "./resources/templates/", "{0}.java", true, "ejb"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("EJBGenerator", ejbOptions);
				
		tg.testGenerator();
	}
	
	
	
	
}
