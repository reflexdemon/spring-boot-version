package io.vpv.version.springbootversion.modal;

import java.util.List;

/**
 * Created by vprasanna on 12/1/18.
 * The type DependencyDetails.
 */
public class DependencyDetails {

    List<Dependency> dependencies;
    String source;
    String bootVersion;


    public DependencyDetails(List<Dependency> dependencies, String source, String bootVersion) {
        this.dependencies = dependencies;
        this.source = source;
        this.bootVersion = bootVersion;
    }


    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public String getSource() {
        return source;
    }

    public String getBootVersion() {
        return bootVersion;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DependencyDetails{");
        sb.append("dependencies=").append(dependencies);
        sb.append(", source='").append(source).append('\'');
        sb.append(", bootVersion='").append(bootVersion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
