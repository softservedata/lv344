package com.softserve.edu.opencart.data;

public class Appl {

	public static void main(String[] args) {
		//
		// 1. Use Constructors
//		User user = new User("firstName1", "lastName1",
//				"eMail1", "telephone1", "address1", "city11", "postCode!",
//				"country1", "regionState1", "password1");
//		System.out.println("user.firstName = " + user.getFirstName());
		//
		// 2. Readable Parameters
//		User user = new User();
//		user.setFirstName("firstName1");
//		user.setLastName("lastName1");
//		user.setEMail("eMail1");
//		user.setTelephone("telephone1");
//		user.setAddress1("address1");
//		user.setCity("city11");
//		user.setPostCode("postCode");
//		user.setCountry("country1");
//		user.setRegionState("regionState1");
//		user.setPassword("password1");
//		System.out.println("user.firstName = " + user.getFirstName());
		//
		// 3. Use Fluent Interface
//		User user = new User()
//			.setFirstName("firstName1")
//			.setLastName("lastName1")
//			.setEMail("eMail1")
//			.setTelephone("telephone1")
//			.setAddress1("address1")
//			.setCity("city11")
//			.setPostCode("postCode")
//			.setCountry("country1")
//			.setRegionState("regionState1")
//			.setPassword("password1");
//		System.out.println("user.firstName = " + user.getFirstName());
//		}
		//
		// 4. Add Statiс Factory
//		User user = User.get()
//			.setFirstName("firstName1")
//			.setLastName("lastName1")
//			.setEMail("eMail1")
//			.setTelephone("telephone1")
//			.setAddress1("address1")
//			.setCity("city11")
//			.setPostCode("postCode")
//			.setCountry("country1")
//			.setRegionState("regionState1")
//			.setPassword("password1");
//		System.out.println("user.firstName = " + user.getFirstName());
		//
		// 5. Add Builder by Interfaces
//		User user = User.get()
//				.setFirstName("firstName2")
//				.setLastName("lastName2")
//				.setEMail("eMail2")
//				.setTelephone("telephone2")
//				.setAddress1("address12")
//				.setCity("city2")
//				.setPostCode("postCode2")
//				.setCountry("country2")
//				.setRegionState("regionState2")
//				.setPassword("password2")
//				.setFax("fax2")
//				.build();
//		System.out.println("user.firstName = " + user.setFirstName("123")); // Error
//		// Code
//		System.out.println("user.firstName = " + user.getFirstName());
		//
		// 6. Dependency Inversion
//		IUser user = User.get()
//				.setFirstName("firstName2")
//				.setLastName("lastName2")
//				.setEMail("eMail2")
//				.setTelephone("telephone2")
//				.setAddress1("address12")
//				.setCity("city2")
//				.setPostCode("postCode2")
//				.setCountry("country2")
//				.setRegionState("regionState2")
//				.setPassword("password2")
//				.setFax("fax2")
//				.build();
		//System.out.println("user.firstName = " + user.setFirstName("123")); // Fixed Error
		//System.out.println("user.firstName = " + ((User) user).setFirstName("123"));
		// Code
//		System.out.println("user.firstName = " + user.getFirstName());
		//
		// 6-7. Add Repository and Singletone
		IUser user = UserRepository.get().customer();
		System.out.println("user.firstName = " + user.getFirstName());
	}

}

