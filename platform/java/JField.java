package platform.java;

import java.io.PrintStream;

/**
 * Field
 */
public final class JField implements JPrintable {

    private final boolean aPublic;
    private final boolean aPrivate;
    private final boolean aStatic;
    private final String name;
    private final String type;
    private Object value;

    private JDoc doc;
    private JComment comment;

    public JField(boolean aPublic, boolean aPrivate, boolean aStatic, String name, String type, Object value) {
        this.aPublic = aPublic;
        this.aPrivate = aPrivate;
        this.aStatic = aStatic;
        this.name = name;
        this.type = type;
        this.value = value;
    }


    public JField(boolean aPublic, boolean aPrivate, boolean aStatic, String name, String type) {
        this.aPublic = aPublic;
        this.aPrivate = aPrivate;
        this.aStatic = aStatic;
        this.name = name;
        this.type = type;
    }

    /**
     * Add comment
     *
     * @param comment Comment
     * @return This
     */
    public JField add(JComment comment) {
        this.comment = comment;
        return this;
    }

    public JField add(JDoc doc) {
        this.doc = doc;
        return this;
    }


    @Override
    public void print(PrintStream out, int indent) {

        // doc
        if (this.doc != null) this.doc.print(out, indent);

        // comment + indent
        if (this.comment != null) this.comment.print(out, indent);

        // indent
        for (int i = 0; i < indent; i++) out.print(' ');

        // self
        if (this.aPublic) out.print("public ");
        if (this.aPrivate) out.print("private ");
        if (this.aStatic) out.print("static ");
        // type
        out.print(this.type);
        out.print(" ");
        // name
        out.print(this.name);
        if (this.value != null) {
            out.print(" = ");
            if (this.type.equals("String")) {
                out.print('"');
                out.print(this.value);
                out.print('"');
//            } if (this.type.equals("boolean")) {
//                out.print(this.value);
            } else {
                out.print(this.value);
            }
        }
        out.println(";");

    }

}
