package com.github.crud.generator.constant;

public enum ImportedAnnotation {
	REPOSITORY("@Repository"),
	SERVICE("@Service"),
	AUTOWIRED("@Autowired"),
	OVERRIDE("@Override"),
	REST_CONTROLLER("@RestController"),
	REQUEST_MAPPING("@RequestMapping");

	private ImportedAnnotation(final String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}
}
