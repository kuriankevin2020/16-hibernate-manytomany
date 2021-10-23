package com.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.Review;
import com.hibernate.entity.Student;

public class UpdateStudent {

	public static void main(String[] args) {

		Course course1 = new Course("Course1");
		Course course2 = new Course("Course2");

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Student.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(course1);
			session.save(course2);

			int theId = 1;

			Student student = session.get(Student.class, theId);

			course1.addStudent(student);
			course2.addStudent(student);
			session.save(student);

			session.getTransaction().commit();

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
