package POJO;

public class BookCategory {

    private String category_id ;
    private String category_name;




    public String getId() {
        return category_id;
    }

    public void setId(String id) {
        this.category_id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "id='" + category_id + '\'' +
                ", name='" + category_name + '\'' +
                '}';
    }
}
