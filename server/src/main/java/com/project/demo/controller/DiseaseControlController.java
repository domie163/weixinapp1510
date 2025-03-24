package com.project.demo.controller;

import com.project.demo.entity.DiseaseControl;
import com.project.demo.service.DiseaseControlService;
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
 * 疾病防治：(DiseaseControl)表控制层
 *
 */
@RestController
@RequestMapping("/disease_control")
public class DiseaseControlController extends BaseController<DiseaseControl, DiseaseControlService> {

    /**
     * 疾病防治对象
     */
    @Autowired
    public DiseaseControlController(DiseaseControlService service) {
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
