package com.testobject.calculatortest.util;

public enum Device {

//    motog("Motorola_Moto_G_2nd_gen_real", true),
    motoe("Motorola_Moto_E_2nd_gen_real", true);

    public final String name;
    public final boolean isPhone;
    public final boolean isTablet;

    Device(String name, boolean isPhone) {
        this.name = name;
        this.isPhone = isPhone;
        this.isTablet = !isPhone;
    }

}
