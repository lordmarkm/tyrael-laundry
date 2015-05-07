package com.tyrael.laundry.service.custom.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import com.baldy.commons.models.BaseEntity;
import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;
import com.tyrael.commons.data.service.TyraelJpaService;
import com.tyrael.commons.data.service.TyraelJpaServiceCustomImpl;
import com.tyrael.commons.dto.BaseTyraelDto;
import com.tyrael.commons.dto.PageInfo;
import com.tyrael.laundry.service.custom.RqlSearchingService;
import com.tyrael.laundry.service.rql.RsqlParserVisitor;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * @author mbmartinez
 */
public abstract class RqlSearchingServiceImpl<E extends BaseEntity, D extends BaseTyraelDto, R extends TyraelJpaService<E>>
    extends TyraelJpaServiceCustomImpl<E, D, R>
    implements RqlSearchingService<E, D> {

    private static Logger LOG = LoggerFactory.getLogger(RqlSearchingServiceImpl.class);

    protected abstract ImmutableMap<String, Path<?>> fieldMapping();

    @Override
    public PageInfo<D> searchRql(String term, Pageable pageRequest) {
        LOG.debug("Performing paginated rql search. term={}, page = {}", term, pageRequest);

        BooleanExpression predicate = null;
        if (!StringUtils.isBlank(term)) {
            try {
                Node rootNode = new RSQLParser().parse(term);
                RsqlParserVisitor visitor = new RsqlParserVisitor();
                predicate = rootNode.accept(visitor, this.fieldMapping());
            } catch (Exception e) {
                LOG.error("Error parsing or interpreting rql term. term={}, error={}", term, e.getMessage());
                return PageInfo.blank();
            }
        }

        return super.pageInfo(predicate, pageRequest);
    }

}
