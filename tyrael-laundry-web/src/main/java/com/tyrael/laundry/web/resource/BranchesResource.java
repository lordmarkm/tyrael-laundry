package com.tyrael.laundry.web.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.service.BranchService;
import com.tyrael.web.dto.BranchInfo;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/branches")
public class BranchesResource {

    @Autowired
    private BranchService service;

    public ResponseEntity<List<BranchInfo>> getAllBranches() {
        return new ResponseEntity<>(service.findAllInfo(), OK);
    }

}
