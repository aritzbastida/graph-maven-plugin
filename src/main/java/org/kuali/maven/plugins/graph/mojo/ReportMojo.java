/**
 * Copyright 2011-2012 The Kuali Foundation
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
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.maven.reporting.MavenReport;
import org.apache.maven.reporting.MavenReportException;
import org.codehaus.doxia.sink.Sink;
import org.kuali.maven.plugins.graph.pojo.Category;
import org.kuali.maven.plugins.graph.pojo.GraphDescriptor;
import org.kuali.maven.plugins.graph.pojo.MojoContext;
import org.kuali.maven.plugins.graph.pojo.Row;
import org.kuali.maven.plugins.graph.util.Helper;

/**
 * <p>
 * Generate a set of common dependency graphs and a report linking to them during Maven site generation.
 * </p>
 *
 * @goal report
 * @requiresDependencyResolution compile|test|runtime
 */
@SuppressWarnings("deprecation")
public class ReportMojo extends MultiMojo implements MavenReport {
    private static final String FS = System.getProperty("file.separator");

    /**
     * <p>
     * Output folder where the main page of the report will be generated.
     * </p>
     * <p>
     * Note that this parameter is only relevant if the goal is run directly from the command line or from the default
     * lifecycle. If the goal is run indirectly as part of site generation, the output directory configured in the Maven
     * Site Plugin will be used instead.
     * </p>
     *
     * @parameter expression="${project.reporting.outputDirectory}"
     * @required
     */
    private File reportOutputDirectory;

    /**
     * <p>
     * Sub-directory underneath the output folder where graphs are created.
     * </p>
     *
     * @parameter expression="${graph.subDirectory}" default-value="graph"
     * @required
     */
    private String subDirectory;

    /**
     * <p>
     * List of graphs to generate organized into categories and rows.
     * </p>
     *
     * <p>
     * For example:
     * </p>
     *
     * <pre>
     * &lt;categories&gt;
     *   &lt;category&gt;
     *     &lt;name&gt;logging&lt;/name&gt;
     *     &lt;description&gt;Dependencies on logging libraries&lt;/description&gt;
     *     &lt;rows&gt;
     *       &lt;row&gt;
     *         &lt;name&gt;logging&lt;/name&gt;
     *         &lt;description&gt;Dependencies on logging libraries&lt;/description&gt;
     *         &lt;descriptors&gt;
     *           &lt;descriptor&gt;
     *             &lt;includes&gt;org.slf4j,log4j&lt;/includes&gt;
     *             &lt;name&gt;other-logging&lt;/name&gt;
     *           &lt;/descriptor&gt;
     *           &lt;descriptor&gt;
     *             &lt;includes&gt;commons-logging&lt;/includes&gt;
     *             &lt;filterType&gt;PATH&lt;/filterType&gt;
     *             &lt;name&gt;commons-logging&lt;/name&gt;
     *           &lt;/descriptor&gt;
     *         &lt;/descriptors&gt;
     *       &lt;/row&gt;
     *     &lt;/rows&gt;
     *   &lt;/category&gt;
     * &lt;/categories&gt;
     * </pre>
     *
     * @parameter
     */
    private List<Category> categories;

    @Override
    public void generate(Sink sink, Locale locale) throws MavenReportException {
        setOutputDir(new File(reportOutputDirectory + FS + subDirectory));
        MojoContext mc = Helper.copyProperties(MojoContext.class, this);
        GraphDescriptor gd = Helper.copyProperties(GraphDescriptor.class, this);
        MojoHelper helper = new MojoHelper();
        if (isSkip()) {
            categories = Collections.emptyList();
            getLog().info("Skipping graphs");
        } else {
            categories = Helper.toEmptyList(categories);
            helper.categories(mc, gd, categories);
        }
        doHead(sink);
        doBody(sink, categories);
        sink.flush();
        sink.close();
    }

    protected void doBody(Sink sink, List<Category> categories) {
        sink.body();
        sink.section1();
        sink.sectionTitle1();
        sink.text("Project Dependency Graphs");
        sink.sectionTitle1_();
        if (isEmpty(categories)) {
            sink.text("No graphs to display");
        } else {
            for (Category category : categories) {
                doCategory(sink, category);
            }
        }
        sink.section1_();
        sink.body_();
    }

    protected void doCategory(Sink sink, Category category) {
        if (isEmpty(category)) {
            return;
        }
        sink.section2();
        doCategoryHeader(sink, category);
        sink.table();
        doTableHeader(sink);
        for (Row row : category.getRows()) {
            doRow(sink, row);
        }
        sink.table_();
        sink.section2_();
    }

    protected void doCategoryHeader(Sink sink, Category category) {
        sink.sectionTitle2();
        sink.text(category.getName());
        sink.sectionTitle2_();
        sink.text(category.getDescription());
        sink.lineBreak();
        sink.lineBreak();
    }

    protected void doTableHeader(Sink sink) {
        sink.tableRow();
        sink.tableHeaderCell();
        sink.text("Type");
        sink.tableHeaderCell_();
        sink.tableHeaderCell();
        sink.text("Graphs");
        sink.tableHeaderCell_();
        sink.tableHeaderCell();
        sink.text("Description");
        sink.tableHeaderCell_();
        sink.tableRow_();
    }

    protected void doRow(Sink sink, Row row) {
        if (isEmpty(row)) {
            return;
        }
        sink.tableRow();
        sink.tableCell();
        sink.text(row.getName());
        sink.tableCell_();
        sink.tableCell();
        doDescriptors(sink, row.getDescriptors());
        sink.tableCell_();
        sink.tableCell();
        sink.text(row.getDescription());
        sink.tableCell_();
        sink.tableRow_();
    }

    protected void doDescriptors(Sink sink, List<GraphDescriptor> gds) {
        for (int i = 0; i < gds.size(); i++) {
            if (i != 0) {
                sink.text(",");
                sink.nonBreakingSpace();
            }
            doLink(sink, gds.get(i));
        }
    }

    protected void doLink(Sink sink, GraphDescriptor gd) {
        MojoHelper helper = new MojoHelper();
        String href = subDirectory + "/" + helper.getRelativeFilename(gd);
        sink.link(href);
        sink.text(gd.getName());
        sink.link_();
    }

    protected void doHead(Sink sink) {
        sink.head();
        sink.title();
        sink.text("Project Dependency Graphs");
        sink.title_();
        sink.head_();
    }

    @Override
    public String getOutputName() {
        return "dependency-graphs";
    }

    @Override
    public String getCategoryName() {
        return CATEGORY_PROJECT_INFORMATION;
    }

    @Override
    public String getName(Locale locale) {
        return "Dependency Graphs";
    }

    @Override
    public String getDescription(Locale locale) {
        return "This document provides links to graphs that make it easier to visualize the project's dependencies";
    }

    @Override
    public boolean isExternalReport() {
        return false;
    }

    @Override
    public boolean canGenerateReport() {
        return true;
    }

    @Override
    public File getReportOutputDirectory() {
        return reportOutputDirectory;
    }

    @Override
    public void setReportOutputDirectory(File reportOutputDirectory) {
        this.reportOutputDirectory = reportOutputDirectory;
    }

    public String getSubDirectory() {
        return subDirectory;
    }

    public void setSubDirectory(String subDirectory) {
        this.subDirectory = subDirectory;
    }

    protected boolean isEmpty(List<Category> categories) {
        if (Helper.isEmpty(categories)) {
            return true;
        } else {
            for (Category category : categories) {
                if (!isEmpty(category)) {
                    return false;
                }
            }
            return true;
        }
    }

    protected boolean isEmpty(Category category) {
        if (Helper.isEmpty(category.getRows())) {
            return true;
        } else {
            return isEmptyRows(category.getRows());
        }
    }

    protected boolean isEmptyRows(List<Row> rows) {
        if (Helper.isEmpty(rows)) {
            return true;
        }
        for (Row row : rows) {
            if (!Helper.isEmpty(row.getDescriptors())) {
                return false;
            }
        }
        return true;
    }

    protected boolean isEmpty(Row row) {
        return Helper.isEmpty(row.getDescriptors());
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
