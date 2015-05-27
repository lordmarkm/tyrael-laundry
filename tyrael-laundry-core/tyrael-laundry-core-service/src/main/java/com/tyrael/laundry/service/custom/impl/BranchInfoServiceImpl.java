package com.tyrael.laundry.service.custom.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tyrael.laundry.model.Branch;
import com.tyrael.laundry.model.Brand;
import com.tyrael.laundry.model.QDeliveryRequest;
import com.tyrael.laundry.model.QJobOrder;
import com.tyrael.laundry.model.QPickupRequest;
import com.tyrael.laundry.reference.JobOrderStatus;
import com.tyrael.laundry.reference.TransportRequestStatus;
import com.tyrael.laundry.service.BranchService;
import com.tyrael.laundry.service.BrandService;
import com.tyrael.laundry.service.DeliveryRequestService;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.laundry.service.PickupRequestService;
import com.tyrael.laundry.service.custom.BranchInfoService;
import com.tyrael.web.dto.BranchInfo;

@Service
@PropertySource("classpath:branchinfo.properties")
public class BranchInfoServiceImpl implements BranchInfoService {

    @Autowired
    private JobOrderService jobOrderService;
    @Autowired
    private PickupRequestService pickupRequestService;
    @Autowired
    private DeliveryRequestService deliveryRequestService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Environment env;

    @Override
    public BranchInfo getBranchInfo() {
        BranchInfo branchInfo = new BranchInfo();
        branchInfo.setNewJobOrders((int) jobOrderService.count(QJobOrder.jobOrder.status.eq(JobOrderStatus.NEW)));
        setTransportRequests(branchInfo);
        setBranchInfo(branchInfo);
        return branchInfo;
    }

    private void setTransportRequests(BranchInfo branchInfo) {
        long newPrs = pickupRequestService.count(QPickupRequest.pickupRequest.status.eq(TransportRequestStatus.NEW));
        long newDrs = deliveryRequestService.count(QDeliveryRequest.deliveryRequest.status.eq(TransportRequestStatus.NEW));
        branchInfo.setNewTransportRequests((int) (newPrs + newDrs));
    }

    private void setBranchInfo(BranchInfo branchInfo) {
        Branch branch;
        if (branchService.count() < 1) {
            Brand brand = new Brand();
            brand.setName(env.getProperty("brand.name"));
            brand = brandService.save(brand);
            
            Branch tyrael = new Branch();
            tyrael.setName(env.getProperty("branch.name"));
            tyrael.setBrand(brand);
            branch = branchService.save(tyrael);
        } else {
            branch = branchService.findAll().get(0);
        }
        mapper.map(branch, branchInfo);
    }

    @Override
    public BranchInfo saveBranchInfo(BranchInfo branchInfo) {
        Branch branch = mapper.map(branchInfo, Branch.class);
        branchService.saveAndFlush(branch);
        return getBranchInfo();
    }
}
