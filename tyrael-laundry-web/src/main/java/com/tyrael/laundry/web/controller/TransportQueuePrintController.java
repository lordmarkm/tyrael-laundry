package com.tyrael.laundry.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baldy.commons.web.controller.GenericController;
import com.tyrael.laundry.service.TransportQueueService;
import com.tyrael.web.dto.TransportQueueInfo;

@Controller
@RequestMapping("/transport/print")
public class TransportQueuePrintController extends GenericController {

    @Autowired
    private TransportQueueService service;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView printTransportQueue(Principal principal, @PathVariable Long id) {
        TransportQueueInfo queue = service.findOneInfo(id);
        return  mav("transport").addObject("queue", queue);
    }
}
