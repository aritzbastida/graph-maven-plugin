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
package org.kuali.maven.plugins.graph.mojo;

import java.io.File;

import org.kuali.maven.plugins.graph.pojo.GraphContext;
import org.kuali.maven.plugins.graph.pojo.LayoutStyle;
import org.kuali.maven.plugins.graph.pojo.MojoContext;
import org.kuali.maven.plugins.graph.tree.Helper;

/**
 * <p>
 * </p>
 *
 */
public abstract class FilteredGraphMojo extends BaseGraphMojo {

    /**
     * <p>
     * Set to false to show only the dependencies for the current project
     * </p>
     *
     * @parameter expression="${graph.transitive}" default-value="true"
     */
    private boolean transitive;

    /**
     * <p>
     * The style for the graph layout. Valid options are <code>CONDENSED</code> and <code>FLAT</code>
     * </p>
     *
     * <p>
     * In <code>CONDENSED</code> mode, each dependency appears on the graph only once. Graphviz algorithms present the
     * connections between the dependencies as a directed hierarchical graph.
     * </p>
     *
     * <p>
     * For transitive dependencies that are widely used (eg commons-logging), <code>CONDENSED</code> mode makes it
     * possible to quickly pinpoint what other dependencies are causing the transitive dependency to be included in the
     * build.
     * </p>
     *
     * <p>
     * In <code>FLAT</code> mode, dependencies are displayed exactly how they are defined in the pom's. This style can
     * make it easier to comprehend the dependency tree but connections between shared dependencies are not drawn.
     * </p>
     *
     * @parameter expression="${graph.layout}" default-value="CONDENSED"
     */
    private LayoutStyle layout;

    /**
     * <p>
     * Comma delimited list of patterns for including artifacts. The pattern syntax has the form -
     * [groupId]:[artifactId]:[type]:[classifier]:[version]
     * </p>
     *
     * <p>
     * Include patterns work "bottom up" and are overridden by exclude patterns. If an artifact matches an include
     * pattern, it, and all of the dependencies in the path from it back to the root of the dependency tree are
     * displayed.
     * </p>
     *
     * <p>
     * If not provided all dependencies are included.
     * </p>
     *
     * <p>
     * Each pattern segment is optional and supports <code>*</code> wildcards. An empty pattern segment is treated as a
     * wildcard.
     * </p>
     *
     * @parameter expression="${graph.includes}"
     */
    private String includes;

    /**
     * <p>
     * Comma delimited list of artifact patterns to exclude. The pattern syntax has the form -
     * [groupId]:[artifactId]:[type]:[classifier]:[version]
     * </p>
     *
     * <p>
     * Exclude patterns override include patterns and work "top down". If a dependency matches any exclude pattern, it,
     * and all dependencies below it, are removed from the display.
     * </p>
     *
     * <p>
     * If not provided, no artifacts are excluded.
     * </p>
     *
     * <p>
     * Each pattern segment is optional and supports <code>*</code> wildcards. An empty pattern segment is treated as a
     * wildcard.
     * </p>
     *
     * @parameter expression="${graph.excludes}"
     */
    private String excludes;

    /**
     * <p>
     * Comma delimited list of dependency patterns used for hiding artifacts. The pattern syntax has the form -
     * [scope]:[optional|required]:[state]
     * </p>
     *
     * <p>
     * Hide patterns override show patterns and work "top down". If a dependency matches any hide pattern, it, and all
     * dependencies below it, are removed from the display.
     * </p>
     *
     * <p>
     * If not provided, no dependencies are hidden.
     * </p>
     *
     * <p>
     * Scopes: compile,provided,runtime,test,system,import<br/>
     * States: normal,conflict,cyclic,duplicate
     * </p>
     *
     * <p>
     * Each pattern segment is optional and supports <code>*</code> wildcards. An empty pattern segment is treated as a
     * wildcard.
     * </p>
     *
     * @parameter expression="${graph.hide}"
     */
    private String hide;

    /**
     * <p>
     * Comma delimited list of dependency patterns used for showing artifacts.<br/>
     * The pattern syntax has the form - [scope]:[optional|required]:[state]
     * </p>
     *
     * <p>
     * Show patterns work "bottom up" and are overridden by hide patterns. If a dependency matches any show criteria,
     * it, and all of the dependencies in the direct path from it back to the root of the dependency tree are displayed.
     * </p>
     *
     * <p>
     * Scopes: compile,provided,runtime,test,system,import States: normal,conflict,cyclic,duplicate
     * </p>
     *
     * <p>
     * Each pattern segment is optional and supports <code>*</code> wildcards. An empty pattern segment is treated as a
     * wildcard.
     * </p>
     *
     * @parameter expression="${graph.show}"
     */
    private String show;

    public abstract File getFile();

    /**
     * Restricts the depth of the dependency tree. To show only the dependencies of the current project, set this to 1.
     * To show the dependencies of the current project and their direct dependencies, set this to 2.
     *
     * @parameter expression="${graph.depth}" default-value="-1"
     */
    private int depth;

    @Override
    public void execute() {
        MojoContext mc = Helper.copyProperties(MojoContext.class, this);
        GraphContext gc = Helper.copyProperties(GraphContext.class, this);
        MojoHelper mh = new MojoHelper();
        mh.execute(mc, gc);
    }

    public boolean isTransitive() {
        return transitive;
    }

    public void setTransitive(boolean transitive) {
        this.transitive = transitive;
    }

    public LayoutStyle getLayout() {
        return layout;
    }

    public void setLayout(LayoutStyle layout) {
        this.layout = layout;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

}