package com.mongodb.test.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.test.document.Employee;
import com.mongodb.test.repository.EmployeeRepository;
import com.mongodb.test.repository.EmployeeRepositoryCustom;

@Controller
public class MainController {

	private static final String[] NAMES = { "Tom", "Jerry", "Donald" };

	@Autowired
	private EmployeeRepositoryCustom employeeRepositoryCustom;

	@Autowired
	private EmployeeRepository employeeRepository;

	@ResponseBody
	@RequestMapping("/")
	public String home() {
		String html = "<!doctype html>\r\n" + "<html lang=\"en\">\r\n" + "  <head>\r\n" + "<title>Title</title>\r\n"
				+ " <link rel=\"icon\" type=\"image/jpg\" href=\"/SpringBootMongoDB/resources/static/meo.jpg\" />\r\n  "
				+ " <!-- Required meta tags -->\r\n" + "    <meta charset=\"utf-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n"
				+ "\r\n" + "    <!-- Bootstrap CSS -->\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\r\n"
				+ "  </head>\r\n" + "  <body>\r\n" + "   \r\n" + "        <div class=\"list-group\">\r\n"
				+ "            <a href=\"/testInsert\" class=\"list-group-item list-group-item-action active\">Test Insert</a>\r\n"
				+ "            <a href=\"/showAllEmployee\" class=\"list-group-item list-group-item-action\">Show All</a>\r\n"
				+ "            <a href=\"/showFullNameLikeTom\" class=\"list-group-item list-group-item-action\">Show Tom</a>\r\n"
				+ "            <a href=\"/deleteAllEmployee\" class=\"list-group-item list-group-item-action\">Delete</a>\r\n"
				+ "        </div>\r\n" + "    <!-- Optional JavaScript -->\r\n"
				+ "    <!-- jQuery first, then Popper.js, then Bootstrap JS -->\r\n"
				+ "    <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\r\n"
				+ "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\r\n"
				+ "    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  </body>\r\n" + "</html>";
//		html += "<ul>";
//		html += " <li><a href='/testInsert'>Test Insert</a></li>";
//		html += " <li><a href='/showAllEmployee'>Show All Employee</a></li>";
//		html += " <li><a href='/showFullNameLikeTom'>Show All 'Tom'</a></li>";
//		html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
//		html += "</ul>";
		return html;
	}

	@ResponseBody
	@RequestMapping("/testInsert")
	public String testInsert() {
		Employee employee = new Employee();

		long id = this.employeeRepositoryCustom.getMaxEmpld() + 1;
		int idx = (int) (id % NAMES.length);
		String fullName = NAMES[idx] + " " + id;

		employee.setId(id);
		employee.setEmpNo("E" + id);
		employee.setFullName(fullName);
		employee.setHireDate(new Date());
		this.employeeRepository.insert(employee);

		return "Inserted: " + employee;
	}

	@ResponseBody
	@RequestMapping("/showAllEmployee")
	public String showAllEmployee() {

		List<Employee> employees = this.employeeRepository.findAll();

		String html = "";
		for (Employee emp : employees) {
			html += emp + "<br>";
		}

		return html;
	}

	@ResponseBody
	@RequestMapping("/showFullNameLikeTom")
	public String showFullNameLikeTom() {

		List<Employee> employees = this.employeeRepository.findByFullNameLike("Tom");

		String html = "";
		for (Employee emp : employees) {
			html += emp + "<br>";
		}

		return html;
	}

	@ResponseBody
	@RequestMapping("/deleteAllEmployee")
	public String deleteAllEmployee() {

		this.employeeRepository.deleteAll();
		return "Deleted!";
	}

}
