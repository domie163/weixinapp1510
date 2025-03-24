package com.project.demo.controller;

import com.project.demo.entity.DietaryHealthPreservation;
import com.project.demo.service.DietaryHealthPreservationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 饮食养生：(DietaryHealthPreservation)表控制层
 *
 */
@RestController
@RequestMapping("/dietary_health_preservation")
public class DietaryHealthPreservationController extends BaseController<DietaryHealthPreservation, DietaryHealthPreservationService> {

    /**
     * 饮食养生对象
     */
    @Autowired
    public DietaryHealthPreservationController(DietaryHealthPreservationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
