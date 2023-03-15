package com.sample.mall.common;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Objects;

/**
 * Created by LuoboGan
 * Date:2023/3/10
 */
public class SpElTest {

    @Test
    public void testSpringFormat(){
        String goodsCacheKey = "mall:goods:%s";
        String cacheKey = String.format(goodsCacheKey,1);
        Assert.assertEquals("mall:goods:1",cacheKey);
    }

    @Test
    public void testSpEl(){
        // 创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 解析表达式
        Expression expression = parser.parseExpression("'mall:goods:' + #userId");
        // 构建上下文
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("userId",1);
        String result = Objects.requireNonNull(expression.getValue(context)).toString();
        Assert.assertEquals("mall:goods:1",result);
    }

}
