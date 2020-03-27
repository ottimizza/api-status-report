package br.com.ottimizza.statusreportapi.query.utils;

public class UtilsQuery {

    public static String stringFormat(String field) {
        return "'" + field + "'";
    }
    
    public static String makeStartsWith(String field) {
        return "'" + field + "%'";
    }

    public static String makeEndsWith(String field) {
        return "'%" + field + "'";
    }

    public static String makeStartsAndEndsWith(String field) {
        return "'%" + field + "%'";
    }
}