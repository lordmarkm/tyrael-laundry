package com.tyrael.laundry.service.rql;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

/**
 * @author mbmartinez
 */
public abstract class AbstractExpressionEvaluator {

    public abstract BooleanExpression evaluate(Path<?> path, ComparisonOperator operator, List<String> arguments);

    @SuppressWarnings("rawtypes")
    protected List cast(Class<?> type, List<String> string) {
        if (type.isAssignableFrom(String.class)) {
            return string;
        }
        List<Object> converted = Lists.newArrayList();
        for (int i = 0; i < string.size(); i++) {
            converted.add(cast(type, string.get(i)));
        }
        return converted;
    }

    @SuppressWarnings("unchecked")
    protected Object cast(Class<?> type, String string) {
        if (type.isAssignableFrom(String.class)) {
            return string;
        } else if (type.isAssignableFrom(Long.class)) {
            return Long.valueOf(string);
        } else if (type.isAssignableFrom(Boolean.class)) {
            return Boolean.valueOf(string);
        } else if (type.isAssignableFrom(DateTime.class)) {
            return DateTime.parse(string);
        } else if (type.isAssignableFrom(Enum.class)) {
            return EnumUtils.getEnum((Class) type, string);
        }
        throw new IllegalArgumentException("Selector class not supported. selector class=" + type);
    }
}
