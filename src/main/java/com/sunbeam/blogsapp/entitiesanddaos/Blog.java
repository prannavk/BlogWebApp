package com.sunbeam.blogsapp.entitiesanddaos;

import java.util.Date;

public class Blog {

	private int id;
	private String title;
	private String contents;
	private Date created_time;
	private int userid;
	private String extraField;
	private int categoryid;

	public Blog() {
		// def
	}

	public Blog(int id, String title, String contents, Date created_time, int userid, String extrafield,
			int categoryid) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.created_time = created_time;
		this.userid = userid;
		this.extraField = extraField;
		this.categoryid = categoryid;
	}

	public Blog(String title, String contents, Date created_time, int userid, int categoryid) {
		super();
		this.title = title;
		this.contents = contents;
		this.created_time = created_time;
		this.userid = userid;
		this.categoryid = categoryid;
	}

	public Blog(int id, String title, String contents, Date created_time, int categoryid) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.created_time = created_time;
		this.categoryid = categoryid;
	}

	public Blog(int id, String title, String contents, Date created_time, int userid, int categoryid) {
		super();
		this.id = id;
		this.title = title;
		this.contents = contents;
		this.created_time = created_time;
		this.userid = userid;
		this.categoryid = categoryid;
	}

	public String getExtraField() {
		return extraField;
	}

	public void setExtraField(String extraField) {
		this.extraField = extraField;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}


}
