package org.kuali.maven.plugins.graph.processor;

import java.util.List;

import org.kuali.maven.plugins.graph.dot.EdgeGenerator;
import org.kuali.maven.plugins.graph.pojo.Edge;
import org.kuali.maven.plugins.graph.pojo.Layout;
import org.kuali.maven.plugins.graph.pojo.MavenContext;
import org.kuali.maven.plugins.graph.pojo.State;
import org.kuali.maven.plugins.graph.tree.Node;
import org.kuali.maven.plugins.graph.tree.TreeHelper;

public class HideConflictsProcessor implements Processor {
    Layout layout;
    TreeHelper helper = new TreeHelper();
    EdgeGenerator generator = new EdgeGenerator();

    public HideConflictsProcessor() {
        this(null);
    }

    public HideConflictsProcessor(Layout layout) {
        super();
        this.layout = layout;
    }

    @Override
    public void process(Node<MavenContext> root) {
        switch (layout) {
        case LINKED:
            handleLinkedConflicts(root);
            return;
        case FLAT:
            handleFlatConflicts(root);
            return;
        default:
            throw new IllegalStateException("Unknown layout " + layout);
        }
    }

    protected void handleLinkedConflicts(Node<MavenContext> root) {
        List<Node<MavenContext>> conflicts = helper.getNodeList(root, State.CONFLICT);
        for (Node<MavenContext> conflict : conflicts) {
            String replacementId = TreeHelper.getArtifactId(conflict.getObject().getReplacement());
            Node<MavenContext> replacement = TreeHelper.findRequiredIncludedNode(root, replacementId);
            Edge edge = generator.getParentChildEdge(conflict, replacement);
            generator.addEdge(conflict.getParent(), edge);
            helper.hide(conflict);
        }
    }

    protected void handleFlatConflicts(Node<MavenContext> root) {
        List<Node<MavenContext>> conflicts = helper.getNodeList(root, State.CONFLICT);
        for (Node<MavenContext> conflict : conflicts) {
            helper.hide(conflict);
        }
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }
}
