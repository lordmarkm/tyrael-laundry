package com.tyrael.laundry.security.method;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.tyrael.laundry.security.service.CustomerAccountService;
import com.tyrael.laundry.service.JobOrderService;

@Component
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    @Autowired
    private JobOrderService jobOrderService;

    @Autowired
    private CustomerAccountService customerAccountService;

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {

        final CustomTyraelLaundrySecurityExpressionRoot root = new CustomTyraelLaundrySecurityExpressionRoot(authentication);
        root.setPermissionEvaluator(new BasePermissionEvaluator());
        root.setJobOrderService(jobOrderService);
        root.setCustomerAccountService(customerAccountService);

        return root;
    }
}
