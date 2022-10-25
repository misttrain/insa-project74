package kr.co.seoulit.insa.attdsvc.attdmgmt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.seoulit.insa.attdsvc.attdappvl.to.MonthAttdMgtTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.co.seoulit.insa.attdsvc.attdmgmt.service.AttdMgmtService;
import kr.co.seoulit.insa.attdsvc.attdmgmt.to.RestAttdTO;

@RestController
@CrossOrigin
@RequestMapping("/attdmgmt/*")
public class ExcusedAttendanceController {
	
	@Autowired
	private AttdMgmtService attdMgmtService;	
	ModelMap map = null;
	
	
	@PostMapping("/excused-attnd")
	public ModelMap registRestAttd(HttpServletRequest request, HttpServletResponse response) {
		map = new ModelMap();

		String sendData = request.getParameter("sendData");
		System.out.println(sendData);
		try {
			Gson gson = new Gson();
			RestAttdTO restAttd = gson.fromJson(sendData, RestAttdTO.class);
			System.out.println(restAttd);
			attdMgmtService.registRestAttd(restAttd);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
			
		} catch (Exception dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	@CrossOrigin
	@PostMapping("react-excused-attnd")
	public ModelMap registRestAttd2(@RequestBody HashMap<String, RestAttdTO> restAttdList) {
//	public ModelMap registRestAttd2(@RequestBody RestAttdTO monthAttdMgt) {

		map = new ModelMap();
		System.out.println("근태외 요청 도착");
		System.out.println("출장신청 요청 도착");
		System.out.println(restAttdList);
		System.out.println(restAttdList.get("sandData"));

		try {
			RestAttdTO restAttd = restAttdList.get("sandData");
			attdMgmtService.registRestAttd(restAttd);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);

		} catch (Exception dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}
	
	@GetMapping("/excused-attnd")
	public ModelMap findRestAttdList(@RequestParam("empCode") String empCode, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("code") String code, HttpServletResponse response) {
		 
		map = new ModelMap();		
		response.setContentType("application/json; charset=UTF-8");
		System.out.println(empCode);
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(code);

		try {
			ArrayList<RestAttdTO> restAttdList = attdMgmtService.findRestAttdList(empCode, startDate, endDate, code);
			map.put("restAttdList", restAttdList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);
			
		}catch (Exception dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}

	@DeleteMapping("/excused-attnd")
	public ModelMap removeRestAttdList(HttpServletRequest request, HttpServletResponse response) {

		map = new ModelMap();
		String sendData = request.getParameter("sendData");
		response.setContentType("application/json; charset=UTF-8");
		try {
			Gson gson = new Gson();
			ArrayList<RestAttdTO> restAttdList = gson.fromJson(sendData, new TypeToken<ArrayList<RestAttdTO>>() {
			}.getType());
			attdMgmtService.removeRestAttdList(restAttdList);
			map.put("errorMsg", "success");
			map.put("errorCode", 0);

		} catch (Exception dae) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", dae.getMessage());
		}
		return map;
	}

}
