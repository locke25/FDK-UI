package com.jcim.fdk.workbench.place;

import org.apache.commons.lang.StringUtils;

import com.inubit.gui.vaadin.mvp.place.Place;
import com.inubit.gui.vaadin.mvp.place.PlaceTokenizer;
import com.inubit.gui.vaadin.mvp.place.Prefix;
import com.jcim.fdk.navigation.place.NavigationPlace;

@Prefix("workbench")
public class WorkbenchPlace extends Place implements NavigationPlace {

    private static final WorkbenchPlace[] WORKBENCHES = {new WorkbenchPlace("PlaceOne"), 
    	new WorkbenchPlace("PlaceTwo"), new WorkbenchPlace("PlaceThree"), new WorkbenchPlace("PlaceFour")};

	public static class Tokenizer implements PlaceTokenizer<WorkbenchPlace>{

        @Override
        public WorkbenchPlace getPlace(String token) {
            return new WorkbenchPlace(token);
        }

        @Override
        public String getToken(WorkbenchPlace place) {
            return place.getWorkbenchName();
        }
    }

	public final static WorkbenchPlace[] getWorkbenches() {
		return WORKBENCHES;
	}
	
    private String workbenchName;

    public WorkbenchPlace(String workbenchName) {
        if(StringUtils.isBlank(workbenchName)){
            throw new IllegalArgumentException("workbenchName cannot be null");
        }
        this.workbenchName = workbenchName;
    }

    public String getWorkbenchName() {
        return workbenchName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((workbenchName == null) ? 0 : workbenchName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WorkbenchPlace)) {
            return false;
        }
        WorkbenchPlace other = (WorkbenchPlace) obj;
        if (workbenchName == null) {
            if (other.workbenchName != null) {
                return false;
            }
        }
        else if (!workbenchName.equals(other.workbenchName)) {
            return false;
        }
        return true;
    }

	@Override
	public String getLabel() {
		return workbenchName;
	}

	@Override
	public Place asPlace() {
		return this;
	}

}
