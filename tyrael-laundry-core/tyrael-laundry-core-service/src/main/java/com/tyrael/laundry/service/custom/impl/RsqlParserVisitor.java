package com.tyrael.laundry.service.custom.impl;

import org.slf4j.Logger;

import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.EntityPathBase;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

public class RsqlParserVisitor<B> implements RSQLVisitor<BooleanExpression, B> {

    private Logger logger;

    @Override
    public BooleanExpression visit(AndNode node, EntityPathBase<?> param) {
        String nombreTmp = "OrNode";
        printLogicalNode(nombreTmp, node, param);
        return null;
    }

    @Override
    public BooleanExpression visit(OrNode node, EntityPathBase<?> param) {
        String nombreTmp = "EqualNode";
        printLogicalNode(nombreTmp, node, param);
        return null;
    }

    @Override
    public BooleanExpression visit(ComparisonNode node, EntityPathBase<?> param) {
        String nombreTmp = "EqualNode";
        printComparisonNode(nombreTmp, node, param);

        return null;
    }

    public void printLogicalNode(String pNombreNodo, LogicalNode pNode,
            EntityPathBase<?> pParam) {
        logger.debug(pNombreNodo + ". node: " + pNode + ". param: " + pParam);

        logger.debug("operator: " + pNode.getOperator().name());

        for (Node subNode : pNode) {
            logger.debug(pNombreNodo + ". subNode: " + subNode);
            subNode.accept(this, pParam);
        }
    }

    public void printComparisonNode(String pNombreNodo, ComparisonNode pNode,
            EntityPathBase<?> pParam) {
        logger.debug(pNombreNodo + ". node: " + pNode + ". param: " + pParam);

        logger.debug("Selector: " + pNode.getSelector());
        logger.debug("operator: " + pNode.getOperator());

        for (String argTmp : pNode.getArguments()) {
            logger.debug(pNombreNodo + ". argTmp: " + argTmp);
        }

    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public BooleanExpression visit(AndNode node, B param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BooleanExpression visit(OrNode node, B param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BooleanExpression visit(ComparisonNode node, B param) {
        // TODO Auto-generated method stub
        return null;
    }
}
