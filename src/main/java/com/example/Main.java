package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Шаг 1: Парсинг XML
        List<Employee> list = parseXML("data.xml");

        // Шаг 2: Преобразование списка в JSON
        String json = listToJson(list);

        // Шаг 3: Запись JSON в файл
        writeString(json, "data2.json");
    }

    // Метод для парсинга XML
    public static List<Employee> parseXML(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try {
            // Создаем объект для чтения XML
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // Получаем список всех узлов <employee>
            NodeList nodeList = doc.getElementsByTagName("employee");

            // Проходим по каждому узлу <employee>
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Извлекаем данные из узла
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());

                    // Создаем объект Employee и добавляем его в список
                    employees.add(new Employee(id, firstName, lastName, country, age));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Метод для преобразования списка в JSON
    public static String listToJson(List<Employee> list) {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < list.size(); i++) {
            Employee employee = list.get(i);
            json.append("  {\n")
                    .append("    \"id\": ").append(employee.getId()).append(",\n")
                    .append("    \"firstName\": \"").append(employee.getFirstName()).append("\",\n")
                    .append("    \"lastName\": \"").append(employee.getLastName()).append("\",\n")
                    .append("    \"country\": \"").append(employee.getCountry()).append("\",\n")
                    .append("    \"age\": ").append(employee.getAge()).append("\n")
                    .append("  }");
            if (i < list.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("]");
        return json.toString();
    }

    // Метод для записи JSON в файл
    public static void writeString(String json, String filePath) {
        try (java.io.FileWriter file = new java.io.FileWriter(filePath)) {
            file.write(json);
            System.out.println("JSON файл успешно создан: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}