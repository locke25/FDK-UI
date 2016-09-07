package com.inubit.gui.vaadin.mvp.shell;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * Allows to get sub fragments of a url fragment. Each fragment is separated with the
 * {@link #FRAGMENT_SEPARATOR} and has the format <code>id:subfragment</code>.
 */
public class Fragmenter {

    private static final String ID_SEPARATOR = ":";

    public static final String FRAGMENT_SEPARATOR = "~";

    private Map<String, String> fragments = new LinkedHashMap<String, String>();


    public Fragmenter(String fragment) {
        if(fragment == null){
            return;
        }
        String[] subFragments = StringUtils.split(fragment, FRAGMENT_SEPARATOR);
        for (String subFragment : subFragments) {
            String id = StringUtils.substringBefore(subFragment, ID_SEPARATOR);
            String token = StringUtils.substringAfter(subFragment, ID_SEPARATOR);
            fragments.put(id, token);
        }
    }

    public String getSubFragment(String id) {
        return fragments.get(id);
    }

    /**
     * Sets a sub-fragment. The fragment is removed if the passed value is null.
     */
    public void setSubFragment(String id, String fragment) {

        if(fragment == null){
            fragments.remove(id);
            return;
        }

        fragments.put(id, fragment);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Entry<String, String> entry : fragments.entrySet()) {
            final String id = entry.getKey();
            final String fragment = entry.getValue();
            str.append(id).append(ID_SEPARATOR).append(fragment).append(FRAGMENT_SEPARATOR);
        }
        if(str.length()>0){
            str.deleteCharAt(str.length()-1);
        }
        return str.toString();
    }
}
