package com.tyrael.laundry.service.custom;

import com.tyrael.laundry.model.TransportRequest;
import com.tyrael.web.dto.TransportRequestInfo;

/**
 * @author mbmartinez
 */
public interface TransportRequestCustom<T extends TransportRequest, D extends TransportRequestInfo>
    extends RqlSearchingService<T, D> {

}
