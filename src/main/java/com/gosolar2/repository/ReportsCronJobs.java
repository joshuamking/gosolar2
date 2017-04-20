package com.gosolar2.repository;

import com.gosolar2.model.Course;
import com.gosolar2.model.Student;
import com.gosolar2.utils.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * Created by Joshua King on 4/18/17.
 */
@Component
public class ReportsCronJobs {

	final static Logger logger = Logger.getLogger(ReportsCronJobs.class);

	private final ProfessorRepository professorRepository;
	private final CourseRepository    courseRepository;
	private final StudentRepository   studentRepository;
	private final UserRepository      userRepository;

	@Autowired public ReportsCronJobs (@Qualifier ("professorRepository") ProfessorRepository professorRepository,
									   @Qualifier ("courseRepository") CourseRepository courseRepository,
									   @Qualifier ("studentRepository") StudentRepository studentRepository, @Qualifier ("userRepository") UserRepository userRepository) {
		this.professorRepository = professorRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.userRepository = userRepository;
	}


	@Scheduled (cron = "0 1 1 ? * SUN")
	@Scheduled (fixedDelay = 10000, initialDelay = 0)
	public void weeklyReports () throws IOException {
		logger.info("Starting weeklyReports");

		int numberOfProfessors = professorRepository.findAll().size();
		int numberOfStudents = studentRepository.findAll().size();
		int numberOfUsers = userRepository.findAll().size();
		int numberOfAdmins = numberOfUsers - numberOfProfessors - numberOfStudents;

		int numberOfCourses = courseRepository.findAll().size();
		Integer numberOfStudentsInCourses = studentRepository
				.findAll()
				.stream()
				.map(new Function<Student, Set<Course>>() {
					@Override public Set<Course> apply (Student student) {
						return student.getCourses();
					}
				})
				.map(new Function<Set<Course>, Integer>() {
					@Override public Integer apply (Set<Course> courses) {
						return courses.size();
					}
				})
				.reduce(new BinaryOperator<Integer>() {
					@Override public Integer apply (Integer i1, Integer i2) {
						return Utils.sum(i1, i2);
					}
				})
				.orElse(0);

		long numberOfCoursesWithoutAProfessor = courseRepository.findAll()
																.stream()
																.map(Course::getProfessor)
																.filter(Utils::isNull)
																.count();

		StringBuilder sb = new StringBuilder();
		sb.append("There are ")
		  .append(numberOfProfessors)
		  .append(" professors, ")
		  .append(numberOfStudents)
		  .append(" students, and ")
		  .append(numberOfAdmins)
		  .append(" admins for a total of ")
		  .append(numberOfUsers)
		  .append(" system users.");

		sb.append("\n");
		sb.append("\n");

		sb.append("There are ")
		  .append(numberOfCourses)
		  .append(" courses, and ")
		  .append(numberOfCoursesWithoutAProfessor)
		  .append(" of them have no professor assigned to them.");

		sb.append("\n");
		sb.append("\n");

		sb.append("There are ")
		  .append(numberOfStudentsInCourses)
		  .append(" course registrations.");

		File systemReportsDirectory = new File("systemReports");
		systemReportsDirectory.mkdirs();

		DateTimeFormatter reportDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		File thisWeeksReportsFile = new File(systemReportsDirectory.getPath()
											 + "/"
											 + LocalDate.now().format(reportDateTimeFormatter)
											 + "-weekly_reports.txt");
		if (thisWeeksReportsFile.exists()) {
			thisWeeksReportsFile.delete();
		}
		if (thisWeeksReportsFile.createNewFile()) {
			PrintWriter out = new PrintWriter(thisWeeksReportsFile);
			out.print(sb.toString());
			out.close();
		}

		logger.info("Completed weeklyReports\n");
	}
}