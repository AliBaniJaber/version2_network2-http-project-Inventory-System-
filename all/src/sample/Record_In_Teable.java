package sample;

public class Record_In_Teable {
    private String Id;
    private String name;
    private String mount;
    private String USD;
    private String EUR;

    public Record_In_Teable(String id, String name, String mount, String USD, String EUR) {
        this.Id = id;
        this.name = name;
        this.mount = mount;
        this.USD = USD;
        this.EUR = EUR;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }

    public String getUSD() {
        return USD;
    }

    public void setUSD(String USD) {
        this.USD = USD;
    }

    public String getEUR() {
        return EUR;
    }

    public void setEUR(String EUR) {
        this.EUR = EUR;
    }
}
