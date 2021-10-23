package com.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;

public class CreateStudent {

	public static void main(String[] args) {

		Course course = new Course("Course");

		Student student1 = new Student("Kurian", "Kevin", "kuriankevin@google.com");
		Student student2 = new Student("Deva", "Gopu", "devagopu@google.com");

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(course);

			course.addStudent(student1);
			course.addStudent(student2);

			session.save(student1);
			session.save(student2);

			session.getTransaction().commit();

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
