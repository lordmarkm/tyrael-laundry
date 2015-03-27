package com.tyrael.laundry.web.resource;

import java.util.List;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baldy.commons.models.proper.Name;
import com.google.common.collect.Lists;
import com.tyrael.laundry.model.Customer;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.service.CustomerService;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.web.dto.JobOrderInfo;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;
import static org.dozer.loader.api.FieldsMappingOptions.oneWay;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.OK;
import static org.dozer.loader.api.TypeMappingOptions.*;

/**
 * @author mbmartinez
 */
@Controller
@RequestMapping("/joborder")
public class JobOrderResource {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderResource.class);

    @Autowired
    private JobOrderService service;

    @Autowired
    private DozerBeanMapper mapper;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void init() {
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(JobOrder.class, JobOrderInfo.class, wildcard(true))
                    .fields("customer.name.surname", "lastName");
            }
        });
    }

    @RequestMapping(method = GET)
    public ResponseEntity<List<JobOrderInfo>> findAll() {
        List<JobOrder> jobOrders = service.findAll();
        List<JobOrderInfo> jobOrderInfos = Lists.newArrayList();
        for (JobOrder jobOrder : jobOrders) {
            jobOrderInfos.add(mapper.map(jobOrder, JobOrderInfo.class));
        }
        return new ResponseEntity<>(jobOrderInfos, OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<JobOrder> save(JobOrderInfo jobOrderForm) {
        LOG.debug("Save request received. Job order form={}", jobOrderForm);

        JobOrder jobOrder = jobOrderForm.toJobOrder();

        Customer customer = new Customer();
        customer.setName(new Name());
        customer.getName().setSurname(jobOrderForm.getLastName());
        customer = customerService.save(customer);

        jobOrder.setCustomer(customer);
        jobOrder.setDateReceived(DateTime.now());

        jobOrder = service.save(jobOrder);

        return new ResponseEntity<>(jobOrder, OK);
    }

}
