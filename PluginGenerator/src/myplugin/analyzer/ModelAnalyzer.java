package myplugin.analyzer;

import java.util.Iterator;
import java.util.List;

import javax.management.relation.Relation;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMEnumeration;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.fmmodel.FMProperty;
import myplugin.generator.fmmodel.FMAssociation;
import myplugin.generator.fmmodel.FMType;
import myplugin.generator.fmmodel.Validation;

import com.nomagic.uml2.ext.jmi.helpers.ModelHelper;
import com.nomagic.uml2.ext.jmi.helpers.StereotypesHelper;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.EnumerationLiteral;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Class;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Enumeration;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Property;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Generalization;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Type;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Association;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.MultiplicityElement;
import com.nomagic.uml2.ext.magicdraw.mdprofiles.Stereotype;
import java.beans.Introspector;

/**
 * Model Analyzer takes necessary metadata from the MagicDraw model and puts it
 * in the intermediate data structure (@see myplugin.generator.fmmodel.FMModel)
 * optimized for code generation using freemarker. Model Analyzer now takes
 * metadata only for ejb code generation
 * 
 * @ToDo: Enhance (or completely rewrite) myplugin.generator.fmmodel classes and
 *        Model Analyzer methods in order to support GUI generation.
 */

public class ModelAnalyzer {
	// root model package
	private Package root;

	// java root package for generated code
	private String filePackage;

	public ModelAnalyzer(Package root, String filePackage) {
		super();
		this.root = root;
		this.filePackage = filePackage;
	}

	public Package getRoot() {
		return root;
	}

	public void prepareModel() throws AnalyzeException {
		FMModel.getInstance().getClasses().clear();
		FMModel.getInstance().getEnumerations().clear();
		FMModel.getInstance().getAssociations().clear();
		processPackage(root, filePackage);
	}

	private void processPackage(Package pack, String packageOwner) throws AnalyzeException {
		// Recursive procedure that extracts data from package elements and stores it in
		// the
		// intermediate data structure

		if (pack.getName() == null)
			throw new AnalyzeException("Packages must have names!");

		String packageName = packageOwner;
		if (pack != root) {
			packageName += "." + pack.getName();
		}

		if (pack.hasOwnedElement()) {
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Association) {
					Association as = (Association) ownedElement;
					FMAssociation fmAssociation = getAssociationData(as, packageName);
					FMModel.getInstance().getAssociations().add(fmAssociation);
				}
				
				if (ownedElement instanceof Generalization) {
					Generalization ge = (Generalization) ownedElement;
				}
				
			}
			
			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Class) {
					Class cl = (Class) ownedElement;
					FMClass fmClass = getClassData(cl, packageName);
					FMModel.getInstance().getClasses().add(fmClass);
				}

				if (ownedElement instanceof Enumeration) {
					Enumeration en = (Enumeration) ownedElement;
					FMEnumeration fmEnumeration = getEnumerationData(en, packageName);
					FMModel.getInstance().getEnumerations().add(fmEnumeration);
				}
				
			}

			for (Iterator<Element> it = pack.getOwnedElement().iterator(); it.hasNext();) {
				Element ownedElement = it.next();
				if (ownedElement instanceof Package) {
					Package ownedPackage = (Package) ownedElement;
					if (StereotypesHelper.getAppliedStereotypeByString(ownedPackage, "BusinessApp") != null)
						// only packages with stereotype BusinessApp are candidates for metadata
						// extraction and code generation:
						processPackage(ownedPackage, packageName);
				}
			}

			/**
			 * @ToDo: Process other package elements, as needed
			 */
		}
	}

	private FMClass getClassData(Class cl, String packageName) throws AnalyzeException {
		if (cl.getName() == null)
			throw new AnalyzeException("Classes must have names!");

		FMClass fmClass = new FMClass(cl.getName(), packageName, cl.getVisibility().toString());
		Iterator<Property> it = ModelHelper.attributes(cl);
		while (it.hasNext()) {
			Property p = it.next();
			FMProperty prop = getPropertyData(p, cl);
			fmClass.addProperty(prop);
		}
		
		if (!cl.getGeneralization().isEmpty()) {
			String generalization = ((Class)cl.getGeneralization().iterator().next().getTarget().iterator().next()).getName();
			fmClass.setGeneralization(generalization);
		}

		/**
		 * @ToDo: Add import declarations etc.
		 */
		return fmClass;
	}

	private FMProperty getPropertyData(Property p, Class cl) throws AnalyzeException {
		String attName = p.getName();
		if (attName == null || attName.isEmpty())
			throw new AnalyzeException("Properties of the class: " + cl.getName() + " must have names!");
		Type attType = p.getType();
		if (attType == null)
			throw new AnalyzeException("Property " + cl.getName() + "." + p.getName() + " must have type!");

		if (attType.getName() == null)
			throw new AnalyzeException("Type ot the property " + cl.getName() + "." + p.getName() + " must have name!");
		FMType type = new FMType(attType.getName(), attType.getPackage().getName());

		int lower = p.getLower();
		int upper = p.getUpper();

		
		FMProperty prop = new FMProperty(attName, type, p.getVisibility().toString(), lower, upper);

		Stereotype validationStereotype = StereotypesHelper.getAppliedStereotypeByString(p, "Validation");
		Boolean unique = false;
		Boolean notNull = false;
		
		if(validationStereotype != null) {
			for (Property tag : validationStereotype.getOwnedAttribute()){
				List tagValue = StereotypesHelper.getStereotypePropertyValue(p, validationStereotype, tag.getName());

				if (tagValue.isEmpty()) continue;

				switch(tag.getName()){
					case "unique" : unique = (Boolean) tagValue.get(0); break;
					case "notNull" : notNull = (Boolean) tagValue.get(0); break;
				}
			}
		}
		
		Validation val = new Validation(unique, notNull);
		prop.setValidation(val);
		
		FMAssociation fma = FMModel.getInstance().getAssociationByClassNames(cl.getName(), attType.getName());

		String association;

		association = determineAssociation(cl.getName(), p, fma);

//		if (upper == 1) {
//			if (p.getAssociation() != null) {
//				if (p.getAggregation().toString().toLowerCase() == "none") {
//					association = "@ManyToOne(fetch=FetchType.LAZY)";
//				} else {
//					association = "@OneToOne";
//				}
//			} else {
//				association = String.format("@Column(name=\"%s\"", attName.toLowerCase());
//				if (p.isUnique()) {
//					association += String.format(", unique=%s)", unique);
//				}else {
//					association += ")";
//				}
//			}
//		} else if (upper == -1) {
//			association = String.format("@OneToMany(mappedBy=\"%s\",cascade=CascadeType.REMOVE)", Introspector.decapitalize(cl.getName()));
//		} else {
//			association = null;
//		}
		
		prop.setAssociation(association);

		
		return prop;
	}

	private FMEnumeration getEnumerationData(Enumeration enumeration, String packageName) throws AnalyzeException {
		FMEnumeration fmEnum = new FMEnumeration(enumeration.getName(), packageName);
		List<EnumerationLiteral> list = enumeration.getOwnedLiteral();
		for (int i = 0; i < list.size() - 1; i++) {
			EnumerationLiteral literal = list.get(i);
			if (literal.getName() == null)
				throw new AnalyzeException("Items of the enumeration " + enumeration.getName() + " must have names!");
			fmEnum.addValue(literal.getName());
		}
		return fmEnum;
	}
	

	private FMAssociation getAssociationData(Association as, String packageName) {
		as.getEndType();
		as.getHumanType();
		FMAssociation fma = new FMAssociation(as.getName(), packageName);
		Property firstMember = as.getMemberEnd().get(0);
		Property secondMember = as.getMemberEnd().get(1);
		
		String firstMemberClass = as.getEndType().get(0).getName();
		String secondMemberClass = as.getEndType().get(1).getName();

		fma.setFirstMemberClass(firstMemberClass);
		fma.setSecondMemberClass(secondMemberClass);
		fma.setFirstMemberLower(firstMember.getLower());
		fma.setFirstMemberUpper(firstMember.getUpper());
		fma.setSecondMemberLower(secondMember.getLower());
		fma.setSecondMemberUpper(secondMember.getUpper());
		
		return fma;
	}
	
	private String determineAssociation(String clName, Property p, FMAssociation fma) {
		if (fma == null) {
			String assoc = String.format("@Column(name=\"%s\"",  p.getName().toLowerCase());
			if (p.isUnique()) {
				assoc += ", unique=true)";
			}else {
				assoc += ")";
			}
			return assoc;
		}
		String assoc = fma.determineType();
		
		if (assoc.equalsIgnoreCase("OneToOne")) {
			return "@OneToOne";
		} else if (assoc.equalsIgnoreCase("ManyToMany")) {
			return String.format("@ManyToMany\n@JoinTable(name=\"%ss\",\n" + 
					"	 joinColumns=@JoinColumn(name=\"%sId\"),\n" + 
					"	 inverseJoinColumns=@JoinColumn(name=\"%sId\")\n" + 
					"	)", p.getName(), clName.toLowerCase(), p.getName());
		}

		if (clName == fma.getFirstMemberClass()) {
			if (assoc.equalsIgnoreCase("ManyToOne")) {
				return "@ManyToOne(fetch=FetchType.LAZY)";
			} else if (assoc.equalsIgnoreCase("OneToMany")) {
				return String.format("@OneToMany(mappedBy=\"%s\",cascade=CascadeType.REMOVE)", Introspector.decapitalize(clName));
			}
		} else {
			if (assoc.equalsIgnoreCase("ManyToOne")) {
				return String.format("@OneToMany(mappedBy=\"%s\",cascade=CascadeType.REMOVE)", Introspector.decapitalize(clName));
			} else if (assoc.equalsIgnoreCase("OneToMany")) {
				return "@ManyToOne(fetch=FetchType.LAZY)";
			}
		}
		return null;
	}

}
