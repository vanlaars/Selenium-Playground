package core.test_manager.domain;

/**
 * Created by svanlaar on 02/03/2016.
 */
public class TestData {

    private String search_criteria;
    private String user_name;
    private String password;
    private String subsection;

    // ToDo: Create builder pattern, maybe even with a list of items to buy. Build Search, Build User
    // ToDo: http://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/

    public TestData(String search_criteria, String user_name, String password, String subsection) {
        this.search_criteria = search_criteria;
        this.user_name = user_name;
        this.password = password;
        this.subsection = subsection;
    }

    public TestData(String search_criteria) {
        this.search_criteria = search_criteria;
    }

    public String getSearch_criteria() {
        return search_criteria;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getSubsection() {
        return subsection;
    }

    @Override
    public String toString() {
        return "TestData { " +
                "search_criteria='" + search_criteria + '\'' +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", sub section='" + subsection + '\'' +
                '}';
    }
}
