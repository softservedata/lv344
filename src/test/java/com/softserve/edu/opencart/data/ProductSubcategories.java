package com.softserve.edu.opencart.data;

public enum ProductSubcategories {
	ALL_CATEGORIES("value='0'"),
	DESKTOPS("value='20'"), 				
	DESKTOPS__PC("value='26'"), 				
	DESKTOPS__MAC("value='27'"), 				
	LAPTOPS_AND_NOTEBOOKS("value='18'"), 				
	LAPTOPS_AND_NOTEBOOKS__ACER("value='71'"), 				
	LAPTOPS_AND_NOTEBOOKS__APPLE("value='46'"), 				
	LAPTOPS_AND_NOTEBOOKS__HP("value='45'"), 				
	LAPTOPS_AND_NOTEBOOKS__OTHERS("value='72'"), 				
	COMPONENTS("value='25'"), 				
	COMPONENTS__MICE_AND_TRACKBALLS("value='29'"), 				
	COMPONENTS__MONITORS("value='28'"), 				
	COMPONENTS__MONITORS__TEST1("value='35'"),
	COMPONENTS__MONITORS__TEST2("value='36'"), 				
	COMPONENTS__PRINTERS("value='30'"),
	COMPONENTS__SCANNERS("value='31'"), 				
	COMPONENTS__WEB_CAMERAS("value='32'"),
    TABLETS("value='57'"), 				
	APPLE("value='65'"),
	APPLE__OTHER("value='67'"), 				
	APPLE__SAMSUNG("value='66'"),
    SOFTWARE("value='17'"), 				
    SOFTWARE__APPLE("value='76'"),
    SOFTWARE__MICROSOFT("value='68'"), 				
    SOFTWARE__OTHER("value='70'"),
    PHONES_AND_PDAS("value='24'"),
    PHONES_AND_PDAS__APPLE("value='61'"),
    PHONES_AND_PDAS__HTC("value='63'"),
    PHONES_AND_PDAS__OTHERS("value='64'"),
    PHONES_AND_PDAS__SAMSUNG("value='62'"),
    CAMERAS("value='33'"),
    CAMERAS__CANON("value='59'"),
    CAMERAS__NIKON("value='60'"),
    MP3_PLAYERS("value='34'"),
    MP3_PLAYERS__Apple("value='52'"),
    MP3_PLAYERS__Others("value='58'");

	private String name;

	private ProductSubcategories(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
