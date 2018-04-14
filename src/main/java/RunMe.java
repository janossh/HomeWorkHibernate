import businessLogic.HibernateUtil;
import dao.ImplDAO;
import entity.T;
import entity.Customer;
import entity.Project;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Objects;

public class RunMe {

    private static Session session = HibernateUtil.getSession();

    public static void main(String[] args) {
        System.out.println("вывести на консоль:\n" +
                "1. зарплату(сумму) всех разработчиков отдельного проекта;\n" +
                "2. список разработчиков отдельного проекта;\n" +
                "3. список всех Java разработчиков;\n" +
                "4. список всех middle разработчиков;\n" +
                "5. список проектов в следующем формате: дата создания - название проекта - количество разработчиков на этом проекте.\n" +
                "6. создать Customer.\n" +
                "7. выход\n");

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String flEnd = "exit";
        String str;

        System.out.println("Введите номер операции и нажмите ENTER");
        try {
            while (!flEnd.equals(str = bufferedReader.readLine())) {

                switch (str) {
                    default:
                        System.out.println("Нет такой операции");
                        break;
                    case "1":
                        getSalary();
                        break;
                    case "2":
                        getDevelopersList();
                        break;
                    case "3":
                        getDevelopersListBySkils();
                        break;
                    case "4":
                        getDevelopersListByLevel();
                        break;
                    case "5":
                        ImplDAO.getProjectsList();
                        break;
                    case "6":
                        createCustomer("Roman", "tourist");
                        break;
                    case "7":
                        str = flEnd;
                        break;

                }
                if (Objects.equals(str, flEnd)) {
                    System.out.println("Работа программы завершена");
                    break;
                }
                System.out.println("Введите номер операции и нажмите ENTER");
            }
        } catch (IOException e) {
            System.out.println("Error read from keyboard");
        }

        try {
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error to close stream reader");
        }
    }

    private static void createCustomer(String name, String kindOfActivity) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setKindOfActivity(kindOfActivity);
        ImplDAO implDAO = new ImplDAO();

        implDAO.save(customer);
    }

    private static void getDevelopersListByLevel() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println("Введите название уровня");

        String str = "";

        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s: ImplDAO.getDevelopersListByLevel(str)) {
            System.out.println("name " + s);
        }
    }

    private static void getDevelopersListBySkils() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println("Введите название скила");

        String str = "";

        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s: ImplDAO.getDevelopersListBySkils(str)) {
            System.out.println("name " + s);
        }
    }

    private static void getDevelopersList() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println("Введите название проэкта");

        String str = "";

        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s: ImplDAO.getDevelopersList(str)) {
            System.out.println("name " + s);
        }
    }

    private static void getSalary() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        System.out.println("Введите название проэкта");

        String str = "";

        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("salary " + ImplDAO.getSalary(str));
    }


}
