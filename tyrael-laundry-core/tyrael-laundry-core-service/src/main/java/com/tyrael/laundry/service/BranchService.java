package com.tyrael.laundry.service;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.Branch;

public interface BranchService extends BranchServiceCustom,
    TyraelJpaService<Branch> {

}
