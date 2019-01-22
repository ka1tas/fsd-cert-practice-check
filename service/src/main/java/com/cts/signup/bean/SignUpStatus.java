package com.cts.signup.bean;

public class SignUpStatus {
	private boolean signupStatus;
	private boolean emailExist;

	public boolean isSignupStatus() {
		return signupStatus;
	}

	public void setSignupStatus(boolean signupStatus) {
		this.signupStatus = signupStatus;
	}

	public boolean isEmailExist() {
		return emailExist;
	}

	public void setEmailExist(boolean emailExist) {
		this.emailExist = emailExist;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (emailExist ? 1231 : 1237);
		result = prime * result + (signupStatus ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignUpStatus other = (SignUpStatus) obj;
		if (emailExist != other.emailExist)
			return false;
		if (signupStatus != other.signupStatus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SignupStatus [signupStatus=" + signupStatus + ", emailExist=" + emailExist + "]";
	}

}
