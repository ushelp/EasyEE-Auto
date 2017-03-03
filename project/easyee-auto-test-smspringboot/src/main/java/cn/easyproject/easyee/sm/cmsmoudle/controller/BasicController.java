package cn.easyproject.easyee.sm.cmsmoudle.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.easyproject.easyee.sm.base.controller.BaseController;
import cn.easyproject.easyee.sm.base.pagination.PageBean;
import cn.easyproject.easyee.sm.base.util.StatusCode;
import cn.easyproject.easyee.sm.cmsmoudle.criteria.BasicCriteria;
import cn.easyproject.easyee.sm.cmsmoudle.entity.Basic;
import cn.easyproject.easyee.sm.cmsmoudle.service.BasicService;


 /**
 * Basic Controller, generated by EasyAuto
 *
 * 所有Controller处理类统一继承BaseController.
 * 
 * BaseController中定义了一下内容：
 * - request, application Servlet API 
 * - 请求响应相关的JSON参数（EasyUI框架请求都是通过JSON响应） 
 * - 初始化JSON响应数据的方法（setJsonMap，setJsonMsgMap，setJsonPaginationMap(PageBean, Object...)） 
 * - 获得分页对象：super.getPageBean() 
 * 
 * @author easyproject.cn
 * @version 1.0
 *
 */
@RestController
@RequestMapping("Basic")
public class BasicController extends BaseController {

	public static Logger logger = LoggerFactory.getLogger(BasicController.class);
	
	@Resource
	private BasicService basicService;
	
	
	/**
	 * Forward to page
	 * 
	 * @return
	 */
	@RequestMapping("page")
	public ModelAndView page(ModelAndView mv) {
		mv.setViewName("main/cmsmoudle/Basic");
		return mv;
	}
	
	
	/*
	* CRUD
	*/
	
	
	/**
	 * List
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("list")
	public Map<Object, Object> list(BasicCriteria basicCriteria) {
		PageBean pb = super.getPageBean(); // 获得分页对
		basicService.findByPage(pb, basicCriteria);
		// EasyUI框架响应结果都是JSON
		// JSON数据初始化，包含EasySSH Ajax响应信息和分页信息
		return super.setJsonPaginationMap(pb);
	}
	
	/**
	 * Get
	 * 
	 * @return
	 */
	@RequestMapping("get")
	public Map<Object, Object> get(Serializable id) {
		Basic basic=null;
		try {
			basic=basicService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.setJsonMsgMap("data", basic);
	}
	
	/**
	 * Save
	 * @return
	 */
	@RequestMapping("save")
	public Map<Object, Object> save(Basic basic) {
		// 保存
		try {
			basicService.save(basic);
			// 处理成功 消息
			super.setMsgKey("msg.saveSuccess");
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgKey("msg.saveFail");
			super.setStatusCode(StatusCode.ERROR); // 默认为OK
		}

		/*
		 * Ajax响应信息 statusCode: 响应状态码; msg: 响应消息; callback: 执行回调函数, locationUrl:
		 * 跳转页面
		 */
		// EasyUI框架响应结果都是JSON
		// JSON数据初始化，包含EasySSH Ajax响应信息
		// super.setJsonMsgMap();

		// 添加数据后，使用rowData信息更新行的内容
		// 返回JSON
		return super.setJsonMsgMap("rowData", basic);

		// 如果需要刷新，跳转到最后一页
		// int page = basicService.findMaxPage(rows);
		// return super.setJsonMsgMap("rowData", basic, "page", page);
	}
	
	
	/**
	 * Update
	 * 
	 * @return
	 */
	@RequestMapping("update")
	public Map<Object, Object> update(Basic basic) {
		try {
			basicService.update(basic);
			super.setMsgKey("msg.updateSuccess");
		} catch (Exception e) {
			e.printStackTrace();
			super.setMsgKey("msg.updateFail");
			super.setStatusCode(StatusCode.ERROR); // 默认为OK
		}

		return super.setJsonMsgMap();
	}

	/**
	 * Delete
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	public Map<Object, Object> delete(Basic basic) {
		try {
			basicService.delete(basic.getId());
		} catch (Exception e) {
			e.printStackTrace();
			super.setStatusCode(StatusCode.ERROR); // 默认为OK
		}
		return super.setJsonMsgMap();
	}
	
	/**
	 * DeleteBatch
	 * 
	 * @return
	 */
	@RequestMapping("deleteBatch")
	public Map<Object, Object> deleteBatch(String[] id) {
		try {
			basicService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			super.setStatusCode(StatusCode.ERROR); // 默认为OK
		}
		return super.setJsonMsgMap();
	}


	
	
}