package com.tyrael.laundry.service.custom.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.laundry.model.ServiceType;
import com.tyrael.laundry.service.ServiceTypeService;
import com.tyrael.laundry.service.custom.ServiceTypeServiceCustom;
import com.tyrael.web.dto.ServiceTypeInfo;

/**
 * @author mbmartinez
 */
public class ServiceTypeServiceCustomImpl 
    extends TyraelJpaServiceCustomImpl<ServiceType, ServiceTypeInfo, ServiceTypeService>
    implements ServiceTypeServiceCustom {

    private static Logger LOG = LoggerFactory.getLogger(ServiceTypeServiceCustomImpl.class);
    private static final String CODE = "CODE";
    private static final String LABEL = "LABEL";
    private static final String ICON = "ICON";
    private static final String PPK = "PRICE_PER_KILO";

    @PostConstruct
    public void init() throws IOException {
        if (repo.findAll().size() > 0) {
            return;
        }

        LOG.debug("Loading service types from csv.");

        Resource resource = new ClassPathResource("ref_servicetypes");
        for (CSVRecord record : CSVFormat.RFC4180.withHeader().withDelimiter(',').parse(new InputStreamReader(resource.getInputStream()))) {
            ServiceType servicetype = new ServiceType();
            servicetype.setCode(record.get(CODE));
            servicetype.setLabel(record.get(LABEL));
            servicetype.setIcon(record.get(ICON));
            servicetype.setPricePerKilo(new BigDecimal(record.get(PPK)));
            servicetype.setEnabled(true);
            servicetype.setDeleted(false);
            repo.save(servicetype);
        }

    }

    @Override
    public List<ServiceTypeInfo> findByEnabledInfo() {
        return toDto(repo.findByEnabled(true));
    }

}
