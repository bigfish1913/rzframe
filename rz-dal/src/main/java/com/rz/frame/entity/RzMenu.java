package com.rz.frame.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Sensitive;
import com.ctrip.platform.dal.dao.annotation.Type;
import java.sql.Types;
import java.sql.Timestamp;

import com.ctrip.platform.dal.dao.DalPojo;

/**
 * @author fu
 * @date 2019-07-14
 */
@Entity
@Database(name = "rzframe")
@Table(name = "rz_menu")
public class RzMenu implements DalPojo {


    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(value = Types.INTEGER)
	private Integer id;

    /**
     * �˵�����
     */
	@Column(name = "menu_name")
	@Type(value = Types.VARCHAR)
	private String menuName;

    /**

    /**
     * �˵�Url
     */
	@Column(name = "menu_src")
	@Type(value = Types.VARCHAR)
	private String menuSrc;
	@Column(name = "menu_icon")
	@Type(value = Types.VARCHAR)
	private String menuIcon;

    /**
     * �˵�״̬0 ����,1 ������
     */
	@Column(name = "menu_status")
	@Type(value = Types.INTEGER)
	private Integer menuStatus;

    /**
     * �ֲ˵�Id
     */
	@Column(name = "parentmenu_id")
	@Type(value = Types.INTEGER)
	private Integer parentmenuId;

    /**
     * ����ʱ��
     */
	@Column(name = "create_time")
	@Type(value = Types.TIMESTAMP)
	private Timestamp createTime;

    /**
     * ������ʱ��
     */
	@Column(name = "dataChange_lastTime", insertable = false, updatable = false)
	@Type(value = Types.TIMESTAMP)
	private Timestamp datachangeLasttime;


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public String getMenuSrc() {
		return menuSrc;
	}

	public void setMenuSrc(String menuSrc) {
		this.menuSrc = menuSrc;
	}

	public Integer getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getDatachangeLasttime() {
		return datachangeLasttime;
	}

	public void setDatachangeLasttime(Timestamp datachangeLasttime) {
		this.datachangeLasttime = datachangeLasttime;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentmenuId() {
		return parentmenuId;
	}

	public void setParentmenuId(Integer parentmenuId) {
		this.parentmenuId = parentmenuId;
	}
}