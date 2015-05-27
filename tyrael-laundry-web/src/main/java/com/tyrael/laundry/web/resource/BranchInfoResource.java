package com.tyrael.laundry.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.service.custom.BranchInfoService;
import com.tyrael.web.dto.BranchInfo;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/branch")
public class BranchInfoResource {

    @Autowired
    private BranchInfoService service;

    @RequestMapping(method = GET)
    public ResponseEntity<BranchInfo> getBranchInfo() {
        return new ResponseEntity<>(service.getBranchInfo(), OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<BranchInfo> saveBranchInfo(@RequestBody BranchInfo branchInfo) {
        return new ResponseEntity<>(service.saveBranchInfo(branchInfo), OK);
    }
}
