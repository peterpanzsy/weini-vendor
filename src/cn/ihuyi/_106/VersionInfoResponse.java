
package cn.ihuyi._106;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VersionInfoResult" type="{http://106.ihuyi.cn/}VersionInfoResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "versionInfoResult"
})
@XmlRootElement(name = "VersionInfoResponse")
public class VersionInfoResponse {

    @XmlElement(name = "VersionInfoResult")
    protected VersionInfoResult versionInfoResult;

    /**
     * ��ȡversionInfoResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link VersionInfoResult }
     *     
     */
    public VersionInfoResult getVersionInfoResult() {
        return versionInfoResult;
    }

    /**
     * ����versionInfoResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link VersionInfoResult }
     *     
     */
    public void setVersionInfoResult(VersionInfoResult value) {
        this.versionInfoResult = value;
    }

}