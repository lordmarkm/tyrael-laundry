package com.tyrael.laundry.reports.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tyrael.laundry.reports.extraction.ExtractionService;

@RestController
@RequestMapping("/extraction")
public class ExtractionResource {

    private static Logger LOG = LoggerFactory.getLogger(ExtractionResource.class);

    @Autowired
    private ExtractionService extractionService;

    @RequestMapping(method = RequestMethod.GET)
    public void doExtract() {
        LOG.debug("Running kettle extraction!");
        extractionService.runKettle();
    }
}
