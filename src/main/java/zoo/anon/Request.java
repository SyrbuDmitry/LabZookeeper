package zoo.anon;

public class Request {
    public String url;
    public int count;
    Request(String url, String count){
        this.url = url;
        this.count = Integer.parseInt(count);
    }
    public int getCount(){
        return count;
    }
    public String getUrl(){
        return url;
    }
}
