package HELLOz.hello.springz.domain;

public class Member {
    private Long id; //구분위해 컴퓨터에서 세팅하는 id
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
