package org.cloud.sonic.agent.tests.script;

/**
 * @Author: Tian Yu
 * @Date: 2024/03/03/12:29
 * @Description:
 */
import org.cloud.sonic.agent.tests.handlers.IOSStepHandler;


def testFastbot(){
        String udId = iosStepHandler.udId

        iosStepHandler.closeIOSDriver()

        Runtime.getRuntime().exec("tidevice  -u  "+ udId +"  xctest -B  pro.bingbon.trade.xctrunner -e BUNDLEID:pro.bingbon.trade -e duration:5  -e throttle:800 --debug");

}

        testFastbot()
