package org.cloud.sonic.agent.tests.script;

/**
 * @Author: Tian Yu
 * @Date: 2024/03/03/12:29
 * @Description:
 */
import org.cloud.sonic.agent.tests.handlers.IOSStepHandler;


def testFastbot(){
//        String udId = iosStepHandler.udId

//        iosStepHandler.closeIOSDriver()
//        String commend = "/Users/admin/Desktop/note/code/sonic-agent/plugins/sonic-ios-bridge"
//        // 执行命令
//        String sibCommand = "/Users/admin/Desktop/note/code/sonic-agent/plugins/sonic-ios-bridge   run xctest -b pro.bingbon.trade.xctrunner -e BUNDLEID=pro.bingbon.trade -e duration=5 -e throttle=300"
        Process ps = null;

        ps = Runtime.getRuntime().exec("sh -c  /Users/admin/Desktop/note/code/sonic-agent/plugins/sonic-ios-bridge   run xctest -b pro.bingbon.trade.xctrunner -e BUNDLEID=pro.bingbon.trade -e duration=5 -e throttle=300");

        InputStreamReader inputStreamReader = new InputStreamReader(ps.getInputStream());
        BufferedReader stdInput = new BufferedReader(inputStreamReader);
        String s;
        while (ps.isAlive()) {
                try{
                        if ((s = stdInput.readLine()) != null) {
//                                log.sendStepLog(StepType.INFO,"ps msg",s)
                                println s
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        stdInput.close();
        inputStreamReader.close();

}

testFastbot()
