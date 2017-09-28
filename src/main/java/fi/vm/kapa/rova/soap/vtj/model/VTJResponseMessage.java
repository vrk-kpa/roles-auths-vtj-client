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
package fi.vm.kapa.rova.soap.vtj.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "VTJHenkiloVastaussanoma", namespace = "http://xml.vrk.fi/schema/vtjkysely")
@XmlAccessorType(XmlAccessType.FIELD)
public class VTJResponseMessage {

    @XmlElement(name = "Henkilo")
    private Person person;

    @XmlElement(name = "Paluukoodi")
    private ResponseCode responseCode;

    @XmlAttribute(name = "tietojenPoimintaaika")
    private String dataTimeStamp;

    @XmlAttribute(name = "sanomatunnus")
    private VTJSchema messageID;

    @XmlAttribute(name = "versio")
    private String version;

    public String getDataTimeStamp() {
        return dataTimeStamp;
    }

    public void setDataTimeStamp(String dataTimeStamp) {
        this.dataTimeStamp = dataTimeStamp;
    }

    public VTJSchema getMessageID() {
        return messageID;
    }

    public void setMessageID(VTJSchema messageID) {
        this.messageID = messageID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "VTJResponseMessage [person=" + person + ", responseCode="
                + responseCode + ", dataTimeStamp=" + dataTimeStamp
                + ", messageID=" + messageID + ", version=" + version + "]";
    }

}
