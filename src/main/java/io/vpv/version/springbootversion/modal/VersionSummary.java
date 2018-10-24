package io.vpv.version.springbootversion.modal;

import java.util.ArrayList;
import java.util.List;

/**
 * /**
 * Created by vprasanna on 10/23/18.
 * The type Version summary.
 */
public class VersionSummary {
    /**
     * The First boot version.
     */
    String firstBootVersion;
    /**
     * The Second boot version.
     */
    String secondBootVersion;
    /**
     * The Artifacts.
     */
    List<Artifact> artifacts = new ArrayList<>();

    /**
     * Gets first boot version.
     *
     * @return the first boot version
     */
    public String getFirstBootVersion() {
        return firstBootVersion;
    }

    /**
     * Sets first boot version.
     *
     * @param firstBootVersion the first boot version
     */
    public void setFirstBootVersion(String firstBootVersion) {
        this.firstBootVersion = firstBootVersion;
    }

    /**
     * Gets second boot version.
     *
     * @return the second boot version
     */
    public String getSecondBootVersion() {
        return secondBootVersion;
    }

    /**
     * Sets second boot version.
     *
     * @param secondBootVersion the second boot version
     */
    public void setSecondBootVersion(String secondBootVersion) {
        this.secondBootVersion = secondBootVersion;
    }

    /**
     * Gets artifacts.
     *
     * @return the artifacts
     */
    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    /**
     * Sets artifacts.
     *
     * @param artifacts the artifacts
     */
    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("io.vpv.version.springbootversion.modal.VersionSummary{");
        sb.append("firstBootVersion='").append(firstBootVersion).append('\'');
        sb.append(", secondBootVersion='").append(secondBootVersion).append('\'');
        sb.append(", artifacts=").append(artifacts);
        sb.append('}');
        return sb.toString();
    }
}
