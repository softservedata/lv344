package com.softserve.edu.opencart.data;

// Using Singletone
public final class UserRepository {

	private static volatile UserRepository instance = null;

    private UserRepository() {
    }

    public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public IUser defaultUser() {
        return customer();
    }
    
    public IUser admin() {
        return User.get()
				.setFirstName("firstName2")
				.setLastName("lastName2")
				.setEMail("eMail2")
				.setTelephone("telephone2")
				.setAddress1("address12")
				.setCity("city2")
				.setPostCode("postCode2")
				.setCountry("country2")
				.setRegionState("regionState2")
				.setPassword("password2")
				.setFax("fax")
				.build();
    }

    public IUser customer() {
        return User.get()
				.setFirstName("firstname9")
				.setLastName("lastname")
				.setEMail("hahaha@gmail.com")
				.setTelephone("phone")
				.setAddress1("address12")
				.setCity("city2")
				.setPostCode("postCode2")
				.setCountry("country2")
				.setRegionState("regionState2")
				.setPassword("qwerty")
				.setFax("fax")
				.build();
    }

    public IUser customerHahaha() {
        return User.get()
				.setFirstName("firstname9")
				.setLastName("lastname")
				.setEMail("hahaha@gmail.com")
				.setTelephone("phone")
				.setAddress1("address12")
				.setCity("city2")
				.setPostCode("postCode2")
				.setCountry("Ukraine")
				.setRegionState("regionState2")
				.setPassword("qwerty")
				.setFax("fax")
				.build();
    }

    public IUser customerSearch() {
        return User.get()
				.setFirstName("Search")
				.setLastName("lastname")
				.setEMail("search@test.mail")
				.setTelephone("phone")
				.setAddress1("address12")
				.setCity("city2")
				.setPostCode("postCode2")
				.setCountry("country2")
				.setRegionState("regionState2")
				.setPassword("search")
				.setFax("fax")
				.build();
    }

    public IUser newUser() {
        return User.get()
				.setFirstName("firstName3")
				.setLastName("lastName3")
				.setEMail("eMail3")
				.setTelephone("telephone3")
				.setAddress1("address13")
				.setCity("city3")
				.setPostCode("postCode3")
				.setCountry("country3")
				.setRegionState("regionState3")
				.setPassword("password3")
				.setFax("fax3")
				.build();
    }

    //public IUser admin() {}
    //public List<IUser> fromExcel() {}
    //public List<IUser> fromDB() { create class, read, max 5-7 lines}

}
