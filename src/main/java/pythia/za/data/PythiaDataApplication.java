package pythia.za.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class PythiaDataApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(PythiaDataApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
		String sqlStatements[] = {
				"drop table Positions if exists;",
				"drop table Awards_Achieved if exists;",
				"drop table Profiles if exists;",
				"create table Profiles( profile_id varchar(255) PRIMARY KEY, fullname varchar(255), cell varchar(255), email varchar(255))",
				"create table Positions( profile_id varchar(255) , position_id numeric auto_increment PRIMARY KEY,club_position varchar(255),club varchar(255),date_started TIMESTAMP,date_ended TIMESTAMP,foreign key (profile_id) references Profiles(profile_id));",
				"create table Awards_Achieved( profile_id varchar(255) ,award_id numeric auto_increment PRIMARY KEY,description varchar(255),date_achieved TIMESTAMP,foreign key (profile_id) references Profiles(profile_id));",
				"insert into Profiles(profile_ID, fullname, cell, email) values('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'James Aglioti II', '071318333', 'gafhjd@gmail.com');",
				"insert into Profiles(profile_ID, fullname, cell, email) values('938edbac-f288-420b-ae6f-c6d80b5be527', 'Billy dfd', '0834445555', 'fdsfdsd@gmail.com');",
				"insert into Awards_Achieved(profile_id, description, date_achieved) values ('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'Competent Communicator', CURRENT_TIMESTAMP());",
				"insert into Awards_Achieved(profile_id, description, date_achieved) values ('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'Competent Leadership', CURRENT_TIMESTAMP());",
				"insert into Awards_Achieved(profile_id, description, date_achieved) values ('938edbac-f288-420b-ae6f-c6d80b5be527', 'Competent Communicator', CURRENT_TIMESTAMP());",
				"insert into Awards_Achieved(profile_id, description, date_achieved) values ('938edbac-f288-420b-ae6f-c6d80b5be527', 'Advanced Communicator Bronze', CURRENT_TIMESTAMP());",
				"insert into Positions(profile_id, club_position, club, date_started, date_ended) values ('938edbac-f288-420b-ae6f-c6d80b5be527', 'VPE', 'Melrose Arch Toastmasters', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());",
				"insert into Positions(profile_id, club_position, club, date_started, date_ended) values ('2169d99d-0ed2-4e00-a09d-a78096ff052a', 'PRESIDENT', 'Woodlands Toastmasters', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());"
		};

		Arrays.asList(sqlStatements).forEach(sql -> {
			jdbcTemplate.execute(sql);
		});



		// Query test data and print results
	}

}
