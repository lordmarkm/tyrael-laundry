package com.tyrael.laundry.service.custom;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author mbmartinez
 */
public interface TyraelLaundryJobOrderSequenceServiceCustom {

    String next();

}
