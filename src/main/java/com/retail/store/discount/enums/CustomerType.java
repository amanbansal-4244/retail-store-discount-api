package com.retail.store.discount.enums;

public enum CustomerType {
	
	AFFLILIATE("AFFLILIATE"),
	CUSTOMER("CUSTOMER"),
	STORE_EMPLOYEE("STORE_EMPLOYEE");

    private String name;

    CustomerType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}



