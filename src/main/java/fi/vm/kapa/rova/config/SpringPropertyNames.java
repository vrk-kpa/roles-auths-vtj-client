/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.config;

public interface SpringPropertyNames {

    String VTJ_USERNAME = "${vtj_username}"; // vtj-palvelun käyttäjätunnus
    String VTJ_PASSWORD = "${vtj_password}"; //NOSONAR ei ole kovakoodattu vtj-palvelun salasana

    String SERVICE_SDSB_INSTANCE = "${service_sdsb_instance}"; // xroad instanssin id (eg. FI_DEV)
    String SERVICE_MEMBER_CLASS = "${service_member_class}"; // xroadia kutsuvan organisaation tyyppi (eg. COM, ORG, GOV)
    String SERVICE_MEMBER_CODE = "${service_member_code}"; // xroadia kutsuvan organisaation id (eg. yt-tunnus)
    String SERVICE_SUBSYSTEM_CODE = "${service_subsystem_code}"; // xroadin kautta kutsuttavan alijärjestelmän nimi (eg. DemoService)
    String SERVICE_SERVICE_CODE = "${service_service_code}"; // xroadin kautta kutsuttavan palvelun nimi (eg. getRandom)

    String SERVICE_OBJECT_TYPE = "${service_object_type}"; // xroad-kutsun palvelutyyppi 
    String SERVICE_SERVICE_VERSION = "${service_service_version}"; // palvelun versio

    String CLIENT_OBJECT_TYPE = "${client_object_type}"; // xroad-kutsun objektityyppi  

    String CLIENT_SDSB_INSTANCE = "${client_sdsb_instance}";
    String CLIENT_MEMBER_CLASS = "${client_member_class}";
    String CLIENT_MEMBER_CODE = "${client_member_code}";
    String CLIENT_SUBSYSTEM_CODE = "${client_subsystem_code}";

    String XROAD_ENDPOINT = "${xroad_endpoint}"; // xroad-kutsun endpoint (oma liityntäpalvelin)

}
