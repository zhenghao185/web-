package com.web.webwork4.Data;

import lombok.Data;

import java.io.Serializable;
import java.util.Vector;

@Data
public class Table implements Serializable {

    private Vector<ContactData> tableinfo;

    public Table() {
        tableinfo = new Vector<ContactData>();
        tableinfo.add(new ContactData("张大三", "13621130234", "fwafwaf@qq.com"
                , "北京市海淀区北京邮电大学附属小学", "17341933", ""));
        tableinfo.add(new ContactData("李丽丽", "13412219418", "awdawf@126.com"
                , "北京市海淀区北京邮电大学附属中学", "472810191", ""));
        tableinfo.add(new ContactData("呜呜呜", "12378429111", "fwafwa@163.com"
                , "北京市海淀区北京邮电大学附属幼儿园", "67123891", ""));
        tableinfo.add(new ContactData("张小红", "13161109663", "awfw@qq.com"
                , "北京市海淀区北京邮电大学", "1838831597", ""));
    }
}
