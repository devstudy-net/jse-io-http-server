package net.devstudy.jse.lection02_inheritance_polymorph;

import net.devstudy.jse.lection01_classes_objects.Student;

/**
 *
 * @author devstudy
 * @see http://devstudy.net
 */
public class ForeignStudent extends Student {
	private String language;

	public ForeignStudent(String firstName, String lastName, int age, String language) {
		super(firstName, lastName, age);
		setLanguage(language);
	}

	public ForeignStudent() {
		super();
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	@Override
	public String toString() {
		return String.format("ForeignStudent [language=%s, getFirstName()=%s, getLastName()=%s, getAge()=%s]", language,
				getFirstName(), getLastName(), getAge());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForeignStudent other = (ForeignStudent) obj;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		return true;
	}
}
