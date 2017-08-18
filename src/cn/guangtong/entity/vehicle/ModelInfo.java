package cn.guangtong.entity.vehicle;


public class ModelInfo {
    private Integer id;

    private String height;

    private String width;

    private String length;

    private String bulk;

    private String volumerate;

    private String capacity;

    private Boolean refrigerate;

    private String lwh;
    
    private String desc;
    
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLwh() {
		return lwh;
	}

	public void setLwh(String lwh) {
		this.lwh = lwh;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id == null ? null : id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getBulk() {
        return bulk;
    }

    public void setBulk(String bulk) {
        this.bulk = bulk;
    }

    public String getVolumerate() {
        return volumerate;
    }

    public void setVolumerate(String volumerate) {
        this.volumerate = volumerate;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Boolean getRefrigerate() {
        return refrigerate;
    }

    public void setRefrigerate(Boolean refrigerate) {
        this.refrigerate = refrigerate;
    }



	public String toString() {
		return "ModelInfo [id=" + id + ", height=" + height + ", width="
				+ width + ", length=" + length + ", bulk=" + bulk
				+ ", volumerate=" + volumerate + ", capacity=" + capacity
				+ ", refrigerate=" + refrigerate +  "]";
	}

	public ModelInfo(Integer id, String height, String width,
			String length, String bulk, String volumerate,
			String capacity, Boolean refrigerate, String desc) {
		super();
		this.id = id;
		this.height = height;
		this.width = width;
		this.length = length;
		this.bulk = bulk;
		this.volumerate = volumerate;
		this.capacity = capacity;
		this.refrigerate = refrigerate;
	}

	public ModelInfo() {
		super();
	}
    
}
