package com.shing.leetmaster.json;

/**
 * @Author: Shing
 */
public class StringTest {

    public static void main(String[] args) {
        // 原始带有换行符的 SQL 查询字符串
        String sqlQuery = "SELECT id, name, UPPER(name) AS upper_name\n" +
                "FROM student\n" +
                "WHERE name = '热dog';";

        // 去除换行符并合并为一整串
        String result = sqlQuery.replace("\n", " ");  // 将换行符替换为空格，避免文字黏在一起

        // 优化方案方案一
        String betterResultWithSpace  = sqlQuery.replaceAll("\\s+", " "); // 将所有空白字符（包括换行、回车、制表符）替换为一个空格

        // todo优化方案方案二
        // todo优化方案方案三

        // 打印结果
        System.out.println(result);
        System.out.println(betterResultWithSpace);

    }

}
