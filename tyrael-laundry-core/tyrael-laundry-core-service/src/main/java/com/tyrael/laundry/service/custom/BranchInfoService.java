package com.tyrael.laundry.service.custom;

import com.tyrael.web.dto.BranchInfo;

/**
 * @author markm
 */
public interface BranchInfoService {

    BranchInfo getBranchInfo();
    BranchInfo saveBranchInfo(BranchInfo branchInfo);

}
