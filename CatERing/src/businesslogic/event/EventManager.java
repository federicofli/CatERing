package businesslogic.event;

import javafx.collections.ObservableList;

public class EventManager {
    public ObservableList<EventInfo> getEventInfo() {
        return EventInfo.loadAllEventInfo();
    }
    public ObservableList<ServiceInfo> getServiceInfo() { return ServiceInfo.loadAllServiceInfo();
    }
}
