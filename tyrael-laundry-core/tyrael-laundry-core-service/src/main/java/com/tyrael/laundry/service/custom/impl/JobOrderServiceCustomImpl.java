package com.tyrael.laundry.service.custom.impl;

import static com.tyrael.laundry.model.QJobOrder.jobOrder;
import static com.tyrael.laundry.reference.JobOrderStatus.CLEANED;
import static com.tyrael.laundry.reference.JobOrderStatus.NEW;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.model.JobItem;
import com.tyrael.laundry.model.JobOrder;
import com.tyrael.laundry.model.JobService;
import com.tyrael.laundry.model.LostAndFoundItem;
import com.tyrael.laundry.reference.JobOrderStatus;
import com.tyrael.laundry.service.JobOrderService;
import com.tyrael.laundry.service.TyraelLaundryJobOrderSequenceService;
import com.tyrael.laundry.service.custom.JobOrderServiceCustom;
import com.tyrael.laundry.service.rql.RsqlParserVisitor;
import com.tyrael.web.dto.JobOrderInfo;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * @author mbmartinez
 */
public class JobOrderServiceCustomImpl extends TyraelJpaServiceCustomImpl<JobOrder, JobOrderInfo, JobOrderService>
    implements JobOrderServiceCustom  {

    private static final Logger LOG = LoggerFactory.getLogger(JobOrderServiceCustomImpl.class);

    private static final String STATUS_OPEN = "OPEN";

    @Autowired
    private TyraelLaundryJobOrderSequenceService sequenceService;

    @Override
    public List<JobOrderInfo> rqlSearch(String term) {
        LOG.debug("Performing rql search. term={}", term);

        // Parse a RSQL into a Node
        Node rootNode = new RSQLParser().parse(term);

        // Create the JPA Visitor
        RsqlParserVisitor visitor = new RsqlParserVisitor();

        BooleanExpression predicate = rootNode.accept(visitor, FIELD_MAPPING);
        return toDto(repo.findAll(predicate));
    }

    @Override
    public PageInfo<JobOrderInfo> pageInfo(String term,  Map<String, Object> params, String status, PageRequest pageRequest) {
        //Name filter
        BooleanExpression predicate = jobOrder.customer.name.surname.containsIgnoreCase(term)
                .or(jobOrder.customer.name.givenName.containsIgnoreCase(term));

        //trackingNo filter
        predicate = predicate.or(jobOrder.trackingNo.eq(term));

        //Status filter
        if (STATUS_OPEN.equals(status)) {
            predicate = predicate.and(jobOrder.status.eq(NEW)
                    .or(jobOrder.status.eq(CLEANED)));
        } else if (null != status && status.length() > 0) {
            predicate = predicate.and(jobOrder.status.eq(JobOrderStatus.valueOf(status)));
        }

        //Additional params
        //Customer
        Long customerId = (Long) params.get("customer");
        if (null != customerId) {
            predicate = predicate.and(jobOrder.customer.id.eq(customerId));
        }

        return super.pageInfo(predicate, pageRequest);
    }

    @Override
    public JobOrderInfo findByTrackinNoInfo(String trackingNo) {
        return toDto(repo.findByTrackingNo(trackingNo));
    }

    @Override
    public JobOrderInfo saveInfo(JobOrderInfo jobOrderInfo) {
        if (null == jobOrderInfo.getTrackingNo()) {
            jobOrderInfo.setDateReceived(DateTime.now());
            jobOrderInfo.setDateDue(jobOrderInfo.getDateReceived().plusDays(3));
            jobOrderInfo.setTrackingNo(sequenceService.next());
        }

        JobOrder jobOrder = toEntity(jobOrderInfo);
        for (JobService service : jobOrder.getJobServices()) {
            service.setJobOrder(jobOrder);
        }
        for (JobItem item : jobOrder.getJobItems()) {
            item.setJobOrder(jobOrder);
        }
        for (LostAndFoundItem lostAndFound : jobOrder.getLostAndFoundItems()) {
            lostAndFound.setJobOrder(jobOrder);
        }

        return toDto(repo.save(jobOrder));
    }

}
