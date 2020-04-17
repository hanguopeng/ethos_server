package com.dutyMS.common.utils;


import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

//import com.boco.eoms.commons.loging.BocoLog;
//import com.boco.eoms.crm.model.RecordInfo;
//import com.boco.eoms.sheet.engineercutandnetadjust.service.Bo.GeJieRecordInfo;

public class StaxParser {

    private static XMLInputFactory factory = null;

    private static StaxParser _staxParser = null;

    public static StaxParser getInstance() {
        if (_staxParser == null) {
            factory = XMLInputFactory.newInstance();
            _staxParser = new StaxParser();
        }
        return _staxParser;
    }

    public List getOpdetailList(String opdetail) throws Exception {
//        BocoLog.info(this, "start extra opdetail");
        XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(opdetail));
        String fieldEnName = "";
        List list = new ArrayList();
        Map map = new HashMap();
        while (reader.hasNext()) {
            switch (reader.getEventType()) {
                case XMLStreamReader.START_ELEMENT:
                    String name = reader.getLocalName();
                    if ("recordInfo".equals(name)) {
                        if (map != null && !map.isEmpty()) {
                            Map tmap = new HashMap();
                            tmap.putAll(map);
                            list.add(tmap);
                            map = new HashMap();
                        }
                    } else if ("fieldChName".equals(name)) {

                    } else if ("fieldEnName".equals(name)) {
                        fieldEnName = reader.getElementText();
                    } else if ("fieldContent".equals(name)) {
                        String desc = reader.getElementText();
                        if (fieldEnName != null && fieldEnName.length() > 0)
                            map.put(fieldEnName, desc);
                    }
                    break;
            }
            reader.next();//获取下一个解析事件
        }
        if (map != null && !map.isEmpty()) {
            Map tmap = new HashMap();
            tmap.putAll(map);
            list.add(tmap);
        }
//        BocoLog.info(this, "end extra opdetail");
        return list;
    }

    public static void main(String avg[]) {
        String xml = "<opDetail><recordInfo><fieldInfo><fieldChName>???????</fieldChName><fieldEnName>alarmId</fieldEnName><fieldContent>BOCO_WNMS_1581330500_3443997320_4149258040_3998280080</fieldContent></fieldInfo><fieldInfo><fieldChName>????ID</fieldChName><fieldEnName>alarmStaId</fieldEnName><fieldContent>007-012-00-002332</fieldContent></fieldInfo><fieldInfo><fieldChName>?????</fieldChName><fieldEnName>oriAlarmId</fieldEnName><fieldContent>117475848</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmTitle</fieldEnName><fieldContent>????</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmCreateTime</fieldEnName><fieldContent>2009-4-29 10:50:56</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>NeType</fieldEnName><fieldContent>010102</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmVendor</fieldEnName><fieldContent>??</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>equipType</fieldEnName><fieldContent>200</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>neName</fieldEnName><fieldContent>??</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmLevel</fieldEnName><fieldContent>4</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmType</fieldEnName><fieldContent>????</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmSubType</fieldEnName><fieldContent>BTS????</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmProvince</fieldEnName><fieldContent>GS</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmRegion</fieldEnName><fieldContent>???</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmLocation</fieldEnName><fieldContent>huawei_m2000</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmDetail</fieldEnName><fieldContent>????????:??????????????:???????????:&lt;+++&gt;???????  =  164242?????  =  117475848????  =  .3221229568.3221233664.3221282830.3221987331.3221954563.3221959024????  =  ??????  =  BTS????  =  .3221229568.3221233664.3221282830????  =  JICBSC8????  =  BSC6000??ID  =  2332????  =  ????????  =  ??????  =  ??????  =  ??? &amp; ???????  =  ????????  =  2009-04-29 10:50:56????  =  ???=35,????=5,??ID=133,???=2,???=255,?????=65535&lt;---&gt;</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>createType</fieldEnName><fieldContent>1</fieldContent></fieldInfo><fieldInfo><fieldChName>???</fieldChName><fieldEnName>sender</fieldEnName><fieldContent>wangjie</fieldContent></fieldInfo></recordInfo></opDetail>";
        String xml2 = "<opDetail><recordInfo><fieldInfo><fieldChName>???????</fieldChName><fieldEnName>alarmId</fieldEnName><fieldContent>BOCO_WNMS_1581330500_3443997320_4149258040_3998280080"
                + "</fieldContent></fieldInfo><fieldInfo><fieldChName>????ID</fieldChName><fieldEnName>alarmStaId</fieldEnName><fieldContent>007-012-00-002332</fieldContent></fieldInfo><fieldInfo>"
                + "<fieldChName>?????</fieldChName><fieldEnName>oriAlarmId</fieldEnName><fieldContent>117475848</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmTitle"
                + "</fieldEnName><fieldContent>????</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmCreateTime</fieldEnName><fieldContent>2009-4-29 10:50:56</fieldContent>"
                + "</fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>NeType</fieldEnName><fieldContent>010102</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName>"
                + "<fieldEnName>alarmVendor</fieldEnName><fieldContent>??</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>equipType</fieldEnName><fieldContent>"
                + "200</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>neName</fieldEnName><fieldContent>??</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName>"
                + "<fieldEnName>alarmLevel</fieldEnName><fieldContent>4</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmType</fieldEnName><fieldContent>????</fieldContent>"
                + "</fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmSubType</fieldEnName><fieldContent>BTS????</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName>"
                + "<fieldEnName>alarmProvince</fieldEnName><fieldContent>GS</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmRegion</fieldEnName><fieldContent>???</fieldContent>"
                + "</fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmLocation</fieldEnName><fieldContent>huawei_m2000</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName>"
                + "<fieldEnName>alarmDetail</fieldEnName><fieldContent>????????:??????????  =  ???=35,????=5,??ID=133,???=2,???=255,?????=65535&lt;---&gt;</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName>"
                + "<fieldEnName>createType</fieldEnName><fieldContent>1</fieldContent></fieldInfo><fieldInfo><fieldChName>???</fieldChName><fieldEnName>sender</fieldEnName><fieldContent>wangjie</fieldContent>"
                + "</fieldInfo></recordInfo><recordInfo><fieldInfo><fieldChName>???????</fieldChName><fieldEnName>alarmId</fieldEnName><fieldContent>BOCO_WNMS_1581330500_3443997320_4149258040_3998280080</fieldContent>"
                + "</fieldInfo><fieldInfo><fieldChName>????ID</fieldChName><fieldEnName>alarmStaId</fieldEnName><fieldContent>007-012-00-002332</fieldContent></fieldInfo><fieldInfo><fieldChName>?????</fieldChName>"
                + "<fieldEnName>oriAlarmId</fieldEnName><fieldContent>117475848</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmTitle</fieldEnName><fieldContent>????</fieldContent>"
                + "</fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmCreateTime</fieldEnName><fieldContent>2009-4-29 10:50:56</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName>"
                + "<fieldEnName>NeType</fieldEnName><fieldContent>010102</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmVendor</fieldEnName><fieldContent>??</fieldContent></fieldInfo>"
                + "<fieldInfo><fieldChName>????</fieldChName><fieldEnName>equipType</fieldEnName><fieldContent>200</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>neName</fieldEnName>"
                + "<fieldContent>??</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmLevel</fieldEnName><fieldContent>4</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName>"
                + "<fieldEnName>alarmType</fieldEnName><fieldContent>????</fieldContent></fieldInfo><fieldInfo><fieldChName>??????</fieldChName><fieldEnName>alarmSubType</fieldEnName><fieldContent>BTS????</fieldContent>"
                + "</fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmProvince</fieldEnName><fieldContent>GS</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName>"
                + "<fieldEnName>alarmRegion</fieldEnName><fieldContent>???</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmLocation</fieldEnName>"
                + "<fieldContent>huawei_m2000</fieldContent></fieldInfo><fieldInfo><fieldChName>????</fieldChName><fieldEnName>alarmDetail</fieldEnName>"
                + "<fieldContent>????????:??????????  =  ????????  =  2009-04-29 10:50:56????  =  ???=35,????=5,??ID=133,???=2,???=255,?????=65535&lt;---&gt;</fieldContent></fieldInfo>"
                + "<fieldInfo><fieldChName>??????</fieldChName><fieldEnName>createType</fieldEnName><fieldContent>1</fieldContent></fieldInfo><fieldInfo><fieldChName>???</fieldChName>"
                + "<fieldEnName>sender</fieldEnName><fieldContent>abc</fieldContent></fieldInfo></recordInfo></opDetail>";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(dateFormat.format(new Date()));
        Map sheetMap = new HashMap();
        sheetMap.put("createType", "0");
        try {
            getInstance().getOpdetailList(xml);
            System.out.println(dateFormat.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
