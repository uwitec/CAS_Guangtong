package cn.guangtong.entity.cooperation;

public class ModelInfo {
    private String id;
    private double length;
    private double bulk;
    private int refrigerate;
    private String desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getBulk() {
		return bulk;
	}
	public void setBulk(double bulk) {
		this.bulk = bulk;
	}
	public int getRefrigerate() {
		return refrigerate;
	}
	public void setRefrigerate(int refrigerate) {
		this.refrigerate = refrigerate;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ModelInfo(String id, double length, double bulk, int refrigerate,
			String desc) {
		super();
		this.id = id;
		this.length = length;
		this.bulk = bulk;
		this.refrigerate = refrigerate;
		this.desc = desc;
	}
	public ModelInfo() {
		super();
	}
}