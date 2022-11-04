package kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.service.EmpInfoService;
import kr.co.seoulit.insa.empmgmtsvc.empinfomgmt.to.EmpTO;


@RequestMapping("/empinfomgmt/*")
@RestController
@CrossOrigin
public class EmpListController {

	@Autowired
	private EmpInfoService empInfoService;

	ModelMap map = null;

	@GetMapping("/emplist")
	public ModelMap emplist(@RequestParam("value") String val, HttpServletResponse response) {

		map = new ModelMap();

		try {
			System.out.println("성민="+val);
			String value = "전체부서";
			if (val != null) {
				value = val;
			}
			ArrayList<EmpTO> list = empInfoService.findEmpList(value);
			map.put("list", list);

		} catch (Exception e) {
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}

	@GetMapping("/empreallist")
	public ModelMap emplist(HttpServletRequest request, HttpServletResponse response) {

		map = new ModelMap();

		try {

			ArrayList<EmpTO> list = empInfoService.findEmprealList();
			map.put("list", list);

		} catch (Exception e) {
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}

}