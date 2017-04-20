package com.gosolar2.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Joshua King on 4/18/17.
 */
@Component
public class ReportsCronJobs {

	private final ProfessorRepository professorRepository;
	private final CourseRepository    courseRepository;
	private final StudentRepository   studentRepository;

	@Autowired public ReportsCronJobs (@Qualifier ("professorRepository") ProfessorRepository professorRepository,
									   @Qualifier ("courseRepository") CourseRepository courseRepository,
									   @Qualifier ("studentRepository") StudentRepository studentRepository) {
		this.professorRepository = professorRepository;
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
	}

//	@Scheduled (cron = "0 1 1 ? * SUN")

//	@Scheduled (fixedDelay = 10000, initialDelay = 0)
//	public void weeklyReports () throws IOException {
//		System.out.println("Starting weeklyReports");
//
//		int numberOfProfessors = professorRepository.findAll().size();
//		int numberOfStudents = studentRepository.findAll().size();
//		int numberOfUsers = numberOfProfessors + numberOfStudents;
//
//		int numberOfCourses = courseRepository.findAll().size();
//		Integer numberOfStudentsInCourses = studentRepository
//				.findAll()
//				.stream()
//				.map(new Function<Student, List<Course>>() {
//					@Override public List<Course> apply (Student student) {
//						return student.getCourses();
//					}
//				})
//				.map(new Function<List<Course>, Integer>() {
//					@Override public Integer apply (List<Course> courses) {
//						return courses.size();
//					}
//				})
//				.reduce(new BinaryOperator<Integer>() {
//					@Override public Integer apply (Integer i1, Integer i2) {
//						return Utils.sum(i1, i2);
//					}
//				})
//				.orElse(0);
//
//		long numberOfCoursesWithoutAProfessor = courseRepository.findAll()
//																.stream()
//																.map(Course::getProfessor)
//																.filter(Utils::isNull)
//																.count();
//
//		StringBuilder sb = new StringBuilder();
//		sb.append("There are ")
//		  .append(numberOfProfessors)
//		  .append(" professors and ")
//		  .append(numberOfStudents)
//		  .append(" students for a total of ")
//		  .append(numberOfUsers)
//		  .append(" system users.\n\n");
//
//		File systemReportsDirectory = new File("systemReports");
//		systemReportsDirectory.mkdirs();
//
//		DateTimeFormatter reportDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//		File thisWeeksReportsFile = new File(systemReportsDirectory.getPath()
//											 + "/"
//											 + LocalDate.now().format(reportDateTimeFormatter)
//											 + "-weekly_reports.txt");
//		if (thisWeeksReportsFile.exists()) {
//			thisWeeksReportsFile.delete();
//		}
//		if (thisWeeksReportsFile.createNewFile()) {
//			PrintWriter out = new PrintWriter(thisWeeksReportsFile);
//			out.print(sb.toString());
//			out.close();
//		}
//	}
}