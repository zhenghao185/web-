package com.zh.web5.Data;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="contact")
public class ContactInfor implements Serializable {

    @Id
    @Column(name = "Cname", length = 40)
    private String contactname;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;                             // 邮箱

    @Column(name = "addr")
    private String addr;                              // 住址

    @Column(name = "qq")
    private String qq;                                // qq号

    @Transient
    private String message;                           // 提示消息

    public ContactInfor(String cont, String tel, String email, String addr, String qq, String message) {
        this.contactname = cont;
        this.tel = tel;
        this.email = email;
        this.qq = qq;
        this.addr = addr;
        this.message = message;
    }

    public ContactInfor() {                           // 对于 Entity 实体类，必须存在默认构造函数
        this.contactname = "";
        this.tel = "";
        this.email = "";
        this.qq = "";
        this.addr = "";
        this.message = null;
    }
}
