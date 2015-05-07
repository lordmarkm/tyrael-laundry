package com.tyrael.laundry.service.custom.impl;

import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.laundry.model.TransportRequest;
import com.tyrael.laundry.service.custom.TransportRequestCustom;
import com.tyrael.web.dto.TransportRequestInfo;

/**
 * @author mbmartinez
 */
public abstract class AbstractTransportRequestCustomImpl<E extends TransportRequest, D extends TransportRequestInfo, R extends TyraelJpaService<E>>
    extends RqlSearchingServiceImpl<E, D, R>
    implements TransportRequestCustom<E, D> {

}
