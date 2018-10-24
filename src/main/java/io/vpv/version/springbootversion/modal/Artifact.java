package io.vpv.version.springbootversion.modal;


/**
 * Created by vprasanna on 6/12/18.
 * The type Artifact.
 */
public class Artifact {

    /**
     * The Group id.
     */
    String groupId;
    ;
    /**
     * The Artifact id.
     */
    String artifactId;
    /**
     * The First artifact version.
     */
    String firstArtifactVersion;
    /**
     * The Second artifact version.
     */
    String secondArtifactVersion;
    /**
     * The Changed.
     */
    Change change;

    /**
     * Instantiates a new Artifact.
     *
     * @param groupId               the group id
     * @param artifactId            the artifact id
     * @param firstArtifactVersion  the first artifact version
     * @param secondArtifactVersion the second artifact version
     */
    public Artifact(String groupId, String artifactId, String firstArtifactVersion, String secondArtifactVersion) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.firstArtifactVersion = firstArtifactVersion;
        this.secondArtifactVersion = secondArtifactVersion;
    }

    /**
     * Gets group id.
     *
     * @return the group id
     */
    public String getGroupId() {
        return groupId;
    }


    /**
     * Gets artifact id.
     *
     * @return the artifact id
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Gets first artifact version.
     *
     * @return the first artifact version
     */
    public String getFirstArtifactVersion() {
        return firstArtifactVersion;
    }


    /**
     * Gets second artifact version.
     *
     * @return the second artifact version
     */
    public String getSecondArtifactVersion() {
        return secondArtifactVersion;
    }


    public Change getChange() {
        if (this.secondArtifactVersion == null) {
            this.change = Change.REMOVED;
        } else if (this.firstArtifactVersion == null) {
            this.change = Change.ADDED;
        } else if (this.firstArtifactVersion.equalsIgnoreCase(this.secondArtifactVersion)) {
            this.change = Change.NOCHANGE;
        } else {
            this.change = Change.MODIFIED;
        }
        return change;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("io.vpv.version.springbootversion.modal.Artifact{");
        sb.append("groupId='").append(groupId).append('\'');
        sb.append(", artifactId='").append(artifactId).append('\'');
        sb.append(", firstArtifactVersion='").append(firstArtifactVersion).append('\'');
        sb.append(", secondArtifactVersion='").append(secondArtifactVersion).append('\'');
        sb.append(", change=").append(getChange());
        sb.append('}');
        return sb.toString();
    }


    public static enum Change {
        ADDED, REMOVED, MODIFIED, NOCHANGE;
    }
}
