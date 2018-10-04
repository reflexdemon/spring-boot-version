package io.vpv.version.springbootversion.modal;

/**
 * Created by vprasanna on 6/12/18.
 * The type Dependency.
 */
public class Dependency {

    private String bootVersion;
    private String groupId;
    private String artifactId;
    private String version;

    /**
     * Instantiates a new Dependency.
     *
     * @param bootVersion the boot version
     * @param groupId     the group id
     * @param artifactId  the artifact id
     * @param version     the version
     */
    public Dependency(String bootVersion, String groupId, String artifactId, String version) {
        this.bootVersion = bootVersion;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    /**
     * Gets boot version.
     *
     * @return the boot version
     */
    public String getBootVersion() {
        return bootVersion;
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
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dependency{");
        sb.append("bootVersion='").append(bootVersion).append('\'');
        sb.append(", groupId='").append(groupId).append('\'');
        sb.append(", artifactId='").append(artifactId).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
