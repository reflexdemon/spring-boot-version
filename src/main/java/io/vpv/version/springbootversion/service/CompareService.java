package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.VersionSummary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vprasanna on 10/23/18.
 */
@Service
public class CompareService {

    public VersionSummary merge(final List<Dependency> first, final List<Dependency> second) {
        VersionSummary summary = null;

        Map<String, Dependency> firstMap = parseListToMap(first);
        Map<String, Dependency> secondMap = parseListToMap(second);

        firstMap.keySet().stream()
                .forEach(
                        key -> {
                            Dependency f = firstMap.get(key);
                            Dependency s = secondMap.get(key);
                            if (s == null) {
                                // Dependency has been removed
                                //TODO: Need to improve tis logic
                            }
                        }
                );

        return summary;
    }

    private Map<String, Dependency> parseListToMap(List<Dependency> first) {
        if (null == first || first.isEmpty()) {
            return null;
        }
        final Map<String, Dependency> entries = new HashMap<>();

        first.stream()
                .forEach(item ->
                        entries.put(item.getGroupId() + ":" + item.getArtifactId(), item)
                );

        return entries;
    }
}
