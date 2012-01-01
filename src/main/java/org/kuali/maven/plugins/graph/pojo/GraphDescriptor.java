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
package org.kuali.maven.plugins.graph.pojo;

import java.io.File;

/**
 *
 */
public class GraphDescriptor {
    String executable = "dot";
    String title;
    Boolean keepDotFile;
    Boolean transitive;
    String includes;
    String excludes;
    String hide;
    String show;
    Direction direction;
    Boolean showGroupIds;
    Boolean showDuplicates;
    Boolean showConflicts;
    Boolean cascadeOptional;
    Boolean showLegend;
    Boolean showTitle;
    Boolean executeDot;
    Boolean ignoreDotFailure;
    Boolean skipEmptyGraphs;
    Integer depth;
    Scope scope;
    File file;
    String content;
    File dotFile;
    String outputFormat;
    Layout layout;
    Display filterType;
    String description;
    String name;
    Row row;

    public String getExecutable() {
        return executable;
    }

    public void setExecutable(String executable) {
        this.executable = executable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getKeepDotFile() {
        return keepDotFile;
    }

    public void setKeepDotFile(Boolean keepDotFile) {
        this.keepDotFile = keepDotFile;
    }

    public Boolean getTransitive() {
        return transitive;
    }

    public void setTransitive(Boolean transitive) {
        this.transitive = transitive;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Boolean getShowGroupIds() {
        return showGroupIds;
    }

    public void setShowGroupIds(Boolean showGroupIds) {
        this.showGroupIds = showGroupIds;
    }

    public Boolean getShowLegend() {
        return showLegend;
    }

    public void setShowLegend(Boolean showLegend) {
        this.showLegend = showLegend;
    }

    public Boolean getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(Boolean showTitle) {
        this.showTitle = showTitle;
    }

    public Boolean getExecuteDot() {
        return executeDot;
    }

    public void setExecuteDot(Boolean executeDot) {
        this.executeDot = executeDot;
    }

    public Boolean getIgnoreDotFailure() {
        return ignoreDotFailure;
    }

    public void setIgnoreDotFailure(Boolean ignoreDotFailure) {
        this.ignoreDotFailure = ignoreDotFailure;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getDotFile() {
        return dotFile;
    }

    public void setDotFile(File dotFile) {
        this.dotFile = dotFile;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String type) {
        this.outputFormat = type;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Boolean getShowDuplicates() {
        return showDuplicates;
    }

    public void setShowDuplicates(Boolean showDuplicates) {
        this.showDuplicates = showDuplicates;
    }

    public Boolean getCascadeOptional() {
        return cascadeOptional;
    }

    public void setCascadeOptional(Boolean cascadeOptional) {
        this.cascadeOptional = cascadeOptional;
    }

    public Boolean getShowConflicts() {
        return showConflicts;
    }

    public void setShowConflicts(Boolean showConflicts) {
        this.showConflicts = showConflicts;
    }

    public Boolean getSkipEmptyGraphs() {
        return skipEmptyGraphs;
    }

    public void setSkipEmptyGraphs(Boolean skipEmptyGraphs) {
        this.skipEmptyGraphs = skipEmptyGraphs;
    }

    public Display getFilterType() {
        return filterType;
    }

    public void setFilterType(Display filterType) {
        this.filterType = filterType;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

}