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
@Table(name = "rz_user")
public class RzUser implements DalPojo {

    /**
     * ��������
     */
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(value = Types.BIGINT)
	private Long id;

    /**
     * ��¼��
     */
	@Column(name = "login_name")
	@Type(value = Types.VARCHAR)
	private String loginName;

    /**
     * ��¼��
     */
	@Column(name = "login_password")
	@Type(value = Types.VARCHAR)
	private String loginPassword;

    /**
     * ����
     */
	@Column(name = "user_name")
	@Type(value = Types.VARCHAR)
	private String userName;

    /**
     * �û�Id
     */
	@Column(name = "user_id")
	@Type(value = Types.VARCHAR)
	private String userId;

    /**
     * �Ա� 0δ֪��1�У�2 Ů
     */
	@Column(name = "user_sex")
	@Type(value = Types.INTEGER)
	private Integer userSex;

    /**
     * ����
     */
	@Column(name = "user_age")
	@Type(value = Types.INTEGER)
	private Integer userAge;

    /**
     * �ֻ���
     */
	@Column(name = "user_mobileno")
	@Type(value = Types.VARCHAR)
	private String userMobileno;

    /**
     * ����
     */
	@Column(name = "user_email")
	@Type(value = Types.INTEGER)
	private Integer userEmail;

    /**
     * �û�״̬ 0 ����,1 �����ã�2 ����
     */
	@Column(name = "user_status")
	@Type(value = Types.INTEGER)
	private Integer userStatus;

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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserMobileno() {
		return userMobileno;
	}

	public void setUserMobileno(String userMobileno) {
		this.userMobileno = userMobileno;
	}

	public Integer getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(Integer userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
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