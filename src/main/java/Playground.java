import cz.cvut.fel.vyzkumodolnosti.model.entities.sleeps.SleepSummary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class Playground {
	public static void main(String[] args) {
		PasswordEncoder pw = new BCryptPasswordEncoder();
		System.out.println(pw.encode("abc"));

		SleepSummary sleepSummary1 = new SleepSummary();
		SleepSummary sleepSummary2 = new SleepSummary();

		sleepSummary1.setCalendarDate("aeostuh");
		sleepSummary2.setCalendarDate(null);

		List<SleepSummary> sleepSummaryList = List.of(sleepSummary1, sleepSummary2);

		sleepSummaryList.stream().map(SleepSummary::getCalendarDate).forEach(e -> {
			System.out.println(e.equals("ahoj"));
		});
	}
}
