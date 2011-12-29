/**
 * Copyright 2010-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.plugins.graph.dot;

import java.util.ArrayList;
import java.util.List;

import org.kuali.maven.plugins.graph.pojo.Edge;
import org.kuali.maven.plugins.graph.pojo.GraphNode;
import org.kuali.maven.plugins.graph.pojo.MavenContext;
import org.kuali.maven.plugins.graph.pojo.Scope;
import org.kuali.maven.plugins.graph.pojo.State;
import org.kuali.maven.plugins.graph.pojo.Style;
import org.kuali.maven.plugins.graph.processor.StyleProcessor;
import org.kuali.maven.plugins.graph.tree.Node;
import org.kuali.maven.plugins.graph.tree.TreeHelper;

/**
 * <p>
 * Draw one parent->child edge for each node in the graph.
 * </p>
 */
public class EdgeGenerator {
    StyleProcessor sp = new StyleProcessor();

    /**
     * <p>
     * Add this edge to this node. If there are no edges on this node yet, create a <code>List<Edge></code> and store it
     * on the node
     * </p>
     */
    public void addEdge(Node<MavenContext> node, Edge edge) {
        MavenContext context = node.getObject();
        List<Edge> edges = context.getEdges();
        if (edges == null) {
            edges = new ArrayList<Edge>();
            context.setEdges(edges);
        }
        edges.add(edge);
    }

    /**
     * <p>
     * Create an edge running from this node's parent to itself
     * </p>
     */
    public Edge getParentChildEdge(Node<MavenContext> node) {
        GraphNode parent = node.getParent().getObject().getGraphNode();
        MavenContext context = node.getObject();
        GraphNode child = context.getGraphNode();
        boolean optional = context.isOptional();
        State state = context.getState();
        Scope scope = Scope.getScope(context.getArtifact().getScope());
        return getStyledEdge(parent, child, optional, scope, state);
    }

    /**
     * <p>
     * Create an edge between the indicated parent and child using styling for the indicated scope, state, and optional
     * settings
     * </p>
     */
    public Edge getStyledEdge(GraphNode parent, GraphNode child, boolean optional, Scope scope, State state) {
        Style style = sp.getStyle(scope, optional, state);
        String label = TreeHelper.getRelationshipLabel(scope, optional, state);
        Edge edge = new Edge(parent, child);
        sp.copyStyleProperties(edge, style);
        edge.setLabel(label);
        return edge;
    }
}
