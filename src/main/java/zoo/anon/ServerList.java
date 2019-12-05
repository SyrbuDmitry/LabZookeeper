package zoo.anon;

import java.util.List;

public class ServerList {
    private List<String> serverlist;
    ServerList(List<String> serverlist){
        this.serverlist = serverlist;
    }

    public List<String> getServerlist() {
        return serverlist;
    }
}
