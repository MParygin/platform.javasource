package platform.java;

import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;

/**
 * Method
 */
public final class JMethod implements JPrintable {

    private final boolean aPublic;
    private final boolean aPrivate;
    private final boolean aStatic;

    private final String name;
    private final String type;

    private JMethodBody methodBody;

    private JDoc doc = null;
    private final List<JComment> comments = new ArrayList<>();
    private final List<JAnnotation> annotations = new ArrayList<>();
    private final List<JArg> arguments = new ArrayList<>();
    private final List<JThrow> aThrows = new ArrayList<>();


    public JMethod(boolean aPublic, boolean aPrivate, boolean aStatic, String name, String type) {
        this.aPublic = aPublic;
        this.aPrivate = aPrivate;
        this.aStatic = aStatic;
        this.name = name;
        this.type = type;
        this.methodBody = null;
    }


    public JMethod(boolean aPublic, boolean aPrivate, boolean aStatic, String name, String type, String body) {
        this.aPublic = aPublic;
        this.aPrivate = aPrivate;
        this.aStatic = aStatic;
        this.name = name;
        this.type = type;
        this.methodBody = new JMethodBody();
        this.methodBody.append(body);
    }

    public JMethod(boolean aPublic, boolean aPrivate, boolean aStatic, String name, String type, JMethodBody body) {
        this.aPublic = aPublic;
        this.aPrivate = aPrivate;
        this.aStatic = aStatic;
        this.name = name;
        this.type = type;
        this.methodBody = body;
    }


    public JMethod add(JDoc doc) {
        this.doc = doc;
        return this;
    }

    public JMethod add(JAnnotation annotation) {
        this.annotations.add(annotation);
        return this;
    }

    public JMethod add(JComment $) {
        this.comments.add($);
        return this;
    }

    public JMethod add(JArg $) {
        this.arguments.add($);
        return this;
    }

    public JMethod add(JThrow $) {
        this.aThrows.add($);
        return this;
    }

    public List<JArg> getArguments() {
        return arguments;
    }

    @Override
    public void print(PrintStream out, int indent) {
        // comments
        for (JComment comment : comments) {
            comment.print(out, indent);
        }

        // doc
        if (doc != null) doc.print(out, indent);

        // annotations
        for (JAnnotation annotation : annotations) {
            annotation.print(out, indent);
            out.println();
        }

        // indent
        for (int i = 0; i < indent; i++) out.print(' ');

        // name
        if (this.aPublic) out.print("public ");
        if (this.aPrivate) out.print("private ");
        if (this.aStatic) out.print("static ");
        out.print(this.type);
        out.print(" ");
        out.print(this.name);

        // arguments
        out.print("(");
        for (int i = 0; i < this.arguments.size(); i++) {
            if (i != 0) out.print(", ");
            this.arguments.get(i).print(out, 0);
        }
        out.print(") ");

        // throws
        if (this.aThrows.size() > 0) {
            out.print("throws ");
            for (int i = 0; i < this.aThrows.size(); i++) {
                if (i != 0) out.print(", ");
                this.aThrows.get(i).print(out, 0);
            }
            out.print(" ");
        }

        // {
        out.println("{");

        // body
        this.methodBody.print(out, indent + 2);

        // indent
        for (int i = 0; i < indent; i++) out.print(' ');
        // }
        out.println("}");
        out.println();
    }
}
