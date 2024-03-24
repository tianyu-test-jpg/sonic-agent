package org.cloud.sonic.agent.tests.script;

/**
 * @Author: Tian Yu
 * @Date: 2024/03/20/21:32
 * @Description:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdbCommandExecutor {

    public static void executeAdbCommandAndLogCrash(String serialNumber) {
        String command = "adb -s " + serialNumber + " shell 'CLASSPATH=/sdcard/monkeyq.jar:/sdcard/framework.jar:/sdcard/fastbot-thirdpart.jar exec app_process /system/bin com.android.commands.monkey.Monkey -p com.example.crashdemo --agent reuseq --running-minutes 10 --throttle 800 -v -v -v --output-directory  /sdcard/fastboot --bugreport'";

        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
        try {
            Process process = pb.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String s = null;
            StringBuilder output = new StringBuilder();
//            while ((s = stdInput.readLine()) != null) {
//                output.append(s).append("\n");
//                System.out.println(s);
//                if (s.contains("*** ERROR ***")) {
//                    // 开始捕获crash信息
//                    boolean isCrashInfo = true;
//                    while (isCrashInfo && (s = stdInput.readLine()) != null) {
//                        output.append(s).append("\n");
//                        if (s.endsWith("_app_crash.txt:")) {
//                            // 找到了crash日志结尾
//                            isCrashInfo = false;
//                            System.out.println("\n------------------ Crash Log Start ------------------\n");
//                            System.out.print(output.toString());
//                            System.out.println("\n------------------ Crash Log End ------------------\n");
//                        }
//                    }
//                }
//            }

            // 读取错误流中的内容
            while ((s = stdError.readLine()) != null) {
                System.err.println(s);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExit code: " + exitCode);

            // 查看是否有crash日志


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void fastbootIos() {
        Process process;
        String command = "BUNDLEID=pro.bingbon.finance  duration=3 throttle=300 xcodebuild test  -workspace /Users/admin/Desktop/note/Fastbot_iOS/Fastbot-iOS/Fastbot-iOS.xcworkspace -scheme FastbotRunner  -configuration Release  -destination 'platform=iOS,id=00008101-001830300C20001E' -only-testing:FastbotRunner/FastbotRunner/testFastbot";

//        String command = "tidevice -u  00008101-001830300C20001E  xctest -B pro.bingbon.trade.xctrunner -e BUNDLEID:pro.bingbon.finance -e duration:4 -e throttle:300 --debug";

        try {
            // 创建ProcessBuilder实例，并重定向错误输出流到标准输出流
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);
            pb.redirectErrorStream(true);

            // 启动进程
            process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // 记录输出到日志
                    System.out.println(line);
                }

                int exitCode = process.waitFor(); // 等待命令执行完成
                if (exitCode == 0) {

                    System.out.println("执行完成");
                } else {
                    System.out.println("执行失败");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        fastbootIos();
    }
}
