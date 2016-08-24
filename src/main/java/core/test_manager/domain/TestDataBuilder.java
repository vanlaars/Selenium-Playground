package core.test_manager.domain;

public class TestDataBuilder {
    private String search_criteria;
    private String user_name;
    private String password;
    private String subsection;

    public TestDataBuilder setSearch_criteria(String search_criteria) {
        this.search_criteria = search_criteria;
        return this;
    }

    public TestDataBuilder setUser_name(String user_name) {
        this.user_name = user_name;
        return this;
    }

    public TestDataBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public TestDataBuilder setSubsection(String subsection) {
        this.subsection = subsection;
        return this;
    }

    public TestData createTestData() {
        return new TestData(search_criteria, user_name, password, subsection);
    }
}