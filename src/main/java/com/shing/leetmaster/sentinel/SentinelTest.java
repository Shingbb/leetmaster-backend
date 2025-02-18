package com.shing.leetmaster.sentinel;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class SentinelTest {
    public static void main(String[] args) {

        // 配置规则.
        initFlowRules();
        long startTime = System.currentTimeMillis();
        long timeout = 10000;  // 设置循环时间限制为 10 秒
        while (System.currentTimeMillis() - startTime < timeout) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("hello world");
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                System.out.println("blocked!");
            }
        }
    }

    /**
     * 初始化流控规则。
     * 该方法配置并设置了针对 "HelloWorld" 资源的流控规则，将其最大 QPS 限制为 20。
     */
    private static void initFlowRules() {
        // 创建一个列表用于存储流控规则。
        List<FlowRule> rules = new ArrayList<>();
        // 创建一个新的流控规则实例。
        FlowRule rule = new FlowRule();
        // 设置与规则关联的资源名称，即 "HelloWorld"。
        rule.setResource("HelloWorld");
        // 设置流控模式为 QPS 模式。
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置最大允许的 QPS 为 20。
        rule.setCount(20);
        // 将配置好的规则添加到规则列表中。
        rules.add(rule);
        // 将配置好的流控规则加载到 FlowRuleManager 中。
        FlowRuleManager.loadRules(rules);
    }


}
