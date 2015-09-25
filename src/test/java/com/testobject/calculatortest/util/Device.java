package com.testobject.calculatortest.util;

public enum Device {

    Motorola_Moto_e_2nd_gen("Motorola_Moto_E_2nd_gen_real", true),
    Motorola_Moto_g_2nd_gen("Motorola_Moto_G_2nd_gen_real", true);

    public final String name;
    public final boolean isPhone;
    public final boolean isTablet;

    Device(String name, boolean isPhone) {
        this.name = name;
        this.isPhone = isPhone;
        this.isTablet = !isPhone;
    }

}
