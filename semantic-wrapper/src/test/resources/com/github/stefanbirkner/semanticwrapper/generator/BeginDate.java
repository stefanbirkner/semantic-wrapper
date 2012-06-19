package com.github.stefanbirkner.semanticwrapper.generator;

import java.util.Date;

public class BeginDate {
	private final Date beginDate;

	public static BeginDate beginDate(Date beginDate) {
		return (beginDate == null) ? null : new BeginDate(beginDate);
	}

	public BeginDate(Date beginDate) {
		if (beginDate == null)
			throw new IllegalArgumentException("Cannot wrap null.");
		this.beginDate = beginDate;
	}

	public Date dateValue() {
		return beginDate;
	}

	@Override
	public int hashCode() {
		return beginDate.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeginDate other = (BeginDate) obj;
		return (beginDate.equals(other.beginDate));
	}

	@Override
	public String toString() {
		return beginDate.toString();
	}
}