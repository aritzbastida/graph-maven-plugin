/**
 * Copyright 2010-2012 The Kuali Foundation
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

import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactCollector;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.dependency.tree.DependencyNode;
import org.apache.maven.shared.dependency.tree.DependencyTreeBuilder;
import org.kuali.maven.plugins.graph.tree.Node;

/**
 *
 */
@SuppressWarnings("deprecation")
public class MojoContext {

    private MavenProject project;

    private ArtifactRepository localRepository;

    private ArtifactResolver artifactResolver;

    private ArtifactFactory artifactFactory;

    private ArtifactMetadataSource artifactMetadataSource;

    private ArtifactCollector artifactCollector;

    private DependencyTreeBuilder treeBuilder;

    private boolean verbose;

    private boolean skip;

    private File outputDir;

    private boolean generateDefaultGraphs;

    private DependencyNode mavenTree;

    private Node<MavenContext> sanitizedTree;

    public MavenProject getProject() {
        return project;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public ArtifactRepository getLocalRepository() {
        return localRepository;
    }

    public void setLocalRepository(ArtifactRepository localRepository) {
        this.localRepository = localRepository;
    }

    public ArtifactResolver getArtifactResolver() {
        return artifactResolver;
    }

    public void setArtifactResolver(ArtifactResolver artifactResolver) {
        this.artifactResolver = artifactResolver;
    }

    public ArtifactFactory getArtifactFactory() {
        return artifactFactory;
    }

    public void setArtifactFactory(ArtifactFactory artifactFactory) {
        this.artifactFactory = artifactFactory;
    }

    public ArtifactMetadataSource getArtifactMetadataSource() {
        return artifactMetadataSource;
    }

    public void setArtifactMetadataSource(ArtifactMetadataSource artifactMetadataSource) {
        this.artifactMetadataSource = artifactMetadataSource;
    }

    public ArtifactCollector getArtifactCollector() {
        return artifactCollector;
    }

    public void setArtifactCollector(ArtifactCollector artifactCollector) {
        this.artifactCollector = artifactCollector;
    }

    public DependencyTreeBuilder getTreeBuilder() {
        return treeBuilder;
    }

    public void setTreeBuilder(DependencyTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File dir) {
        this.outputDir = dir;
    }

    public boolean isGenerateDefaultGraphs() {
        return generateDefaultGraphs;
    }

    public void setGenerateDefaultGraphs(boolean useDefaultDescriptors) {
        this.generateDefaultGraphs = useDefaultDescriptors;
    }

    public DependencyNode getMavenTree() {
        return mavenTree;
    }

    public void setMavenTree(DependencyNode mavenTree) {
        this.mavenTree = mavenTree;
    }

    public Node<MavenContext> getSanitizedTree() {
        return sanitizedTree;
    }

    public void setSanitizedTree(Node<MavenContext> sanitizedTree) {
        this.sanitizedTree = sanitizedTree;
    }
}