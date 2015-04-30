package com.tyrael.laundry.security.service.custom;

import com.tyrael.commons.data.service.TyraelJpaServiceCustom;
import com.tyrael.laundry.security.dto.CustomerAccountInfo;
import com.tyrael.laundry.security.model.CustomerAccount;

/**
 * @author mbmartinez
 */
public interface CustomerAccountServiceCustom  extends TyraelJpaServiceCustom<CustomerAccount, CustomerAccountInfo> {

    CustomerAccountInfo findByAccountUsernameInfo(String username);

}
