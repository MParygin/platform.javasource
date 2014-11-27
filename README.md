#platform.javasource

##Java source code generator

This library allows you to generate java souce code from your java code in a "functional" style.
Just create the code as a model and print it into print stream

Example: Create web servlet source code


    String pname = "myservlet;
    JClass cl = JClass("test", "testpackage").add(new JDoc("Servlet").addSee(pname));

    // extends
    cl.setExtends("javax.servlet.http.HttpServlet");

    // annotation
    cl.add(new JAnnotation("javax.servlet.annotation.WebServlet").
    add("name", pname).
    add("urlPatterns", new String[]{"/" + pname}).
    add("description", process.description));

    // get
    JMethodBody bodyGet = new JMethodBody();
    cl.add(new JMethod(true, false, false, "doGet", "void", bodyGet).
                    add(new JArg("req", "javax.servlet.http.HttpServletRequest")).
                    add(new JArg("res", "javax.servlet.http.HttpServletResponse")).
                    add(new JAnnotation("Override")).
                    add(new JThrow("java.io.IOException")).
                    add(new JThrow("javax.servlet.ServletException")).
                    add(new JDoc("Method GET").
                            addParam("req", "Request").
                            addParam("res", "Response")));

    cl.print(System.out, 0);


