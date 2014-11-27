package platform.java;

/**
 * Bean Property (field + get + set)
 */
public final class JProperty {

    private String name;
    private String type;
    private JField field;
    private JMethod set;
    private JMethod get;

    public JProperty(String name, String type, Object dflt) {
        this.name = name;
        this.type = type;
        this.field = new JField(false, true, false, name, type, dflt);
        this.get = new JMethod(true, false, false, "get" + name, type, "return this." + name + ";");
        this.set = new JMethod(true, false, false, "set" + name, "void", "this." + name + " = $;").add(new JArg("$", type));
    }

    public JField getField() {
        return field;
    }

    public JMethod getSet() {
        return set;
    }

    public JMethod getGet() {
        return get;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
