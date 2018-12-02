package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Artifact;
import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.DependencyDetails;
import io.vpv.version.springbootversion.modal.VersionSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by vprasanna on 10/23/18.
 */
@Service
public class CompareService {

    private BootVersionService bootVersionService;

    @Autowired
    public CompareService(BootVersionService bootVersionService) {
        this.bootVersionService = bootVersionService;
    }

    private boolean isEmpty(final DependencyDetails details) {
        return (null == details) || (details.getDependencies() == null) || (details.getDependencies().isEmpty());
    }

    public VersionSummary merge(final String first, final String second) {
        return merge(bootVersionService.getDependencies(first), bootVersionService.getDependencies(second));
    }

    public VersionSummary merge(final DependencyDetails first, final DependencyDetails second) {
        if (!isEmpty(first) && !isEmpty(second)) {

            VersionSummary summary = new VersionSummary();
            //Doing this as i ensure i dont run into empty list
            summary.setFirstBootVersion(first.getDependencies().stream()
                    .map(Dependency::getBootVersion)
                    .findFirst()
                    .orElse(null));
            summary.setSecondBootVersion(second.getDependencies().stream()
                    .map(Dependency::getBootVersion)
                    .findFirst()
                    .orElse(null));
            Map<String, Dependency> firstMap = parseListToMap(first.getDependencies());
            Map<String, Dependency> secondMap = parseListToMap(second.getDependencies());

            Set<String> completeKeySet = new TreeSet<>(firstMap.keySet());
            completeKeySet.addAll(secondMap.keySet());

            completeKeySet
                    .forEach(
                            key -> {
                                Dependency f = firstMap.get(key);
                                Dependency s = secondMap.get(key);
                                if (s == null) {
                                    // Dependency has been removed
                                    summary.getArtifacts().add(new Artifact(
                                            f.getGroupId(), f.getArtifactId(), f.getVersion(), null
                                    ));
                                } else if (f == null){
                                    //New dependency!
                                    summary.getArtifacts().add(new Artifact(
                                            s.getGroupId(), s.getArtifactId(), null, s.getVersion()
                                    ));
                                } else {
                                    summary.getArtifacts().add(new Artifact(
                                            f.getGroupId(), f.getArtifactId(), f.getVersion(), s.getVersion()
                                    ));
                                }
                            }
                    );

            return summary;
        }

        return null;
    }

    private Map<String, Dependency> parseListToMap(List<Dependency> first) {
        final Map<String, Dependency> entries = new HashMap<>();

        first.forEach(item ->
                        entries.put(item.getGroupId() + ":" + item.getArtifactId(), item)
                );

        return entries;
    }
}
