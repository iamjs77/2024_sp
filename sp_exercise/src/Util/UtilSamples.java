package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class UtilSamples {

    public static void main(String[] args) throws Exception {


    }

    //현재 날짜, 시각 문자열로 가져오기
    void aboutDate() {
        LocalDateTime now = LocalDateTime.now();
        String strDT = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

        long ct = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String strCT = sdf.format(ct);
    }

    // 문자열 날짜, 시각 → Date 타입으로 변경`
    void aboutConvertToDate() throws Exception {
        String strTime = "2022-03-31 21:40:15";
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = transFormat.parse(strTime);

        // Date 타입 → LocalDateTime 타입으로 변경
        LocalDateTime dt1 = null;
//        dt1 = dt1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        String input = "04:30 PM, Sat 5/12/2018";
        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mm a, EEE M/d/uuuu", Locale.US);  // Specify locale to determine human language and cultural norms used in translating that input string.
        LocalDateTime ldt = LocalDateTime.parse(input, f);

        ZoneId z = ZoneId.of("America/Toronto");
        ZonedDateTime zdt = ldt.atZone(z);  // Give meaning to that `LocalDateTime` by assigning the context of a particular time zone. Now we have a moment, a point on the timeline.

        Instant instant = zdt.toInstant();

    }

    void aboutTime() throws Exception {
        //◆ 시간 차이 계산
        String start = "20220331142310";
        String end = "20220331142420";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dd1 = sf.parse(start);
        Date dd2 = sf.parse(end);
        long diff = dd2.getTime() - dd1.getTime();
        System.out.println(diff / 1000); // Sec Difference
//        출력) 70
    }

    void aboutPrintNumber() {
//        10진수 4자리로 출력
        int a = 14;
        System.out.println(String.format("%04d", a));
//        출력) 0014

//◆ 16진수 출력
        int a1 = 14;
        System.out.println(String.format("%02X %02x", a1, a1));
//        출력) 0E 0e
//◆ 소수점 출력
        double b = 12.345678;
        System.out.println(String.format("%08.3f", b));
        //➔ 총 8자리 확보 후 앞자리에 0붙여주고, 뒤 3자리 소수점 반올림하여 출력
//        출력) 0012.346
    }

    void aboutConvertString() throws Exception{
        //String → Byte Array
        String strTest = "ABCD123";
        byte [] byteTest = new byte[80];
        byteTest = strTest.getBytes("UTF-8");
        for (byte b : byteTest)
            System.out.print(b + " ");
        //출력) 65 66 67 68 49 50 51


        //◆ Byte Array → String
        String strTest2 = new String(byteTest);
        System.out.println(strTest2);
        //출력) ABCD12
    }

    void aboutScanner() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println("Output : " + input);
        //입력) haha
        //출력) Output : haha
    }

    void aboutBufferReader() throws Exception {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);
        String input = br.readLine();
        System.out.println("Output : " + input);

        //입력) haha
        //출력) Output : haha
    }
}
