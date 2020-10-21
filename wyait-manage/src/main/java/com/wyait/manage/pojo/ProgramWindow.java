package com.wyait.manage.pojo;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 办事窗口
 * @author ：lp
 * @date ：Created in 2020/9/21 15:05
 * @modified By：
 * @version: 1.0$
 */
@TableName("program_window")
public class ProgramWindow implements Serializable {

    private static final long serialVersionUID = -6047262851674458516L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Integer id;


//    @Column(name = "win_id")
    private String winId;  //窗口主键

//    @Column(name = "win_name")
    private String winName; // 窗口名称

//    @Column(name = "tel")
    private String tel;    // 联系电话

//    @Column(name = "address")
    private String address;  // 窗口地址

//    @Column(name = "worktime")
    private String worktime;  // 办公时间

//    @Column(name = "traffic_guide")
    private String trafficGuide;  // 交通指引

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWinId() {
        return winId;
    }

    public void setWinId(String winId) {
        this.winId = winId;
    }

    public String getWinName() {
        return winName;
    }

    public void setWinName(String winName) {
        this.winName = winName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getTrafficGuide() {
        return trafficGuide;
    }

    public void setTrafficGuide(String trafficGuide) {
        this.trafficGuide = trafficGuide;
    }

}
