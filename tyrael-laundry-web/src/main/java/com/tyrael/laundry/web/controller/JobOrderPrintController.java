package com.tyrael.laundry.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.baldy.commons.web.controller.GenericController;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.JobOrderInfo;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/joborder/print")
public class JobOrderPrintController extends GenericController {

    @Autowired
    private JobOrderService service;

    @RequestMapping(value = "/{trackingNo}", method = GET)
    public ModelAndView printJobOrder(Principal principal, @PathVariable String trackingNo) {
        JobOrderInfo jobOrder = service.findByTrackinNoInfo(trackingNo);
        if (null == principal) {
            jobOrder.setCustomer(null);
        }
        return mav("joborder")
                .addObject("jobOrder", jobOrder);
    }

}
