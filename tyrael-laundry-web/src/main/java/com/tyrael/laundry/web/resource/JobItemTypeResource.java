package com.tyrael.laundry.web.resource;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.tyrael.laundry.reference.JobItemType;
import com.tyrael.web.dto.EnumInfo;

@RestController
@RequestMapping("/jobitemtype")
public class JobItemTypeResource {

    @Autowired
    private Mapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<EnumInfo> findAll() {
        List<EnumInfo> infos = Lists.newArrayList();
        for (JobItemType type : JobItemType.values()) {
            infos.add(mapper.map(type, EnumInfo.class));
        }
        return infos;
    }

}
