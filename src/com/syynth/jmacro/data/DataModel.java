package com.syynth.jmacro.data;

/**
 * @author bcochrane
 * @since 3/5/14
 */
public class DataModel {

	private String[] fields;

	public DataModel(String[] fields) {
		this.fields = fields;
		for (String f : fields) {
			f = f.trim();
		}
	}

	String get(int i) {
		return fields[i];
	}

	@Override public String toString() {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < fields.length; ++i) {
			builder.append(fields[i]).append(i == fields.length - 1 ? "" : ", ");
		}
		return builder.append("]").toString();
	}

}
