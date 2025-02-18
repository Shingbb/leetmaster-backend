package com.shing.leetmaster.json;

/**
 * @author Shing
 */
public class JsonTest {
    public static void main(String[] args) {
        String json = "SELECT id, name, UPPER(name) AS upper_name\n" +
                "FROM student\n" +
                "WHERE name = '热dog';";

        System.out.println(json);
    }
}
