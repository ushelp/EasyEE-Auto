package cn.easyproject.easyee.auto.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import cn.easyproject.easyee.auto.EasyCriteria;
import cn.easyproject.easyee.auto.EasyCriterias;
import cn.easyproject.easyee.auto.EasyAddDialog;
import cn.easyproject.easyee.auto.EasyField;
import cn.easyproject.easyee.auto.EasyAutoException;
import cn.easyproject.easyee.auto.EasyEditDialog;
import cn.easyproject.easyee.auto.EasyPage;
import cn.easyproject.easyee.auto.EasyId;
import cn.easyproject.easyee.auto.EasyModule;
import cn.easyproject.easyee.auto.PageType;
import cn.easyproject.easyee.auto.PackageUtil;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/**
 * EasyAuto Code Generator for SM-SpringBoot
 * 
 * @author Ray
 * @author inthinkcolor@gmail.com
 * @author easyproject.cn
 * @since 1.0.0
 */
public class SMSpringBootCodeGenerator {

	private final String TEMPLATE_DIR = "/templates/sm";

	/**
	 * 新模块所在包
	 */
	private String modulePackage = null;
	/**
	 * 新模块所在目录
	 */
	private String javaModuleFolder = null;
	/**
	 * webapp/content 目录
	 */
	private String webFolder = null;
	/**
	 * webapp/content 目录
	 */
	private String resourcesFolder = null;
	/**
	 * PageBean 完全限定名
	 */
	private String pageBeanImport = "cn.easyproject.easyee.sm.base.pagination.PageBean";

	/**
	 * BaseService 完全限定名
	 */
	private String baseServiceImport = "cn.easyproject.easyee.sm.service.BaseService";

	/**
	 * EasyCriteria 完全限定名
	 */
	private String easyCriteriaImport = "cn.easyproject.easyee.sm.base.pagination.EasyCriteria";
	/**
	 * StringUtils 完全限定名
	 */
	private String stringUtilsImport = "cn.easyproject.easyee.sm.util.StringUtils";
	/**
	 * StatusCode 完全限定名
	 */
	private String statusCodeImport = "cn.easyproject.easyee.sm.base.util.StatusCode";
	/**
	 * BaseController 完全限定名
	 */
	private String baseControllerImport = "cn.easyproject.easyee.sm.base.controller.BaseController";
	// TODO

	/**
	 * 生成文件
	 * 
	 * @param allClass
	 * @param modules
	 * @throws TemplateException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void generatorFile(List<Class> allClass, String modules) throws TemplateException, IOException {
		for (Class nowClass : allClass) {
			/* Create a data-model */
			Map root = new HashMap();
			// 包
			String pkgName = modulePackage;
			// 类
			String className = nowClass.getSimpleName();
			// 模块
			String module = modulePackage;
			if (module.contains(".")) {
				module = module.substring(module.lastIndexOf(".") + 1);
			}

			// ========== EasyId
			// 主键ID
			String oid = null;
			String oidColumn = null;
			// ========== EasyId

			// ========== Criteria
			// 条件属性数据列表
			Set<String> propertys = new LinkedHashSet<String>();
			// 需要额外导入的包列表
			Set<String> imports = new LinkedHashSet<String>();
			// ========== Criteria

			// ========== EasyField
			// 类显示名
			String clsLabel = className;
			// 表格列显示信息
			Set<String> autos = new LinkedHashSet<String>();
			// ========== EasyField

			// ========== EasyPage
			// 页面数据编辑方式(dialog,row,cell)
			PageType pageType = PageType.NONE;

			EasyPage ee = (EasyPage) nowClass.getAnnotation(EasyPage.class);
			if (ee != null) {
				pageType = ee.value();
			}
			// ========== EasyPage

			Field[] fields = nowClass.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				String fieldName = f.getName();
				if (!fieldName.equals("serialVersionUID")) {

					// ========== EasyId
					// 主键id属性查询
					EasyId e = f.getAnnotation(EasyId.class);
					if (e != null) {
						oid = fieldName;
						oidColumn = f.getAnnotation(EasyField.class).mybatisColumn();
						if (oidColumn.equals("")) {
							oidColumn = fieldName;
						}
					}
					// ========== EasyId

					// ========== EasyField
					EasyField ea = f.getAnnotation(EasyField.class);
					if (ea != null) {
						String label = fieldName;
						String field = fieldName;
						String inputField = null;
						String mybatisColumn = null;
						// boolean show =ea.show();
						
						if(!ea.labelKey().equals("")){
							label="<s:message code=\""+ea.labelKey()+"\"></s:message>";
						}
						if (!ea.label().equals("")) {
							label = ea.label();
						}
						if (!ea.field().equals("")) {
							field = ea.field();
						}
						if (!ea.inputField().equals("")) {
							inputField = ea.inputField();
						} else {
							inputField = field;
						}
						// mybatis
						mybatisColumn = ea.mybatisColumn();
						if (mybatisColumn == null || mybatisColumn.equals("")) {
							mybatisColumn = Utils.escapeToMyBatisName(field).toUpperCase();
						}

						// field#inputField#label#required#show#inputShow#editable#updateAble
						/*
						 * field, 0 inputField, 1 label, 2 required, 3 show, 4
						 * inputShow, 5 updateAble, 6 inputClass, 7
						 * mybatisColumn, 8
						 */
						// TODO
						autos.add(field + "#" + inputField + "#" + label + "#" + ea.inputRequired() + "#" + ea.show()
								+ "#" + ea.inputShow() + "#" + ea.updateAble() + "#" + ea.inputClass() + "#"
								+ mybatisColumn);
					}
					// ========== EasyField

					// ========== Criteria
					EasyCriteria c = f.getAnnotation(EasyCriteria.class);
					// 作为查询条件
					if (c != null) {

						String simpleTypeName = f.getType().getSimpleName();

						if (simpleTypeName.equals("byte")) {
							simpleTypeName = "Byte";
						} else if (simpleTypeName.equals("char")) {
							simpleTypeName = "Character";
						} else if (simpleTypeName.equals("short")) {
							simpleTypeName = "Short";
						} else if (simpleTypeName.equals("int")) {
							simpleTypeName = "Integer";
						} else if (simpleTypeName.equals("long")) {
							simpleTypeName = "Long";
						} else if (simpleTypeName.equals("boolean")) {
							simpleTypeName = "Boolean";
						} else if (simpleTypeName.equals("float")) {
							simpleTypeName = "Float";
						} else if (simpleTypeName.equals("double")) {
							simpleTypeName = "Double";
						} else {
							// 如果数据类型不是java.lang，则添加import自动导入
							if (!f.getType().getName().startsWith("java.lang.")) {
								imports.add(f.getType().getName());
							}
						}

						String type = simpleTypeName;
						String field = fieldName;
						String label = null;
						String queryConditionName = null;
						String like = "=";

						if (!c.type().getName().equals("java.lang.Object")) {
							type = c.type().getSimpleName();
							// 如果数据类型不是java.lang，则添加import自动导入
							if (!f.getType().getName().startsWith("java.lang.")) {
								imports.add(c.type().getName());
							}
						}
						if (!c.filed().equals("")) {
							field = c.filed();
						}
						if (!c.queryConditionName().equals("")) {
							queryConditionName = c.queryConditionName();
						} else {
							queryConditionName = field;
						}
						
						label = field;
						if(!c.labelKey().equals("")){
							label="<s:message code=\""+c.labelKey()+"\"></s:message>";
						}
						if (!c.label().equals("")) {
							label = c.label();
						} 
						
						if (c.like()) {
							like = "like"; // like查询
						}
						// type#field#queryConditionName#like#label
						// propertys.add("Integer#empno#empno#=");
						// propertys.add("String#ename#ename#like");
						// propertys.add("Double#sal#sal#=");
						// propertys.add("Integer#deptno#dept.deptno#=");
						propertys.add(type + "#" + field + "#" + queryConditionName + "#" + like + "#" + label);
					}
				}
				// ========== Criteria
			}

			// ========== EasyModule

			boolean pagination = true;
			boolean save = true;
			boolean update = true;
			boolean remove = true;
			boolean search = true;
			boolean toolbar = true;
			boolean contextMenu = true;
			boolean mutipleDelete = true;
			// MyBatis
			String table = null;
			EasyModule ea = (EasyModule) nowClass.getAnnotation(EasyModule.class);
			if (ea != null) {
				
				if(!ea.labelKey().equals("")){
					clsLabel="<s:message code=\""+ea.labelKey()+"\"></s:message>";
				}
				
				if(!ea.label().equals("")){
					clsLabel = ea.label();
				}
				
				pagination = ea.pagination();
				save = ea.saveButton();
				update = ea.updateButton();
				remove = ea.removeButton();
				search = ea.searchButton();
				toolbar = ea.toolbar();
				contextMenu = ea.contextMenu();
				mutipleDelete = ea.mutipleDelete();
				table = ea.mybatisTable();
				if (table == null || table.equals("")) {
					table = Utils.escapeToMyBatisName(className).toUpperCase();
				}
				// TODO EasyModule
			}

			// ========== EasyModule

			// ========== EasyAddDialog
			boolean addDialog = false;
			boolean addMutilpart = false;
			EasyAddDialog ead = (EasyAddDialog) nowClass.getAnnotation(EasyAddDialog.class);
			if (ead != null) {
				addDialog = true;
				addMutilpart = ead.multipart();
			} else {
				if (pageType == PageType.NONE) {
					addDialog = true;
					addMutilpart = false;
				}
			}
			// ========== EasyAddDialog

			// ========== EasyEditDialog
			boolean editDialog = false;
			boolean editMutilpart = false;
			EasyEditDialog eed = (EasyEditDialog) nowClass.getAnnotation(EasyEditDialog.class);
			if (eed != null) {
				editDialog = true;
				editMutilpart = eed.multipart();
			} else {
				if (pageType == PageType.NONE) {
					editDialog = true;
					editMutilpart = false;
				}
			}
			// ========== EasyEditDialog

			// 类上的条件注解
			// ========== Criteria

			EasyCriterias ca = (EasyCriterias) nowClass.getAnnotation(EasyCriterias.class);
			EasyCriteria ca2 = (EasyCriteria) nowClass.getAnnotation(EasyCriteria.class);

			List<EasyCriteria> clsCriterias = new ArrayList<EasyCriteria>();

			// 类上的条件注解列表
			if (ca != null) {
				EasyCriteria[] cArray = ca.value();
				for (EasyCriteria criteria : cArray) {
					clsCriterias.add(criteria);
				}
			}
			// 类上的条件注解
			if (ca2 != null) {
				clsCriterias.add(ca2);
			}

			// 类上存在条件信息
			if (clsCriterias.size() > 0) {

				for (EasyCriteria c : clsCriterias) {
					String type = c.type().getSimpleName();
					String field = c.filed();
					String queryConditionName = null;
					String like = "=";
					String label = null;

					if (field.equals("")) {
						throw new EasyAutoException("When use @Criteria on Class, you must defined 'name'");
					}

					if (!c.type().getName().equals("java.lang.Object")) {
						type = c.type().getSimpleName();
						// 如果数据类型不是java.lang，则添加import自动导入
						if (!c.type().getName().startsWith("java.lang.")) {
							imports.add(c.type().getName());
						}
					}

					if (!c.queryConditionName().equals("")) {
						queryConditionName = c.queryConditionName();
					} else {
						queryConditionName = field;
					}
					
					label = field;
					if(!c.labelKey().equals("")){
						label="<s:message code=\""+c.labelKey()+"\"></s:message>";
					}
					if (!c.label().equals("")) {
						label = c.label();
					} 
					
					if (c.like()) {
						like = "like"; // like查询
					}
					// TODO
					// type#field#queryConditionName#like#label
					propertys.add(type + "#" + field + "#" + queryConditionName + "#" + like + "#" + label);
				}
			}
			
			// 如果没有 Criteria 查询条件，则不显示搜索按钮
			if(propertys.size()==0){
				search=false;
			}
			
			root.put("pkgName", pkgName);
			root.put("Imports", imports);
			root.put("ClassName", className);
			root.put("Propertys", propertys);
			root.put("Oid", oid);

			root.put("ClsLabel", clsLabel);
			root.put("pagination", pagination);
			root.put("save", save);
			root.put("update", update);
			root.put("remove", remove);
			root.put("search", search);
			root.put("toolbar", toolbar);
			root.put("contextMenu", contextMenu);
			root.put("mutipleDelete", mutipleDelete);
			// mybatis
			root.put("table", table);
			root.put("oidColumn", oidColumn);

			root.put("Autos", autos);
			root.put("Module", module);
			root.put("pageBeanImport", pageBeanImport);
			root.put("baseServiceImport", baseServiceImport);
			root.put("easyCriteriaImport", easyCriteriaImport);
			root.put("stringUtilsImport", stringUtilsImport);
			root.put("statusCodeImport", statusCodeImport);
			root.put("baseControllerImport", baseControllerImport);

			root.put("addMutilpart", addMutilpart);
			root.put("editMutilpart", editMutilpart);
			// TODO

			// /* Merge data-model with template */
			// Writer out = new OutputStreamWriter(System.out);

			// 配置
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setDefaultEncoding("UTF-8");
			// cfg.setDirectoryForTemplateLoading(new File(folder));
			cfg.setClassForTemplateLoading(SMSpringBootCodeGenerator.class, TEMPLATE_DIR);

			Template template = null;
			String fileName = null;
			String criteriaFolder = null;
			File dir = null;
			FileWriter out = null;

			// XXXCriteria
			if (modules.contains("criteria")) {
				/* Get the template (uses cache internally) */
				template = cfg.getTemplate("criteria.tpl");
				fileName = className + "Criteria.java";
				criteriaFolder = javaModuleFolder + "/criteria/";
				dir = new File(criteriaFolder);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				out = new FileWriter(criteriaFolder + fileName);
				template.process(root, out);
				out.close();
			}

			// XXXDAO
			if (modules.contains("dao")) {
				template = cfg.getTemplate("dao.tpl");
				fileName = className + "DAO.java";
				criteriaFolder = javaModuleFolder + "/dao/";
				dir = new File(criteriaFolder);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				out = new FileWriter(criteriaFolder + fileName);
				template.process(root, out);
				out.close();
			}

			// XXXDAO.xml Mapper
			if (modules.contains("mapper")) {
				template = cfg.getTemplate("mapper.tpl");
				fileName = className + "DAO.xml";
				criteriaFolder = resourcesFolder + "/" + module + "/";
				dir = new File(criteriaFolder);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				out = new FileWriter(criteriaFolder + fileName);
				template.process(root, out);
				out.close();
			}

			// XXXService
			if (modules.contains("service")) {
				template = cfg.getTemplate("service.tpl");
				fileName = className + "Service.java";
				criteriaFolder = javaModuleFolder + "/service/";
				dir = new File(criteriaFolder);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				out = new FileWriter(criteriaFolder + fileName);
				template.process(root, out);
				out.close();
			}

			// XXXServiceImpl
			if (modules.contains("serviceImpl")) {
				template = cfg.getTemplate("serviceImpl.tpl");
				fileName = className + "ServiceImpl.java";
				criteriaFolder = javaModuleFolder + "/service/impl/";
				dir = new File(criteriaFolder);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				out = new FileWriter(criteriaFolder + fileName);
				template.process(root, out);
				out.close();
			}

			// XXXAction
			if (modules.contains("controller")) {
				template = cfg.getTemplate("controller.tpl");
				fileName = className + "Controller.java";
				criteriaFolder = javaModuleFolder + "/controller/";
				dir = new File(criteriaFolder);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				out = new FileWriter(criteriaFolder + fileName);
				template.process(root, out);
				out.close();
			}

			if (modules.contains("page")) {
				if (pageType == PageType.NONE) {
					// dialog
					// XXX.jsp
					template = cfg.getTemplate("page.tpl");
					fileName = className + ".jsp";
					criteriaFolder = webFolder + "/main/" + module + "/";
					dir = new File(criteriaFolder);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					out = new FileWriter(criteriaFolder + fileName);
					template.process(root, out);
					out.close();

				} else if (pageType == PageType.ROW_EDIT) {
					// rowedit
					// XXX.jsp
					template = cfg.getTemplate("page_rowedit.tpl");
					fileName = className + ".jsp";
					criteriaFolder = webFolder + "/main/" + module + "/";
					dir = new File(criteriaFolder);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					out = new FileWriter(criteriaFolder + fileName);
					template.process(root, out);
					out.close();

				} else if (pageType == PageType.CELL_EDIT) {
					// rowedit
					// XXX.jsp
					template = cfg.getTemplate("page_celledit.tpl");
					fileName = className + ".jsp";
					criteriaFolder = webFolder + "/main/" + module + "/";
					dir = new File(criteriaFolder);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					out = new FileWriter(criteriaFolder + fileName);
					template.process(root, out);
					out.close();

				} else if (pageType == PageType.FORM_EDIT) {
					// form
					// XXX.jsp
					template = cfg.getTemplate("page_form.tpl");
					fileName = className + ".jsp";
					criteriaFolder = webFolder + "/main/" + module + "/";
					dir = new File(criteriaFolder);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					out = new FileWriter(criteriaFolder + fileName);
					template.process(root, out);
					out.close();
				}

				// XXXAdd.jsp
				if (addDialog) {
					template = cfg.getTemplate("pageAdd.tpl");
					fileName = className + "Add.jsp";
					criteriaFolder = webFolder + "/dialog/" + module + "/";
					dir = new File(criteriaFolder);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					out = new FileWriter(criteriaFolder + fileName);
					template.process(root, out);
					out.close();
				}

				// XXXEdit.jsp
				if (editDialog) {
					template = cfg.getTemplate("pageEdit.tpl");
					fileName = className + "Edit.jsp";
					criteriaFolder = webFolder + "/dialog/" + module + "/";
					dir = new File(criteriaFolder);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					out = new FileWriter(criteriaFolder + fileName);
					template.process(root, out);
					out.close();
				}

			}
		}
		System.out.println("需要配置的权限列表/Need permission list");
		System.out.println("`#` Menu permissions");
		System.out.println("`-` Operation permissions");
		System.out.println();

		for (Class nowClass : allClass) {
			String className = nowClass.getSimpleName();
			System.out.println("# " + className + "/page");
			System.out.println("\t - " + className + "/list");
			System.out.println("\t - " + className + "/get");
			System.out.println("\t - " + className + "/save");
			System.out.println("\t - " + className + "/update");
			System.out.println("\t - " + className + "/delete");
			System.out.println("\t - " + className + "/deleteBatch");
		}

	}

	/**
	 * 为指定包下的实体类生成模块代码
	 * 
	 * @param entityPackage
	 */
	public void generator(String entityPackage) {
		this.generator(null, entityPackage);

	}

	/**
	 * 为指定包下的实体类生成特定模块代码
	 * 
	 * @param modules
	 * @param entityPackage
	 */
	@SuppressWarnings("rawtypes")
	public void generator(EasyAutoModule[] modules, String entityPackage) {
		try {
			List<Class> allClass = PackageUtil.getClasses(entityPackage);

			if (entityPackage.contains(".")) {
				modulePackage = entityPackage.substring(0, entityPackage.lastIndexOf("."));
			}

			String m = (entityPackage.substring(0, entityPackage.lastIndexOf("."))).replace('.', '/');
			String basePath = System.getProperty("user.dir");
			javaModuleFolder = basePath + "/src/main/java/" + m;

			webFolder = basePath + "/src/main/webapp/WEB-INF/content";

			resourcesFolder = basePath + "/src/main/resources/mybatis/mapper";

			generatorAll(allClass, modules);
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 为指定类生成模块代码
	 * 
	 * @param clses
	 */
	@SuppressWarnings("rawtypes")
	public void generator(Class... clses) {
		this.generator(null, clses);

	}

	/**
	 * 为指定类生成模块代码
	 * 
	 * @param modules
	 * @param clses
	 */
	@SuppressWarnings("rawtypes")
	public void generator(EasyAutoModule[] modules, Class... clses) {
		try {

			for (Class cls : clses) {
				String entityPackage = cls.getPackage().getName();
				if (entityPackage.contains(".")) {
					modulePackage = entityPackage.substring(0, entityPackage.lastIndexOf("."));
				}

				String m = (entityPackage.substring(0, entityPackage.lastIndexOf("."))).replace('.', '/');
				String basePath = System.getProperty("user.dir");
				javaModuleFolder = basePath + "/src/main/java/" + m;
				webFolder = basePath + "/src/main/webapp/WEB-INF/content";
				resourcesFolder = basePath + "/src/main/resources/mybatis/mapper";

				List<Class> allClass = new ArrayList<Class>();
				allClass.add(cls);

				generatorAll(allClass, modules);
			}
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据类名搜索类的完全限定名
	 * 
	 * @return 完全限定名
	 */
	private String searchPageBeanImport(final String simpleClassName) {
		String path = "";
		String basePath = System.getProperty("user.dir") + "/src/main/java/";
		Collection<File> files = FileUtils.listFiles(new File(basePath), new NameFileFilter(simpleClassName + ".java"),
				TrueFileFilter.INSTANCE);
		if (files.size() > 0) {
			path = files.iterator().next().getAbsolutePath().replace("\\", "/");
			path = path.substring(path.indexOf("src/main/java/") + ("src/main/java/").length());

			path = path.replace("/", ".").substring(0, path.lastIndexOf("."));
		}
		return path;
	}

	/**
	 * 生成所有
	 * 
	 * @param allClass
	 * @param modules
	 * @throws TemplateException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void generatorAll(List<Class> allClass, EasyAutoModule[] modules) throws TemplateException, IOException {
		pageBeanImport = searchPageBeanImport("PageBean");
		baseServiceImport = searchPageBeanImport("BaseService");
		easyCriteriaImport = searchPageBeanImport("EasyCriteria");
		stringUtilsImport = searchPageBeanImport("StringUtils");
		statusCodeImport = searchPageBeanImport("StatusCode");
		baseControllerImport = searchPageBeanImport("BaseController");
		// TODO
		String mds = "#";
		if (modules == null) {
			mds = "#controller#criteria#dao#mapper#page#service#serviceImpl#";
		} else {
			for (int i = 0; i < modules.length; i++) {
				mds += modules[i].value + "#";
			}
		}
		generatorFile(allClass, mds);
	}

}
