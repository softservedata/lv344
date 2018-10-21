package com.softserve.edu.opencart.data;

interface IFirstName {
	ILastName setFirstName(String firstName);
}

interface ILastName {
	IEMail setLastName(String lastName);
}

interface IEMail {
	ITelephone setEMail(String eMail);
}

interface ITelephone {
	IAddress1 setTelephone(String telephone);
}

interface IAddress1 {
	ICity setAddress1(String address1);
}

interface ICity {
	IPostCode setCity(String city);
}

interface IPostCode {
	ICountry setPostCode(String postCode);
}

interface ICountry {
	IRegionState setCountry(String country);
}

interface IRegionState {
	IPassword setRegionState(String regionState);
}

interface IPassword {
	IBuildUser setPassword(String password);
}

interface IBuildUser {
	IBuildUser setFax(String fax);
	IBuildUser setCompany(String company);
	IBuildUser setAddress2(String address2);
	IBuildUser setSubscribe(boolean subscribe);
	User build();
}

public class User implements IFirstName, ILastName, IEMail, ITelephone,
							 IAddress1, ICity, IPostCode, ICountry,
							 IRegionState, IPassword, IBuildUser, IUser {
	// Required
	private String firstName;
	private String lastName;
	private String eMail;
	private String telephone;
	private String address1;
	private String city;
	private String postCode;
	private String country;
	private String regionState;
	private String password;
	// not Required
	private String fax;
	private String company;
	private String address2;
	private boolean subscribe;

	// 1. Use Constructors
//	public User(String firstName, String lastName,
//			String eMail, String telephone, String address1, String city,
//			String postCode, String country, String regionState,
//			String password, String fax, String company,
//			String address2, boolean subscribe) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.eMail = eMail;
//		this.telephone = telephone;
//		this.address1 = address1;
//		this.city = city;
//		this.postCode = postCode;
//		this.country = country;
//		this.regionState = regionState;
//		this.password = password;
//		this.fax = fax;
//		this.company = company;
//		this.address2 = address2;
//		this.subscribe = subscribe;
//	}

//	public User(String firstName, String lastName,
//			String eMail, String telephone, String address1, String city,
//			String postCode, String country, String regionState, String password) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.eMail = eMail;
//		this.telephone = telephone;
//		this.address1 = address1;
//		this.city = city;
//		this.postCode = postCode;
//		this.country = country;
//		this.regionState = regionState;
//		this.password = password;
//	}

	// 2. Readable Parameters. Default Constructor
//	public User() {
//	}

	// 4. Add Private Constructor
	private User() {
		fax = new String();
		company = new String();
		address2 = new String();
		subscribe = true;
	}

	// 4. Add Statiс Factory
	// public static User get() {
	// 5. Add Builder by Interfaces
	public static IFirstName get() {
		return new User();
	}

	// setters

	// 3. Use Fluent Interface
	public ILastName setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public IEMail setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public ITelephone setEMail(String eMail) {
		this.eMail = eMail;
		return this;
	}

	public IAddress1 setTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	public ICity setAddress1(String address1) {
		this.address1 = address1;
		return this;
	}

	public IPostCode setCity(String city) {
		this.city = city;
		return this;
	}

	public ICountry setPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}

	public IRegionState setCountry(String country) {
		this.country = country;
		return this;
	}

	public IPassword setRegionState(String regionState) {
		this.regionState = regionState;
		return this;
	}

	public IBuildUser setPassword(String password) {
		this.password = password;
		return this;
	}

	public IBuildUser setFax(String fax) {
		this.fax = fax;
		return this;
	}

	public IBuildUser setCompany(String company) {
		this.company = company;
		return this;
	}

	public IBuildUser setAddress2(String address2) {
		this.address2 = address2;
		return this;
	}

	public IBuildUser setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
		return this;
	}

	public User build() {
		return this;
	}
	
	// getters

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEMail() {
		return eMail;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getAddress1() {
		return address1;
	}

	public String getCity() {
		return city;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getCountry() {
		return country;
	}

	public String getRegionState() {
		return regionState;
	}

	public String getPassword() {
		return password;
	}

	public String getFax() {
		return fax;
	}

	public String getCompany() {
		return company;
	}

	public String getAddress2() {
		return address2;
	}

	public boolean getSubscribe() {
		return subscribe;
	}

}
