package com.testobject.calculatortest;

import com.testobject.calculatortest.util.AppiumDriverBuilder;
import com.testobject.calculatortest.util.Device;
import com.testobject.calculatortest.util.Parallelized;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parallelized.class)
public class OperationTestsParallel extends OperationTests {

    @Parameterized.Parameters
    public static List<Object[]> data() {
        Device[] devices = Device.values();
        Object[][] parameters = new Object[Device.values().length][1];
        for (int i = 0; i <devices.length; i++) {
            parameters[i][0] = devices[i];
        }

        return Arrays.asList(parameters);
    }

    public OperationTestsParallel(Device device){
        super(device, AppiumDriverBuilder.forAndroid().againstTestobject());
    }
}
