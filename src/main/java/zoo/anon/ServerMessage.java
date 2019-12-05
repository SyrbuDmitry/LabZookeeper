package zoo.anon;

public class ServerMessage {
    String url;
    ServerMessage(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
