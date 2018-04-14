package dao;

import businessLogic.HibernateUtil;
import entity.Customer;
import entity.ResultOfQuery;
import entity.T;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ImplDAO implements GenericDAO {

    public void save(Customer t) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

    public Customer getById(int id) {
        Session session = HibernateUtil.getSession();
        Customer t = (Customer) session.get(T.class, id);
        session.close();

        return t;
    }

    public void update(Customer t) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.evict(t);
        session.update(t);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Customer t) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    public List<Customer> getAll() {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("select c.name from Customer c");
        List<Customer> result = query.list();
        session.close();
        return result;
    }


    //зарплату(сумму) всех разработчиков отдельного проекта;
    public static BigDecimal getSalary(String nameProject) {

        Session session = HibernateUtil.getSession();
        String sqlSumOfSalaryQuery = "SELECT sum(salary) AS salary FROM developers\n" +
                "WHERE id IN (SELECT id FROM developers_projects \n" +
                "\t\t\t\tWHERE project IN (SELECT id FROM projects \n" +
                "\t\t\t\t\t\t\t\t\tWHERE name = :nameProject))";
        Query query = session.createSQLQuery(sqlSumOfSalaryQuery);
        query.setParameter("nameProject", nameProject);
        List<BigDecimal> result = query.list();
        session.close();

        if (!result.isEmpty()) {
            return result.get(0);
        }
        return BigDecimal.ZERO;
    }

    //список разработчиков отдельного проекта;
    public static List<String> getDevelopersList(String nameProject) {

        Session session = HibernateUtil.getSession();

        String sqlSumOfSalaryQuery = "SELECT name AS name FROM developers\n" +
                "WHERE id IN (SELECT id FROM developers_projects\n" +
                "\t\t\t\tWHERE project IN (SELECT id FROM projects \n" +
                "\t\t\t\t\t\t\t\t\tWHERE name = :nameProject))";

        Query query = session.createSQLQuery(sqlSumOfSalaryQuery);
        query.setParameter("nameProject", nameProject);
        List<String> result = query.list();
        session.close();

        return result;

    }

    //    список всех Java разработчиков;
    public static List<String> getDevelopersListBySkils(String skillName) {

        Session session = HibernateUtil.getSession();

        String sqlSumOfSalaryQuery = "SELECT name AS name FROM developers\n" +
                "WHERE id IN (SELECT id FROM developers_skills\n" +
                "\t\t\t\tWHERE skill IN (SELECT id FROM skills \n" +
                "\t\t\t\t\t\t\t\t\tWHERE skill = :skillName))";

        Query query = session.createSQLQuery(sqlSumOfSalaryQuery);
        query.setParameter("skillName", skillName);
        List<String> result = query.list();
        session.close();

        return result;

    }

    //    список всех middle разработчиков;
    public static List<String> getDevelopersListByLevel(String levelName) {

        Session session = HibernateUtil.getSession();

        String sqlSumOfSalaryQuery = "SELECT name AS name FROM developers\n" +
                "WHERE id IN (SELECT id FROM developers_skills\n" +
                "\t\t\t\tWHERE skill IN (SELECT id FROM skills \n" +
                "\t\t\t\t\t\t\t\t\tWHERE level = :levelName))";
        Query query = session.createSQLQuery(sqlSumOfSalaryQuery);
        query.setParameter("levelName", levelName);
        List<String> result = query.list();
        session.close();

        return result;

    }

    //    список проектов в следующем формате: дата создания - название проекта - количество разработчиков на этом проекте.
    public static void getProjectsList() {

        Session session = HibernateUtil.getSession();

        String sqlDropTempTableQuery = "DROP TEMPORARY TABLE IF EXISTS projects_developers;";
        session.createSQLQuery(sqlDropTempTableQuery).executeUpdate();

        String sqlCreateTempTableQuery = "CREATE TEMPORARY TABLE projects_developers AS (\n" +
                "SELECT \n" +
                "count(developer) AS countOfDeveloper,\n" +
                "project\n" +
                "FROM homework11.developers_projects\n" +
                "GROUP BY project);";
        session.createSQLQuery(sqlCreateTempTableQuery).executeUpdate();


        String sqlQuery = "SELECT \n" +
                "name, \n" +
                "description, \n" +
                "projects_developers.countOfDeveloper\n" +
                "FROM \n" +
                "homework11.projects\n" +
                "LEFT JOIN projects_developers ON projects.id = projects_developers.project;";
        Query query = session.createSQLQuery(sqlQuery)
                .addScalar("name", new StringType())
                .addScalar("description", new StringType())
                .addScalar("countOfDeveloper", new BigDecimalType())
                .setResultTransformer(Transformers.aliasToBean(ResultOfQuery.class));

        List<ResultOfQuery> result = query.list();
        session.close();

        for (ResultOfQuery obg: result) {
            System.out.println("description " + obg.getDescription()
                    + "\tname " + obg.getName()
                    + "\tcountOfDeveloper " + obg.getCountOfDeveloper());
        }
    }


}
