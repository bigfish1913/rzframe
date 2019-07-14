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

    /**
     * ��������
     */
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(value = Types.BIGINT)
	private Long id;

    /**
     * �˵�����
     */
	@Column(name = "menu_name")
	@Type(value = Types.VARCHAR)
	private String menuName;

    /**
     * �˵�Id
     */
	@Column(name = "menu_id")
	@Type(value = Types.VARCHAR)
	private String menuId;

    /**
     * �˵�Url
     */
	@Column(name = "menu_src")
	@Type(value = Types.VARCHAR)
	private String menuSrc;

    /**
     * �˵�״̬0 ����,1 ������
     */
	@Column(name = "menu_status")
	@Type(value = Types.INTEGER)
	private Integer menuStatus;

    /**
     * �ֲ˵�Id
     */
	@Column(name = "submenu_id")
	@Type(value = Types.VARCHAR)
	private String submenuId;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
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

	public String getSubmenuId() {
		return submenuId;
	}

	public void setSubmenuId(String submenuId) {
		this.submenuId = submenuId;
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

}