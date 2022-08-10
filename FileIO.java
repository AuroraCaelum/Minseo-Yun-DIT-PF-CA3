/*
# Env       -  JDK 1.8.0_331
# File      -  FileIO.java
# Author    -  Yun Minseo / 10240311
# Github    -  https://github.com/AuroraCaelum/YunMinseo-ITSD004-PF-CA2
# Disc      -  Individual Assignment for ITSD004 Programming Fundamentals
#              SIM Global Education
#              Diploma in Information Technology
*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO {
    public static void exportData() throws ParserConfigurationException, IOException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbFactory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element root = document.createElement("gradetracker");

        for (Student student : GradeTracker.getStudents()) {
            Element studentRoot = document.createElement("student");

            Element studentName = document.createElement("name");
            studentName.setTextContent(student.getStudentName());
            Element studentId = document.createElement("id");
            studentId.setTextContent(student.getStudentID());

            studentRoot.appendChild(studentName);
            studentRoot.appendChild(studentId);

            for (Module module : student.getModules()) {
                Element moduleRoot = document.createElement("module");

                Element moduleCode = document.createElement("code");
                moduleCode.setTextContent(module.getModuleCode());
                Element moduleName = document.createElement("name");
                moduleName.setTextContent(module.getName());
                Element moduleDesc = document.createElement("description");
                moduleDesc.setTextContent(module.getDescription());
                Element moduleCredit = document.createElement("credit");
                moduleCredit.setTextContent(Integer.toString(module.getCreditUnits()));

                moduleRoot.appendChild(moduleName);
                moduleRoot.appendChild(moduleCode);
                moduleRoot.appendChild(moduleDesc);
                moduleRoot.appendChild(moduleCredit);

                for (Assessment assessment : module.getAssessments()) {
                    Element assessmentRoot = document.createElement("assessment");

                    Element assessmentName = document.createElement("name");
                    assessmentName.setTextContent(assessment.getName());
                    Element assessmentDescription = document.createElement("description");
                    assessmentDescription.setTextContent(assessment.getDescription());
                    Element assessmentMarks = document.createElement("marks");
                    assessmentMarks.setTextContent(Double.toString(assessment.getMarks()));
                    Element assessmentTotalMarks = document.createElement("totalmarks");
                    assessmentTotalMarks.setTextContent(Double.toString(assessment.getTotalMarks()));
                    Element assessmentWeightage = document.createElement("weightage");
                    assessmentWeightage.setTextContent(Double.toString(assessment.getWeightage()));

                    assessmentRoot.appendChild(assessmentName);
                    assessmentRoot.appendChild(assessmentDescription);
                    assessmentRoot.appendChild(assessmentMarks);
                    assessmentRoot.appendChild(assessmentTotalMarks);
                    assessmentRoot.appendChild(assessmentWeightage);
                    moduleRoot.appendChild(assessmentRoot);
                }
                studentRoot.appendChild(moduleRoot);
            }
            root.appendChild(studentRoot);
        }
        document.appendChild(root);

        OutputStream outputStream = Files.newOutputStream(Paths.get("data.xml"));
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(outputStream);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
        outputStream.close();

        System.out.println("Export successful!");
    }

    public static void importData() {
        try {
            File file = new File("data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            GradeTracker.getStudents().clear();             // Clear the values in the ArrayList : students

            NodeList studentList = document.getElementsByTagName("student");                // List : <student> tag
            for (int i = 0; i < studentList.getLength(); i++) {
                Node studentNode = studentList.item(i);
                if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) studentNode;
                    Student student = new Student(getTagValue("name",element), getTagValue("id",element));
                    GradeTracker.addStudent(student);

                    NodeList moduleList = element.getElementsByTagName("module");           // List : <module> tag
                    for (int j = 0; j < moduleList.getLength(); j++) {
                        Node moduleNode = moduleList.item(j);
                        if (moduleNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element moduleElement = (Element) moduleNode;
                            Module module = new Module(getTagValue("name",moduleElement), getTagValue("code",moduleElement), getTagValue("description",moduleElement), Integer.parseInt(getTagValue("credit",moduleElement)));
                            student.addModule(module);

                            NodeList assessmentList = moduleElement.getElementsByTagName("assessment");         // List : <assessment> tag
                            for (int k = 0; k < assessmentList.getLength(); k++) {
                                Node assessmentNode = assessmentList.item(k);
                                if (assessmentNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element assessmentElement = (Element) assessmentNode;
                                    Assessment assessment = new Assessment(getTagValue("name",assessmentElement), getTagValue("description",assessmentElement), module, Double.parseDouble(getTagValue("marks",assessmentElement)), Double.parseDouble(getTagValue("totalmarks",assessmentElement)), Double.parseDouble(getTagValue("weightage",assessmentElement)));
                                    module.addAssessment(assessment);
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Import successful!");
        } catch (FileNotFoundException e) {
            System.out.println("[data.xml] file not found!");
        } catch (SAXParseException e) {                             // If the format of XML file has broken
            System.out.println("[data.xml] has corrupted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node value = (Node) nodeList.item(0);
        return value.getNodeValue();
    }
}
