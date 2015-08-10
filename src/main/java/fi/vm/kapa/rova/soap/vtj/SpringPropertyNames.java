package fi.vm.kapa.rova.soap.vtj;

public interface SpringPropertyNames {

    String VTJ_USERNAME = "${vtj_username}"; // vtj-palvelun käyttäjätunnus
    String VTJ_PASSWORD = "${vtj_password}"; // vtj-palvelun salasana
    String VTJ_USERID = "${userId}"; // xroad-kutsun userId
    String VTJ_ID = "${id}"; // xroad-kutsun id
    String VTJ_ISSUE = "${vtj_issue}"; // xroad-kutsun issue
    String VTJ_SDSB_INSTANCE = "${service_sdsb_instance}"; // kutsuttavan xroad-instanssin id (eg. FI_DEV)
    String VTJ_MEMBER_CLASS = "${service_member_class}"; // xroad-kohdeorganisaation tyyppi (eg. COM, ORG, GOV)
    String VTJ_MEMBER_CODE = "${service_member_code}"; // xroad-kohdeorganisaation id (eg. y-tunnus)
    String VTJ_SUBSYSTEM_CODE = "${service_subsystem_code}"; // xroad-kohdealijärjestelmän nimi (eg. DemoService)
    String VTJ_SERVICE_CODE = "${service_service_code}"; // xroad-kohdepalvelun nimi (eg. getRandom)

    String SERVICE_OBJECT_TYPE = "${service_object_type}"; // xroad-kutsun palvelutyyppi 
    String CLIENT_OBJECT_TYPE = "${client_object_type}"; // xroad-kutsun objektityyppi	

    String ROVA_ENDPOINT = "${rova_endpoint}"; // xroad-kutsun endpoint (oman liityntäpalvelimen ip)
    String ROVA_SDSB_INSTANCE = "${client_sdsb_instance}"; // oman xroad-instanssin id
    String ROVA_MEMBER_CLASS = "${client_member_class}"; // oman xroad-organisaation tyyppi
    String ROVA_MEMBER_CODE = "${client_member_code}"; // oman xroad-organisaation id
    String ROVA_SUBSYSTEM_CODE = "${client_subsystem_code}"; // oman xroad-alijärjestelmän nimi

}
