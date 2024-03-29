package com.vass.reniec.pe.api.enums;
public enum Filters {
    ID_UBIGEO("id")
    , CODIGO_UBIGEO("codigo")
    , NAME_DEPARTAMENTO("departamento")
    , NAME_PROVINCIA("provincia")
    , NAME_DISTRITO("distrito")
    , CENTRO_ATENCION("centroDeAtencion")
    , RESTRICT_FIELDS("restrictFields")
    , EMAIL_ADDRESS("emailAddress")
    , LIFERAY_COMPANY_ID("liferayCompanyId")
    , PAGE_SIZE("pageSize")
    , KEY("Key")
    , SORT("sort")
    , ASC("asc")
    , DESC("desc")
    , DATE_CREATED("dateCreated")
    , MODIFIED_DATE("dateModified")
    , ID_UBIGEO_OFICINA("r_ubigeo_c_ubigeoId")
    ;

    private String filter;

    Filters(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }
}
