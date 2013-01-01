/**
 * Copyright 2011-2013 The Kuali Foundation
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
package org.kuali.maven.plugins.graph.validate;

import java.util.List;

import org.apache.maven.shared.dependency.tree.DependencyNode;
import org.kuali.maven.plugins.graph.pojo.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Perform validation on nodes Maven has marked as <code>State.DUPLICATE</state>
 * </p>
 */
public class DuplicateDependencyNodeValidator extends OmittedDependencyNodeValidator {
    private static final Logger logger = LoggerFactory.getLogger(DuplicateDependencyNodeValidator.class);

    public DuplicateDependencyNodeValidator() {
        super(State.DUPLICATE);
    }

    @Override
    protected void validateState(List<DependencyNode> nodes) {
        super.validateState(nodes);
        for (DependencyNode node : nodes) {
            boolean equal = helper.equals(node.getArtifact(), node.getRelatedArtifact());
            if (!equal) {
                // This really shouldn't happen.
                // It is a confusing way to label nodes in the dependency tree
                // Maven marked this node as a "duplicate" but the related artifact is a different version
                // The term "duplicate" would reasonably seem to imply that this version of this artifact is
                // participating in the build.
                // Why then, would the related artifact be a different version?
                // This gets cleaned up by the sanitizers, so just log a message here
                logger.debug("fake dup->" + node.getArtifact());
            }
        }
        logger.debug("Validated " + state + " nodes");
    }

}
