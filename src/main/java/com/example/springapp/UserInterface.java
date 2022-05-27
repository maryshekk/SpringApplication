package com.example.springapp;

import net.minidev.json.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class UserInterface {
    String http = "http://localhost:8080/";
    String emp = "employees";
    String dep = "departments";
    String de = "departments_employees";
    String p = "projects";

    Map<String, Runnable> commands = new HashMap<>();
    Map<Long, String> cmdNumbers = new HashMap<>();
    RestTemplate restTemplate = new RestTemplate();

    private String token = null;


    public void printAllCommands()
    {
        System.out.println("commands:");
        int i = 1;
        for (Long key: cmdNumbers.keySet())
        {
            System.out.println(key + ". " + cmdNumbers.get(key));
            i++;
        }
    }

    public void doGet(String url)
    {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String result = responseEntity.getBody();
        result = Objects.requireNonNull(result).replaceAll("}", "\n")
                .replaceAll("\\{", "").
                replaceAll(",", "\n").
                replaceAll("\"", "");
        System.out.println(result);
    }

    public void doPost(String url, JSONObject requestJson, String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token == null) {
            System.err.println("You are not authorized");
            return;
        }
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void doPut(String url, JSONObject requestJson, String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token == null) {
            System.err.println("You are not authorized");
            return;
        }
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestJson.toString(), headers);
        restTemplate.put(url, request);
//        System.out.println(responseEntity.getBody());
    }

    public void getCommand(Long key) {
        String cmd = null;
        if (cmdNumbers.containsKey(key))
        {
            cmd = cmdNumbers.get(key);
            if (commands.containsKey(cmd))
            {
                commands.get(cmd).run();
            }
        }
        else if (!cmd.equals("exit")) {
            System.err.println("No such command!");
        }
    }

    {
        Runnable addEmployee = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("employee first_name: ");
            String firstName = in.nextLine();
            System.out.println("employee last_name: ");
            String lastName = in.nextLine();
            System.out.println("employee pather_name: ");
            String patherName = in.nextLine();
            System.out.println("employee position: ");
            String position = in.nextLine();
            System.out.println("employee salary: ");
            Long salary = in.nextLong();
            if (salary < 0) {
                throw new InputMismatchException("invalid input");
            }
            JSONObject req = new JSONObject();
            req.put("first_name", firstName);
            req.put("last_name", lastName);
            req.put("pather_name", patherName);
            req.put("position", firstName);
            req.put("first_name", position);
            req.put("salary", salary);
            String url = http + emp + "/add";
            doPost(url, req, token);
        };

        Runnable printEmployees = () ->
        {
            String url = http + emp + "/getAll";
            doGet(url);
        };

        Runnable findEmployee = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("employee id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + emp + "/get/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

        Runnable getDepartmentsEmployees = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("departments name: ");
            String name = in.nextLine();
            String url = http + emp + "/getByDepartmentName?name=" + name;
            doGet(url);
        };

        Runnable getEmployeesDepartments = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("employee id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + emp + "/getListOfDepartments/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

        Runnable countOfDepartments = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("employee id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + emp + "/getCountOfDepartments/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

        Runnable getEmployeesProjects = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("employee id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + emp + "/getListOfProjects/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

//    Runnable deleteEmployee = () ->
//    {
//        String url = http + emp + "/deleteById";
//        doGet(url);
//    };

//    Runnable deleteFromDepartment = () ->
//    {
//        String url = http + emp + "/deleteDepartment";
//        doGet(url);
//    };

        Runnable addIntoDepartment = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("employee id: ");
            JSONObject req = new JSONObject();
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                req.put("id", id);
                url = http + emp + "/addNewDepartment?id=" + id;
                System.out.println("department id: ");
                Long depId = Long.parseLong(in.nextLine());
                if (depId < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                req.put("depId", depId);
                url = url + "&depId=" + depId;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doPost(url, req, token);
        };

        Runnable changePosition = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("employee id: ");
            String url = null;
            JSONObject req = new JSONObject();
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                req.put("id", id);
                System.out.println("new position: ");
                String position = in.nextLine();
                req.put("position", position);
                url = http + emp + "/setNewPosition?id=" + id + "&position=" + position;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doPut(url, req, token);
        };


        Runnable printProjects = () ->
        {
            String url = http + p + "/getAll";
            doGet(url);
        };

        Runnable addProject = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("project name: ");
            String name = in.nextLine();
            System.out.println("project cost: ");
            Long cost = in.nextLong();
            if (cost < 0) {
                throw new InputMismatchException("invalid input");
            }
            System.out.println("project date_beg: ");
            String date_beg = in.nextLine();
            System.out.println("project date_end: ");
            String date_end = in.nextLine();
            System.out.println("project date_end_real: ");
            String date_end_real = in.nextLine();
            System.out.println("project department_id: ");
            Long department_id = in.nextLong();
            if (department_id < 0) {
                throw new InputMismatchException("invalid input");
            }
            JSONObject req = new JSONObject();
            req.put("name", name);
            req.put("cost", cost);
            req.put("date_beg", date_beg);
            req.put("date_end", date_end);
            req.put("date_end_real", date_end_real);
            req.put("department_id", department_id);
            String url = http + p + "/add";
            doPost(url, req, token);
        };

        Runnable findProject = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("project id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + p + "/get/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

        Runnable getDepartmentsProjects = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("department name: ");
            String name = in.nextLine();
            String url = http + p + "/getByDepartmentName?name=" + name;
            doGet(url);
        };

        Runnable getProjectsEmployees = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("project id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + p + "/getListOfEmployees/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

//    Runnable deleteProject = () ->
//    {
//        String url = http + p + "/getListOfDepartments";
//        doGet(url);
//    };

        Runnable changeDepartment = () ->
        {
            Scanner in = new Scanner(System.in);
            JSONObject req = new JSONObject();
            System.out.println("project id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                req.put("id", id);
                System.out.println("new department name: ");
                String name = in.nextLine();
                req.put("name", name);
                url = http + p + "/setNewDepartmentByName?id=" + id + "&name=" + name;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doPut(url, req, token);
        };


        Runnable printDepartments = () ->
        {
            String url = http + dep + "/getAll";
            doGet(url);
        };

        Runnable addDepartment = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("department name: ");
            String name = in.nextLine();
            JSONObject req = new JSONObject();
            req.put("name", name);
            String url = http + dep + "/add";
            doPost(url, req, token);
        };

        Runnable findDepartment = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("department id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + dep + "/get/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

        Runnable countOfEmployees = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("department name: ");
            String name = in.nextLine();
            String url = http + dep + "/getCount?name=" + name;
            doGet(url);
        };

        Runnable countOfProjects = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("department name: ");
            String name = in.nextLine();
            String url = http + dep + "/getCountOfProjects?name=" + name;
            doGet(url);
        };

//    Runnable deleteDepartment = () ->
//    {
//        String url = http + dep + "/deleteById";
//        doGet(url);
//    };


        Runnable printEmployeesDepartments = () ->
        {
            String url = http + de + "/getAll";
            doGet(url);
        };

        Runnable addNote = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("department_id: ");
            Long department_id = in.nextLong();
            if (department_id < 0) {
                throw new InputMismatchException("invalid input");
            }
            System.out.println("employee_id: ");
            Long employee_id = in.nextLong();
            if (employee_id < 0) {
                throw new InputMismatchException("invalid input");
            }
            JSONObject req = new JSONObject();
            req.put("department_id", department_id);
            req.put("employee_id", employee_id);
            String url = http + de + "/add";
            doPost(url, req, token);
        };

        Runnable findNote = () ->
        {
            Scanner in = new Scanner(System.in);
            System.out.println("note id: ");
            String url = null;
            try {
                Long id = Long.parseLong(in.nextLine());
                if (id < 0) {
                    throw new InputMismatchException("invalid input!");
                }
                url = http + de + "/get/" + id;
            } catch (NumberFormatException e) {
                throw new InputMismatchException("invalid input");
            }
            doGet(url);
        };

        Runnable authentication = () -> {
            Scanner in = new Scanner(System.in);
            System.out.println("username");
            String userName = in.nextLine();
            System.out.println("password");
            String pwd = in.nextLine();
            String url = http + "auth/signin";
            JSONObject req = new JSONObject();
            req.put("userName", userName);
            req.put("password", pwd);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(req.toString(), headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, request, String.class);
            String token = responseEntity.getBody();
            if (token == null)
            {
                System.err.println("Wrong password or username");
            }
            else {
                token = Objects.requireNonNull(token).substring(token.indexOf("\"token\":\""), token.indexOf("\"}"));
                token = token.substring(9);
                this.token = token;
            }
        };

        commands.put("authentication", authentication);
        commands.put("add employee", addEmployee);
        commands.put("add department", addDepartment);
        commands.put("add project", addProject);
        commands.put("add note", addNote);
        commands.put("add employee into department", addIntoDepartment);
        commands.put("change employee position", changePosition);
        commands.put("change projects department", changeDepartment);
        commands.put("count of departments employees", countOfEmployees);
        commands.put("count of departments projects", countOfProjects);
        commands.put("count of employees departments", countOfDepartments);
        commands.put("get employee", findEmployee);
        commands.put("get department", findDepartment);
        commands.put("get project", findProject);
        commands.put("get note", findNote);
        commands.put("list of employees", printEmployees);
        commands.put("list of departments", printDepartments);
        commands.put("list of projects", printProjects);
        commands.put("list of notes", printEmployeesDepartments);
        commands.put("list of department employees", getDepartmentsEmployees);
        commands.put("list of employees departments", getEmployeesDepartments);
        commands.put("list of employees projects", getEmployeesProjects);
        commands.put("list of departments projects", getDepartmentsProjects);
        commands.put("list of projects employees", getProjectsEmployees);

        cmdNumbers.put(1L, "authentication");
        cmdNumbers.put(2L, "add employee");
        cmdNumbers.put(3L, "add department");
        cmdNumbers.put(4L, "add project");
        cmdNumbers.put(5L, "add note");
        cmdNumbers.put(6L, "add employee into department");
        cmdNumbers.put(7L, "change employee position");
        cmdNumbers.put(8L, "change projects department");
        cmdNumbers.put(9L, "count of departments employees");
        cmdNumbers.put(10L, "count of departments projects");
        cmdNumbers.put(11L, "count of employees departments");
        cmdNumbers.put(12L, "get employee");
        cmdNumbers.put(13L, "get department");
        cmdNumbers.put(14L, "get project");
        cmdNumbers.put(15L, "get note");
        cmdNumbers.put(16L, "list of employees");
        cmdNumbers.put(17L, "list of departments");
        cmdNumbers.put(18L, "list of projects");
        cmdNumbers.put(19L, "list of notes");
        cmdNumbers.put(20L, "list of department employees");
        cmdNumbers.put(21L, "list of employees departments");
        cmdNumbers.put(22L, "list of employees projects");
        cmdNumbers.put(23L, "list of departments projects");
        cmdNumbers.put(24L, "list of projects employees");
    }


//    Runnable deleteNote = () ->
//    {
//        String url = http + de;
//        doGet(url);
//    };




}
