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
    public static String addThreeFiltersAndOrder(String field, Operadores filter, String value, Operadores op1,
                                                 String field2, Operadores filter2, String value2, Operadores op2, String field3, Operadores filter3,
                                                 String value3, String sort, String order) {

        return String.format("?filter=%s %s '%s' %s %s %s '%s' %s %s %s '%s'%s%s%s%s%s%s", field, filter.getOperator(),
                value, op1.getOperator(), field2, filter2.getOperator(), value2, op2.getOperator(), field3,
                filter3.getOperator(), value3, Operadores.SIGN_AND.getOperator(), Filters.SORT.getFilter(),
                Operadores.SIGN_EQUAL.getOperator(), sort, Operadores.SIGN_DOUBLEPOINTS.getOperator(), order);
    }


    public static String addPageSizeAndSort(int pageSize, String sort, String order) {
        return String.format("?%s%s%d%s%s%s%s%s%s", Filters.PAGE_SIZE.getFilter(), Operadores.SIGN_EQUAL.getOperator(),
                pageSize, Operadores.SIGN_AND.getOperator(), Filters.SORT.getFilter(),
                Operadores.SIGN_EQUAL.getOperator(), sort, Operadores.SIGN_DOUBLEPOINTS.getOperator(), order);
    }

    public static String addFilterPageSizeandSort(String field, Operadores filter, String value, int pageSize,
                                                  String orderField, String order) {
        return String.format("?filter=%s %s '%s'%s%s%s%d%s%s%s%s%s%s", field, filter.getOperator(), value,
                Operadores.SIGN_AND.getOperator(), Filters.PAGE_SIZE.getFilter(), Operadores.SIGN_EQUAL.getOperator(),
                pageSize, Operadores.SIGN_AND.getOperator(), Filters.SORT.getFilter(),
                Operadores.SIGN_EQUAL.getOperator(), orderField, Operadores.SIGN_DOUBLEPOINTS.getOperator(), order);
    }

    public static String addFilterAndKey(String field,  String KeyValue) {
        return String.format("?address='%s'%s%s%s%s", field, Operadores.SIGN_AND.getOperator(),Filters.KEY.getFilter(),Operadores.SIGN_EQUAL.getOperator(), KeyValue);
    }

}