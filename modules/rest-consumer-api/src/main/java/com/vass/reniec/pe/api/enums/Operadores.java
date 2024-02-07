package com.vass.reniec.pe.api.enums;

public enum Operadores {
    EQUAL("eq"), NOT_EQUAL("ne"), GREATER_THAN("gt"), GREATER_THAN_OR_EQUAL("ge"), LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL("le"), STARS_WITH("sw"), AND("and"), OR("or"), NOT("not"), SIGN_EQUAL("="), SIGN_AND("&"),
    SIGN_DOUBLEPOINTS(":");

    private String operator;

    Operadores(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static String addFilter(String field, Operadores filter, long value) {
        return String.format("?filter=%s %s %d", field, filter.getOperator(), value);
    }

    public static String addFilter(String field, Operadores filter, boolean value) {
        return String.format("?filter=%s %s %b", field, filter.getOperator(), value);
    }

    public static String addFilter(String field, Operadores filter, int value) {
        return String.format("?filter=%s %s %d", field, filter.getOperator(), value);
    }

    public static String addSearch(String field, Operadores filter, long value) {
        return String.format("?search=%s %s %d", field, filter.getOperator(), value);
    }

    public static String addPageSize(int pageSize) {
        return String.format("?%s%s%d", Filters.PAGE_SIZE.getFilter(), Operadores.SIGN_EQUAL.getOperator(), pageSize);
    }

    public static String addFilter(String field, Operadores filter, String value) {
        return String.format("?filter=%s %s '%s'", field, filter.getOperator(), value);
    }

    public static String addPageSizeAndSort(int pageSize, String sort, String order) {
        return String.format("?%s%s%d%s%s%s%s%s%s", Filters.PAGE_SIZE.getFilter(), Operadores.SIGN_EQUAL.getOperator(),
                pageSize, Operadores.SIGN_AND.getOperator(), Filters.SORT.getFilter(),
                Operadores.SIGN_EQUAL.getOperator(), sort, Operadores.SIGN_DOUBLEPOINTS.getOperator(), order);
    }
}