package org.kuali.maven.plugins.graph.dot.html;

public class Label {

    public Label() {
        super();
    }

    public Label(Table table) {
        super();
        this.table = table;
    }

    public Label(Text text) {
        super();
        this.text = text;
    }

    Text text;
    Table table;

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
