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
				.setFirstName("hahaha")
				.setLastName("hahaha")
				.setEMail("hahaha@gmail.com")
				.setTelephone("1234567")
				.setAddress1("address12")
				.setCity("city2")
				.setPostCode("postCode2")
				.setCountry("country2")
				.setRegionState("regionState2")
				.setPassword("qwerty")
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
    
    public IUser searchUser() {
        return User.get()
				.setFirstName("Search")
				.setLastName("Test")
				.setEMail("search@test.mail")
				.setTelephone("0123456789")
				.setAddress1("Lviv")
				.setCity("Lviv")
				.setPostCode("0123456")
				.setCountry("Ukraine")
				.setRegionState("L'vivs'ka Oblast'")
				.setPassword("search")
				.setFax("0123456789")
				.build();
    }
    
    public IUser yStasiv() {
        return User.get()
				.setFirstName("Yurii")
				.setLastName("Stasiv")
				.setEMail("yurastasiv@hotmail.com")
				.setTelephone("+380972537286")
				.setAddress1("Lviv, ukraine")
				.setCity("Lviv")
				.setPostCode("79054")
				.setCountry("Ukraine")
				.setRegionState("L'vivs'ka Oblast'")
				.setPassword("Q1w2e3r4")
				.setFax("")
				.build();
    }
    
    public IUser wishUser() {
        return User.get()
				.setFirstName("test")
				.setLastName("test")
				.setEMail("test@gmail.com")
				.setTelephone("123456789")
				.setAddress1("address13")
				.setCity("city3")
				.setPostCode("postCode3")
				.setCountry("country3")
				.setRegionState("regionState3")
				.setPassword("qwerty")
				.setFax("123456123")
				.build();
    }



    //public IUser admin() {}
    //public List<IUser> fromExcel() {}
    //public List<IUser> fromDB() { create class, read, max 5-7 lines}

}

