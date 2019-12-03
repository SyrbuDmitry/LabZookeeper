package zoo.anon;

public class Request {
    public String url;
    public int count;
    Request(String url, int count){
        this.url = url;
        this.count = count;
    }
    public int getCount(){
        return count;
    }
    public String getUrl(){
        return url;
    }
}
