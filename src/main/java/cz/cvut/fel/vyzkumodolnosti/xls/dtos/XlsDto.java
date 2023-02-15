package cz.cvut.fel.vyzkumodolnosti.xls.dtos;

public class XlsDto {
	private String name;
	private String nameEng;
	private Object value;

	public XlsDto(String name, String nameEng) {
		this.name = name;
		this.nameEng = nameEng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
