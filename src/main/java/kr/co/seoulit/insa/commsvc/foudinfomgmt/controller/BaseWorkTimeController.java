package kr.co.seoulit.insa.commsvc.foudinfomgmt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.seoulit.insa.attdsvc.attdappvl.to.MonthAttdMgtTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kr.co.seoulit.insa.commsvc.foudinfomgmt.service.FoudInfoMgmtService;
import kr.co.seoulit.insa.commsvc.foudinfomgmt.to.BaseWorkTimeTO;

@RequestMapping("/foudinfomgmt/*")
@RestController
@CrossOrigin
public class BaseWorkTimeController {

	@Autowired
	private FoudInfoMgmtService foudInfoMgmtService;
	ModelMap map = null;

	@GetMapping("basetime")
	public ModelMap findTimeList(HttpServletRequest request, HttpServletResponse response) {

		map = new ModelMap();

		try {
			ArrayList<BaseWorkTimeTO> list = foudInfoMgmtService.findTimeList();
			BaseWorkTimeTO emptyBean = new BaseWorkTimeTO();
			map.put("emptyBean", emptyBean);
			map.put("list", list);
			System.out.println("요청 도착 ! : " +map);
		} catch (Exception e) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}

	
	@PutMapping("basetime")
	public ModelMap batchTimeProcess(HttpServletRequest request, HttpServletResponse response) {


		map = new ModelMap();
		String sendData = request.getParameter("sendData");
		System.out.println("저장 요청 도착");
		System.out.println(sendData);
		Gson gson = new Gson();
		ArrayList<BaseWorkTimeTO> timeList = gson.fromJson(sendData, new TypeToken<ArrayList<BaseWorkTimeTO>>() {
		}.getType()); // 변경

		try {

			foudInfoMgmtService.batchTimeProcess(timeList);
			map.put("errorCode", 0);
			map.put("errorMsg", request.getParameter("applyYear") + "년도 기준근무시간이 등록/삭제가 완료되었습니다.");

		} catch (Exception e) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}

	@PutMapping("react-basetime")
	public ModelMap batchTimeProcess2(@RequestBody HashMap<String, ArrayList<BaseWorkTimeTO>> timeList, HttpServletResponse response) {

		map = new ModelMap();
//		String sendData = request.getParameter("sendData");
		System.out.println("저장 요청 도착");
		System.out.println(timeList.get("sendData"));
		ArrayList<BaseWorkTimeTO> BWTTO = timeList.get("sendData");

		try {

			foudInfoMgmtService.batchTimeProcess(BWTTO);
			map.put("errorCode", 0);
			map.put("errorMsg",  "기준근무시간이 등록/삭제가 완료되었습니다.");

		} catch (Exception e) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}

	@PostMapping("react-deleteBasetime")
	public ModelMap deleteTimeProcess(@RequestBody HashMap<String, ArrayList<BaseWorkTimeTO>> timeList, HttpServletResponse response) {

		map = new ModelMap();
//		String sendData = request.getParameter("sendData");
		System.out.println("삭제 요청 도착");
		System.out.println(timeList.get("sendData"));
		ArrayList<BaseWorkTimeTO> BWTTO = timeList.get("sendData");

		try {

			foudInfoMgmtService.deleteTimeProcess(BWTTO);
			map.put("errorCode", 0);
			map.put("errorMsg",  "기준근무시간이 등록/삭제가 완료되었습니다.");

		} catch (Exception e) {
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", e.getMessage());
		}
		return map;
	}
	
}
