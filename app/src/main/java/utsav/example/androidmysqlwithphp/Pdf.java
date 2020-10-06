package utsav.example.androidmysqlwithphp;


/**
 * POJO class for PDF
 *
 * @Autor - Utsav Sinha
 * @Autor - Shalabh Sinha
 */
public class Pdf {

    private String url;
    private String name;
    private String id;

    private String file_name;
    private String file_name_url;

    public String getFile_name_url() {
        return file_name_url;
    }

    public void setFile_name_url(String file_name_url) {
        this.file_name_url = file_name_url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
