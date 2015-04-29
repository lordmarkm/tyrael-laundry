package com.tyrael.laundry.service.rql;

import org.slf4j.Logger;

import com.google.common.collect.ImmutableMap;
import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.NumberPath;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

/**
 * @author mbmartinez
 */
public class RsqlParserVisitor implements RSQLVisitor<BooleanExpression, ImmutableMap<String, Path<?>>> {

    private NumberPathExpressionEvaluator numPathEvaluator = new NumberPathExpressionEvaluator();
    private DefaultExpressionEvaluator defaultEvaluator = new DefaultExpressionEvaluator();

    @Override
    public BooleanExpression visit(AndNode node, ImmutableMap<String, Path<?>> param) {
        BooleanExpression b = null;
        for (Node subNode : node) {
            BooleanExpression subExp = subNode.accept(this, param);
            if (null != b) {
                b = b.and(subExp);
            } else {
                b = subExp;
            }
        }
        return b;
    }

    @Override
    public BooleanExpression visit(OrNode node, ImmutableMap<String, Path<?>> param) {
        BooleanExpression b = null;
        for (Node subNode : node) {
            BooleanExpression subExp = subNode.accept(this, param);
            if (null != b) {
                b = b.or(subExp);
            } else {
                b = subExp;
            }
        }
        return b;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public BooleanExpression visit(ComparisonNode node, ImmutableMap<String, Path<?>> param) {
        Path<?> path = param.get(node.getSelector());
        if (null == path) {
            throw new IllegalArgumentException("Unsupported query path: " + path);
        }

        if (path.getClass().isAssignableFrom(NumberPath.class)) {
            return numPathEvaluator.evaluate(path, node.getOperator(), node.getArguments());
        } else {
            return defaultEvaluator.evaluate(path, node.getOperator(), node.getArguments());
        }
    }

}
