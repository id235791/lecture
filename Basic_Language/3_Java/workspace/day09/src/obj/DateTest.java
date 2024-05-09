package obj;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTest {
	public static void main(String[] args) {
		Date obj1 = new Date();
		System.out.println(obj1);
		
//		System.out.println(obj1.getMonth());
		ZoneId zone = ZoneId.systemDefault();
		LocalDate ld = obj1.toInstant().atZone(zone).toLocalDate();  
		LocalDateTime ldt = obj1.toInstant().atZone(zone).toLocalDateTime();  
		
		System.out.println(ld.getYear());
		System.out.println(ld.getMonthValue());
		System.out.println(ld.getDayOfMonth());
		
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());
		
//		자바에서만 시간 정보를 추출해서 사용하려고 할 때
//		현재 순간
		LocalDateTime now = LocalDateTime.now();
//		지정된 시간
		LocalDateTime ind_day = LocalDateTime.of(1945,8,15,12,30,11);
		System.out.println(ind_day);
		
		// 1945/08/15 12:30:11
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String time = ind_day.format(formatter);
		
		System.out.println(time);
		
	}
}










