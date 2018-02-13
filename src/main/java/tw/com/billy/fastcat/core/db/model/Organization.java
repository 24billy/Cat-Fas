package tw.com.billy.fastcat.core.db.model;

/**
 * [單位]表格物件
 * 
 * @author Billy
 *
 */
public class Organization {

	private Integer organizationid;
	private String name;
	private Boolean isVisible;

	public Organization() {
	}

	public Organization(Integer organizationid, String name, Boolean isVisible) {
		super();
		this.organizationid = organizationid;
		this.name = name;
		this.isVisible = isVisible;
	}

	public Integer getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(Integer organizationid) {
		this.organizationid = organizationid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	@Override
	public String toString() {
		return "Organization [organizationid=" + organizationid + ", name=" + name + ", isVisible=" + isVisible + "]";
	}

}
